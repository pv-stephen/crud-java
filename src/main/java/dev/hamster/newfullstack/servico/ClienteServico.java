package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.excecao.ExcecaoCampoObrigatorio;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
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
        if(clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.ok(clientes);
    }

    public ResponseEntity<List<Cliente>> clientesComAtributos() {
        List<Cliente> clientes = clienteRepositorio.buscarClientesComAtributos();
        if(clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.ok(clientes);
    }

    public ResponseEntity<?> cadastrarCliente(Cliente obj){
        if(obj.getNome().isEmpty() || obj.getNome().isBlank()){
            throw new ExcecaoCampoObrigatorio("O NOME do Cliente é obrigatório!");
        } else return new ResponseEntity<>(clienteRepositorio.save(obj), HttpStatus.CREATED);
    }

    public ResponseEntity<?> editarCliente(Cliente obj){
        if(obj.getNome().isEmpty() || obj.getNome().isBlank()){
            throw new ExcecaoCampoObrigatorio("O NOME do Cliente é obrigatório!");
        } else return new ResponseEntity<>(clienteRepositorio.save(obj), HttpStatus.OK);
    }

}