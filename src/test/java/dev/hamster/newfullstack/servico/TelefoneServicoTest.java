package dev.hamster.newfullstack.servico;

import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.Telefone;
import dev.hamster.newfullstack.entidades.excecao.Mensagem;
import dev.hamster.newfullstack.repositorio.TelefoneRepositorio;
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
    @Mock
    private Mensagem mensagem;

    List<Telefone> telefones = new ArrayList<>();

    @BeforeEach
    void SetUp(){
        MockitoAnnotations.openMocks(this);
        startClass();
    };

    @Test
    void buscarTodos() {
        Mockito.when(telefoneServico.buscarTodos())
                .thenReturn(new ResponseEntity<>(telefones, HttpStatus.OK));

        Mockito.verify(telefones.isEmpty());
    }

    @Test
    void cadastrarTelefone() {
        Telefone telefoneTest = new Telefone(6L, " ", new Cliente());

        ResponseEntity<?> status = telefoneServico.cadastrarTelefone(telefoneTest);
        assertEquals(HttpStatus.BAD_REQUEST, status);
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