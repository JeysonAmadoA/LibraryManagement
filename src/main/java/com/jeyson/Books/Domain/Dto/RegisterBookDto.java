package com.jeyson.Books.Domain.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterBookDto {

    @NotNull(message = "El nombre del libro es requerido")
    private String bookName;

    private String author;

    @NotNull(message = "El año de publicación es requerido")
    private short publicationYear;

    @NotNull(message = "La cantidad de paginas es requerido")
    private short pages;

    private String editorial;

    @NotNull(message = "Debe ingresar a que estanteria está asignado el libro")
    private long bookcaseId;

}
