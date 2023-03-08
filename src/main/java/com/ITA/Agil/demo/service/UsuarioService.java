package com.ITA.Agil.demo.service;

import com.ITA.Agil.demo.model.Usuario;
import com.ITA.Agil.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario adicionarUsuario(Usuario usuario) {
        usuario.setCreated_at(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obterUsuarioPorId(Long id) {
        Optional<Usuario> entity = usuarioRepository.findById(id);
        return entity.get();
    }

    public Usuario atualizarUsuario(Long id, Usuario entity) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        updateData(entity, usuario);
        return usuarioRepository.save(usuario);
    }

    private void updateData(Usuario entity, Usuario usuario) {
        usuario.setNome(entity.getNome());
        usuario.setEmail(entity.getEmail());
        usuario.setSenha(entity.getSenha());
        usuario.setPontuacao(entity.getPontuacao());
        usuario.setTrofeu(entity.getTrofeu());
        usuario.setUpdated_at(LocalDateTime.now());
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}
