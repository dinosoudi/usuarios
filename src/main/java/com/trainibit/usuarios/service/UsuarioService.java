package com.trainibit.usuarios.service;

import com.trainibit.usuarios.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    void guardaUsuario(Usuario usuario);
    List<Usuario> findAll();
}
