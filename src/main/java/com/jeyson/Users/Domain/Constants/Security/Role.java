package com.jeyson.Users.Domain.Constants.Security;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Role {

    ADMIN(
            Arrays.asList(
                    Permission.MONITOR_APP,
                    Permission.CREATE_ADMIN,
                    Permission.CREATE_LIBRARIAN,
                    Permission.CREATE_CUSTOMER,
                    Permission.UPDATE_USERS,
                    Permission.DELETE_USERS,
                    Permission.GET_ALL_USERS,
                    Permission.GET_ONE_USER,
                    Permission.CREATE_BOOKCASE,
                    Permission.DELETE_BOOKCASE,
                    Permission.UPDATE_BOOKCASE,
                    Permission.GET_BOOKCASES,
                    Permission.CREATE_BOOK,
                    Permission.DELETE_BOOK,
                    Permission.UPDATE_BOOK,
                    Permission.GET_BOOKS,
                    Permission.CREATE_RENT,
                    Permission.DELETE_RENT,
                    Permission.UPDATE_RENT,
                    Permission.GET_RENT
            )
    ),

    LIBRARIAN(
            Arrays.asList(
                    Permission.CREATE_CUSTOMER,
                    Permission.UPDATE_USERS,
                    Permission.GET_ALL_USERS,
                    Permission.GET_ONE_USER,
                    Permission.UPDATE_BOOKCASE,
                    Permission.GET_BOOKCASES,
                    Permission.UPDATE_BOOK,
                    Permission.GET_BOOKS,
                    Permission.CREATE_RENT,
                    Permission.UPDATE_RENT,
                    Permission.GET_RENT
            )
    ),

    CUSTOMER(
            List.of(
                    Permission.CREATE_CUSTOMER,
                    Permission.GET_ONE_USER,
                    Permission.GET_BOOKCASES,
                    Permission.GET_BOOKS
            )
    );

    private final List<Permission> permissions;
    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }
}