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
@Table(name = "CONFIG_PAIS")
@EqualsAndHashCode(callSuper = false)
public class Pais extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "pais_seq", sequenceName = "pais_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_seq")

    @Column(name = "id", nullable = false)
    private Long id;


    public Pais(String nombre, Boolean activo, Long id) {
        super(nombre, activo);
        this.id = id;
    }
}
