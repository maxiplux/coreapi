package io.base.coreapi.config;


import io.base.coreapi.model.*;
import io.base.coreapi.repositories.DepartamentoRepository;
import io.base.coreapi.repositories.MunicipioRepository;
import io.base.coreapi.repositories.PaisRepository;
import io.base.coreapi.repositories.RoleRepository;
import io.base.coreapi.services.UserService;
import lombok.SneakyThrows;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {

    private EasyRandom factory;


    @Autowired
    private PaisRepository paisRepository;


    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    @SneakyThrows
    private void createUsers() {
        this.roleRepository.deleteAll();
        this.userService.deleteAll();

       Role adminRole= this.roleRepository.save(Role.builder().name("ADMIN").build());
       Role userRole= this.roleRepository.save(Role.builder().name("USER").build());


       Set<Role> roleSet=new HashSet<>();
       roleSet.add(adminRole);
      User admin=  User.builder()
                .username("admin")
                .name("admin")
                .password("admin")
                .username("admin")
                 .roles(roleSet)
                .build();

        this.userService.saveUser(admin);

        roleSet.add(userRole);
        User user=  User.builder()
                .username("user")
                .name("user")
                .password("user")
                .username("user")
                .roles(roleSet)
                .build();
        this.userService.saveUser(user);




    }
    private void main() {

        this.municipioRepository.deleteAll();
        this.departamentoRepository.deleteAll();
        this.paisRepository.deleteAll();

        Pais pais = Pais.builder().id(1L).build();
        pais.setActivo(true);
        pais.setNombre("Colombia");
        pais = this.paisRepository.save(pais);

        Departamento departamento = Departamento.builder().pais(pais).build();
        departamento.setNombre("DPT" + 1);
        departamento.setActivo(true);
        departamento = this.departamentoRepository.save(departamento);
        createUsers();
        for (int i = 0; i < 5; i++) {


            Municipio municipio = Municipio.builder().departamento(departamento).build();
            municipio.setNombre("MUNICIPIO" + i);
            this.municipioRepository.save(municipio);

        }


    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createFactory();
        main();
    }


    private void createFactory() {
        EasyRandomParameters parameters = new EasyRandomParameters()
            .seed(123L)
            .objectPoolSize(100)
            .randomizationDepth(3)
            .charset(StandardCharsets.UTF_8)
            .stringLengthRange(5, 50)
            .collectionSizeRange(1, 10)
            .scanClasspathForConcreteTypes(true)
            .overrideDefaultInitialization(false)
            .ignoreRandomizationErrors(true);
        factory = new EasyRandom(parameters);
    }
}
