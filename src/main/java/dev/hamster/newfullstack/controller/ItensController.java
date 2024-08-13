package dev.hamster.newfullstack.controller;

import dev.hamster.newfullstack.entidades.Itens;
import dev.hamster.newfullstack.repositorio.ItensRepositorio;
import dev.hamster.newfullstack.servico.ItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "itens")
@CrossOrigin("*")
public class ItensController {

    @Autowired
    private ItensRepositorio itensRepositorio;

    @Autowired
    private ItensService itensService;


    @GetMapping
    public List<Itens> findAll(){
        return itensRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return itensService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Itens obj){
        return itensService.cadastrar(obj);
    }

    @PutMapping
    public ResponseEntity<?> editar(Itens obj){
        return itensService.editar(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        return itensService.remover(id);
    }


}
