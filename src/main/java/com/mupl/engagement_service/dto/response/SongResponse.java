package com.mupl.engagement_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongResponse {
    private Long songId;
    private String songName;
    private List<ArtistResponse> artists;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArtistResponse {
        private String name;
    }
}
