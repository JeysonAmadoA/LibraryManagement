package com.jeyson.Books.Domain.Dto;

import java.io.Serializable;

public record BookDto(long id,
                      String bookName,
                      String author,
                      short publicationYear,
                      short pages,
                      String editorial) implements Serializable {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String bookName;
        private String author;
        private short publicationYear;
        private short pages;
        private String editorial;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder bookName(String bookName) {
            this.bookName = bookName;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder publicationYear(short publicationYear) {
            this.publicationYear = publicationYear;
            return this;
        }

        public Builder pages(short pages) {
            this.pages = pages;
            return this;
        }

        public Builder editorial(String editorial) {
            this.editorial = editorial;
            return this;
        }

        public BookDto build() {
            return new BookDto(id, bookName, author, publicationYear, pages, editorial);
        }
    }
}

