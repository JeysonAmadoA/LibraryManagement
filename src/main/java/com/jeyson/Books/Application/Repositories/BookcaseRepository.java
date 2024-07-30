package com.jeyson.Books.Application.Repositories;

import com.jeyson.Books.Domain.Entities.Bookcase;
import com.jeyson.Core.Application.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookcaseRepository extends BaseRepository<Bookcase> {
}
