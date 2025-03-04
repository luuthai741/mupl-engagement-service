package com.mupl.engagement_service.controller;

import com.mupl.engagement_service.dto.request.CommentRequest;
import com.mupl.engagement_service.dto.response.CommentResponse;
import com.mupl.engagement_service.dto.response.PageableResponse;
import com.mupl.engagement_service.entity.CommentEntity;
import com.mupl.engagement_service.service.CommentService;
import com.mupl.engagement_service.util.constant.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/engagement")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/songs/{songId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long songId, @RequestBody CommentRequest commentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(commentService.createComment(songId, commentRequest));
    }

    @PutMapping("/songs/{songId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok().body(commentService.updateComment(commentId, commentRequest));
    }

    @DeleteMapping("/songs/{songId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok().body(commentService.deleteComment(commentId, commentRequest));
    }

    @GetMapping("/songs/{songId}/comments")
    public ResponseEntity<PageableResponse> getComments(@PathVariable Long songId, HttpServletRequest request) {
        Pageable pageable = RequestUtils.getPageable(request, "createdAt", "DESC", CommentEntity.class);
        return ResponseEntity.ok().body(commentService.getAllComments(songId, pageable));
    }
}
