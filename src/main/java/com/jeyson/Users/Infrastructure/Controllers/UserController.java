package com.jeyson.Users.Infrastructure.Controllers;

import com.jeyson.Core.Infrastructure.Controller.BaseController;
import com.jeyson.Users.Application.Services.UserService;
import com.jeyson.Users.Domain.Dto.Users.UpdateUserDto;
import com.jeyson.Users.Domain.Dto.Users.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDto users = userService.filterUserById(id);
        Map<String, Object> response = getJsonResponse(users);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> users = userService.findAllUsers();
        Map<String, Object> response = getJsonResponse(users);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/filter/email")
    public ResponseEntity<?> getUsersByEmail(@RequestParam("email") String email) {
        List<UserDto> users = userService.filterUsersByEmail(email);
        Map<String, Object> response = getJsonResponse(users);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/filter/name")
    public ResponseEntity<?> getUsersByName(@RequestParam("name") String name) {
        List<UserDto> users = userService.filterUsersByName(name);
        Map<String, Object> response = getJsonResponse(users);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> getUsersByName(@PathVariable Long id, @RequestBody UpdateUserDto userDto) {
        UserDto users = userService.updateUser(userDto, id);
        Map<String, Object> response = getJsonResponse(users);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        Map<String, Object> response = getJsonResponse("Usuario eliminado con exito");
        return ResponseEntity.ok().body(response);
    }

}
