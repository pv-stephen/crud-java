package dev.hamster.newfullstack.repositorio;

import dev.hamster.newfullstack.entidades.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefoneRepositorio extends JpaRepository<Telefone, Long> {

    @Query("SELECT t FROM telefones t ORDER BY LOWER(t.cliente.nome) ASC")
    List<Telefone> buscarTodos();


    @Query("SELECT t from telefones t WHERE t.numero LIKE %:termo% ORDER BY t.numero ASC")
    List<Telefone> buscarPorNumero(String termo);

    Long countById(Long id);
}
