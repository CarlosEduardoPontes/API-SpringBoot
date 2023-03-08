package com.ITA.Agil.demo.model;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Livro {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id;

    public String nome;

    public String estilo;

    public int numeroPagina;
}
