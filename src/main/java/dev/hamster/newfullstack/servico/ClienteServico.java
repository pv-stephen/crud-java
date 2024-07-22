package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Cliente;
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


    public List<Cliente> buscarTodos(){
        return clienteRepositorio.buscarTodos();
    }

//    public ResponseEntity<List<Cliente>> buscarPorNome(String nome){
//        List<Cliente> resultado = clienteRepositorio.buscarPorNome(nome);
//        if(resultado.isEmpty()){
//            return ResponseEntity.noContent().build();
//        } else return ResponseEntity.ok().body(resultado);
//    }

    public List<Cliente> buscarPorNome(String nome){
        List<Cliente> resultado = clienteRepositorio.buscarPorNome(nome);
        return resultado;
    }

    public ResponseEntity<?> cadastrarCliente(Cliente obj){
       if(obj.getNome().isEmpty() || obj.getNome().isBlank()){
           return new ResponseEntity<>("O nome do Cliente é obrigatório", HttpStatus.BAD_REQUEST);
       } else return new ResponseEntity<>(clienteRepositorio.save(obj), HttpStatus.CREATED);
    }

    public ResponseEntity<?> editar(Cliente cliente){
        if(cliente.getNome().isEmpty() || cliente.getNome().isBlank()){
            return new ResponseEntity<>("O nome do Cliente é obrigatório", HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<Cliente>(clienteRepositorio.save(cliente), HttpStatus.OK);
        }
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }
}
