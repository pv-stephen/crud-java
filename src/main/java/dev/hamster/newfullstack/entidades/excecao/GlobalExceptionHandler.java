package dev.hamster.newfullstack.entidades.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExcecaoCampoObrigatorio.class)
    public ResponseEntity<String> campoObrigatorioExcepiton(ExcecaoCampoObrigatorio excecao){
        return new ResponseEntity<>(excecao.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> objetoNaoEncontradoException (ResourceNotFoundException excecao){
        return new ResponseEntity<>(excecao.getMessage(), HttpStatus.NOT_FOUND);
    }
}
