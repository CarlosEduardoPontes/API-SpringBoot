package com.ITA.Agil.demo.model.dtos;

import com.ITA.Agil.demo.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String nome;
    private String email;
    private Integer pontuacao;
    private Boolean trofeu;

    public UsuarioDTO(Usuario usuario) {
        nome = usuario.getNome();
        email = usuario.getEmail();
        pontuacao = usuario.getPontuacao();
        trofeu = usuario.getTrofeu();
    }

}
