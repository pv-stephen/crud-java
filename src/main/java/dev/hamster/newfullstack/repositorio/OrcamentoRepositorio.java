package dev.hamster.newfullstack.repositorio;

import dev.hamster.newfullstack.entidades.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrcamentoRepositorio extends JpaRepository<Orcamento, Long> {
    Optional<Orcamento> findById(Long id);
    Long countByID(Long id);

    List<Orcamento> findAll();

    List<Orcamento> findOrcamentoByClienteNome(String parametro);
}
