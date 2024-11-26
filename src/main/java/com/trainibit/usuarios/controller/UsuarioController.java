package com.trainibit.usuarios.controller;

import com.trainibit.usuarios.mapper.UsuarioMapper;
import com.trainibit.usuarios.request.UsuarioRequest;
import com.trainibit.usuarios.response.UsuarioResponse;
import com.trainibit.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.trainibit.usuarios.entity.Usuario;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity< List<UsuarioResponse> > getUsuarios(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    // Crear o subir usuario
    @PostMapping
    public ResponseEntity<UsuarioResponse> postUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){

        return ResponseEntity.ok( usuarioService.guardaUsuario(usuarioRequest) );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable UUID uuid){
        return ResponseEntity.ok(usuarioService.findById(uuid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponse> deleteUsuarioById(@PathVariable Long id){
        // debe cambiar la bandera de visible en usuarioServiceimplement
        UsuarioResponse usuarioResponse = usuarioService.deleteById(id);
        return ResponseEntity.ok(usuarioResponse);
    }

    // Update o actualiza
    @PutMapping("/{id}")
    public ResponseEntity< UsuarioResponse > putUsuario(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest){
        // boolean editado = usuarioService.putById(id, usuarioRequest );
        // debe actualizar el usuario con el mismo ID

        UsuarioResponse usuarioResponse = usuarioService.putById(id, usuarioRequest);
        return ResponseEntity.ok(usuarioResponse);

    }

}
