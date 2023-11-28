package utn.tp.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.tp.entities.Categoria;
import utn.tp.entities.Cliente;
import utn.tp.entities.Orden;
import utn.tp.entities.Tecnico;
import utn.tp.repositories.CategoriaRepository;
import utn.tp.repositories.ClienteRepository;
import utn.tp.repositories.OrdenRepository;
import utn.tp.repositories.TecnicoRepository;

@RestController
@RequestMapping("/")
public class AppController {

  @Autowired
  CategoriaRepository categoriaRepository;
  @Autowired
  TecnicoRepository tecnicoRepository;
  @Autowired
  ClienteRepository clienteRepository;
  @Autowired
  OrdenRepository ordenRepository;

  @GetMapping("/orden")
  public String cargarOrden() {
    System.out.println("cargando Orden");

    Categoria unaCategoria = new Categoria(null, "Actualizacion");
    Tecnico unTecnico = new Tecnico(null, "Tecnico1", "Apellido1");
    Cliente unCliente = new Cliente(55555555, "Cliente1", "Direccion1", "email1");
    this.categoriaRepository.save(unaCategoria);
    this.tecnicoRepository.save(unTecnico);
    this.clienteRepository.save(unCliente);

    LocalDate unaFecha = LocalDate.of(2023, 12, 30);
    Orden unaOrden = new Orden(null, "descripcion de orden", 22.2f, true, unaFecha, unaCategoria, unTecnico, unCliente);

    this.ordenRepository.save(unaOrden);

    return "Orden guardada";
  }

}
