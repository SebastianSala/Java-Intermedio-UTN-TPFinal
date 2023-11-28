package utn.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.tp.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
