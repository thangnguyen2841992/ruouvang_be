package com.thang.story.repository;

import com.thang.story.model.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccessoryRepository extends JpaRepository<Accessory, Long> {
}
