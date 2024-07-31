package com.jeyson.Users.Domain.Dto.Users;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDto implements Serializable {
    private long id;
    private String name;
    private String email;
    private String documentNumber;
    private String address;
    private String role;
    private LocalDateTime createdAt;
}
