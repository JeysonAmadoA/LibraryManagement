package com.jeyson.Books.Domain.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterBookcaseDto {

    @NotNull(message = "El nombre de la estanteria es requerido")
    private String bookcaseName;

    @NotNull(message = "La categoria es requerida")
    private String category;
}
