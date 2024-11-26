package com.trainibit.usuarios.mapper;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.request.UsuarioRequest;
import com.trainibit.usuarios.response.UsuarioResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    // recibe una entidad/usuario y regresa un Response/DTO
    public static UsuarioResponse mapEntityToDto(@RequestBody Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setName(usuario.getName());
        usuarioResponse.setEmail(usuario.getEmail());
        usuarioResponse.setPassword(usuario.getPassword());
        usuarioResponse.setBirthDate(usuario.getBirthDate());
        usuarioResponse.setLastName(usuario.getLastName());
        usuarioResponse.setEdad(Period.between(usuario.getBirthDate() , LocalDate.now()).getYears());
        return usuarioResponse;
    }


    // recibe una lista de entidades/usuarios y regresa lista de Request/DTO
    public static List<UsuarioResponse> mapListEntityToListDto(@RequestBody List<Usuario> usuarios) {
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioResponses.add(mapEntityToDto(usuario));
        }
        return usuarioResponses;
    }

    // pasa un Request y regresa un usuario/entidad
    public static Usuario mapRequestToEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setName(request.getName());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setBirthDate(request.getBirthDate());
        usuario.setLastName(request.getLastName());
        return usuario;
    }

}
