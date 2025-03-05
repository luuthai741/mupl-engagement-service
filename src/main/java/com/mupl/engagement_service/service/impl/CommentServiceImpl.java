package com.mupl.engagement_service.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mupl.engagement_service.dto.request.CommentRequest;
import com.mupl.engagement_service.dto.response.CommentResponse;
import com.mupl.engagement_service.dto.response.PageableResponse;
import com.mupl.engagement_service.dto.response.SongResponse;
import com.mupl.engagement_service.entity.CommentEntity;
import com.mupl.engagement_service.exception.BadRequestException;
import com.mupl.engagement_service.feign.MusicServiceClient;
import com.mupl.engagement_service.repository.CommentRepository;
import com.mupl.engagement_service.service.CommentService;
import com.mupl.engagement_service.util.constant.CommentStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final MusicServiceClient musicServiceClient;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    @Override
    public PageableResponse getAllComments(Long songId, Pageable pageable) {
        Page<CommentEntity> commentPage = commentRepository.findAllBySongId(songId, pageable);
        List<CommentResponse> comments = commentPage
                .getContent()
                .stream()
                .map(comment -> {
                    CommentResponse commentResponse = modelMapper.map(comment, CommentResponse.class);
                    if (commentResponse.getStatus() == CommentStatus.DELETED){
                        commentResponse.setContent("This comment has been deleted");
                    }
                    return commentResponse;
                })
                .toList();
        return new PageableResponse(comments, pageable, commentPage.getTotalElements());
    }

    @Override
    public CommentResponse createComment(Long songId, CommentRequest commentRequest) {
        SongResponse song = getSongById(songId);
        if (ObjectUtils.isEmpty(song)) {
            throw new BadRequestException("Song has not been found");
        }
        CommentEntity commentEntity = modelMapper.map(commentRequest, CommentEntity.class);
        commentEntity.setStatus(CommentStatus.CREATED);
        // todo: will be changing after add spring security
        commentEntity.setUsername("anonymous");
        commentEntity.setCreatedAt(LocalDateTime.now());
        commentEntity.setUpdatedAt(LocalDateTime.now());
        commentEntity.setSongId(songId);
        return modelMapper.map(commentRepository.save(commentEntity), CommentResponse.class);
    }

    @Override
    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException("Comment not found"));
        if (commentEntity.getStatus() == CommentStatus.DELETED) {
            throw new BadRequestException("Comment has been deleted");
        }
        commentEntity.setUpdatedAt(LocalDateTime.now());
        commentEntity.setContent(commentRequest.getContent());
        commentEntity.setStatus(CommentStatus.UPDATED);
        return modelMapper.map(commentRepository.save(commentEntity), CommentResponse.class);
    }

    @Override
    public CommentResponse deleteComment(Long commentId, CommentRequest commentRequest) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException("Comment not found"));
        commentEntity.setStatus(CommentStatus.DELETED);
        commentEntity.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(commentEntity);
        CommentResponse commentResponse = modelMapper.map(commentEntity, CommentResponse.class);
        commentResponse.setContent("This comment has been deleted");
        return commentResponse;
    }

    @Override
    public void deleteAllCommentsBySongId(Long songId) {
        commentRepository.deleteBySongId(songId);
        log.info("Deleted all comments by songId: {}", songId);
    }

    private SongResponse getSongById(Long songId) {
        // todo: should get from redis
        return objectMapper.convertValue(musicServiceClient.getSongById(songId), SongResponse.class);
    }
}
