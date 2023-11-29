package utn.tp.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  //datos para cargar orden y ultimo, datos para el cliente
  @GetMapping("/orden/{descripcion}/{costo}/{estado}/{idCategoria}/{idTecnico}/{dniCliente}/{nombreCliente}/{direccionCliente}/{mailCliente}")
  public String cargarOrden(
      @PathVariable String descripcion,
      @PathVariable double costo,
      @PathVariable boolean estado,
      @PathVariable int idCategoria,
      @PathVariable int idTecnico,
      @PathVariable int dniCliente,
      @PathVariable String nombreCliente,
      @PathVariable String direccionCliente,
      @PathVariable String mailCliente) {

    System.out.println("cargando Orden");

    Categoria categoria1 = new Categoria(null, "Actualizacion");
    Categoria categoria2 = new Categoria(null, "Reparacion");
    Categoria categoria3 = new Categoria(null, "Limpieza");
    Tecnico tecnico1 = new Tecnico(null, "Tecnico1");
    Tecnico tecnico2 = new Tecnico(null, "Tecnico2");
    Tecnico tecnico3 = new Tecnico(null, "Tecnico3");
    Cliente cliente1 = new Cliente(11111111, "Cliente1", "Direccion1", "email1");
    Cliente cliente2 = new Cliente(11111112, "Cliente2", "Direccion2", "email2");
    Cliente cliente3 = new Cliente(11111113, "Cliente3", "Direccion3", "email3");
    this.categoriaRepository.save(categoria1);
    this.categoriaRepository.save(categoria2);
    this.categoriaRepository.save(categoria3);
    this.tecnicoRepository.save(tecnico1);
    this.tecnicoRepository.save(tecnico2);
    this.tecnicoRepository.save(tecnico3);
    this.clienteRepository.save(cliente1);
    this.clienteRepository.save(cliente2);
    this.clienteRepository.save(cliente3);

    //comprobar si existe cliente, de lo contrario crear uno con los datos recibidos.
    if (this.clienteRepository.findById(dniCliente).isEmpty()) {
      Cliente nuevoCliente = new Cliente(dniCliente, nombreCliente, direccionCliente, mailCliente);
      this.clienteRepository.save(nuevoCliente);
    }

    //obtener tecnico, categoria y cliente de la db
    Tecnico tecnico = this.tecnicoRepository.findById(idTecnico).orElse(tecnico1);
    Categoria categoria = this.categoriaRepository.findById(idCategoria).orElse(categoria1);
    Cliente cliente = this.clienteRepository.findById(dniCliente).get();

    //guardar orden
    LocalDate fechaActual = LocalDate.now();
    Orden nuevaOrden = new Orden(null, descripcion, costo, estado, fechaActual, categoria, tecnico, cliente);
    this.ordenRepository.save(nuevaOrden);

    String textoOrden = nuevaOrden.toString();
    return "Orden guardada: \n" + textoOrden;
  }

  @GetMapping("/ordenes/{fechaInicial}/{fechaFinal}")
  public String mostrarOrdenes(
      @PathVariable String fechaInicial,
      @PathVariable String fechaFinal) {

    LocalDate inicio = LocalDate.parse(fechaInicial, formatter);
    LocalDate fin = LocalDate.parse(fechaFinal);

    List<Orden> ordenes = this.ordenRepository.findAll();
    List<Orden> ordenesFiltradas = ordenes.stream().filter(orden -> ((orden.getFecha().isAfter(inicio)) && (orden.getFecha().isBefore(fin)))).collect(Collectors.toList());

    String textoOrdenes = ordenesFiltradas.stream().map(Orden::toString).collect(joining("\n"));

    return "Ordenes entre " + fechaInicial + " y " + fechaFinal + ":\n" + textoOrdenes;
//    return textoOrdenes;

  }

}
