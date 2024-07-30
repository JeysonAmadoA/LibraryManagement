package com.jeyson.Books.Infrastructure.Services;

import com.jeyson.Books.Application.Repositories.BookcaseRepository;
import com.jeyson.Books.Application.Services.BookcaseService;
import com.jeyson.Books.Domain.Dto.BookcaseDto;
import com.jeyson.Books.Domain.Dto.RegisterBookcaseDto;
import com.jeyson.Books.Domain.Entities.Bookcase;
import com.jeyson.Books.Domain.Exceptions.BookcaseNotFoundException;
import com.jeyson.Books.Domain.Exceptions.RegisterBookException;

import java.util.List;
import java.util.stream.Collectors;
import static com.jeyson.Books.Application.Mappers.BookcaseMapper.*;
import static com.jeyson.Users.Domain.Helpers.AuthHelper.getActualUser;

public class BookcaseServiceImpl implements BookcaseService {

    private final BookcaseRepository bookcaseRepository;

    public BookcaseServiceImpl(BookcaseRepository bookcaseRepository) {
        this.bookcaseRepository = bookcaseRepository;
    }

    @Override
    public List<BookcaseDto> findAll() {
        List<Bookcase> bookcases = bookcaseRepository.findAll();
        return bookcases.stream().map(BookcaseDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookcaseDto filterById(Long id) {
        Bookcase bookcase = bookcaseRepository.findById(id).orElseThrow(BookcaseNotFoundException::new);
        return BookcaseDtoMapper.toDto(bookcase);
    }

    @Override
    public BookcaseDto store(RegisterBookcaseDto bookDto) {
        Bookcase newBookcase = RegisterBookcaseDtoMapper.toEntity(bookDto);
        try {
            newBookcase.commitCreate(getActualUser());
            Bookcase storedBookcase = bookcaseRepository.save(newBookcase);
            return BookcaseDtoMapper.toDto(storedBookcase);
        } catch (Exception exception){
            throw new RegisterBookException(exception.getMessage());
        }
    }

    @Override
    public BookcaseDto update(Long id, RegisterBookcaseDto bookDto) {
        Bookcase bookcase = bookcaseRepository.findById(id).orElseThrow(BookcaseNotFoundException::new);
        try {
            Bookcase updatedBook = RegisterBookcaseDtoMapper.update(bookcase, bookDto);
            updatedBook.commitUpdate(getActualUser());
            bookcaseRepository.save(updatedBook);
            return BookcaseDtoMapper.toDto(bookcase);
        } catch (Exception exception){
            throw new RegisterBookException(exception.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        Bookcase bookcase = bookcaseRepository.findById(id).orElseThrow(BookcaseNotFoundException::new);
        try {
            bookcase.commitDelete(getActualUser());
            bookcaseRepository.save(bookcase);
        } catch (Exception exception){
            throw new RegisterBookException(exception.getMessage());
        }
    }
}
