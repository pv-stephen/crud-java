package dev.hamster.newfullstack.controles;


import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.servico.TelefoneServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefones")
public class TelefoneControle {

    @Autowired
    private TelefoneServico telefoneServico;



    @GetMapping()
    public List<Telefone> listarTodos(){
        return telefoneServico.buscarTodos();
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Telefone>> buscarPorNumero(@RequestParam String termo) {
        return telefoneServico.buscarPorNumero(termo);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarTelefone(@RequestBody Telefone telefone){
        return telefoneServico.cadastrarTelefone(telefone);
    }

    @PutMapping
    public ResponseEntity<?> editarTelefone(@RequestBody Telefone telefone){
        return telefoneServico.cadastrarTelefone(telefone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTelefone(@PathVariable Long id){
        return telefoneServico.excluir(id);
    }

}
