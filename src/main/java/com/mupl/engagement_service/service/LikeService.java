package com.mupl.engagement_service.service;

import com.mupl.engagement_service.dto.response.LikeResponse;

public interface LikeService {
    LikeResponse updateSongLike(Long songId);
    long countLikesBySongId(Long songId);
}
