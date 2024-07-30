package com.jeyson.Books.Domain.Dto;


import java.util.Set;

public record BookcaseDto(long id, String BookcaseName, String category, Set<BookDto> books) {
}
