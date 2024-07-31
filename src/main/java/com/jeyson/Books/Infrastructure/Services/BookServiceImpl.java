package com.jeyson.Books.Infrastructure.Services;

import com.jeyson.Books.Application.Repositories.BookRepository;
import com.jeyson.Books.Application.Services.BookService;
import com.jeyson.Books.Domain.Dto.BookDto;
import com.jeyson.Books.Domain.Dto.RegisterBookDto;
import com.jeyson.Books.Domain.Entities.Book;
import com.jeyson.Books.Domain.Exceptions.BookNotFoundException;
import com.jeyson.Books.Domain.Exceptions.RegisterBookException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static com.jeyson.Books.Application.Mappers.BookMapper.*;
import static com.jeyson.Users.Domain.Helpers.AuthHelper.getActualUser;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable("getAllBooks")
    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDto filterById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return BookDtoMapper.toDto(book);
    }

    @Override
    public BookDto store(RegisterBookDto bookDto) {
        Book newBook = RegisterBookDtoMapper.toEntity(bookDto);
        try {
            newBook.commitCreate(getActualUser());
            Book storedBook = bookRepository.save(newBook);
            return BookDtoMapper.toDto(storedBook);
        } catch (Exception exception){
            throw new RegisterBookException(exception.getMessage());
        }
    }

    @Override
    public BookDto update(Long id, RegisterBookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        try {
            Book updatedBook = RegisterBookDtoMapper.update(book, bookDto);
            updatedBook.commitUpdate(getActualUser());
            bookRepository.save(updatedBook);
            return BookDtoMapper.toDto(book);
        } catch (Exception exception){
            throw new RegisterBookException(exception.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        try {
            book.commitDelete(getActualUser());
            bookRepository.save(book);
        } catch (Exception exception){
            throw new RegisterBookException(exception.getMessage());
        }
    }
}
