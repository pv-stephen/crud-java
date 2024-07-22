package dev.hamster.newfullstack.controles;

import dev.hamster.newfullstack.dto.cadastroDTO;
import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.servico.ClienteServico;
import dev.hamster.newfullstack.servico.EnderecoServico;
import dev.hamster.newfullstack.servico.TelefoneServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geral")
public class ControleGeral {

    @Autowired
    private ClienteServico clienteServico;

    @Autowired
    private EnderecoServico enderecoServico;

    @Autowired
    private TelefoneServico telefoneServico;

//Não se aceita vários requestBody no metodo
//    @PostMapping
//    public ResponseEntity<?> cadastroCompleto(@RequestBody Cliente cliente, @RequestBody Endereco endereco, @RequestBody Telefone telefone){
//        clienteServico.cadastrarCliente(cliente);
//
//        telefone.setCliente(cliente);
//        endereco.setCliente(cliente);
//
//        telefoneServico.cadastrarTelefone(telefone);
//        enderecoServico.cadastrarEndereco(endereco);
//
//        return ResponseEntity.ok().body(cliente);
//    }

    @PostMapping
    public ResponseEntity<?> cadastroCompleto(@RequestBody cadastroDTO cadastroDTO){
        Cliente cliente = cadastroDTO.getCliente();
        clienteServico.cadastrarCliente(cliente);

        cadastroDTO.getTelefones().forEach(telefone -> {
            telefone.setCliente(cliente);
            telefoneServico.cadastrarTelefone(telefone);
        });

        cadastroDTO.getEnderecos().forEach(endereco -> {
            endereco.setCliente(cliente);
            enderecoServico.cadastrarEndereco(endereco);
        });

        return ResponseEntity.ok().body(cliente);
    }
}
