package com.jeyson.BooksRent.Domain.Exceptions;

import com.jeyson.Core.Domain.Exceptions.BusinessLogicException;

public class RegisterBookRentException extends BusinessLogicException {

    public RegisterBookRentException(String message) {
        super("Hubo un error al rentar libro: " + message);
    }
}
