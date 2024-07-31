package com.jeyson.Books.Application.Services;


import com.jeyson.Books.Domain.Dto.BookDto;
import com.jeyson.Books.Domain.Dto.RegisterBookDto;
import com.jeyson.Core.Application.Services.CRUDService;

public interface BookService extends CRUDService<BookDto, RegisterBookDto> {

}
