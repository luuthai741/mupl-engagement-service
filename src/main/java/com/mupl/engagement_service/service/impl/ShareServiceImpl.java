package com.mupl.engagement_service.service.impl;

import com.mupl.engagement_service.dto.request.ShareRequest;
import com.mupl.engagement_service.dto.response.ShareResponse;
import com.mupl.engagement_service.entity.ShareEntity;
import com.mupl.engagement_service.repository.ShareRepository;
import com.mupl.engagement_service.service.ShareService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {
    private final ShareRepository shareRepository;
    private final ModelMapper modelMapper;
    @Override
    public ShareResponse share(Long songId, ShareRequest shareRequest) {
        String username = "anonymous";
        ShareEntity shareEntity = modelMapper.map(shareRequest, ShareEntity.class);
        shareEntity.setUsername(username);
        shareEntity.setSongId(songId);
        shareEntity.setCreatedAt(LocalDateTime.now());
        return modelMapper.map(shareRepository.save(shareEntity), ShareResponse.class);
    }

    @Override
    public long countShareBySongId(Long songId) {
        return shareRepository.countBySongId(songId);
    }
}
