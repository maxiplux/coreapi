package io.base.coreapi.config;

import io.base.coreapi.model.Role;
import io.base.coreapi.model.User;
import io.base.coreapi.model.dto.TokenDto;
import io.base.coreapi.model.dto.UserDto;
import io.base.coreapi.repositories.RoleRepository;
import io.base.coreapi.repositories.UserRepository;
import io.base.coreapi.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Slf4j
public   abstract  class AbstractAuthTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    protected String authToken = "";
    private final String authUrl="/api/authentication/login/";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() throws Exception {
        this.dologin();
        this.extraSetupSteps();
    }


    @AfterEach
    public void tearDown() throws Exception {
        this.extraTearDownSteps();
    }

    public abstract void extraTearDownSteps() ;

    public abstract void extraSetupSteps()  ;

    private void dologin() {
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
}
