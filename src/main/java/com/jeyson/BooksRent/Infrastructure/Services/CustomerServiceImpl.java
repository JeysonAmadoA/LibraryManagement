package com.jeyson.BooksRent.Infrastructure.Services;

import com.jeyson.BooksRent.Application.Repositories.CustomerRepository;
import com.jeyson.BooksRent.Application.Services.CustomerService;
import com.jeyson.BooksRent.Domain.Dto.BookRentDto;
import com.jeyson.BooksRent.Domain.Entities.BookRent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.jeyson.BooksRent.Application.Mappers.BookRentMapper.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<BookRentDto> getBooksRentedByCustomerId(Long customerId) {
        List<BookRent> booksRented = customerRepository.findActiveBookRentsByUserId(customerId);
        return booksRented.stream().map(BookRentDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean customerHasPenalty(Long customerId) {
        List<BookRent> booksRented = customerRepository
                .findBooksWithDelayedReturnDateByCustomerId(customerId, LocalDate.now());

        return !booksRented.isEmpty();
    }
}
