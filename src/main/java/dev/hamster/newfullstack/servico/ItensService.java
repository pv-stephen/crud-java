package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Itens;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
import dev.hamster.newfullstack.repositorio.ItensRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItensService {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private ItensRepositorio itensRepositorio;


    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(itensRepositorio.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> findById(Long id){
        if (itensRepositorio.countById(id) == 0) {
            mensagem.setMensagem("Não foi encontrada nenhum item!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(itensRepositorio.countById(id),HttpStatus.OK);
        }
    }

    public ResponseEntity<?> cadastrar(Itens obj){
        if(obj.getDescricao().isBlank()){
            mensagem.setMensagem("O campo descrição deve ser preenchido!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getCategoria() == null) {
            mensagem.setMensagem("Um item deve possuir uma categoria!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getPreco() < 0 || obj.getPreco() == null) {
            mensagem.setMensagem("O campo preço deve ser preenchido ou maior que 0!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getQuantidade() < 0 || obj.getQuantidade() == null){
            mensagem.setMensagem("O campo quantidade deve ser preenchido ou maior que 0!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(itensRepositorio.save(obj), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> editar(Itens obj){
        if(itensRepositorio.countById(obj.getId()) == 0){
            mensagem.setMensagem("Este código não existe!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if(obj.getDescricao().isBlank()){
            mensagem.setMensagem("O campo descrição deve ser preenchido!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if(obj.getQuantidade() <= 0 || obj.getQuantidade() == null ){
            mensagem.setMensagem("O campo quantidade deve ser preenchido ou maior que 0!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getPreco()<= 0 || obj.getPreco() == null) {
            mensagem.setMensagem("O campo preço deve ser preenchido ou maior que 0!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getCategoria() == null) {
            mensagem.setMensagem("O campo categoria deve ser preenchido!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(itensRepositorio.save(obj),HttpStatus.OK);
        }
    }


    public ResponseEntity<?> remover(Long id){
        if(itensRepositorio.countById(id) == 0){
            mensagem.setMensagem("O codigo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else {
            itensRepositorio.deleteById(id);
            mensagem.setMensagem("Item apagado com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
