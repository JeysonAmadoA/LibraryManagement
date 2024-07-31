package com.jeyson.Books.Domain.Exceptions;

import com.jeyson.Core.Domain.Exceptions.BusinessLogicException;

public class RegisterBookcaseException extends BusinessLogicException {

    public RegisterBookcaseException(String message) {
        super("Hubo un error al registrar estanteria: " + message);
    }
}
