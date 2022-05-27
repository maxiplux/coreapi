package io.base.coreapi.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CONFIG_MUNICIPIO")
@EqualsAndHashCode(callSuper = false)
public class Municipio extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "municipio_seq",
        sequenceName = "municipio_seq",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "municipio_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    //@RestResource(path = "departamentos", rel="departamentos")

    private Departamento departamento;

    public Municipio(String nombre, Boolean activo, Long id, Departamento departamento) {
        super(nombre, activo);
        this.id = id;
        this.departamento = departamento;
    }
}
