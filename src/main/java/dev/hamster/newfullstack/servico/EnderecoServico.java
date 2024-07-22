package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.repositorio.EnderecoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServico {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;


    public List<Endereco> buscarTodos(){
        return enderecoRepositorio.buscarTodos();
    }

    public ResponseEntity<List<Endereco>> buscarPorRua(String termo){
        List<Endereco> resultado = enderecoRepositorio.buscarPorRua(termo);
        if(resultado.isEmpty()){
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.ok().body(resultado);
    }

    public ResponseEntity<?> cadastrarEndereco(Endereco obj){
       if(obj.getRua().isEmpty() || obj.getRua().isBlank()){
           return new ResponseEntity<>("O campo Rua é obrigátorio", HttpStatus.BAD_REQUEST);
       } if (obj.getComplemento().isEmpty() || obj.getComplemento().isBlank()){
            return new ResponseEntity<>("O campo Complemento é obrigátorio", HttpStatus.BAD_REQUEST);
       }
       if(obj.getBairro().isEmpty() || obj.getBairro().isBlank()){
           return new ResponseEntity<>("O campo Bairro é obrigátorio", HttpStatus.BAD_REQUEST);
       }
       else return new ResponseEntity<>(enderecoRepositorio.save(obj), HttpStatus.CREATED);
    }

    public ResponseEntity<?> editarEndereco(Endereco obj){
        if(obj.getRua().isEmpty() || obj.getRua().isBlank()){
            return new ResponseEntity<>("O campo Rua é obrigátorio", HttpStatus.BAD_REQUEST);
        } if (obj.getComplemento().isEmpty() || obj.getComplemento().isBlank()){
            return new ResponseEntity<>("O campo Complemento é obrigátorio", HttpStatus.BAD_REQUEST);
        }
        if(obj.getBairro().isEmpty() || obj.getBairro().isBlank()){
            return new ResponseEntity<>("O campo Bairro é obrigátorio", HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(enderecoRepositorio.save(obj), HttpStatus.CREATED);
    }

    public ResponseEntity<?> excluir(Long id){
        enderecoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
