package com.jeyson.Core.Domain.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_who_created_id")
    private Long userWhoCreatedId;

    @Column(name = "user_who_updated_id")
    private Long userWhoUpdatedId;

    @Column(name = "user_who_deleted_id")
    private Long userWhoDeletedId;

    @Setter
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Setter
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void commitDelete(Long userId) {
        this.setUserWhoDeletedId(userId);
        this.commitDelete();
    }

    public void commitDelete() {
        this.setDeletedAt(LocalDateTime.now()) ;
    }

    public void commitUpdate(Long userId) {
        this.setUserWhoUpdatedId(userId);
        this.commitUpdate();
    }

    public void commitUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }

    public void commitCreate(Long userId) {
        this.setUserWhoCreatedId(userId);
        this.commitCreate();
    }

    public void commitCreate() {
        this.setCreatedAt(LocalDateTime.now());
    }

}