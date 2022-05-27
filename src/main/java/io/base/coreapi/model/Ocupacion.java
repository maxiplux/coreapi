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
@Table(name = "CONFIG_OCUPACION")
@EqualsAndHashCode(callSuper = false)
public class Ocupacion extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "ocupacion_seq", sequenceName = "ocupacion_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ocupacion_seq")

    @Column(name = "id", nullable = false)
    private Long id;


}
