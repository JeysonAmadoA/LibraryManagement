package com.jeyson.Users.Application.Mappers;

import com.jeyson.Users.Domain.Dto.Users.UserDto;
import com.jeyson.Users.Domain.Entities.User;

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

        public static User update(User user, UserDto userDto){
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setDocumentNumber(userDto.getDocumentNumber());
            user.setAddress(userDto.getAddress());
            return user;
        }
    }
}
