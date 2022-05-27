package io.base.coreapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper=false)
public abstract class Catalog extends Auditable<String> {
    @Column(unique = true)
    @NotNull(message = "nombre cannot be null")
    private String nombre;

    private Boolean activo = true;
}
