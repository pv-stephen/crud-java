package dev.hamster.newfullstack.controles;


import dev.hamster.newfullstack.dto.ClienteDTO;
import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.servico.ClienteServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteControle {

    @Autowired
    private ClienteServico clienteServico;


    @GetMapping()
    public List<ClienteDTO> listarTodos(){
        List<Cliente> clientes = clienteServico.buscarTodos();

        //Pode ser feito dessas maneiras:
        //TODO List<ClienteDTO> clientesDTO = clientes.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
        //TODO return clientes.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
        return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/buscarPorNome")
    public List<ClienteDTO> buscarPorNome(@RequestParam String nome) {
        List<Cliente> clientes = clienteServico.buscarPorNome(nome);
        return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente){
        return clienteServico.cadastrarCliente(cliente);
    }

    @PutMapping
    public ResponseEntity<?> editarCliente(@RequestBody Cliente cliente){
        return clienteServico.cadastrarCliente(cliente);
    }
}
