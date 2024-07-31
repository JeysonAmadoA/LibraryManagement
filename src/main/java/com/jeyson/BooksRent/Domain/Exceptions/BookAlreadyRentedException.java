package com.jeyson.BooksRent.Domain.Exceptions;

import com.jeyson.Core.Domain.Exceptions.BusinessLogicException;

public class BookAlreadyRentedException extends BusinessLogicException {

    public BookAlreadyRentedException(String message) {
        super(message);
    }

    public BookAlreadyRentedException() {
        super("El libro ya fue alquilado");
    }

}
