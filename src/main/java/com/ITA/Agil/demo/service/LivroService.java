package com.ITA.Agil.demo.service;

import com.ITA.Agil.demo.exception.RecordNotFoundException;
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

    public Livro findById(@PathVariable("id") @NotNull @Positive Long id) {
        return livroRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id));
    }

    public Livro create(@Valid Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro update(@NotNull @Positive Long id, @Valid Livro livro) {
        return livroRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(livro.getNome());
                    recordFound.setNumeroPagina(livro.getNumeroPagina());
                    recordFound.setEstilo(livro.getEstilo());
                    return livroRepository.save(recordFound);
                }).orElseThrow(()-> new RecordNotFoundException(id));
    }
    public void delete(@PathVariable @NotNull @Positive Long id){
        livroRepository.delete(livroRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
