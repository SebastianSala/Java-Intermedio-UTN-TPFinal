package utn.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.tp.entities.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {  
}
