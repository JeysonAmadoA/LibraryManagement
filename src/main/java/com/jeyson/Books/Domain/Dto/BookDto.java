package com.jeyson.Books.Domain.Dto;

public record BookDto(long id,
                      String bookName,
                      String author,
                      short publicationYear,
                      short pages,
                      String editorial) {
}
