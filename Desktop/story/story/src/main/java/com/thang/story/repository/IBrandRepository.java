package com.thang.story.repository;

import com.thang.story.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> getBrandsByCategoryId(Long categoryId);
}
