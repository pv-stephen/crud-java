package dev.hamster.newfullstack.controller;


import dev.hamster.newfullstack.entidades.Itens;
import dev.hamster.newfullstack.entidades.Orcamento;
import dev.hamster.newfullstack.repositorio.OrcamentoRepositorio;
import dev.hamster.newfullstack.servico.ItensService;
import dev.hamster.newfullstack.servico.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "orcamentos")
@CrossOrigin("*")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;
    @Autowired private ItensService itensService;


    @GetMapping
    public ResponseEntity<?> findAll(){
        return orcamentoService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return orcamentoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Orcamento obj){
        return orcamentoService.cadastrar(obj);
    }
    
    @PutMapping
    public ResponseEntity<?> editar(Orcamento obj){
        return orcamentoService.editar(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        return orcamentoService.remover(id);
    }


}
