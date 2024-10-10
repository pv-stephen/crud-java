package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.excecao.ExcecaoCampoObrigatorio;
import dev.hamster.newfullstack.entidades.excecao.GlobalExceptionHandler;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
import dev.hamster.newfullstack.repositorio.ClienteRepositorio;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServicoTest {

    @InjectMocks
    private ClienteServico clienteServico;
    @Mock
    private ClienteRepositorio repositorio;

    private Cliente cliente;
    private Cliente clienteNomeIsBlank;
    private Cliente clienteNomeIsEmpty;
    private List<Cliente> clientes = new ArrayList<>();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void quandoBuscarTodos_RetornaListadeClientes_E_Status_OK() {
        Mockito.when(repositorio.buscarTodos()).thenReturn(clientes);

        ResponseEntity<List<Cliente>> statusResposta = clienteServico.buscarTodos();

        assertNotNull(clientes);
        assertEquals(HttpStatus.OK, statusResposta.getStatusCode());
    }

    @Test
    void quandoCadastrarCliente_RetornaStatusCREATED() {
        ResponseEntity<?> statusResposta = clienteServico.cadastrarCliente(cliente);
        assertEquals(HttpStatus.CREATED, statusResposta.getStatusCode());
    }

    @Test
    void quandoEditarCliente_RetornaStatusOKD() {
        ResponseEntity<?> statusResposta = clienteServico.editarCliente(cliente);
        assertEquals(HttpStatus.OK, statusResposta.getStatusCode());
        assertNotEquals(HttpStatus.CREATED, statusResposta.getStatusCode());
    }
    @Test
    void seCadastrarCliente_NomeClienteIsEmpty_EntaoRetorna_BadRequest(){
        ExcecaoCampoObrigatorio excecaoNomeIsEmpty = assertThrows(ExcecaoCampoObrigatorio.class,
                () -> clienteServico.cadastrarCliente(clienteNomeIsEmpty), "O NOME do Cliente é obrigatório!");

        assertEquals("O NOME do Cliente é obrigatório!", excecaoNomeIsEmpty.getMessage());

        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<String> status = globalExceptionHandler.campoObrigatorioExcepiton(excecaoNomeIsEmpty);
        assertEquals(HttpStatus.BAD_REQUEST, status.getStatusCode());
    }

    @Test
    void seCadastrar_ClienteNomeIsBlank_RetornaBadRequest(){

        ExcecaoCampoObrigatorio excecaoNomeIsBlank = assertThrows(ExcecaoCampoObrigatorio.class,
                ()-> clienteServico.cadastrarCliente(clienteNomeIsBlank), "O NOME do Cliente é obrigatório!");


        assertEquals("O NOME do Cliente é obrigatório!", excecaoNomeIsBlank.getMessage());

        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<String> status = globalExceptionHandler.campoObrigatorioExcepiton(excecaoNomeIsBlank);

        assertEquals(HttpStatus.BAD_REQUEST, status.getStatusCode());
    }

    @Test
    void quandoEditarClienteNomeIsBlank_ou_ClienteNomeIsEmpty_RetornaBadRequest() {
        ExcecaoCampoObrigatorio excecaoNomeIsBlank = assertThrows(ExcecaoCampoObrigatorio.class,
                ()-> clienteServico.cadastrarCliente(clienteNomeIsBlank), "O NOME do Cliente é obrigatório!");

        ExcecaoCampoObrigatorio excecaoNomeIsEmpty = assertThrows(ExcecaoCampoObrigatorio.class,
                ()-> clienteServico.cadastrarCliente(clienteNomeIsEmpty), "O NOME do Cliente é obrigatório!");

        assertEquals("O NOME do Cliente é obrigatório!", excecaoNomeIsBlank.getMessage());
        assertEquals("O NOME do Cliente é obrigatório!", excecaoNomeIsEmpty.getMessage());

        GlobalExceptionHandler globalExceptionHandlerNomeIsEmpty = new GlobalExceptionHandler();
        ResponseEntity<String> statusNomeIsEmpty = globalExceptionHandlerNomeIsEmpty.campoObrigatorioExcepiton(excecaoNomeIsBlank);

        assertEquals(HttpStatus.BAD_REQUEST, statusNomeIsEmpty.getStatusCode());

        GlobalExceptionHandler globalExceptionHandlerNomeIsBlank = new GlobalExceptionHandler();
        ResponseEntity<String> statusNomeIsBlank = globalExceptionHandlerNomeIsBlank.campoObrigatorioExcepiton(excecaoNomeIsBlank);

        assertEquals(HttpStatus.BAD_REQUEST, statusNomeIsBlank.getStatusCode());

    }


    private void startCliente(){
        cliente = new Cliente(1L,"Pedro Augusto");
        clientes.add(cliente);

        clienteNomeIsBlank = new Cliente(2L, "");
        clienteNomeIsEmpty = new Cliente(3L, "  ");
    }
}