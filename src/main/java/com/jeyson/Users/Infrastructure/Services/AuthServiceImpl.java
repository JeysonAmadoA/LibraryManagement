package com.jeyson.Users.Infrastructure.Services;

import com.jeyson.Users.Application.Repositories.UserRepository;
import com.jeyson.Users.Application.Services.AuthService;
import com.jeyson.Users.Application.Services.JwtService;
import com.jeyson.Users.Domain.Dto.Auth.LoginDto;
import com.jeyson.Users.Domain.Dto.Auth.RegisterDto;
import com.jeyson.Users.Domain.Dto.Users.UserDto;
import com.jeyson.Users.Domain.Entities.User;
import com.jeyson.Users.Domain.Exceptions.RegisterUserException;
import com.jeyson.Users.Domain.Exceptions.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jeyson.Users.Domain.Helpers.AuthHelper.verifyRegisterPasswords;
import static com.jeyson.Users.Application.Mappers.UserMapper.RegisterUserDtoMapper.toEntity;
import static com.jeyson.Users.Application.Mappers.UserMapper.UserDtoMapper.toDto;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtService jwtService,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDto registerUser(RegisterDto registerDto) {
        User userRegistered;
        verifyRegisterPasswords(registerDto);
        try {
            User newUser = toEntity(registerDto);
            newUser.commitCreate();
            userRegistered = userRepository.save(newUser);
        } catch (Exception e) {
            throw new RegisterUserException(e.getMessage());
        }
        return toDto(userRegistered);
    }

    @Override
    public String loginUser(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword());

        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(UserNotFoundException::new);
        authenticationManager.authenticate(authToken);
        return jwtService.generateToken(user);
    }
}
