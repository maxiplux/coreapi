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
@Table(name = "CONFIG_CUOTA")
@EqualsAndHashCode(callSuper = false)
public class Cuota extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "cuota_seq",
        sequenceName = "cuota_seq",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "cuota_seq")
    @Column(name = "id", nullable = false)
    private Long id;
    private Double valor;


}
