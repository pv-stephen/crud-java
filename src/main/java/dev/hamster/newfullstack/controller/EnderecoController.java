package dev.hamster.newfullstack.controller;

import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.servico.EnderecoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("enderecos")
@CrossOrigin("*")
public class EnderecoController {


    @Autowired private EnderecoServico enderecoServico;


    @GetMapping()
    public ResponseEntity<List<Endereco>> findAll(){
        return enderecoServico.buscarTodos();
    }

    @PostMapping()
    public ResponseEntity<?> cadastrarEndereco(@RequestBody Endereco endereco){
        return enderecoServico.cadastrarEndereco(endereco);
    }
    
    @PutMapping()
    public ResponseEntity<?> editarEndereco(@RequestBody Endereco endereco){
        return enderecoServico.editarEndereco(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id){
        return enderecoServico.remover(id);
    }


}
