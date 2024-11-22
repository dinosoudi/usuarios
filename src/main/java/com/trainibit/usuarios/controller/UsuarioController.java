package com.trainibit.usuarios.controller;

import com.trainibit.usuarios.mapper.UsuarioMapper;
import com.trainibit.usuarios.response.UsuarioResponse;
import com.trainibit.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.trainibit.usuarios.entity.Usuario;

import java.util.List;

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

    @PostMapping
    /*public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
        usuarioService.guardaUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }*/

    public ResponseEntity<UsuarioResponse> postUsuario(@Valid @RequestBody Usuario usuario){
        usuarioService.guardaUsuario(usuario);
        return ResponseEntity.ok(UsuarioMapper.mapEntityToDto(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuarioById(@PathVariable Long id){
        boolean borrado = usuarioService.deleteById(id);
        if(borrado){
            return ResponseEntity.ok("Usuario borrado correctamnte");
        }else {
            return ResponseEntity.ok("Error, Usuario no borrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        boolean editado = usuarioService.putById(id, usuario);
        if(editado){
            return ResponseEntity.ok("Usuario editado correctamnte");
        }else
            return ResponseEntity.ok("Error, Usuario no editado");
    }

}
