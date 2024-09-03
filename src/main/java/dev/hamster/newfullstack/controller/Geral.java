package dev.hamster.newfullstack.controller;

import dev.hamster.newfullstack.dto.ClienteDTO;
import dev.hamster.newfullstack.dto.EnderecoDTO;
import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.repositorio.ClienteRepositorio;
import dev.hamster.newfullstack.repositorio.EnderecoRepositorio;
import dev.hamster.newfullstack.servico.ClienteServico;
import dev.hamster.newfullstack.servico.EnderecoServico;
import dev.hamster.newfullstack.servico.TelefoneServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/geral")
@CrossOrigin("*")
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

    @GetMapping
    public ResponseEntity<?> pesquisar(@RequestParam String termo) {
        // verifica se o parametro nao está vazio:
        if (termo.isBlank() || termo.isEmpty()) {
            return ResponseEntity.badRequest().body("O parametro de consulta não pode estar vazio");
        }

        // pesquisa cliente por nome:
        List<Cliente> clientes = clienteRepositorio.buscarPorNome(termo);
        // pesquisa enderecos por rua e bairro:
        List<Endereco> enderecos = enderecoRepositorio.buscarPorRua(termo);

        Set<List<?>> resultados = new LinkedHashSet<>();
        resultados.add(clientes);
        resultados.add(enderecos);
        if (clientes.isEmpty() && enderecos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.ok().body(resultados);
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Cliente obj) {

        Cliente cliente = new Cliente();
        cliente.setNome(obj.getNome());

        ResponseEntity<?> clienteResponse = clienteServico.cadastrarCliente(cliente);
        if(!clienteResponse.getStatusCode().equals(HttpStatus.CREATED)){
            return ResponseEntity.badRequest().body("O nome do Cliente deve ser preenchido");
        }

        for (Endereco endereco : obj.getEnderecos()) {
            Endereco novoEndereco = new Endereco();
            novoEndereco.setRua(endereco.getRua());
            novoEndereco.setBairro(endereco.getBairro());
            novoEndereco.setComplemento(endereco.getComplemento());
            novoEndereco.setCliente(cliente);

            ResponseEntity<?> enderecoResponse = enderecoServico.cadastrarEndereco(novoEndereco);
            if (!enderecoResponse.getStatusCode().equals(HttpStatus.CREATED)) {
                return enderecoResponse;
            }

            cliente.adicionarEndereco(novoEndereco);
        }

        for (Telefone telefone : obj.getTelefones()) {
            Telefone novoTelefone = new Telefone();
            novoTelefone.setNumero(telefone.getNumero());
            novoTelefone.setCliente(cliente);

            ResponseEntity<?> telefoneResponse = telefoneServico.cadastrarTelefone(novoTelefone);
            if (!telefoneResponse.getStatusCode().equals(HttpStatus.CREATED)) {
                return telefoneResponse;
            }

            cliente.adicionarTelefone(novoTelefone);
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }
}