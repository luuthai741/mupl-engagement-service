package com.mupl.engagement_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "music-service", url = "${services.music-service}")
public interface MusicServiceClient {
    String API_PREFIX = "/api/v1/mupl/internal";

    @GetMapping(API_PREFIX + "/songs/{id}")
    Map<String, Object> getSongById(@PathVariable("id") Long id);
}
