package io.base.coreapi;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite

@SelectPackages({"io.base.coreapi.controllers"})
//@SelectClasses( { BatchEmailControllerTest.class, PaisControllerAuthTest.class, MunicipioControllerAuthTest.class, OcupacionControllerAuthTest.class, EmailTypeControllerTest.class} )
class CoreapiApplicationTests {

    @Test
    void contextLoads() {
    }

}
