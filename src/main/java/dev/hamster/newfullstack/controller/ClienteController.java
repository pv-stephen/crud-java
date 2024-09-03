package dev.hamster.newfullstack.controller;

import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.servico.ClienteServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired private ClienteServico clienteServico;

    @GetMapping
    public List<Cliente> todosClientesComAtributos() {
        return clienteServico.clientesComAtributos();
    }

    @PostMapping()
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente){
        return clienteServico.cadastrarCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCliente(@RequestBody Cliente cliente){
        return clienteServico.editarCliente(cliente);
    }
}
