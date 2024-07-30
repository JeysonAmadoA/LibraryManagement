package com.jeyson.Books.Application.Repositories;

import com.jeyson.Books.Domain.Entities.Book;
import com.jeyson.Core.Application.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends BaseRepository<Book> {
}
