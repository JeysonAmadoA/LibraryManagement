package com.jeyson.Books.Application.Mappers;

import com.jeyson.Books.Domain.Dto.BookDto;
import com.jeyson.Books.Domain.Dto.RegisterBookDto;
import com.jeyson.Books.Domain.Entities.Book;

import static com.jeyson.Core.Domain.Helpers.MapperHelper.*;

public class BookMapper {

    public static class BookDtoMapper{

        public static BookDto toDto(Book book){
            return new BookDto(book.getId(),
                    book.getBookName(),
                    book.getAuthor(),
                    book.getPublicationYear(),
                    book.getPages(), book.getEditorial());
        }


    }

    public static class RegisterBookDtoMapper {

        public static Book toEntity(RegisterBookDto bookDto){
            return Book
                    .builder()
                    .bookName(bookDto.getBookName())
                    .pages(bookDto.getPages())
                    .author(bookDto.getAuthor())
                    .editorial(bookDto.getEditorial())
                    .publicationYear(bookDto.getPublicationYear())
                    .bookcaseId(bookDto.getBookcaseId())
                    .build();
        }

        public static Book update(Book book, RegisterBookDto bookDto){
            updateFieldIfNotNull(bookDto.getBookName(), book::setBookName);
            updateFieldIfNotNull(bookDto.getAuthor(), book::setAuthor);
            updateFieldIfNumberNotZero(bookDto.getPublicationYear(), book::setPublicationYear);
            updateFieldIfNumberNotZero(bookDto.getPages(), book::setPages);
            updateFieldIfNotNull(bookDto.getEditorial(), book::setEditorial);
            updateFieldIfNotNull(bookDto.getBookcaseId(), book::setBookcaseId);
            return book;
        }
    }
}
