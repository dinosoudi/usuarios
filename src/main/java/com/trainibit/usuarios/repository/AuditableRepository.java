package com.trainibit.usuarios.repository;

import com.trainibit.usuarios.entity.AuditableRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuditableRepository<T, ID> extends JpaRepository<T, ID> {
    void deleteByIdActive(ID id);

    T updateAudit(T t);
}
