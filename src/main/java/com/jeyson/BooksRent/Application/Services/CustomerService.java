package com.jeyson.BooksRent.Application.Services;

import com.jeyson.BooksRent.Domain.Dto.BookRentDto;

import java.util.List;

public interface CustomerService {

    List<BookRentDto> getBooksRentedByCustomerId(Long customerId);

    boolean customerHasPenalty(Long customerId);

}
