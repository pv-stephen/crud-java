package dev.hamster.newfullstack.repositorio;

import dev.hamster.newfullstack.entidades.Itens;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItensRepositorio extends CrudRepository<Itens,Long> {

    List<Itens> findAll();
    Optional<Itens> findById(Long id);
    Long countByID(Long id);

}
