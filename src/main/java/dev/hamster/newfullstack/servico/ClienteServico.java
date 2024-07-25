package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
import dev.hamster.newfullstack.entidades.excecao.ResourceNotFoundException;
import dev.hamster.newfullstack.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServico {


    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private Mensagem mensagem;

    public ResponseEntity<List<Cliente>> buscarTodos(){
        List<Cliente> clientes = clienteRepositorio.buscarTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    public ResponseEntity<?> cadastrarCliente(Cliente obj){
        if(obj.getNome().isBlank() || obj.getNome().isEmpty()){
            mensagem.setMensagem("O Nome do cliente é obrigatório!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clienteRepositorio.save(obj), HttpStatus.CREATED);
    }
    public ResponseEntity<?> editarCliente (Cliente obj){
        if(obj.getNome().isBlank() || obj.getNome().isEmpty()){
            mensagem.setMensagem("O Nome do cliente é obrigatório!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(clienteRepositorio.save(obj), HttpStatus.OK);
    }

    public Cliente adicionarTelefone(Long clienteId, Telefone telefone) {
        Cliente cliente = clienteRepositorio.getReferenceById(clienteId);
                //.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + clienteId));
        cliente.adicionarTelefone(telefone);
        return clienteRepositorio.save(cliente);
    }

    public Cliente adicionarEndereco(Long clienteId, Endereco endereco) {
        Cliente cliente = clienteRepositorio.getReferenceById(clienteId);
                //.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + clienteId));
        cliente.adicionarEndereco(endereco);
        return clienteRepositorio.save(cliente);
    }
}
