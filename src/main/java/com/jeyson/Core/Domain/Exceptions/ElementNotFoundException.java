package com.jeyson.Core.Domain.Exceptions;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException(String message) {
        super(message);
    }

    public ElementNotFoundException() {
        super("No se ha encontrado elemento en los registros");
    }

}
