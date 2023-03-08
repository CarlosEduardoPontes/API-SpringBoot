package com.ITA.Agil.demo.repository;

import com.ITA.Agil.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNomeLikeIgnoreCase(String nome);
}
