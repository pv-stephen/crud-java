package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
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
    private Mensagem mensagem;

    @BeforeEach
    void SetUp(){
        MockitoAnnotations.openMocks(this);
        startClass();
    };


    @Test
    void quandoBuscarTodos_RetornaListaDeEnderecos() {
    }

    @Test
    void quandoCadastrarEnderecoVazioRetornaBadRequest() {
        Cliente clienteTeste = new Cliente();
        Endereco enderecoTeste = new Endereco(1L, "","","", clienteTeste);
        ResponseEntity<?> status = enderecoServico.cadastrarEndereco(enderecoTeste);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, status.getStatusCode());
    }

    @Test
    void quandoEditarEnderecoVazioRetornaBadRequest() {
        Cliente clienteTeste = new Cliente();
        Endereco enderecoTeste = new Endereco(1L, "","","", clienteTeste);
        Endereco enderecoTeste2 = new Endereco(1L, "Rua A","Bairro A","", clienteTeste);

        ResponseEntity<?> statusEnderecoTeste = enderecoServico.cadastrarEndereco(enderecoTeste);
        ResponseEntity<?> statusEnderecoTeste2 = enderecoServico.cadastrarEndereco(enderecoTeste2);

        assertEquals(HttpStatus.BAD_REQUEST, statusEnderecoTeste.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, statusEnderecoTeste2.getStatusCode());

    }

    @Test
    void quandoIDNaoExisteRemover_RetornaNotFound() {
        when(enderecoRepositorio.countByID(6L)).thenReturn(0L);
        ResponseEntity<?> status = enderecoServico.remover(6L);

        assertEquals(HttpStatus.NOT_FOUND, status.getStatusCode());

        //TODO Nao consegui instanciar a mensagem: "Endereco não encontrado" que é retoranda do metodo
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

      mensagem.setMensagem("");
    }
}