package com.mupl.engagement_service.repository;

import com.mupl.engagement_service.entity.PlayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends JpaRepository<PlayEntity, Long> {
}
