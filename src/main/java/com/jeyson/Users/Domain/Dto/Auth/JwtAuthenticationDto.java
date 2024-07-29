package com.jeyson.Users.Domain.Dto.Auth;

import lombok.Data;

@Data
public class JwtAuthenticationDto {
    String token;
    String refreshToken;
}
