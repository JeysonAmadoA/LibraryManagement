package com.jeyson.Users.Domain.Dto.Auth;

import com.jeyson.Users.Domain.Constants.Security.Role;
import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String documentNumber;
    private String address;
    private Role role;
}
