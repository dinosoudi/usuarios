package com.trainibit.usuarios.service.impl;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.repository.UsuarioRepository;
import com.trainibit.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


    public void guardaUsuario( Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public boolean deleteById(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public boolean putById(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.save(usuario);
            return true;
        }else {
            return false;
        }
    }
}
