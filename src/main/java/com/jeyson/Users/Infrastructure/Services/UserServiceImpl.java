package com.jeyson.Users.Infrastructure.Services;

import com.jeyson.Users.Application.Repositories.UserRepository;
import com.jeyson.Users.Application.Services.UserService;
import com.jeyson.Users.Domain.Dto.Users.UpdateUserDto;
import com.jeyson.Users.Domain.Dto.Users.UserDto;
import com.jeyson.Users.Domain.Entities.User;
import com.jeyson.Users.Domain.Exceptions.RegisterUserException;
import com.jeyson.Users.Domain.Exceptions.UserNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jeyson.Users.Application.Mappers.UserMapper.UserDtoMapper;
import static com.jeyson.Users.Domain.Helpers.AuthHelper.getActualUser;
import static com.jeyson.Users.Domain.Helpers.AuthHelper.verifyUserAccess;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto filterUserById(Long userId) {
        verifyUserAccess(userId);
        User userFound = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userFound != null ? UserDtoMapper.toDto(userFound) : null;
    }

    @Cacheable("getAllUsers")
    @Override
    public List<UserDto> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(UserDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> filterUsersByEmail(String email) {
        List<User> usersByEmail = userRepository.findByEmailLike("%"+email+"%");
        return usersByEmail.stream()
                .map(UserDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> filterUsersByName(String name) {
        List<User> usersByName = userRepository.findByNameLike("%"+name+"%");
        return usersByName.stream()
                .map(UserDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDto updateUser(UpdateUserDto userDto, Long userId) {
        User userUpdated;
        try {
            User userFound = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            userUpdated = UserDtoMapper.update(userFound, userDto);
            userUpdated.commitUpdate(getActualUser());
            userRepository.save(userUpdated);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return UserDtoMapper.toDto(userUpdated);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        try {
            user.commitDelete(getActualUser());
            userRepository.save(user);
        } catch (Exception exception){
            throw new RegisterUserException(exception.getMessage());
        }
    }
}
