package com.example.service;

import java.util.List;

public interface IGenerateService<S> {
    List<S> findAll();
    void save(S p);
    S findById(Long id);
    void remove(Long id);
    List<S> findSByName(String name);

}
