package com.trainibit.usuarios.repository;

import com.trainibit.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends AuditableRepository<Usuario, Long> {

    default void deleteByIdActive(UUID uuid){
        Usuario entity = findByUuid(uuid).get();
        entity.setActive(false);
        save(entity);
    }


    Optional<Usuario> findByUuid(UUID uuid);

    @Override
    default Usuario updateAudit(Usuario entity) {
        entity.setUpdatedAt(Timestamp.from(Instant.now()));
        return save(entity);
    }

}
