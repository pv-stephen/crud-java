package dev.hamster.newfullstack.controles;

import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.servico.EnderecoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControle {

    @Autowired
    private EnderecoServico enderecoServico;



    @GetMapping()
    public List<Endereco> listarTodos(){
        return enderecoServico.buscarTodos();
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Endereco>> buscarPorRua(@RequestParam String termo) {
        return enderecoServico.buscarPorRua(termo);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarEndereco(@RequestBody Endereco endereco){
        return enderecoServico.cadastrarEndereco(endereco);
    }

    @PutMapping
    public ResponseEntity<?> editarEndereco(@RequestBody Endereco endereco){
        return enderecoServico.cadastrarEndereco(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTelefone(@PathVariable Long id){
        return enderecoServico.excluir(id);
    }

}
