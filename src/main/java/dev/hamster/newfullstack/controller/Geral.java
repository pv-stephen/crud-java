package dev.hamster.newfullstack.controller;

import dev.hamster.newfullstack.dto.ClienteDTO;
import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
import dev.hamster.newfullstack.repositorio.ClienteRepositorio;
import dev.hamster.newfullstack.repositorio.EnderecoRepositorio;
import dev.hamster.newfullstack.servico.ClienteServico;
import dev.hamster.newfullstack.servico.EnderecoServico;
import dev.hamster.newfullstack.servico.TelefoneServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/geral")
public class Geral {

    @Autowired
    private ClienteServico clienteServico;
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private EnderecoServico enderecoServico;
    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private TelefoneServico telefoneServico;
    @Autowired
    private Mensagem mensagem;


    @PostMapping()
    public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();

        cliente.setNome(clienteDTO.getNome());
        clienteServico.cadastrarCliente(cliente);

        List<Endereco> enderecos = clienteDTO.getEnderecos().stream().map(dto -> {
            Endereco endereco = new Endereco();
            endereco.setRua(dto.getRua());
            endereco.setBairro(dto.getBairro());
            endereco.setComplemento(dto.getComplemento());
            endereco.setCliente(cliente);
            enderecoServico.cadastrarEndereco(endereco);
            return endereco;
        }).toList();

        List<Telefone> telefones = clienteDTO.getTelefones().stream().map(dto -> {
            Telefone telefone = new Telefone();
            telefone.setNumero(dto.getNumero());
            telefone.setCliente(cliente);
            telefoneServico.cadastrarTelefone(telefone);
            return telefone;
        }).toList();

        cliente.setEnderecos(enderecos);
        cliente.setTelefones(telefones);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<?> pesquisar(@RequestParam String termo){
        // verifica se o parametro nao está vazio:
        if(termo.isBlank() || termo.isEmpty()){
            return ResponseEntity.badRequest().body("O parametro de consulta não pode estar vazio");
        }

        // pesquisa cliente por nome:
        List<Cliente> clientes = clienteRepositorio.buscarPorNome(termo);
        // pesquisa enderecos por rua e bairro:
        List<Endereco> enderecos = enderecoRepositorio.buscarPorRua(termo);

        Set<List<?>> resultados = new LinkedHashSet<>();
        resultados.add(clientes);
        resultados.add(enderecos);
        return ResponseEntity.ok().body(resultados);

    }
}
