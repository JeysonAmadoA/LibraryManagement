package com.jeyson.BooksRent.Infrastructure.Services;

import com.jeyson.BooksRent.Application.Repositories.BookRentRepository;
import com.jeyson.BooksRent.Application.Services.BookRentService;
import com.jeyson.BooksRent.Application.Services.CustomerService;
import com.jeyson.BooksRent.Domain.Dto.BookRentDto;
import com.jeyson.BooksRent.Domain.Dto.RegisterBookRentDto;
import com.jeyson.BooksRent.Domain.Entities.BookRent;
import com.jeyson.BooksRent.Domain.Exceptions.BookAlreadyRentedException;
import com.jeyson.BooksRent.Domain.Exceptions.BookRentNotFoundException;
import com.jeyson.BooksRent.Domain.Exceptions.CustomerWithPenaltiesException;
import com.jeyson.BooksRent.Domain.Exceptions.RegisterBookRentException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.jeyson.BooksRent.Application.Mappers.BookRentMapper.*;
import static com.jeyson.Core.Domain.Helpers.TimeHelper.addBusinessDays;
import static com.jeyson.Users.Domain.Helpers.AuthHelper.getActualUser;
import static com.jeyson.BooksRent.Domain.Helpers.BookRentHelper.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRentServiceImpl implements BookRentService {

    private final BookRentRepository bookRentRepository;
    private final CustomerService customerService;

    public BookRentServiceImpl(BookRentRepository bookRentRepository, CustomerService customerService) {
        this.bookRentRepository = bookRentRepository;
        this.customerService = customerService;
    }

    @Cacheable("getAllBooksRented")
    @Override
    public List<BookRentDto> findAll() {
        List<BookRent> booksRented = bookRentRepository.findAll();
        return booksRented.stream().map(BookRentDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookRentDto filterById(Long id) {
        BookRent bookRented = bookRentRepository.findById(id).orElseThrow(BookRentNotFoundException::new);
        return BookRentDtoMapper.toDto(bookRented);
    }

    @Override
    public BookRentDto store(RegisterBookRentDto registerDto) {
        validateStoreRent(registerDto);
        BookRent newBookRent = RegisterBookRentDtoMapper.toEntity(registerDto);
        try {
            newBookRent.setReturningDate(addBusinessDays(5));
            newBookRent.commitCreate(getActualUser());
            BookRent storedBookRent = bookRentRepository.save(newBookRent);
            return BookRentDtoMapper.toDto(storedBookRent);
        } catch (Exception exception){
            throw new RegisterBookRentException(exception.getMessage());
        }
    }

    private void validateStoreRent(RegisterBookRentDto registerDto){
        if (customerService.customerHasPenalty(registerDto.getUserWhoRentedId()))
            throw new CustomerWithPenaltiesException();

        List<BookRentDto> booksRentedByUser = customerService
                .getBooksRentedByCustomerId(registerDto.getUserWhoRentedId());

        verifyBooksRented(booksRentedByUser);

        if (bookRentRepository.existsByBookIdAndReturnedAtIsNull(registerDto.getBookId()))
            throw new BookAlreadyRentedException();
    }

    @Override
    public BookRentDto update(Long id, RegisterBookRentDto updateDto) {
        BookRent bookRented = bookRentRepository.findById(id).orElseThrow(BookRentNotFoundException::new);
        try {
            BookRent updatedBook = RegisterBookRentDtoMapper.update(bookRented, updateDto);
            updatedBook.commitUpdate(getActualUser());
            bookRentRepository.save(updatedBook);
            return BookRentDtoMapper.toDto(bookRented);
        } catch (Exception exception){
            throw new RegisterBookRentException(exception.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        BookRent bookRented = bookRentRepository.findById(id).orElseThrow(BookRentNotFoundException::new);
        try {
            bookRented.commitDelete(getActualUser());
            bookRentRepository.save(bookRented);
        } catch (Exception exception){
            throw new RegisterBookRentException(exception.getMessage());
        }
    }

    @Override
    public void storeReturnBookRent(long bookRentId) {
        BookRent bookRented = bookRentRepository.findById(bookRentId).orElseThrow(BookRentNotFoundException::new);
        try {
            bookRented.setReturnedAt(LocalDateTime.now());
            bookRented.commitUpdate(getActualUser());
            bookRentRepository.save(bookRented);
        } catch (Exception exception){
            throw new RegisterBookRentException(exception.getMessage());
        }
    }
}
