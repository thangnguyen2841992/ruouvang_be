package com.thang.story.repository;

import com.thang.story.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findCartsByUserIdOrderByDateCreated(Long userId);

    Optional<Cart> findCartByUserIdAndProductId(Long userId, Long cartId);
}
