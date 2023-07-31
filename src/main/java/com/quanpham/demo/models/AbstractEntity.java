package com.quanpham.demo.models;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@MappedSuperclass
public class AbstractEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    // Lúc insert vào db tạo đối tượng không cần thêm createdAt và updatedAt
    @PrePersist
    public void prePersist() {
        createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        updatedAt = updatedAt == null ? LocalDateTime.now() : updatedAt;
    }

    // Lúc u pdate record vào db tạo đối tượng không cần thêm updatedAt
    @PreUpdate
    public void preUpdate() {
        updatedAt = updatedAt == null ? LocalDateTime.now() : updatedAt;
        // createdAt = createdAt;
    }
}
