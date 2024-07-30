package com.jeyson.Users.Application.Mappers;

import com.jeyson.Users.Domain.Dto.Auth.RegisterDto;
import com.jeyson.Users.Domain.Dto.Users.UpdateUserDto;
import com.jeyson.Users.Domain.Dto.Users.UserDto;
import com.jeyson.Users.Domain.Entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

    public static class UserDtoMapper {

        public static UserDto toDto(User user) {
            return UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .documentNumber(user.getDocumentNumber())
                    .address(user.getAddress())
                    .role(String.valueOf(user.getRole()))
                    .createdAt(user.getCreatedAt())
                    .build();
        }

        public static User update(User user, UpdateUserDto userDto){
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            return user;
        }
    }

    public static class RegisterUserDtoMapper{

        public static User toEntity(RegisterDto registerDto) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encryptedPassword = encoder.encode(registerDto.getPassword());

            return User.builder()
                    .name(registerDto.getName())
                    .documentNumber(registerDto.getDocumentNumber())
                    .address(registerDto.getAddress())
                    .email(registerDto.getEmail())
                    .password(encryptedPassword)
                    .role(registerDto.getRole())
                    .build();
        }

    }
}
