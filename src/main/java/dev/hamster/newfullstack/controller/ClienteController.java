package dev.hamster.newfullstack.controller;

import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.servico.ClienteServico;
import dev.hamster.newfullstack.servico.EnderecoServico;
import dev.hamster.newfullstack.servico.TelefoneServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired private ClienteServico clienteServico;
    @Autowired private TelefoneServico telefoneServico;
    @Autowired private EnderecoServico enderecoServico;

//    @GetMapping()
//    public ResponseEntity<List<Cliente>> findAll(){
//        return clienteServico.buscarTodos();
//    }
    @GetMapping
    public List<Cliente> todosClientesComAtributos() {
        return clienteServico.clientesComAtributos();
    }

    @GetMapping("/{id}")
    public Cliente buscarClientePorId(@PathVariable Long id) {
        return clienteServico.buscarClienteComAtributos(id);
    }

    @PostMapping()
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente){
        return clienteServico.cadastrarCliente(cliente);
    }

    @PostMapping("/{clienteId}/telefones")
    public ResponseEntity<Cliente> adicionarTelefone(@PathVariable Long clienteId, @RequestBody Telefone telefone) {
        Cliente clienteAtualizado = clienteServico.adicionarTelefone(clienteId, telefone);
        telefone.setCliente(clienteAtualizado);
        telefoneServico.cadastrarTelefone(telefone);
        return ResponseEntity.ok(clienteAtualizado);
    }
    @PostMapping("/{clienteId}/enderecos")
    public ResponseEntity<Cliente> adicionarEndereco(@PathVariable Long clienteId, @RequestBody Endereco endereco) {
        Cliente clienteAtualizado = clienteServico.adicionarEndereco(clienteId, endereco);
        endereco.setCliente(clienteAtualizado);
        enderecoServico.cadastrarEndereco(endereco);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCliente(@RequestBody Cliente cliente){
        return clienteServico.editarCliente(cliente);
    }
}
