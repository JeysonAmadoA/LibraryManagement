package com.jeyson.Users.Application.Services;

import com.jeyson.Users.Domain.Dto.Auth.JwtAuthenticationDto;
import com.jeyson.Users.Domain.Dto.Auth.LoginDto;
import com.jeyson.Users.Domain.Dto.Auth.RegisterDto;
import com.jeyson.Users.Domain.Dto.Users.UserDto;

public interface AuthService {

    UserDto registerUser(RegisterDto registerDto);

    JwtAuthenticationDto loginUser(LoginDto loginDto);
}
