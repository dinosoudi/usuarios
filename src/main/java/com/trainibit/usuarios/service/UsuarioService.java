package com.trainibit.usuarios.service;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    //void guardaUsuario(Usuario usuario);
    List<UsuarioResponse> findAll();

    Usuario findById(Long id);

    UsuarioResponse guardaUsuario(Usuario usuario);
    boolean deleteById(Long id);

    boolean putById(Long id, Usuario usuario);
}
