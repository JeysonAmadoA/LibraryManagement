package com.jeyson.Books.Application.Services;



import com.jeyson.Books.Domain.Dto.BookcaseDto;
import com.jeyson.Books.Domain.Dto.RegisterBookcaseDto;
import com.jeyson.Core.Application.Services.CRUDService;

public interface BookcaseService extends CRUDService<BookcaseDto, RegisterBookcaseDto> {

}
