package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.entidades.excecao.ExcecaoCampoObrigatorio;
import dev.hamster.newfullstack.entidades.excecao.GlobalExceptionHandler;
import dev.hamster.newfullstack.entidades.excecao.ViolacaoIntegridadeBD;
import dev.hamster.newfullstack.repositorio.TelefoneRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TelefoneServicoTest {

    @InjectMocks
    private TelefoneServico telefoneServico;

    @Mock
    private TelefoneRepositorio telefoneRepositorio;

    List<Telefone> telefones = new ArrayList<>();

    @BeforeEach
    void SetUp(){
        MockitoAnnotations.openMocks(this);
        startClass();
    }

    @Test
    void buscarTodos() {
        Mockito.when(telefoneRepositorio.buscarTodos())
                .thenReturn(telefones);

        Assertions.assertNotNull(telefones);
    }

    @Test
    void telefoneServicoBuscarTodos() {
        Mockito.when(telefoneRepositorio.buscarTodos())
                .thenReturn(telefones);

        ResponseEntity<List<Telefone>> response = telefoneServico.buscarTodos();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(telefones, response.getBody());

    }

    @Test
    void cadastrarTelefoneComNumeroVazio_DeveRetornarBadRequest() {
        Telefone telefoneTest = new Telefone(6L, "", new Cliente());

        ExcecaoCampoObrigatorio excecaoCampoObrigatorio = assertThrows(ExcecaoCampoObrigatorio.class,
                () -> telefoneServico.cadastrarTelefone(telefoneTest),  "O número é obrigatório!");

        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<String> response = globalExceptionHandler.campoObrigatorioExcepiton(excecaoCampoObrigatorio);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void cadastrarTelefoneClienteNulo_RetornaBadRequest(){
        Telefone telefone = new Telefone(1L, "6291111-1111", null);

        ViolacaoIntegridadeBD violacaoIntegridadeBD = assertThrows(ViolacaoIntegridadeBD.class,
                () -> telefoneServico.cadastrarTelefone(telefone), "Um telefone deve ter um cliente associado!");

        assertEquals("Um telefone deve ter um cliente associado!", violacaoIntegridadeBD.getMessage());

        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<String> response = globalExceptionHandler.violacaoIntegridadeBD(violacaoIntegridadeBD);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    void editarTelefone() {
    }

    @Test
    void remover() {
    }

    void startClass(){
        Telefone telefone1 = new Telefone(1L, "6291234-5678", new Cliente());
        Telefone telefone2 = new Telefone(2L, "6291111-1111", new Cliente());
        Telefone telefone3 = new Telefone(3L, "6292222-2222", new Cliente());
        telefones.addAll(Arrays.asList(telefone1,telefone2,telefone3));
        telefoneServico.cadastrarTelefone(telefone1);
        telefoneServico.cadastrarTelefone(telefone2);
        telefoneServico.cadastrarTelefone(telefone3);
    }
}