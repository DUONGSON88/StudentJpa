package com.example.repository;

import java.util.List;

public interface IGenerateRepository<S> {
    List<S> findAll();
    void save(S p);
    S findById(Long id);
    void remove(Long id);
    List<S> findSByName(String name);
}
