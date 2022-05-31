package io.base.coreapi.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CORE_BENEFACTOR")
@EqualsAndHashCode(callSuper = false)

public class Benefactor extends Auditable<String> implements Serializable {
    @Id

    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "benefactor_seq")
    @SequenceGenerator(name = "benefactor_seq", sequenceName = "benefactor_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotNull(message = "Grupo cannot be null")
    private Grupo grupo;

    @ManyToOne
    @NotNull(message = "Genero cannot be null")
    private Genero genero;

    @ManyToOne
    @NotNull(message = "Forma de pago cannot be null")
    private FormaDePago formaDePago;

    @ManyToOne
    @NotNull(message = "Cuota cannot be null")
    private Cuota cuota;

    @ManyToOne
    @NotNull(message = "Tipo de documento cannot be null")
    private TipoDocumento tipoDocumento;


    @ManyToOne
    @NotNull(message = "Pais cannot be null")
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "lugar_de_nacimiento_ID")
    @NotNull(message = "Lugar de nacimiento cannot be null")
    private Municipio LugarDeNacimiento;

    @ManyToOne
    @NotNull(message = "Ocupacion cannot be null")
    private Ocupacion ocupacion;


    private String razonSocial;
    @NotNull(message = "Primer Nombre cannot be null")
    private String primerNombre;

    private String segundoNombre;

    @NotNull(message = "Primer Apellido cannot be null")
    private String primerApellido;
    @NotNull(message = "Primer Apellido cannot be null")
    private String segundoApellido;

    @Email(message = "This field should be work using email a valid format")
    @NotNull(message = "Email cannot be null")
    private String correo;

    private String codigo;

    private String telefono;

    private String celular;

    private String direccion;
    @NotNull(message = "Documento  Nombre cannot be null")
    private String documento;

    private String observaciones;

    private String lugarNacimiento;

    private Double moraActual;


    private LocalDate fechaNacimiento;

    private Integer cantidadRecordatorios;
    private Boolean activo;


}
