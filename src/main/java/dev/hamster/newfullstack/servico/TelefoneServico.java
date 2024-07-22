package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.repositorio.TelefoneRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefoneServico {

    @Autowired
    private TelefoneRepositorio telefoneRepositorio;


    public List<Telefone> buscarTodos(){
        return telefoneRepositorio.buscarTodos();
    }

    public ResponseEntity<List<Telefone>> buscarPorNumero(String termo){
        List<Telefone> resultado = telefoneRepositorio.buscarPorNumero(termo);
        if(resultado.isEmpty()){
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.ok().body(resultado);
    }

    public ResponseEntity<?> cadastrarTelefone(Telefone obj){
       if(obj.getNumero().isEmpty() || obj.getNumero().isBlank()){
           return new ResponseEntity<>("O numero do Telefone é obrigatório", HttpStatus.BAD_REQUEST);
       } else return new ResponseEntity<>(telefoneRepositorio.save(obj), HttpStatus.CREATED);
    }

    public ResponseEntity<?> editar(Telefone celefone){
        if(celefone.getNumero().isEmpty() || celefone.getNumero().isBlank()){
            return new ResponseEntity<>("O numero do Telefone é obrigatório", HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<Telefone>(telefoneRepositorio.save(celefone), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> excluir(Long id){
        telefoneRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
