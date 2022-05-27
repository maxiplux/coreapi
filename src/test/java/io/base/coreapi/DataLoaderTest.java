package io.base.coreapi;


import io.base.coreapi.model.*;
import io.base.coreapi.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoaderTest implements ApplicationRunner {

    @Autowired
    private EmailTypeRepository emailTypeRepository;

    @Autowired
    private BatchEmailRepository batchEmailRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;


    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private EmailDataSourceRepository emailDataSourceRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.emailDataSourceRepository.save(
        EmailDataSource.builder().entityName("Correo Masivo")
                .query("Select * from customer")
                .name("correos masivos")
                .entityName("Benecfactor").build());
        Pais paisRoot=this.paisRepository.save(new Pais("Name", true,null));
        Departamento departamentoRoot=this.departamentoRepository.save(new Departamento("Name", true,null,paisRoot));
        Municipio municipioRoot=this.municipioRepository.save(new Municipio("Name", true,null,departamentoRoot));


        this.emailTypeRepository.save(EmailType.builder().name(EmailTypeEnum.DIARIO.name()).build());
        this.emailTypeRepository.save(EmailType.builder().name(EmailTypeEnum.MENSUAL.name()).build());
        this.emailTypeRepository.save(EmailType.builder().name(EmailTypeEnum.UNICA_VEZ.name()).build());





    }
}
