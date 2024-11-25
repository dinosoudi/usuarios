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

    public static UsuarioResponse mapEntityToDto(@RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setName(usuarioRequest.getName());
        usuarioResponse.setEmail(usuarioRequest.getEmail());
        usuarioResponse.setPassword(usuarioRequest.getPassword());
        usuarioResponse.setBirthDate(usuarioRequest.getBirthDate());
        usuarioResponse.setLastName(usuarioRequest.getLastName());
        usuarioResponse.setEdad(Period.between(usuarioRequest.getBirthDate() , LocalDate.now()).getYears());
        return usuarioResponse;
    }



    public static List<UsuarioResponse> mapListEntityToListDto(@RequestBody List<Usuario> usuarios) {
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioResponses.add(mapEntityToDto(usuario));
        }
        return usuarioResponses;
    }

    public static Usuario mapDtoToEntity(Usuario usuario) {
        Usuario user = new Usuario();

        user.setName(usuario.getName());
        user.setEmail(usuario.getEmail());
        user.setPassword(usuario.getPassword());
        user.setLastName(usuario.getLastName());
        user.setBirthDate(usuario.getBirthDate());

        return usuario;
    }
}
