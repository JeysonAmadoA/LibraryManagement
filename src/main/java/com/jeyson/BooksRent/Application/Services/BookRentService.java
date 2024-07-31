package com.jeyson.BooksRent.Application.Services;

import com.jeyson.BooksRent.Domain.Dto.BookRentDto;
import com.jeyson.BooksRent.Domain.Dto.RegisterBookRentDto;
import com.jeyson.Core.Application.Services.CRUDService;

public interface BookRentService extends CRUDService<BookRentDto, RegisterBookRentDto> {

    void storeReturnBookRent(long bookRentId);
}
