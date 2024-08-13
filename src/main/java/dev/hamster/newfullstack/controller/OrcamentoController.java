package dev.hamster.newfullstack.controller;


import dev.hamster.newfullstack.entidades.Orcamento;
import dev.hamster.newfullstack.repositorio.OrcamentoRepositorio;
import dev.hamster.newfullstack.servico.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "ordemServicos")
@CrossOrigin("*")
public class OrcamentoController {

    @Autowired
    private OrcamentoService ordemServicoService;


    @GetMapping
    public ResponseEntity<?> findAll(){
        return ordemServicoService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ordemServicoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Orcamento obj){
        return ordemServicoService.cadastrar(obj);
    }

    @PutMapping
    public ResponseEntity<?> editar(Orcamento obj){
        return ordemServicoService.editar(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        return ordemServicoService.remover(id);
    }


}
