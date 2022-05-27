package io.base.coreapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CONFIG_GRUPO")
@EqualsAndHashCode(callSuper = false)
public class Grupo extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "grupo_id", sequenceName = "grupo_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupo_id")
    @Column(name = "id", nullable = false)
    private Long id;


}
