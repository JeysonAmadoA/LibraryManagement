package com.jeyson.BooksRent.Application.Repositories;

import com.jeyson.BooksRent.Domain.Entities.BookRent;
import com.jeyson.Core.Application.Repositories.BaseRepository;
import com.jeyson.Users.Domain.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends BaseRepository<User> {

    @Query("SELECT br FROM BookRent br WHERE br.userWhoRented.id = :userId " +
            "AND br.returnedAt IS NULL AND br.deletedAt IS NULL")
    List<BookRent> findActiveBookRentsByUserId(@Param("userId") Long userId);

    @Query("SELECT br FROM BookRent br WHERE br.userWhoRented.id = :userId " +
            "AND br.returnedAt IS NULL AND br.returningDate < :currentDate AND br.deletedAt IS NULL")
    List<BookRent> findBooksWithDelayedReturnDateByCustomerId(
            @Param("userId") Long userId,
            @Param("currentDate") LocalDate currentDate);
}
