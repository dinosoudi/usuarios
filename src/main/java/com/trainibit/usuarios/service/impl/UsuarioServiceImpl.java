package com.trainibit.usuarios.service.impl;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.mapper.UsuarioMapper;
import com.trainibit.usuarios.repository.UsuarioRepository;
import com.trainibit.usuarios.request.UsuarioRequest;
import com.trainibit.usuarios.response.UsuarioResponse;
import com.trainibit.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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


    public UsuarioResponse guardaUsuario(UsuarioRequest usuarioRequest) {
        return UsuarioMapper.mapEntityToDto(usuarioRepository.save(UsuarioMapper.mapRequestToEntity(usuarioRequest)));
    }

    @Override
    public UsuarioResponse findById(Long id) {
        return UsuarioMapper.mapEntityToDto(usuarioRepository.findById(id).get());
    }

    public UsuarioResponse deleteById(Long id) {
        usuarioRepository.deleteByIdActive(id);
        return UsuarioMapper.mapEntityToDto(usuarioRepository.findById(id).get());
    }

    public UsuarioResponse putById(Long id, UsuarioRequest usuarioRequest) {
        // revisar si existe por id
       /* try {
            if (!usuarioRepository.existsById(id)) {
                throw new DataAccessException("Usuario con ID " + id + " no existe") {};
            }
            //cambiar valores de id
            usuarioRequest.setName(usuarioRequest.getName());
            usuarioRequest.setEmail(usuarioRequest.getEmail());
            usuarioRequest.setPassword(usuarioRequest.getPassword());
            usuarioRequest.setBirthDate(usuarioRequest.getBirthDate());
            usuarioRequest.setLastName(usuarioRequest.getLastName());
            //verificar el nombre de

            usuarioRepository.save(usuarioRequest);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al verificar el usuario con ID " + id, e);
        }*/
        return UsuarioMapper.mapEntityToDto(usuarioRepository.updateAudit( UsuarioMapper.mapRequestToEntity(usuarioRequest) ));

    }
}
