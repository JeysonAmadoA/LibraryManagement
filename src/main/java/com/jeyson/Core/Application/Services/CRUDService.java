package com.jeyson.Core.Application.Services;

import java.util.List;

public interface CRUDService <D,R> {

    List<D> findAll();

    D filterById(Long id);

    D store(R bookDto);

    D update(Long id, R bookDto);

    void deleteById(Long id);
}
