package io.base.coreapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CONFIG_GENERO")
@EqualsAndHashCode(callSuper = false)
public class Genero extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "genero_id", sequenceName = "genero_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genero_id")

    @Column(name = "id", nullable = false)
    private Long id;


}
