package utn.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.tp.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {  
}
