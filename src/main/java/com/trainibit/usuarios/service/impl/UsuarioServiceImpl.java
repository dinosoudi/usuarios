package com.trainibit.usuarios.service.impl;

import com.trainibit.usuarios.mapper.UsuarioMapper;
import com.trainibit.usuarios.repository.UsuarioRepository;
import com.trainibit.usuarios.request.UsuarioRequest;
import com.trainibit.usuarios.response.UsuarioResponse;
import com.trainibit.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import com.trainibit.usuarios.service.PlanetService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlanetService planetService;

    @Override
    public List<UsuarioResponse> findAll() {
        if (usuarioRepository.findByActiveTrue().isEmpty()) {
            throw new NoSuchElementException("Error, No se encontro usuarios active=true");
        }else{
            return UsuarioMapper.mapListEntityToListDto(usuarioRepository.findByActiveTrue()) ;
        }
    }

    @Override
    public UsuarioResponse findById(UUID uuid) {
        return UsuarioMapper.mapEntityToDto(usuarioRepository.findByUuidAndActiveTrue(uuid).orElseThrow(() -> new NoSuchElementException("Error al buscar usuario con ID: " + uuid){}));
    }

    // regresa error 400 bad request exception si hay datos incompletos
    public UsuarioResponse guardaUsuario(UsuarioRequest usuarioRequest){
        return UsuarioMapper.mapEntityToDto(usuarioRepository.save(UsuarioMapper.mapRequestToEntity(usuarioRequest)));
    }

    @Override
    public UsuarioResponse deleteById(UUID uuid) {
        return UsuarioMapper.mapEntityToDto( usuarioRepository.findByUuidAndActiveTrue(uuid).map(usuario -> {
            usuarioRepository.deleteByIdActive(uuid);
            usuarioRepository.updateAudit(usuario);
            return usuario;
        }).orElseThrow(() -> new NoSuchElementException("Error al eliminar usuario con uuid: " + uuid) {}));
    }

    public UsuarioResponse putById(UUID uuid, UsuarioRequest usuarioRequest) {
        return usuarioRepository.findByUuidAndActiveTrue(uuid).map(usuario -> {
            usuario.setNombrePlaneta( obtenerNombrePlanetaAleatorio());

            usuario.setName(usuarioRequest.getName());
            usuario.setLastName(usuarioRequest.getLastName());
            usuario.setEmail(usuarioRequest.getEmail());
            usuario.setPassword(usuarioRequest.getPassword());
            usuario.setBirthDate(usuarioRequest.getBirthDate());
            return UsuarioMapper.mapEntityToDto(usuarioRepository.updateAudit(usuario));
        }).orElseThrow(() -> new NoSuchElementException("Error al actualizar usuario con ID: " + uuid) {});
    }


    private String obtenerNombrePlanetaAleatorio() {
        int idPlaneta = (int) (Math.random() * 50) + 1;
        return planetService.getPlanetById(idPlaneta).getResult().getProperties().getName();
    }
}
