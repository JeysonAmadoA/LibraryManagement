package com.jeyson.BooksRent.Domain.Exceptions;

import com.jeyson.Core.Domain.Exceptions.ElementNotFoundException;

public class BookRentNotFoundException extends ElementNotFoundException {

    public BookRentNotFoundException() {
        super("Libro rentado no encontrado");
    }
}
