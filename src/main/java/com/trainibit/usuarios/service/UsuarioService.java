package com.trainibit.usuarios.service;

import com.trainibit.usuarios.request.UsuarioRequest;
import com.trainibit.usuarios.response.UsuarioResponse;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public interface UsuarioService {

    List<UsuarioResponse> findAll();

    UsuarioResponse findById(UUID uuid);

    UsuarioResponse guardaUsuario(UsuarioRequest usuarioNuevo);

    UsuarioResponse deleteById(UUID uuid);

    UsuarioResponse putById(UUID uuid, UsuarioRequest usuarioActualizado);
}
