package io.base.coreapi.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;

@Slf4j
public class ValidationErrorBuilder {



    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError();
        error.setErrorMessage("Validation failed. " + errors.getErrorCount() + " error(s)");
        errors.getFieldErrors().forEach(fieldError -> {
            FieldError fieldErrorCustom=FieldError.builder().field(fieldError.getField()).message(fieldError.getDefaultMessage()).entityName(fieldError.getObjectName()).build();
            if (fieldError.getRejectedValue() != null)
            {
                fieldErrorCustom.setRejectValue(fieldError.getRejectedValue().toString());
            }
            error.addErrors(fieldErrorCustom);
        });



        return error;
    }
}
