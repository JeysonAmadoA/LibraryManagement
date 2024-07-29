package com.jeyson.Users.Domain.Dto.Auth;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
