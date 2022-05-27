package io.base.coreapi.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CONFIG_FORMA_DE_PAGO")
@EqualsAndHashCode(callSuper = false)
public class FormaDePago extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "forma_de_pago_seq", sequenceName = "forma_de_pago_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forma_de_pago_seq")

    @Column(name = "id", nullable = false)
    private Long id;


    @Range(min = 1,max = 100)
    private Integer moraMaxima;


    @Range(min = 1,max = 100)
    private Integer maximoDeRecordatorios;


}
