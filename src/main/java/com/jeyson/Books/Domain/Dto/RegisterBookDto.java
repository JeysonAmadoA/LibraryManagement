package com.jeyson.Books.Domain.Dto;

import lombok.Data;

@Data
public class RegisterBookDto {
    private String bookName;
    private String author;
    private short publicationYear;
    private short pages;
    private String editorial;
    private long bookcaseId;
}
