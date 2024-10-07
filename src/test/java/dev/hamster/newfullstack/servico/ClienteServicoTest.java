package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Cliente;
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
    void seClienteNomeIsEmpty_RetornaBadRequest(){
        ResponseEntity<?> statusResposta = clienteServico.cadastrarCliente(clienteNomeIsEmpty);
        assertEquals(HttpStatus.BAD_REQUEST, statusResposta.getStatusCode());
    }

    @Test
    void seClienteNomeIsBlank_RetornaBadRequest(){
        ResponseEntity<?> statusResposta = clienteServico.cadastrarCliente(clienteNomeIsBlank);
        assertEquals(HttpStatus.BAD_REQUEST, statusResposta.getStatusCode());
    }

    @Test
    void quandoEditarClienteNomeIsBlank_ou_ClienteNomeIsEmpty_RetornaBadRequest() {
        ResponseEntity<?> statusClienteNomeIsBlank = clienteServico.editarCliente(clienteNomeIsBlank);
        ResponseEntity<?> statusClienteNomeIsEmpty = clienteServico.editarCliente(clienteNomeIsEmpty);

        assertEquals(HttpStatus.BAD_REQUEST, statusClienteNomeIsBlank.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, statusClienteNomeIsEmpty.getStatusCode());
    }


    private void startCliente(){
        cliente = new Cliente(1L,"Pedro Augusto");
        clientes.add(cliente);

        clienteNomeIsBlank = new Cliente(2L, "");
        clienteNomeIsEmpty = new Cliente(3L, "  ");
    }
}