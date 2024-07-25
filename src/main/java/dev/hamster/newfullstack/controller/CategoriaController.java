package dev.hamster.newfullstack.controller;

import dev.hamster.newfullstack.entidades.Categoria;
import dev.hamster.newfullstack.repositorio.CategoriaRepositorio;
import dev.hamster.newfullstack.servico.CategoriaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private CategoriaServico categoriaService;


    @GetMapping
    public List<Categoria> findAll(){
        return categoriaRepositorio.findAll();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Categoria obj){
        return categoriaService.cadastrar(obj);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody Categoria obj){ return categoriaService.editar(obj);}

}
