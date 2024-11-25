package com.trainibit.usuarios.repository;

import com.trainibit.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Repository
public interface UsuarioRepository extends AuditableRepository<Usuario, Long> {
    @Override
    default void deleteByIdActive(Long id){
        Usuario entity = findById(id).get();
        entity.setActive(false);
        save(entity);
    }

    @Override
    default Usuario updateAudit(Usuario entity) {
        entity.setUpdatedAt(Timestamp.from(Instant.now()));
        return save(entity);
    }

}
