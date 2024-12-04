package com.trainibit.usuarios.mapper;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.response.UsuarioResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioResponse mapEntityToDto(Usuario usuario);
}
