package com.ITA.Agil.demo;

import com.ITA.Agil.demo.model.Livro;
import com.ITA.Agil.demo.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MetodologiaAgilApplication {

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

			livroRepository.save(c);
		};
	}
}
