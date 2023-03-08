package com.ITA.Agil.demo.repository;

import com.ITA.Agil.demo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
