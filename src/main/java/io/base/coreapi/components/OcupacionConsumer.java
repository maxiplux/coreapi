package io.base.coreapi.components;

import io.base.coreapi.model.Ocupacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OcupacionConsumer {
    public void handleMessage(Ocupacion student) {
        log.info("Consumer>  Esta llegandoooooooo" + student);
    }
}
