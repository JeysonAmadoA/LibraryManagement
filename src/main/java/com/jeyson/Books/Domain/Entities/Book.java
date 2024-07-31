package com.jeyson.Books.Domain.Entities;

import com.jeyson.Core.Domain.Entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted_at is NULL")
@Table(name = "books")
@Getter
@Setter
public class Book extends BaseEntity {

    @Column(name = "book_name")
    private String bookName;

    @Column
    private String author;

    @Column(name = "publication_year")
    private short publicationYear;

    @Column
    private short pages;

    @Column
    private String editorial;

    @Column(name = "bookcase_id", nullable = false)
    private long bookcaseId;

    @ManyToOne
    @JoinColumn(name="bookcase_id", insertable = false, updatable = false)
    private Bookcase bookcase;

}
