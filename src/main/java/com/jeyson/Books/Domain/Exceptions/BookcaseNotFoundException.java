package com.jeyson.Books.Domain.Exceptions;

import com.jeyson.Core.Domain.Exceptions.ElementNotFoundException;

public class BookcaseNotFoundException extends ElementNotFoundException {

    public BookcaseNotFoundException() {
        super("Estanteria no encontrada");
    }
}
