package com.jeyson.Books.Domain.Dto;


import java.io.Serializable;

public record BookcaseDto(long id, String BookcaseName, String category) implements Serializable {
}
