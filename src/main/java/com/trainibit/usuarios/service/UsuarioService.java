package com.trainibit.usuarios.service;

import com.trainibit.usuarios.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    void guardaUsuario(Usuario usuario);
    List<Usuario> findAll();

    Usuario findById(Long id);

    boolean deleteById(Long id);

    boolean putById(Long id, Usuario usuario);
}
