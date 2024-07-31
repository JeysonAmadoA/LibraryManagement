package com.jeyson.BooksRent.Domain.Exceptions;

import com.jeyson.Core.Domain.Exceptions.BusinessLogicException;

public class BooksRentedExceededException extends BusinessLogicException {

    public BooksRentedExceededException(String message) {
        super(message);
    }

}
