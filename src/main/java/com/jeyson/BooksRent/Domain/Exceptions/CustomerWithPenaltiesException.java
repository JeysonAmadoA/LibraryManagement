package com.jeyson.BooksRent.Domain.Exceptions;

import com.jeyson.Core.Domain.Exceptions.BusinessLogicException;

public class CustomerWithPenaltiesException extends BusinessLogicException {

    public CustomerWithPenaltiesException(String message) {
        super(message);
    }

    public CustomerWithPenaltiesException() {
        super("Usuario está retrasado en la entraga de libros.");
    }
}
