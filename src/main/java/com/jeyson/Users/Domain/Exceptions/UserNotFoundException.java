package com.jeyson.Users.Domain.Exceptions;

import com.jeyson.Core.Domain.Exceptions.ElementNotFoundException;

public class UserNotFoundException extends ElementNotFoundException {

    public UserNotFoundException() {
        super("No se ha encontrado usuario");
    }
}
