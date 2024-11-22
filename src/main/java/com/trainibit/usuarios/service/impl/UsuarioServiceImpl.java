package com.trainibit.usuarios.service.impl;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.mapper.UsuarioMapper;
import com.trainibit.usuarios.repository.UsuarioRepository;
import com.trainibit.usuarios.response.UsuarioResponse;
import com.trainibit.usuarios.service.UsuarioService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioResponse> findAll() {
        return UsuarioMapper.mapListEntityToListDto(usuarioRepository.findAll());
    }


    public UsuarioResponse guardaUsuario(Usuario usuario) {
        //usuarioRepository.save(usuario);
        return UsuarioMapper.mapEntityToDto(usuarioRepository.save(usuario));
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
        // revisar si existe por id
        try {
            if (!usuarioRepository.existsById(id)) {
                throw new DataAccessException("Usuario con ID " + id + " no existe") {};
            }
            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al verificar el usuario con ID " + id, e);
        }
    }
}
