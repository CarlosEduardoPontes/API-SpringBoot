package com.ITA.Agil.demo.model.dtos;

import com.ITA.Agil.demo.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    @Column(length = 100, unique = true)
    private String email;
    private Integer pontuacao;
    private Boolean trofeu;

    public UsuarioDTO(Usuario usuario) {
        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();
        pontuacao = usuario.getPontuacao();
        trofeu = usuario.getTrofeu();
    }

    public Usuario fromDTO() {
        return new Usuario(id, nome, email, pontuacao, trofeu);
    }

}
