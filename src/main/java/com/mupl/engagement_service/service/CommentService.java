package com.mupl.engagement_service.service;

import com.mupl.engagement_service.dto.request.CommentRequest;
import com.mupl.engagement_service.dto.response.CommentResponse;
import com.mupl.engagement_service.dto.response.PageableResponse;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    PageableResponse getAllComments(Long songId,Pageable pageable);
    CommentResponse createComment(Long songId,CommentRequest commentRequest);
    CommentResponse updateComment(Long commentId,CommentRequest commentRequest);
    CommentResponse deleteComment(Long commentId,CommentRequest commentRequest);
    void deleteAllCommentsBySongId(Long songId);
}
