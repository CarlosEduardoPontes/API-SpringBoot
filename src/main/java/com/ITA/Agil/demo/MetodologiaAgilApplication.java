package com.ITA.Agil.demo;

import com.ITA.Agil.demo.model.Livro;
import com.ITA.Agil.demo.model.Usuario;
import com.ITA.Agil.demo.repository.LivroRepository;
import com.ITA.Agil.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class MetodologiaAgilApplication {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(MetodologiaAgilApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(LivroRepository livroRepository) {
		return args -> {
			livroRepository.deleteAll();

			Livro c = new Livro();
			c.setNome("Curso de Spring");
			c.setNumeroPagina(200);
			c.setEstilo("Programacao");
			c.setStatus("Ativo");
			livroRepository.save(c);

			Usuario u = new Usuario(
					1L,
					"Zé",
					"zezinhopatriota@mito.com",
					"bozo2022",
					5,
					true,
					LocalDateTime.now());

			usuarioRepository.save(u);
		};
	}
}
