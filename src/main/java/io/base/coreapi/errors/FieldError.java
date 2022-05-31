package io.base.coreapi.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldError implements Serializable {

    private static final long serialVersionUID = 1L;

    private  String entityName;

    private   String field;
    private   String rejectValue;

    private   String message;


}
