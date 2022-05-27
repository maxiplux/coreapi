package io.base.coreapi.constrains;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;


@Slf4j
@Component
public class UniqueValidator implements ConstraintValidator<UniqueValidation, Object>

{

    private  String tableGoal;
    private  String columName;

    @Autowired
    private JdbcTemplate jdbcTemplate;
//    public boolean isValid(String goal, ConstraintValidatorContext cxt) {
//        List list = Arrays.asList(new String[]{"Colombia"});
//        cxt.getDefaultConstraintMessageTemplate();
//        log.info("UniqueValidator -> Jbdc Goal {} {} +++ ", this.tableGoal, this.columName);
//
//        return  this.notExistValue(goal);
//    }

    @Override
    public void initialize(UniqueValidation constraintAnnotation) {
        this.tableGoal = constraintAnnotation.tableGoal();
        this.columName = constraintAnnotation.columName();

    }



    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context){


        return this.notExistValue(value);
    }

    private boolean notExistValue(Object goal) {

        if (jdbcTemplate==null)
        {
            return true;
        }
        try {
            Field fieldId= goal.getClass().getDeclaredField("id");
            Field fieldName= goal.getClass().getSuperclass().getDeclaredField(this.columName);
            fieldName.setAccessible(true);
            fieldId.setAccessible(true);
            String proxyName=(String) fieldName.get(goal);
            Long proxyId=(Long) fieldId.get(goal);

            fieldName.setAccessible(false);
            fieldId.setAccessible(false);

            if (proxyId== null)
            {
                return !jdbcTemplate.queryForObject("SELECT EXISTS(SELECT FROM "+this.tableGoal+" WHERE "+this.columName+" ILIKE ?   )", Boolean.class, proxyName);

            }
            return !jdbcTemplate.queryForObject("SELECT EXISTS(SELECT FROM "+this.tableGoal+" WHERE "+this.columName+" ILIKE ? and id != ? )", Boolean.class, proxyName,proxyId );


        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.warn("UniqueValidator - > NoSuchFieldException | IllegalAccessException {} ", e.getMessage());
        }
        return true;


    }
}
