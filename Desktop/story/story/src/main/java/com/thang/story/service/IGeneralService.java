package com.thang.story.service;

import com.thang.story.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void delete(Long id);
}
