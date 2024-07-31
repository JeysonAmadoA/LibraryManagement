package com.jeyson.Users.Infrastructure.Controllers;

import com.jeyson.Core.Infrastructure.Controller.BaseController;
import com.jeyson.Users.Application.Services.AuthService;
import com.jeyson.Users.Domain.Constants.Security.Role;
import com.jeyson.Users.Domain.Dto.Auth.LoginDto;
import com.jeyson.Users.Domain.Dto.Auth.RegisterDto;
import com.jeyson.Users.Domain.Dto.Users.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterDto registerUserDto) {
        registerUserDto.setRole(Role.ADMIN);
        UserDto userCreated = authService.registerUser(registerUserDto);
        Map<String, Object> response = getJsonResponse(userCreated, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register-librarian")
    public ResponseEntity<?> registerLibrarian(@RequestBody RegisterDto registerUserDto) {
        registerUserDto.setRole(Role.LIBRARIAN);
        UserDto userCreated = authService.registerUser(registerUserDto);
        Map<String, Object> response = getJsonResponse(userCreated, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register-customer")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterDto registerUserDto) {
        registerUserDto.setRole(Role.CUSTOMER);
        UserDto userCreated = authService.registerUser(registerUserDto);
        Map<String, Object> response = getJsonResponse(userCreated, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        String jwt = authService.loginUser(loginDto);
        Map<String, Object> response = getJsonResponse(jwt);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
