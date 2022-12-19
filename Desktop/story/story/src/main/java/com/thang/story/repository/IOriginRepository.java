package com.thang.story.repository;

import com.thang.story.model.entity.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOriginRepository extends JpaRepository<Origin, Long> {
}
