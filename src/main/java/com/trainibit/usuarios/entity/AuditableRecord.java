package com.trainibit.usuarios.entity;


import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Data
public class AuditableRecord {

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_date", nullable = false)
    private LocalDate updatedAt;

    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    private Boolean active = false;


    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
