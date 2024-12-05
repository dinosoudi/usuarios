package com.trainibit.usuarios.service.impl;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.mapper.UsuarioMapper;
import com.trainibit.usuarios.mapper.UsuarioMapper2;
import com.trainibit.usuarios.repository.UsuarioRepository;
import com.trainibit.usuarios.request.UsuarioRequest;
import com.trainibit.usuarios.response.UsuarioResponse;
import com.trainibit.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import com.trainibit.usuarios.service.PlanetService;

@ComponentScan
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
            return UsuarioMapper2.mapListEntityToListDto(usuarioRepository.findByActiveTrue()) ;
        }
    }

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponse findById(UUID uuid) {
        return usuarioMapper.mapEntityToDto(usuarioRepository.findByUuidAndActiveTrue(uuid).orElseThrow(() -> new NoSuchElementException("Error al buscar usuario con ID: " + uuid){}));
    }

    // regresa error 400 bad request exception si hay datos incompletos
    public UsuarioResponse guardaUsuario(UsuarioRequest usuarioRequest){
        Usuario usuario = UsuarioMapper2.mapRequestToEntity(usuarioRequest);
        usuario.setNombrePlaneta( obtenerNombrePlanetaAleatorio());
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return UsuarioMapper2.mapEntityToDto(usuarioGuardado);
    }

    @Override
    public UsuarioResponse deleteById(UUID uuid) {
        return UsuarioMapper2.mapEntityToDto( usuarioRepository.findByUuidAndActiveTrue(uuid).map(usuario -> {
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
            return UsuarioMapper2.mapEntityToDto(usuarioRepository.updateAudit(usuario));
        }).orElseThrow(() -> new NoSuchElementException("Error al actualizar usuario con ID: " + uuid) {});
    }

    private String obtenerNombrePlanetaAleatorio() {
        int idPlaneta = (int) (Math.random() * 60) + 1;
        System.out.println(idPlaneta);
        return planetService.getPlanetById(idPlaneta).getResult().getProperties().getName();
    }
}
