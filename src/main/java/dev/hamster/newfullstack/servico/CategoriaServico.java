package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Categoria;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
import dev.hamster.newfullstack.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServico {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public ResponseEntity<?> cadastrar(Categoria obj){
        if(obj.getNome().isBlank()){
            mensagem.setMensagem("O campo número deve ser preenchido!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>(categoriaRepositorio.save(obj), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> editar(Categoria obj){
        if(obj.getNome().isEmpty() || obj.getNome().isBlank()){
            mensagem.setMensagem("O campo Nome de Categoria deve ser preenchido");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(categoriaRepositorio.save(obj), HttpStatus.CREATED);
    }

    public ResponseEntity<?> remover(Long id){
        if(categoriaRepositorio.countById(id) == 0){
            mensagem.setMensagem("O codigo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else {
            categoriaRepositorio.deleteById(id);
            mensagem.setMensagem("Categoria apagada com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }

}