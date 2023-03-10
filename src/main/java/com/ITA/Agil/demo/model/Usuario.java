package com.ITA.Agil.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(length = 100)
    private String nome;

    @Column(length = 100, unique = true)
    private String email;

    @Length(min = 8, max = 12)
    private String senha;

    private Integer pontuacao;

    private Boolean trofeu;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime created_at;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updated_at;

    public Usuario(Long id, String nome, String email, String senha, Integer pontuacao, Boolean trofeu, LocalDateTime created_at) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pontuacao = pontuacao;
        this.trofeu = trofeu;
        this.created_at = created_at;
    }
}
