package com.ITA.Agil.demo.service;

import com.ITA.Agil.demo.model.Livro;
import com.ITA.Agil.demo.repository.LivroRepository;
import com.sun.istack.NotNull;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class LivroService {
    public final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    public List<Livro> list() {
        return livroRepository.findAll();
    }
    public Optional<Livro> findById(@PathVariable("id") @NotNull @Positive Long id) {
        return livroRepository.findById(id);
    }
    public Livro create(@Valid Livro livro) {
        return livroRepository.save(livro);
    }
    public Optional<Livro> update(@NotNull @Positive Long id, @Valid Livro livro) {
        return livroRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(livro.getNome());
                    recordFound.setNumeroPagina(livro.getNumeroPagina());
                    recordFound.setEstilo(livro.getEstilo());
                    Livro updated = livroRepository.save(recordFound);
                    return livroRepository.save(recordFound);
                });
    }
    public boolean delete(@PathVariable @NotNull @Positive Long id){
        return livroRepository.findById(id)
                .map(recordFound -> {
                    livroRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
