package com.ITA.Agil.demo.controller;

import com.ITA.Agil.demo.model.Usuario;
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
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        usuarioService.adicionarUsuario(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        var list = usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterPorId(@PathVariable Long id) {
        Usuario entity = usuarioService.obterUsuarioPorId(id);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping(params = "nome")
    public ResponseEntity<List<Usuario>> obterPorNomeLike(@RequestParam(value="nome") String nome) {
        List<Usuario> entity = usuarioService.obterUsuarioPorNomeLike("%" + nome + "%");
        return ResponseEntity.ok().body(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario = usuarioService.atualizarUsuario(id, usuario);
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        String msg = "Usuário " + id + " removido com sucesso.";
        return ResponseEntity.ok().body(msg);
    }
}
