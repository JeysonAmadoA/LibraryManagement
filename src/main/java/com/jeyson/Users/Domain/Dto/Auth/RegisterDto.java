package com.jeyson.Users.Domain.Dto.Auth;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String documentNumber;
    private String address;
}
