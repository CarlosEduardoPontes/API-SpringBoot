package com.ITA.Agil.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id=?")
@Where(clause = "status = 'Ativo'")
public class Livro {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long Id;

    @NotBlank
    @NotNull
    @Length(min=2, max = 200)
    @Column(length = 200, nullable = false)
    private String nome;

    @NotBlank
    @NotNull
    @Length(min=2, max = 100)
    @Column(length = 100, nullable = false)
    private String estilo;

    private int numeroPagina;

    @NotNull
    @Length(max = 20)
    @Column(length = 20, nullable = false)
    private String status = "Ativo";
}
