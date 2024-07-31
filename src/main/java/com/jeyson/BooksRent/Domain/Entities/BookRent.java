package com.jeyson.BooksRent.Domain.Entities;

import com.jeyson.Books.Domain.Entities.Book;
import com.jeyson.Core.Domain.Entities.BaseEntity;
import com.jeyson.Users.Domain.Entities.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted_at is NULL")
@Table(name = "books_rent")
@Getter
@Setter
public class BookRent extends BaseEntity {

    @Column(name = "book_id")
    private long bookId;

    @ManyToOne
    @JoinColumn(name="book_id", insertable = false, updatable = false)
    private Book book;

    @Column(name = "user_who_rented_id")
    private long userWhoRentedId;

    @ManyToOne
    @JoinColumn(name="user_who_rented_id", insertable = false, updatable = false)
    private User userWhoRented;

    @Column(name = "rented_at")
    private LocalDateTime rentedAt;

    @Column(name = "user_who_delivery_id")
    private long userWhoDeliveryId;

    @ManyToOne
    @JoinColumn(name="user_who_delivery_id", insertable = false, updatable = false)
    private User userWhoDelivery;

    @Column(name = "delivery_at")
    private LocalDateTime deliveryAt;

    @Column(name = "returning_date", nullable = false)
    private LocalDate returningDate;

    @Column(name = "returned_at")
    private LocalDateTime returnedAt;

}
