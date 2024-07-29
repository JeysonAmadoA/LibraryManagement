package com.jeyson.Users.Application.Services;

import com.jeyson.Users.Domain.Dto.Users.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    List<UserDto> filterUsersByEmail(String email);

    List<UserDto> filterUsersByName(String entrySearch);

    UserDto updateUser(UserDto userDto, Long userId);

}
