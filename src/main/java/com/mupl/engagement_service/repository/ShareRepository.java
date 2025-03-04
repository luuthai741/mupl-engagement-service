package com.mupl.engagement_service.repository;

import com.mupl.engagement_service.entity.ShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<ShareEntity, Long> {
}
