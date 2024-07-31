package com.jeyson.BooksRent.Domain.Dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookRentDto(
        long bookId,
        long userWhoRentedId,
        LocalDateTime rentedAt,
        long userWhoDeliveryId,
        LocalDateTime deliveryAt,
        LocalDate returningDate,
        LocalDateTime returnedAt) implements Serializable {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long bookId;
        private long userWhoRentedId;
        private LocalDateTime rentedAt;
        private long userWhoDeliveryId;
        private LocalDateTime deliveryAt;
        private LocalDate returningDate;
        private LocalDateTime returnedAt;

        public Builder bookId(long bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder userWhoRentedId(long userWhoRentedId) {
            this.userWhoRentedId = userWhoRentedId;
            return this;
        }

        public Builder rentedAt(LocalDateTime rentedAt) {
            this.rentedAt = rentedAt;
            return this;
        }

        public Builder userWhoDeliveryId(long userWhoDeliveryId) {
            this.userWhoDeliveryId = userWhoDeliveryId;
            return this;
        }

        public Builder deliveryAt(LocalDateTime deliveryAt) {
            this.deliveryAt = deliveryAt;
            return this;
        }

        public Builder returningDate(LocalDate returningDate) {
            this.returningDate = returningDate;
            return this;
        }

        public Builder returnedAt(LocalDateTime returnedAt) {
            this.returnedAt = returnedAt;
            return this;
        }

        public BookRentDto build() {
            return new BookRentDto(
                    bookId,
                    userWhoRentedId,
                    rentedAt,
                    userWhoDeliveryId,
                    deliveryAt,
                    returningDate,
                    returnedAt
            );
        }
    }
}
