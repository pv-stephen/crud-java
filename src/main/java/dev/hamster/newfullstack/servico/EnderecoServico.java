package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
import dev.hamster.newfullstack.repositorio.EnderecoRepositorio;
import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnderecoServico {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;
    @Autowired
    private Mensagem mensagem;


    public ResponseEntity<List<Endereco>> buscarTodos(){
        List<Endereco> enderecos = enderecoRepositorio.buscarTodos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    public ResponseEntity<?> selecionarPeloCodigo(Long id){
        if(enderecoRepositorio.countById(id) == 0){
            mensagem.setMensagem("Endereço não encontrado.");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(enderecoRepositorio.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> cadastrarEndereco(Endereco obj){
        if(obj.getRua().isBlank() || obj.getRua().isEmpty()){
            mensagem.setMensagem("O campo Rua é obrigatório!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }if(obj.getBairro().isBlank() || obj.getBairro().isEmpty()){
            mensagem.setMensagem("O campo Bairro é obrigatório!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }if(obj.getComplemento().isBlank() || obj.getComplemento().isEmpty()) {
            mensagem.setMensagem("O campo Complemento é obrigatório!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        try{
            if (obj.getCliente() == null) {
                mensagem.setMensagem("Um Endereco deve obrigatoriamente ter um Cliente associado!");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
            }
        }catch (TransientPropertyValueException transientPropertyValueException){
            transientPropertyValueException.getMessage();
        }

        return new ResponseEntity<>(enderecoRepositorio.save(obj), HttpStatus.CREATED);

    }
    public ResponseEntity<?> editarEndereco (Endereco obj){
        if(obj.getRua().isBlank() || obj.getRua().isEmpty()){
            mensagem.setMensagem("O campo Rua é obrigatório!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }if(obj.getBairro().isBlank() || obj.getBairro().isEmpty()){
            mensagem.setMensagem("O campo Bairro é obrigatório!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }if(obj.getComplemento().isBlank() || obj.getComplemento().isEmpty()){
            mensagem.setMensagem("O campo Complemento é obrigatório!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(enderecoRepositorio.save(obj), HttpStatus.CREATED);
    }

    public ResponseEntity<?> remover(Long id){
        if(enderecoRepositorio.countById(id) == 0){
            mensagem.setMensagem("Endereço não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else enderecoRepositorio.deleteById(id);
        mensagem.setMensagem("Endereço excluído com sucesso!");
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

}
