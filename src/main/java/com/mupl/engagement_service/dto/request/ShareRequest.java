package com.mupl.engagement_service.dto.request;

import com.mupl.engagement_service.util.constant.SharePlatformType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareRequest {
    private SharePlatformType platform;
}
