package com.jeyson.Books.Domain.Exceptions;

import com.jeyson.Core.Domain.Exceptions.ElementNotFoundException;

public class BookNotFoundException extends ElementNotFoundException {

    public BookNotFoundException() {
        super("Libro no encontrado");
    }
}
