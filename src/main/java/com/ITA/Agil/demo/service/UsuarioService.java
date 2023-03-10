package com.ITA.Agil.demo.service;

import com.ITA.Agil.demo.model.Usuario;
import com.ITA.Agil.demo.model.dtos.UsuarioDTO;
import com.ITA.Agil.demo.model.dtos.UsuarioRequestDTO;
import com.ITA.Agil.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioRequestDTO adicionarUsuario(UsuarioRequestDTO dto) {
        var entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setPontuacao(0);
        entity.setTrofeu(false);
        entity.setCreated_at(LocalDateTime.now());
        return new UsuarioRequestDTO(usuarioRepository.save(entity));
    }

    public List<UsuarioDTO> listarTodosUsuarios() {
        var list = usuarioRepository.findAll();
        var dto = list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
        return dto;
    }

    public UsuarioDTO obterUsuarioPorId(Long id) {
        Usuario entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário " + id + " não encontrado."));
        return new UsuarioDTO(entity);
    }

    public List<UsuarioDTO> obterUsuarioPorNomeLike(String nome) {
        var entity = usuarioRepository.findByNomeLikeIgnoreCase(nome);
        var dto = entity.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
        return dto;
    }

    public UsuarioRequestDTO atualizarUsuario(Long id, Usuario usuario) {
        var entity = usuarioRepository.findById(id).map(x -> {
            x.setNome(usuario.getNome());
            x.setEmail(usuario.getEmail());
            x.setSenha(usuario.getSenha());
            x.setPontuacao(usuario.getPontuacao());
            x.setTrofeu(usuario.getTrofeu());
            x.setUpdated_at(LocalDateTime.now());
            return usuarioRepository.save(x);})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário " + id + " não encontrado."));
        return new UsuarioRequestDTO(entity);
    }

    public void deletarUsuario(Long id) {
        var entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário " + id + " não encontrado"));
        usuarioRepository.delete(entity);
    }

}
