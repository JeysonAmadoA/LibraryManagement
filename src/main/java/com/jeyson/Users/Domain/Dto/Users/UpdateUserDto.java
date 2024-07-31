package com.jeyson.Users.Domain.Dto.Users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserDto {
    private String name;
    private String email;
    private String address;
}
