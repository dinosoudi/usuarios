package com.trainibit.usuarios.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class AuditableRecord {

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false)
    private Timestamp updatedAt;

    @ColumnDefault("true")
    @Column(name = "active", insertable = false, nullable = false)
    private Boolean active;
}
