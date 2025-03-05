package com.mupl.engagement_service.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mupl.engagement_service.dto.response.LikeResponse;
import com.mupl.engagement_service.dto.response.SongResponse;
import com.mupl.engagement_service.entity.LikeEntity;
import com.mupl.engagement_service.exception.BadRequestException;
import com.mupl.engagement_service.feign.MusicServiceClient;
import com.mupl.engagement_service.repository.LikeRepository;
import com.mupl.engagement_service.service.LikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final MusicServiceClient musicServiceClient;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    @Override
    public LikeResponse updateSongLike(Long songId) {
        SongResponse song = getSongById(songId);
        if (ObjectUtils.isEmpty(song)) {
            throw new BadRequestException("Song not found");
        }
        String username = "anonymous";
        LikeEntity likeEntity = likeRepository.findBySongIdAndUsername(songId,username);
        if (ObjectUtils.isEmpty(likeEntity)) {
            likeEntity = LikeEntity.builder()
                    .songId(songId)
                    .username(username)
                    .createdAt(LocalDateTime.now())
                    .liked(true)
                    .build();
        }else{
            likeEntity.setLiked(!likeEntity.getLiked());
        }
        return modelMapper.map(likeRepository.save(likeEntity), LikeResponse.class);
    }

    @Override
    public long countLikesBySongId(Long songId) {
        return likeRepository.countBySongId(songId);
    }

    @Override
    public void deleteAllLikesBySongId(Long songId) {
        likeRepository.deleteBySongId(songId);
        log.info("Delete all likes by songId: {}", songId);
    }

    private SongResponse getSongById(Long songId) {
        // todo: should get from redis
        return objectMapper.convertValue(musicServiceClient.getSongById(songId), SongResponse.class);
    }
}
