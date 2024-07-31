package com.jeyson.BooksRent.Application.Repositories;

import com.jeyson.BooksRent.Domain.Entities.BookRent;
import com.jeyson.Core.Application.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRentRepository extends BaseRepository<BookRent> {

    boolean existsByBookIdAndReturnedAtIsNull(Long bookId);
}
