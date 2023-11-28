package utn.tp.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
//@Table(name = "cliente", uniqueConstraints = @UniqueConstraint(columnNames = "id_dni"))
@Table(name = "cliente")
public class Cliente {

  @Id
  private Integer idDni;

  @Basic
  private String nombre;
  private String direcction;
  private String email;

}
