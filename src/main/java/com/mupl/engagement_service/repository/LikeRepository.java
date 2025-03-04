package com.mupl.engagement_service.repository;

import com.mupl.engagement_service.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    LikeEntity findBySongId(Long songId);
    long countBySongId(Long songId);
}
