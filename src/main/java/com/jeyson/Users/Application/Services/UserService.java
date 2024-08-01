package com.jeyson.Users.Application.Services;

import com.jeyson.Users.Domain.Dto.Users.UpdateUserDto;
import com.jeyson.Users.Domain.Dto.Users.UserDto;

import java.util.List;

public interface UserService {

    UserDto filterUserById(Long userId);

    List<UserDto> findAllUsers();

    List<UserDto> filterUsersByEmail(String email);

    List<UserDto> filterUsersByName(String name);

    UserDto updateUser(UpdateUserDto userDto, Long userId);

    void deleteUserById(Long userId);

}
