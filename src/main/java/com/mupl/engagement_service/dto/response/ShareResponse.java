package com.mupl.engagement_service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mupl.engagement_service.util.constant.SharePlatformType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareResponse {
    private Long shareId;
    private String username;
    private Long songId;
    private SharePlatformType platform;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:dd")
    private LocalDateTime createdAt;
}
