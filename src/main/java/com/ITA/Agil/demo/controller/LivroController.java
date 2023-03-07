package com.ITA.Agil.demo.controller;

import com.ITA.Agil.demo.model.Livro;
import com.ITA.Agil.demo.repository.LivroRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroRepository livroRepository;

    //Construtor usado no lugar do @Autowired
    public LivroController(LivroRepository livroRepository) {

        this.livroRepository = livroRepository;
    }

    @GetMapping
    public List<Livro> list() {
        return livroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable("id") Long id) {
        return livroRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElseThrow(( )->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "**************** ID NÃO ENCONTRADO"));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Livro create(@RequestBody Livro course) {
        return livroRepository.save(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable("id") Long id, @RequestBody Livro livro) {
        return livroRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(livro.getNome());
                    recordFound.setNumeroPagina(livro.getNumeroPagina());
                    recordFound.setEstilo(livro.getEstilo());
                    Livro updated = livroRepository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return livroRepository.findById(id)
                .map(recordFound -> {
                    livroRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/api/livros")
    public ResponseEntity find( Livro filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Livro> lista = livroRepository.findAll(example);
        return ResponseEntity.ok(lista);
    }
}
