package com.jeyson.BooksRent.Domain.Helpers;

import com.jeyson.BooksRent.Domain.Dto.BookRentDto;
import com.jeyson.BooksRent.Domain.Exceptions.BooksRentedExceededException;

import java.util.List;

public class BookRentHelper {

    public static final int MAX_BOOK_RENT = 3;

    public static void verifyBooksRented(List<BookRentDto> booksRented){
        if (booksRented.size() >= MAX_BOOK_RENT){
            throw new BooksRentedExceededException("Ha alcanzado el limite de libros rentados");
        }
    }
}
