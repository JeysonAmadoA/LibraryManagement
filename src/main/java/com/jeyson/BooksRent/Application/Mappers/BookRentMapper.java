package com.jeyson.BooksRent.Application.Mappers;

import com.jeyson.BooksRent.Domain.Dto.BookRentDto;
import com.jeyson.BooksRent.Domain.Dto.RegisterBookRentDto;
import com.jeyson.BooksRent.Domain.Entities.BookRent;

import static com.jeyson.Core.Domain.Helpers.MapperHelper.updateFieldIfNotNull;
import static com.jeyson.Core.Domain.Helpers.MapperHelper.updateFieldIfNumberNotZero;

public class BookRentMapper {

    public static class BookRentDtoMapper{

        public static BookRentDto toDto(BookRent rent){
            return BookRentDto.builder()
                    .bookId(rent.getBookId())
                    .userWhoRentedId(rent.getUserWhoRentedId())
                    .rentedAt(rent.getRentedAt())
                    .userWhoDeliveryId(rent.getUserWhoDeliveryId())
                    .deliveryAt(rent.getDeliveryAt())
                    .returningDate(rent.getReturningDate())
                    .returnedAt(rent.getReturnedAt())
                    .build();
        }
    }

    public static class RegisterBookRentDtoMapper {

        public static BookRent toEntity(RegisterBookRentDto rentDto){
            return BookRent
                    .builder()
                    .bookId(rentDto.getBookId())
                    .userWhoRentedId(rentDto.getUserWhoRentedId())
                    .rentedAt(rentDto.getRentedAt())
                    .userWhoDeliveryId(rentDto.getUserWhoDeliveryId())
                    .deliveryAt(rentDto.getDeliveryAt())
                    .build();
        }

        public static BookRent update(BookRent bookRent, RegisterBookRentDto rentDto){
            updateFieldIfNotNull(rentDto.getBookId(), bookRent::setBookId);
            updateFieldIfNotNull(rentDto.getUserWhoRentedId(), bookRent::setUserWhoRentedId);
            updateFieldIfNotNull(rentDto.getRentedAt(), bookRent::setRentedAt);
            updateFieldIfNotNull(rentDto.getUserWhoDeliveryId(), bookRent::setUserWhoDeliveryId);
            updateFieldIfNotNull(rentDto.getDeliveryAt(), bookRent::setDeliveryAt);
            updateFieldIfNotNull(rentDto.getReturningDate(), bookRent::setReturningDate);
            return bookRent;
        }
    }
}
