package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Itens;
import dev.hamster.newfullstack.entidades.Orcamento;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
import dev.hamster.newfullstack.repositorio.OrcamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private OrcamentoRepositorio orcamentoRepositorio;


    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(orcamentoRepositorio.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> findById(Long id){
        if (orcamentoRepositorio.countByID(id) == 0) {
            mensagem.setMensagem("Não foi encontrada nenhum item!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(orcamentoRepositorio.countByID(id),HttpStatus.OK);
        }
    }


    public ResponseEntity<?> cadastrar(Orcamento obj){
        if (obj.getData() == null) {
            mensagem.setMensagem("Uma Ordem de Servico deve ter uma data!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getCliente() == null) {
            mensagem.setMensagem("Um orcamento deve ter um Cliente!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>(orcamentoRepositorio.save(obj), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> editar (Orcamento obj) {
        if (obj.getData() == null) {
            mensagem.setMensagem("Uma Ordem de Servico deve ter uma data!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getItens().isEmpty()) {
            mensagem.setMensagem("Ao editar, não se pode apagar todos os itens!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getCliente() == null) {
            mensagem.setMensagem("Um orcamento deve ter um Cliente!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(orcamentoRepositorio.save(obj), HttpStatus.CREATED);}
    }

    public ResponseEntity<?> remover(Long id){
        if(orcamentoRepositorio.countByID(id) == 0){
            mensagem.setMensagem("O codigo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else {
            orcamentoRepositorio.deleteById(id);
            mensagem.setMensagem("Ordem de Servico apagado com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
