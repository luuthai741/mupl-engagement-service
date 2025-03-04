package com.mupl.engagement_service.service;

import com.mupl.engagement_service.dto.request.ShareRequest;
import com.mupl.engagement_service.dto.response.ShareResponse;

public interface ShareService {
    ShareResponse share(Long songId, ShareRequest shareRequest);
    long countShareBySongId(Long songId);
}
