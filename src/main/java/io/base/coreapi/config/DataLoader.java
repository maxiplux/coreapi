package io.base.coreapi.config;


import io.base.coreapi.model.*;
import io.base.coreapi.repositories.*;
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
import java.time.LocalDate;
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


    @Autowired
    private CuotaRepository cuotaRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private BenefactorRepository benefactorRepository;

    ///
    private Pais pais;
    private Departamento departamento;
    private Municipio municipio;
    private TipoDocumento tipoDocumento;
    private Cuota cuota;
    private FormaDePago formaDePago;
    private Genero genero;
    private Ocupacion ocupacion;
    private Grupo grupo;

    private void  createTipoDocumento()
    {
        this.tipoDocumento= TipoDocumento.builder().build();
        tipoDocumento.setActivo(true);
        tipoDocumento.setNombre("Passport");
        this.tipoDocumento=this.tipoDocumentoRepository.save(tipoDocumento);

    }

    public void createCuota()
    {

        cuota=Cuota.builder().id(1L).valor(500.0).build();
        cuota.setNombre("Cuota base");
        this.cuota= this.cuotaRepository.save(cuota);
    }

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;
    public void createFormaDePago()
    {
        formaDePago=FormaDePago.builder().id(1L).maximoDeRecordatorios(10).maximoDeRecordatorios(10).build();
        formaDePago.setNombre("Forma de pago");
        this.formaDePago= this.formaDePagoRepository.save(formaDePago);
    }

    @Autowired
    private GrupoRepository grupoRepository;
    public void createGrupo()
    {
        grupo=Grupo.builder().id(1L).build();
        grupo.setNombre("Grupo");
        this.grupo= this.grupoRepository.save(grupo);
    }

    @Autowired
    private GeneroRepository generoRepository;

    public void createGenero()
    {
        genero=Genero.builder().id(1L).build();
        genero.setNombre("Male");
        this.genero= this.generoRepository.save(genero);
    }

    @Autowired
    private OcupacionRepository ocupacionRepository;

    public void createOcupacion()
    {
        ocupacion=Ocupacion.builder().id(1L).build();
        ocupacion.setNombre("ocupacion");
        this.ocupacion= this.ocupacionRepository.save(ocupacion);
    }

    private  void createBenefactor()
    {




        Benefactor benefactor=Benefactor.builder()
                .activo(true)
                .celular("+07-316-587-46")
                .correo("email@email.com")
                .cantidadRecordatorios(10)
                .direccion("Kra 0 Av -500")
                .fechaNacimiento(LocalDate.now().minusYears(25))
                .pais(pais)
                .codigo("20255")

                .cuota(cuota)
                .grupo(grupo)
                .genero(genero)
                .ocupacion(ocupacion)
                .formaDePago(formaDePago)
                .LugarDeNacimiento(municipio)
                .tipoDocumento(tipoDocumento)

                .documento("04050")
                .moraActual(0.0)
                .observaciones("observacionesobservacionesobservaciones")
                .primerApellido("Plutarco")
                .primerNombre("Pancracio")
                .segundoNombre("Lepo")

                .segundoApellido("Nepo")

                .razonSocial("Plutcarquino")
                .build();
        this.benefactorRepository.save(benefactor);


    }

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
    private void createPais()
    {
        this.pais = Pais.builder().id(1L).build();
        pais.setActivo(true);
        pais.setNombre("Colombia");
        this.pais=this.paisRepository.save(pais);
    }

    private void createDepartamento()
    {
        Departamento departamento = Departamento.builder().pais(pais).build();
        departamento.setNombre("DPT" + 1);
        departamento.setActivo(true);
        this.departamento= this.departamentoRepository.save(departamento);
    }

    private void createMunicipio()
    {
        Municipio municipio = Municipio.builder().departamento(this.departamento).build();
        municipio.setNombre("Municipio" + 1);
        municipio.setActivo(true);
        this.municipio= this.municipioRepository.save(municipio);
    }

    private void main() {

        this.municipioRepository.deleteAll();
        this.departamentoRepository.deleteAll();
        this.paisRepository.deleteAll();

        this.createPais();
        this.createDepartamento();
        this.createMunicipio();
        this.createGenero();
        this.createGrupo();



        this.createCuota();
        this.createOcupacion();
        this.createTipoDocumento();
        this.createFormaDePago();
        this.createBenefactor();


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
