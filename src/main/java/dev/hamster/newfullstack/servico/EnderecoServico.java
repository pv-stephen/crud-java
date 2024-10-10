package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.entidades.excecao.ExcecaoCampoObrigatorio;
import dev.hamster.newfullstack.entidades.excecao.GlobalExceptionHandler;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
import dev.hamster.newfullstack.entidades.excecao.ResourceNotFoundException;
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

    public ResponseEntity<?> cadastrarEndereco(Endereco obj){
        if(obj.getRua().isEmpty() || obj.getRua().isBlank()){
            throw new ExcecaoCampoObrigatorio("O campo Rua é obrigatório!");
        }
        if (obj.getBairro().isBlank() || obj.getBairro().isEmpty()) {
            throw new ExcecaoCampoObrigatorio("O campo Bairro é obrigatório!");
        }
        if (obj.getComplemento().isBlank() || obj.getComplemento().isEmpty()) {
            throw new ExcecaoCampoObrigatorio("O campo Complemento é obrigatório!");
        }
        else {
            enderecoRepositorio.save(obj);
            return new ResponseEntity<>("Endereço cadastrado com sucesso!", HttpStatus.CREATED);
        }


    }
    public ResponseEntity<?> editarEndereco(Endereco obj){
        if(obj.getRua().isEmpty() || obj.getRua().isBlank()){
            throw new ExcecaoCampoObrigatorio("O campo Rua é obrigatório!");
        }
        if (obj.getBairro().isBlank() || obj.getBairro().isEmpty()) {
            throw new ExcecaoCampoObrigatorio("O campo Bairro é obrigatório!");
        }
        if (obj.getComplemento().isBlank() || obj.getComplemento().isEmpty()) {
            throw new ExcecaoCampoObrigatorio("O campo Complemento é obrigatório!");
        }
        else {
            enderecoRepositorio.save(obj);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

//    public ResponseEntity<?> remover(Long id){
//        if(enderecoRepositorio.countByID(id) == 0){
//            mensagem.setMensagem("Endereço não encontrado");
//            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
//        } else enderecoRepositorio.deleteById(id);
//        mensagem.setMensagem("Endereço excluído com sucesso!");
//        return new ResponseEntity<>(mensagem, HttpStatus.OK);
//    }

    public ResponseEntity<?> remover(Long id){
        if(enderecoRepositorio.countByID(id) == 0){
            throw new ResourceNotFoundException("Objeto não encontrado. ID = " + id);
        }
        enderecoRepositorio.deleteById(id);
        return new ResponseEntity<>("Endereço deletado com sucesso!",HttpStatus.OK);
    }

}
