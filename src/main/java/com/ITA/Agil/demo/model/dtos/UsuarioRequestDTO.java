package com.ITA.Agil.demo.model.dtos;

import com.ITA.Agil.demo.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    private String nome;
    @Column(length = 100, unique = true)
    private String email;
    @Length(min = 8, max = 12)
    private String senha;

    public UsuarioRequestDTO(Usuario usuario) {
        nome = usuario.getNome();
        email = usuario.getEmail();
        senha = usuario.getSenha();
    }

}
