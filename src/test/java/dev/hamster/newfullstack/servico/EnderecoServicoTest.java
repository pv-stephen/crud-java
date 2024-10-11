package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.entidades.excecao.ExcecaoCampoObrigatorio;
import dev.hamster.newfullstack.entidades.excecao.GlobalExceptionHandler;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
import dev.hamster.newfullstack.entidades.excecao.ResourceNotFoundException;
import dev.hamster.newfullstack.repositorio.EnderecoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnderecoServicoTest {

    @InjectMocks
    private EnderecoServico enderecoServico;
    @Mock
    private EnderecoRepositorio enderecoRepositorio;
    private final List<Endereco> enderecos = new ArrayList<>();
    @Mock
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void SetUp(){
        MockitoAnnotations.openMocks(this);
        startClass();
    };


    @Test
    void quandoBuscarTodos_RetornaListaDeEnderecos() {

        when(enderecoRepositorio.buscarTodos()).thenReturn(enderecos);

        Assertions.assertNotNull(enderecos);
    }

    @Test
    void quandoCadastrarEnderecoComCampoVazio_RetornaBadRequest() {
        Cliente clienteTeste = new Cliente();
        Endereco enderecoTeste = new Endereco(1L, "","","", clienteTeste);

        ExcecaoCampoObrigatorio excecao = assertThrows(ExcecaoCampoObrigatorio.class,
                ()-> enderecoServico.cadastrarEndereco(enderecoTeste), "O campo rua é obrigatório");


        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<?> status = globalExceptionHandler.campoObrigatorioExcepiton(excecao);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, status.getStatusCode());
    }

    @Test
    void quandoEditarEndereco_ComCampoVazio_RetornaBadRequest() {
        Cliente clienteTeste = new Cliente();
        Endereco enderecoTeste = new Endereco(1L, "","","", clienteTeste);

        ExcecaoCampoObrigatorio excecaoLancada = assertThrows(ExcecaoCampoObrigatorio.class,
                () -> enderecoServico.editarEndereco(enderecoTeste), "O campo Rua é obrigatório");

        assertEquals("O campo Rua é obrigatório!", excecaoLancada.getMessage());

        GlobalExceptionHandler excecaoCampoObrigatorio = new GlobalExceptionHandler();
        ResponseEntity<String> status = excecaoCampoObrigatorio.campoObrigatorioExcepiton(excecaoLancada);

        assertEquals(HttpStatus.BAD_REQUEST, status.getStatusCode());
        assertEquals("O campo Rua é obrigatório!", status.getBody());

    }

    @Test
    void quandoIDNaoExiste_LancaResourceNotFoundException() {
        when(enderecoRepositorio.countByID(6L)).thenReturn(0L);

        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                ()-> enderecoServico.remover(6L), "Objeto não encontrado. ID = 6");

        assertEquals("Objeto não encontrado. ID = 6", excecao.getMessage());

        verify(enderecoRepositorio).countByID(6L);
    }


    @Test
    void quandoExcecaoLancada_GlobalHandlerExceptionRetornaNotFound(){
        ResourceNotFoundException excecao = new ResourceNotFoundException("Objeto não encontrado. ID = 10");

        GlobalExceptionHandler excecaoResourceNotFoundException = new GlobalExceptionHandler();
        ResponseEntity<?> status = excecaoResourceNotFoundException.objetoNaoEncontradoException(excecao);

        assertEquals(HttpStatus.NOT_FOUND, status.getStatusCode());
        assertEquals("Objeto não encontrado. ID = 10", status.getBody());

    }




    private void startClass(){
      Endereco endereco1 = new Endereco(1L, "Rua A", "Bairro A", "Sala 101", new Cliente());
      Endereco endereco2 = new Endereco(2L, "Rua B", "Bairro B", "Sala 102", new Cliente());
      Endereco endereco3 = new Endereco(3L, "Rua A", "Bairro B", "APT 102", new Cliente());
      Endereco endereco4 = new Endereco(4L, "Rua B", "Bairro A", "APT 304", new Cliente());
      enderecos.addAll(Arrays.asList(endereco1,endereco2,endereco3,endereco4));
      enderecoServico.cadastrarEndereco(endereco1);
      enderecoServico.cadastrarEndereco(endereco2);
      enderecoServico.cadastrarEndereco(endereco3);
      enderecoServico.cadastrarEndereco(endereco4);
    }
}