package com.jeyson.Users.Application.Repositories;

import com.jeyson.Core.Application.Repositories.BaseRepository;
import com.jeyson.Users.Domain.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByEmail(String email);

    List<User> findByEmailLike(String email);

    List<User> findByNameLike(String nameSearch);

}
