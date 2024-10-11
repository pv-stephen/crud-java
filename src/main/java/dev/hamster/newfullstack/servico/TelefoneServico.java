package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.entidades.excecao.ExcecaoCampoObrigatorio;
import dev.hamster.newfullstack.entidades.excecao.ResourceNotFoundException;
import dev.hamster.newfullstack.entidades.excecao.ViolacaoIntegridadeBD;
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

    public ResponseEntity<List<Telefone>> buscarTodos(){
        List<Telefone> telefones = telefoneRepositorio.buscarTodos();
        return new ResponseEntity<>(telefones, HttpStatus.OK);
    }

    public ResponseEntity<?> cadastrarTelefone(Telefone obj){
        if(obj.getNumero().isBlank() || obj.getNumero().isEmpty()){
            throw new ExcecaoCampoObrigatorio("O número é obrigatório!");
        }
        if (obj.getCliente() == null) {
            throw new ViolacaoIntegridadeBD("Um telefone deve ter um cliente associado!");
        }
        return new ResponseEntity<>(telefoneRepositorio.save(obj), HttpStatus.CREATED);
    }

    public ResponseEntity<?> editarTelefone(Telefone obj) {
        if(obj.getNumero().isBlank() || obj.getNumero().isEmpty()){
            throw new ExcecaoCampoObrigatorio("O número é obrigatório!");
        }
        return new ResponseEntity<>(telefoneRepositorio.save(obj), HttpStatus.OK);
    }

    public ResponseEntity<?> remover(Long id){
        if(telefoneRepositorio.countByID(id) == 0){
            throw new ResourceNotFoundException("Objeto não encontrado. ID = " + id);
        }
        telefoneRepositorio.deleteById(id);
        return new ResponseEntity<>("Endereço deletado com sucesso!",HttpStatus.OK);
    }

}
