
package com.mupl.engagement_service.entity;

import com.mupl.engagement_service.util.constant.SharePlatformType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mupl_share")
public class ShareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shareId;
    private String username;
    private Long songId;
    @Enumerated(EnumType.STRING)
    private SharePlatformType platform;
    private LocalDateTime createdAt;
}
