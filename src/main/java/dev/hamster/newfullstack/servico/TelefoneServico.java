package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
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
    @Autowired
    private Mensagem mensagem;

    public ResponseEntity<List<Telefone>> buscarTodos(){
        List<Telefone> telefones = telefoneRepositorio.buscarTodos();
        return new ResponseEntity<>(telefones, HttpStatus.OK);
    }

    public ResponseEntity<?> cadastrarTelefone(Telefone obj){
        if(obj.getNumero().isBlank() || obj.getNumero().isEmpty()){
            mensagem.setMensagem("O Número é obrigatório!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }
        if (obj.getCliente() == null) {
            mensagem.setMensagem("Um telefone deve obrigatoriamente ter um Cliente associado!");
        } return new ResponseEntity<>(telefoneRepositorio.save(obj), HttpStatus.CREATED);
    }

    public ResponseEntity<?> editarTelefone(Telefone obj) {
        if (obj.getNumero().isBlank() || obj.getNumero().isEmpty()) {
            mensagem.setMensagem("O Número é obrigatório!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(telefoneRepositorio.save(obj), HttpStatus.OK);
    }

    public ResponseEntity<?> remover(Long id){
        if(telefoneRepositorio.countById(id) == 0){
            mensagem.setMensagem("Telefone não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else telefoneRepositorio.deleteById(id);
        mensagem.setMensagem("Telefone excluído com sucesso!");
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

}
