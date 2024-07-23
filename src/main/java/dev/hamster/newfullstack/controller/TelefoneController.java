package dev.hamster.newfullstack.controller;

import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.servico.TelefoneServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("telefones")
public class TelefoneController {


    @Autowired private TelefoneServico telefoneServico;


    @GetMapping()
    public ResponseEntity<List<Telefone>> findAll(){
        return telefoneServico.buscarTodos();
    }

    @PostMapping()
    public ResponseEntity<?> cadastrarTelefone(@RequestBody Telefone telefone){
        return telefoneServico.cadastrarTelefone(telefone);
    }
    
    @PutMapping()
    public ResponseEntity<?> editarTelefone(@RequestBody Telefone telefone){
        return telefoneServico.editarTelefone(telefone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id){
        return telefoneServico.remover(id);
    }
}
