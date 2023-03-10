package com.ITA.Agil.demo.controller;

import com.ITA.Agil.demo.model.Usuario;
import com.ITA.Agil.demo.model.dtos.UsuarioDTO;
import com.ITA.Agil.demo.model.dtos.UsuarioRequestDTO;
import com.ITA.Agil.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioRequestDTO> salvar(@RequestBody Usuario usuario) {
        var entity = usuarioService.adicionarUsuario(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        var list = usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obterPorId(@PathVariable Long id) {
        var entity = usuarioService.obterUsuarioPorId(id);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping(params = "nome")
    public ResponseEntity<List<UsuarioDTO>> obterPorNomeLike(@RequestParam(value="nome") String nome) {
        var entity = usuarioService.obterUsuarioPorNomeLike("%" + nome + "%");
        return ResponseEntity.ok().body(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRequestDTO> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        var entity = usuarioService.atualizarUsuario(id, usuario);
        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        String msg = "Usuário " + id + " removido com sucesso.";
        return ResponseEntity.ok().body(msg);
    }
}
