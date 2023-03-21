package com.ITA.Agil.demo.controller;

import com.ITA.Agil.demo.model.Usuario;
import com.ITA.Agil.demo.model.dtos.UsuarioDTO;
import com.ITA.Agil.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Usuario usuario) {
        var entity = usuarioService.adicionarUsuario(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping
    public ResponseEntity listarTodos() {
        return ResponseEntity.ok().body(usuarioService.listarTodosUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(usuarioService.obterUsuarioPorId(id));
    }

    @GetMapping(params = "nome")
    public ResponseEntity obterPorNomeLike(@RequestParam(value="nome") String nome) {
        return ResponseEntity.ok().body(usuarioService.obterUsuarioPorNomeLike("%" + nome + "%"));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok().body(usuarioService.atualizarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
