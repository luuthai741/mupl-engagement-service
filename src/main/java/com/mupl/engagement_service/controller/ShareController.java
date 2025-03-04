package com.mupl.engagement_service.controller;

import com.mupl.engagement_service.dto.request.ShareRequest;
import com.mupl.engagement_service.dto.response.ShareResponse;
import com.mupl.engagement_service.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/engagement")
@RequiredArgsConstructor
public class ShareController {
    private final ShareService shareService;

    @PostMapping("/songs/{songId}/shares")
    public ResponseEntity<ShareResponse> createShare(@PathVariable Long songId, @RequestBody ShareRequest shareRequest) {
        return ResponseEntity.ok(shareService.share(songId, shareRequest));
    }

    @GetMapping("/songs/{songId}/shares")
    public ResponseEntity<Long> countShares(@PathVariable Long songId) {
        return ResponseEntity.ok(shareService.countShareBySongId(songId));
    }
}
