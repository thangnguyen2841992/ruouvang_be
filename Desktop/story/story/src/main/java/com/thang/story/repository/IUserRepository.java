package com.thang.story.repository;

import com.thang.story.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


    Optional<User> findByEmail(String email);


    Optional<User> findByPhone(String phone);
}
