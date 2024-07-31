package com.jeyson.Books.Domain.Exceptions;

import com.jeyson.Core.Domain.Exceptions.BusinessLogicException;

public class RegisterBookException extends BusinessLogicException {

    public RegisterBookException(String message) {
        super("Hubo un error al registrar libro: " + message);
    }
}
