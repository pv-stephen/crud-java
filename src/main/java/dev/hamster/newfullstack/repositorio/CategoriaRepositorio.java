package dev.hamster.newfullstack.repositorio;

import dev.hamster.newfullstack.entidades.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRepositorio extends CrudRepository<Categoria,Long> {

    List<Categoria> findAll();

    Long countById(Long id);
}
