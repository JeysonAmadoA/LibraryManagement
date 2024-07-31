package com.jeyson.Books.Domain.Entities;

import com.jeyson.Core.Domain.Entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted_at is NULL")
@Table(name = "bookcase")
@Getter
@Setter
public class Bookcase extends BaseEntity {

    @Column(name = "bookcase_name")
    private String bookcaseName;

    @Column
    private String category;

    @OneToMany(mappedBy = "bookcase")
    private Set<Book> books;
}
