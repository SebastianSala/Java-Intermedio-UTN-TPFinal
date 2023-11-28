package utn.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.tp.entities.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Integer> {
}
