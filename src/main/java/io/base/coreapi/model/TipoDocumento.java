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
@Table(name = "CONFIG_TIPO_DOCUMENTO")
@EqualsAndHashCode(callSuper = false)
public class TipoDocumento extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "tipo_documento_seq",
        sequenceName = "tipo_documento_seq",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "tipo_documento_seq")
    @Column(name = "id", nullable = false)
    private Long id;


}
