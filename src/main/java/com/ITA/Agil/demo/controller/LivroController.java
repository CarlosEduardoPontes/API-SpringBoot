package com.ITA.Agil.demo.controller;

import com.ITA.Agil.demo.model.Livro;
import com.ITA.Agil.demo.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
@Validated
@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService livroService;

    //Injeção de Dependencia atraves do construtor, usado no lugar do @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public @ResponseBody List<Livro> list() {
        return livroService.list();
    }

    @GetMapping("/{id}")
    public Livro findById(@PathVariable("id") @NotNull @Positive Long id) {
        return livroService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Livro create(@RequestBody @Valid Livro livro) {
        return livroService.create(livro);
    }

    @PutMapping("/{id}")
    public Livro update(@PathVariable("id") @NotNull @Positive Long id,
                                        @RequestBody @Valid Livro livro) {
        return livroService.update(id, livro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        livroService.delete(id);
    }
}
