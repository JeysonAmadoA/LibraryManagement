package com.jeyson.Books.Application.Mappers;

import com.jeyson.Books.Domain.Dto.BookcaseDto;
import com.jeyson.Books.Domain.Dto.RegisterBookcaseDto;
import com.jeyson.Books.Domain.Entities.Bookcase;

import java.util.stream.Collectors;
import static com.jeyson.Core.Domain.Helpers.MapperHelper.updateFieldIfNotNull;

public class BookcaseMapper {

    public static class RegisterBookcaseDtoMapper {

        public static Bookcase toEntity(RegisterBookcaseDto bookcaseDto){
            return Bookcase
                    .builder()
                    .bookcaseName(bookcaseDto.getBookcaseName())
                    .category(bookcaseDto.getCategory())
                    .build();
        }

        public static Bookcase update(Bookcase bookcase, RegisterBookcaseDto bookDto) {
            updateFieldIfNotNull(bookDto.getBookcaseName(), bookcase::setBookcaseName);
            updateFieldIfNotNull(bookDto.getCategory(), bookcase::setCategory);
            return bookcase;
        }
    }

    public static class BookcaseDtoMapper {

        public static BookcaseDto toDto(Bookcase bookcase){

            return new BookcaseDto(bookcase.getId(),
                    bookcase.getBookcaseName(),
                    bookcase.getCategory());
        }
    }
}
