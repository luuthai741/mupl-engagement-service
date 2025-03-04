package com.mupl.engagement_service.controller;

import com.mupl.engagement_service.dto.response.LikeResponse;
import com.mupl.engagement_service.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/engagement")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PutMapping("/songs/{songId}/likes")
    public ResponseEntity<LikeResponse> updateSongLike(@PathVariable Long songId) {
        return ResponseEntity.ok().body(likeService.updateSongLike(songId));
    }

    @GetMapping("/songs/{songId}/likes")
    public ResponseEntity<Long> getSongLikes(@PathVariable Long songId) {
        return ResponseEntity.ok().body(likeService.countLikesBySongId(songId));
    }
}
