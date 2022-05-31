package io.base.coreapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper=false)
public abstract class Catalog extends Auditable<String> implements Serializable {

    //https://stackoverflow.com/questions/1032486/what-is-the-proper-jpa-mapping-for-id-in-parent-and-unique-sequence-in-base-cla
    protected String nombre;
    protected Boolean activo = true;


}
