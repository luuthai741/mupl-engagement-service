package com.mupl.engagement_service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mupl.engagement_service.util.constant.CommentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long commentId;
    private Long songId;
    private String username;
    private String content;
    private CommentStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:dd")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:dd")
    private LocalDateTime updatedAt;
}
