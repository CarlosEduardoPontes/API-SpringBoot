package com.ITA.Agil.demo.service;

import com.ITA.Agil.demo.model.Usuario;
import com.ITA.Agil.demo.model.dtos.UsuarioDTO;
import com.ITA.Agil.demo.model.dtos.UsuarioRequestDTO;
import com.ITA.Agil.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario adicionarUsuario(Usuario usuario) {
        usuario.setCreated_at(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return usuario;
    }

    public List<UsuarioDTO> listarTodosUsuarios() {
        var list = usuarioRepository.findAll();
        return list.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    public UsuarioDTO obterUsuarioPorId(Long id) {
        Usuario entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário " + id + " não encontrado."));
        return new UsuarioDTO(entity);
    }

    public List<UsuarioDTO> obterUsuarioPorNomeLike(String nome) {
        var entity = usuarioRepository.findByNomeLikeIgnoreCase(nome);
        return entity.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuario) {
        var entity = usuarioRepository.findById(id)
                .map(x -> {
                    x.setNome(usuario.getNome());
                    x.setEmail(usuario.getEmail());
                    x.setPontuacao(usuario.getPontuacao());
                    x.setTrofeu(usuario.getTrofeu());
                    x.setUpdated_at(LocalDateTime.now());
                    return usuarioRepository.save(x);})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário " + id + " não encontrado."));
        return new UsuarioDTO(entity);
    }

    public void deletarUsuario(Long id) {
        var entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário " + id + " não encontrado"));
        usuarioRepository.delete(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String[] roles = usuario.isAdmin() ?
                new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles()
                .build();
    }
}
