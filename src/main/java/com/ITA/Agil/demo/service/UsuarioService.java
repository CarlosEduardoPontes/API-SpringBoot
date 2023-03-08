package com.ITA.Agil.demo.service;

import com.ITA.Agil.demo.model.Usuario;
import com.ITA.Agil.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return entity.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário " + id + " não encontrado."));
    }

    public List<Usuario> obterUsuarioPorNomeLike(String nome) {
        return usuarioRepository.findByNomeLikeIgnoreCase(nome);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        try {
            Usuario entity = usuarioRepository.getReferenceById(id);
            updateData(entity, usuario);
            return usuarioRepository.save(entity);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Usuário " + id + " não encontrado");
        }
    }

    private void updateData(Usuario entity, Usuario usuario) {
        entity.setNome(usuario.getNome());
        entity.setEmail(usuario.getEmail());
        entity.setSenha(usuario.getSenha());
        entity.setPontuacao(usuario.getPontuacao());
        entity.setTrofeu(usuario.getTrofeu());
        entity.setUpdated_at(LocalDateTime.now());
    }

    public void deletarUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Usuário " + id + " não encontrado");
        }
    }

}
