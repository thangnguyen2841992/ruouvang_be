package com.thang.story.repository;

import com.thang.story.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from products  order by name limit 10 offset ?1", nativeQuery = true)
    List<Product> findAllProducts(int offset);
    @Query(value = "select * from products where category_id = 3 order by name limit 10 offset ?1 ", nativeQuery = true)
    List<Product> findAllProductByCategory3(int offset);
    @Query(value = "select * from products where category_id in (1,2) order by name limit 10 offset ?1 ", nativeQuery = true)
    List<Product> findAllProductByCategory1and2(int offset);
}
