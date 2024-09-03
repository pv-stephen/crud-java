package dev.hamster.newfullstack.repositorio;

import dev.hamster.newfullstack.entidades.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM enderecos e ORDER BY e.rua ASC")
    List<Endereco> buscarTodos();



    @Query("SELECT e FROM enderecos e WHERE LOWER(e.rua) LIKE %:termo% AND LOWER(e.bairro) LIKE %:termo% ORDER BY e.rua ASC")
    List<Endereco> buscarPorRua(String termo);

    Long countByID(Long id);
}
