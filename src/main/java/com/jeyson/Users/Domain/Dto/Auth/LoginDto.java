package com.jeyson.Users.Domain.Dto.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {

    @Email
    private String email;

    @NotNull(message = "El campo constraseña es requerido")
    private String password;
}
