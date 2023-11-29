package utn.tp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "orden")
public class Orden {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "descripcion", length = 100)
  private String descripcion;

  @Basic
  private double costo;
  private boolean estado; //false = pendiente, true = resuelto

  @Column(columnDefinition = "DATE")
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  private LocalDate fecha;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_categoria", referencedColumnName = "id")
  private Categoria categoria;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_tecnico", referencedColumnName = "id")
  private Tecnico tecnico;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_cliente", referencedColumnName = "dni")
  private Cliente cliente;

}
