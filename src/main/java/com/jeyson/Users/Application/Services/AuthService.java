package com.jeyson.Users.Application.Services;

import com.jeyson.Users.Domain.Dto.Auth.LoginDto;
import com.jeyson.Users.Domain.Dto.Auth.RegisterDto;
import com.jeyson.Users.Domain.Dto.Users.UserDto;

public interface AuthService {

    UserDto registerUser(RegisterDto registerDto);

    String loginUser(LoginDto loginDto);
}
