package io.base.coreapi.model;

import io.base.coreapi.constrains.UniqueValidation;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CONFIG_PAIS",uniqueConstraints = { @UniqueConstraint(name = "Unique_nombre",columnNames = { "nombre" }) })
@EqualsAndHashCode(callSuper = false)
@UniqueValidation(entityName = "Pais",tableGoal = "CONFIG_PAIS", message = "Invalid name for Pais, this should be unique", columName = "nombre")
public class Pais extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "pais_seq", sequenceName = "pais_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_seq")

    @Range(max = 9999,min = 1,message = "You need to provide the field Id")
    @Column(name = "id", nullable = false)
    private Long id;





    public Pais(String nombre, Boolean activo, Long id) {
        super(nombre,activo);
        this.id = id;

    }
    @NotEmpty(message = "Check your name")
    public  String getNombre(){
        return  this.nombre;
    }
}
