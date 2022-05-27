package io.base.coreapi.constrains;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueValidation {
    //error message
    public String tableGoal();
    public String columName();
    public String columId() default "id";
    public String message() default "Invalid color: must be RED, GREEN or BLUE";
    //represents group of constraints
    public Class<?>[] groups() default {};
    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
