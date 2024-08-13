package dev.hamster.newfullstack.repositorio;

import dev.hamster.newfullstack.entidades.Cliente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM clientes c ORDER BY c.nome")
    List<Cliente> buscarTodos();


    @Query("SELECT c from clientes c WHERE LOWER(c.nome) LIKE %:nome% ORDER BY c.nome ASC")
    List<Cliente> buscarPorNome(String nome);

    @EntityGraph(attributePaths = {"telefones", "enderecos"})
    Optional<Cliente> findById(Long id);

    @Query("SELECT c FROM clientes c LEFT JOIN FETCH c.enderecos LEFT JOIN FETCH c.telefones WHERE c.id = :id")
    Cliente findByIdWithEnderecosAndTelefones(@Param("id") Long id);

    @EntityGraph(attributePaths = {"telefones", "enderecos"})
    @Query("SELECT c FROM clientes c ORDER BY c.nome")
    List<Cliente> buscarClientesComAtributos();
}
