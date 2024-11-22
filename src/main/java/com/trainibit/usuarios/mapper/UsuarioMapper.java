package com.trainibit.usuarios.mapper;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.response.UsuarioResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static UsuarioResponse mapEntityToDto(@RequestBody Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setName(usuario.getName());
        usuarioResponse.setEmail(usuario.getEmail());
        usuarioResponse.setPassword(usuario.getPassword());
        usuarioResponse.setBirthDate(usuario.getBirthDate());
        usuarioResponse.setLastName(usuario.getLastName());
        usuarioResponse.setEdad(Period.between(usuario.getBirthDate() , LocalDate.now()).getYears());
        return usuarioResponse;
    }



    public static List<UsuarioResponse> mapListEntityToListDto(@RequestBody List<Usuario> usuarios) {
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioResponses.add(mapEntityToDto(usuario));
        }
        return usuarioResponses;
    }

    public static Usuario mapDtoToEntity(UsuarioResponse usuarioResponse2) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioResponse2.getId());
        usuario.setName(usuarioResponse2.getName());
        usuario.setEmail(usuarioResponse2.getEmail());
        usuario.setPassword(usuarioResponse2.getPassword());
        usuario.setBirthDate(usuarioResponse2.getBirthDate());
        usuario.setLastName(usuarioResponse2.getLastName());
        return usuario;
    }
}
