package io.base.coreapi.controllers;


import io.base.coreapi.CoreapiApplication;
import io.base.coreapi.model.*;
import io.base.coreapi.model.dto.BatchEmailDto;
import io.base.coreapi.model.dto.EmailImagePathDto;
import io.base.coreapi.model.dto.TokenDto;
import io.base.coreapi.model.dto.UserDto;
import io.base.coreapi.repositories.*;
import io.base.coreapi.services.BatchEmailServices;
import io.base.coreapi.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.StreamSupport;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT, classes = CoreapiApplication.class)
@Slf4j
public
class BatchEmailControllerTest {
    @LocalServerPort
    int randomServerPort;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmailTypeRepository emailTypeRepository;


    @Autowired
    private BatchEmailServices batchEmailServices;

    @Autowired
    private EmailDataSourceRepository emailDataSourceRepository;

    @Value("${template.base64}")
    private  String templateBase64;

    @Value("${image.base64}")
    private  String imageBase64;

    @Autowired
    private BatchEmailRepository batchEmailRepository;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository authorityRepository;

    private final String authUrl="/api/authentication/login/";
    private String authToken;
    @BeforeEach
    void setUp() {
        this.batchEmailRepository.deleteAll();
        this.userRepository.deleteAll();
        this.authorityRepository.deleteAll();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json " );
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setUsername("admin");

        user.setName("Juanito");
        user.setPassword(passwordEncoder.encode("admin"));

        Set<Role> authorities= new HashSet<>();
        authorities.add(this.authorityRepository.save(Role.builder().name(Constans.ENUMROLES.ROLE_ADMIN.name()).build()));
        authorities.add(this.authorityRepository.save(Role.builder().name(Constans.ENUMROLES.ROLE_USER.name()).build()));
        user.setRoles(authorities);
        userRepository.saveAndFlush(user);

        UserDto login = new UserDto();

        login.setUsername("admin");
        login.setPassword("admin");

        HttpEntity<?> request = new HttpEntity<UserDto>(login, headers);
        ResponseEntity<TokenDto> response = restTemplate.exchange(authUrl, HttpMethod.POST, request, TokenDto.class);

        this.authToken="Bearer "+response.getBody().getAccessToken();


    }

    @AfterEach
    void tearDown() {
    }





   @Test
    public void doLoginTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json " );


        UserDto login = new UserDto();
        login.setUsername("admin");
        login.setPassword("admin");

        HttpEntity<?> request = new HttpEntity<UserDto>(login, headers);
        ResponseEntity<String> response = restTemplate.exchange(authUrl, HttpMethod.POST, request, String.class);
        AssertionErrors.assertEquals("Status code",200,response.getStatusCode().value());



        //   this.token = response.getBody().getAccess_token();
    }

    @Test
    void update_DIARIO()
    {
        this.update(EmailTypeEnum.DIARIO);
    }

    @Test
    void update_MENSUAL()
    {
        this.update(EmailTypeEnum.MENSUAL);
    }

    @Test
    void update_UNICA_VEZ()
    {
        this.update(EmailTypeEnum.UNICA_VEZ);
    }


    @Test
    void create_MENSUAL()
    {
        this.create(EmailTypeEnum.MENSUAL);
    }

    @Test
    void create_UNICA_VEZ()
    {
        this.create(EmailTypeEnum.UNICA_VEZ);
    }

    @Test
    void create_DIARIO()
    {
        this.create(EmailTypeEnum.DIARIO);
    }


    private BatchEmailDto buildBatchDtoEmail(EmailTypeEnum emailTypeEnum){
        LocalDate cronDate = LocalDate.of(2090,04,26);
        LocalTime cronTime = LocalTime.of(23,30,0);

        EmailType emailTyp=this.emailTypeRepository.findByNameEquals(emailTypeEnum.name());
        EmailDataSource emailDataSource=StreamSupport.stream(emailDataSourceRepository.findAll().spliterator(),false).findFirst().get();

        return          BatchEmailDto.builder()
                .emailTypeId(emailTyp.getId())
                .name("SimpleBirthday"+emailTypeEnum.name()+ UUID.randomUUID().toString())
                .start(LocalDateTime.now())
                .end(LocalDateTime.MAX)
                .testMode(false)
                .testEmails(Arrays.asList("maxiplux@gmail.com","root@example.com"))
                .subject("Cumpleanios")
                .cronDate(cronDate)
                .cronTime(cronTime)
                .template(templateBase64)
                .imagesDto(Arrays.asList(
                        EmailImagePathDto.builder().content(imageBase64).name("img1.png").build(),
                        EmailImagePathDto.builder().content(imageBase64).name("img2.jpg").build()
                ))
                .emailDataSourceId(emailDataSource.getId()).build();
    }
    void create(EmailTypeEnum emailTypeEnum) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json " );
        headers.add("Authorization", this.authToken);
        BatchEmailDto batchEmailDto= buildBatchDtoEmail(emailTypeEnum);
        HttpEntity<?> request = new HttpEntity<BatchEmailDto>(batchEmailDto, headers);
        ResponseEntity<BatchEmail> result = restTemplate.postForEntity("/api/v1/batch-email-handle/", request, BatchEmail.class);
        AssertionErrors.assertEquals("Status code",200, result.getStatusCodeValue());
    }


    void update(EmailTypeEnum emailTypeEnum) {

        BatchEmailDto batchEmailDto= buildBatchDtoEmail(  emailTypeEnum);
        BatchEmail batchEmail=this.batchEmailServices.createEmailBatch(batchEmailDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json " );
        headers.add("Authorization", this.authToken);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id",batchEmail.getId().toString());
        HttpEntity<?> request = new HttpEntity<BatchEmailDto>(batchEmailDto, headers);
        final String url = String.format("/api/v1/batch-email-handle/{id}");
        ResponseEntity<BatchEmail> response = restTemplate.exchange(url, HttpMethod.PUT, request, BatchEmail.class, param);
        AssertionErrors.assertEquals("Status code",200, response.getStatusCodeValue());
    }
}
