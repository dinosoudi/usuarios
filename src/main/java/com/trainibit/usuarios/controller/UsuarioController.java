package com.trainibit.usuarios.controller;

import com.trainibit.usuarios.request.UsuarioRequest;
import com.trainibit.usuarios.response.UsuarioResponse;
import com.trainibit.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UsuarioResponse> postUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {

        return ResponseEntity.ok( usuarioService.guardaUsuario(usuarioRequest) );
    }

    @GetMapping("/{uuid}")
    public UsuarioResponse getUsuarioById(@PathVariable UUID uuid){
        return usuarioService.findById(uuid);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<UsuarioResponse> deleteUsuarioById(@PathVariable UUID uuid){
        return ResponseEntity.ok(usuarioService.deleteById(uuid));
    }

    // Update o actualiza
    @PutMapping("/{uuid}")
    public ResponseEntity< UsuarioResponse > putUsuario(@PathVariable UUID uuid, @RequestBody UsuarioRequest usuarioRequest){
        return ResponseEntity.ok(usuarioService.putById(uuid, usuarioRequest));
    }

}
