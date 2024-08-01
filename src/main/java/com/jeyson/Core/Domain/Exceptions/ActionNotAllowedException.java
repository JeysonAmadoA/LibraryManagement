package com.jeyson.Core.Domain.Exceptions;

public class ActionNotAllowedException extends RuntimeException {

    public ActionNotAllowedException() {
        super("No tiene acceso para realizar esta acción");
    }
}
