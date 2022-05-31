package io.base.coreapi.errors;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError {

    private  String errorMessage;
    private Integer totalErrors=0;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final Set<FieldError> errors = new HashSet<>();


    public  void  addErrors( FieldError fieldError)
    {
        this.errors.add(fieldError);
        this.totalErrors=this.errors.size();
    }




}
