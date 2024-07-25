package dev.hamster.newfullstack.infra;

import dev.hamster.newfullstack.entidades.excecao.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<String> clienteNaoEncontrado(ResourceNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
    }
    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<String> clienteNaoEncontrado(EntityNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
    }

}
