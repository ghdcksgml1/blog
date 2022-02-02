package com.cos.blog.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public class TimeZone {
    @Column(updatable = false)
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    @PrePersist
    public void createTime(){
        LocalDateTime now = LocalDateTime.now();
        this.createdTime = now;
        this.updatedTime = now;
    }

    @PreUpdate
    public void updateTime(){
        this.updatedTime = LocalDateTime.now();
    }
}
