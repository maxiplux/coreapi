package io.base.coreapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CONFIG_TIPO_BENEFACTOR")
@EqualsAndHashCode(callSuper = false)
public class TipoBenefactor extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "tipo_benefactor_seq", sequenceName = "tipo_benefactor_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_benefactor_seq")

    @Column(name = "id", nullable = false)
    private Long id;

}
