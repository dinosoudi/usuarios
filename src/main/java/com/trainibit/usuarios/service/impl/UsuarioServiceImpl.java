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
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioResponse> findAll() {
        return UsuarioMapper.mapListEntityToListDto(usuarioRepository.findAll());
    }

    @Override
    public UsuarioResponse findById(UUID uuid) {
        return UsuarioMapper.mapEntityToDto(usuarioRepository.findByUuid(uuid).get());
    }

    public UsuarioResponse guardaUsuario(UsuarioRequest usuarioRequest) {
        return UsuarioMapper.mapEntityToDto(usuarioRepository.save(UsuarioMapper.mapRequestToEntity(usuarioRequest)));
    }

    @Override
    public UsuarioResponse deleteById(UUID uuid) {
        /*//cambia el active a false y se guarda
        usuarioRepository.deleteByIdActive(uuid);
        //regresa el usuarioResponse cabiado
        return UsuarioMapper.mapEntityToDto( usuarioRepository.findByUuid(uuid).get() );*/
        return UsuarioMapper.mapEntityToDto(usuarioRepository.findByUuid(uuid).map(usuario -> {

            usuarioRepository.deleteByIdActive(uuid);
            return usuario; // Devuelve el usuario eliminado
        }).orElseThrow(() -> new DataAccessException("Error al eliminar usuario con ID: " + uuid){

        }));
    }

    public UsuarioResponse putById(UUID uuid, UsuarioRequest usuarioRequest) {
       /* Usuario usuario = usuarioRepository.findByUuid(uuid).get();

        usuario.setBirthDate(usuarioRequest.getBirthDate());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setPassword(usuarioRequest.getPassword());
        usuario.setName(usuarioRequest.getName());
        usuario.setLastName(usuarioRequest.getLastName());
        usuarioRepository.updateAudit(usuario);

        return UsuarioMapper.mapEntityToDto(usuario);*/
        return usuarioRepository.findByUuid(uuid).map(usuario -> {
            usuario.setName(usuarioRequest.getName());
            usuario.setLastName(usuarioRequest.getLastName());
            usuario.setEmail(usuarioRequest.getEmail());
            usuario.setPassword(usuarioRequest.getPassword());
            usuario.setBirthDate(usuarioRequest.getBirthDate());
            return UsuarioMapper.mapEntityToDto(usuarioRepository.updateAudit(usuario));
        }).orElseThrow(() -> new DataAccessException("Error al actualizar usuario con ID: " + uuid) {

        });
    }
}
