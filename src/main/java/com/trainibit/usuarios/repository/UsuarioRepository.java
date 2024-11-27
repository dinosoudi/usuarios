package com.trainibit.usuarios.repository;

import com.trainibit.usuarios.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends AuditableRepository<Usuario, Long> {

    default void deleteByIdActive(UUID uuid){
        Usuario entity = findByUuidAndActiveTrue(uuid).get();
        entity.setActive(false);
        save(entity);
    }


    Optional<Usuario> findByUuidAndActiveTrue(UUID uuid);


    List<Usuario> findByActiveTrue();

    @Override
    default Usuario updateAudit(Usuario entity) {
        entity.setUpdatedAt(Timestamp.from(Instant.now()));
        return save(entity);
    }

}
