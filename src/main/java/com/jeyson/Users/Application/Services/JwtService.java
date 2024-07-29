package com.jeyson.Users.Application.Services;

import com.jeyson.Users.Domain.Entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
