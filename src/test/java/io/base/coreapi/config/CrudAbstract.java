package io.base.coreapi.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.base.coreapi.util.PageSerializer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.test.util.AssertionErrors;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class CrudAbstract  extends AbstractAuthTest {
    protected Map<String, Object> bodyForUpdate = new HashMap<>();
    protected  Map<String, Object> bodyForCreate = new HashMap<>();
    protected  String baseUrl="";

    //todo: test for bad request : 400
    //todo: test for unique name exists  : 500



    @Test
    public void updateElement() {
        ResponseEntity<JsonNode> element=requestToCreateElement();


        JsonNode currentElement=element.getBody();
        String currentId=currentElement.get("id").asText();
        AssertionErrors.assertNotNull("Status code updateElement",currentId);
        HttpEntity<?> request = new HttpEntity<Object>(bodyForUpdate, this.headers);

        ResponseEntity<JsonNode> result = this.restTemplate.exchange(this.baseUrl+currentId+"/", HttpMethod.PUT,  request, JsonNode.class);
        log.info("Status of element updateElement {}", result);
        AssertionErrors.assertEquals("Status code updateElement",200, result.getStatusCodeValue());
    }



    @Test
    public void createElement() {
        ResponseEntity<JsonNode>  element=requestToCreateElement();
        log.info("Status of Create element {}", element.getBody());
        AssertionErrors.assertEquals("Status code createElement ",200,element.getStatusCodeValue());
    }

    @SneakyThrows
    public ResponseEntity<JsonNode>  requestToCreateElement()
    {





        HttpEntity<?> request = new HttpEntity<Object>(bodyForCreate, this.headers);

        /*
        MvcResult resultActions=this.mockMvc.perform( MockMvcRequestBuilders
                        .post(this.baseUrl )
                        .content(asJsonString(this.bodyForCreate))
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        resultActions.getRequest()*/

        ResponseEntity<JsonNode> result = this.restTemplate.exchange(this.baseUrl, HttpMethod.POST,  request, JsonNode.class);
        return result;
    }

    @Test
    public void testGetElements() {


        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        HttpEntity<?> request = new HttpEntity<Object>(body, this.headers);
        ResponseEntity<PageSerializer> result = this.restTemplate.exchange(this.baseUrl, HttpMethod.GET, request, PageSerializer.class);
        log.info("Status of request {}", result.getBody());
        AssertionErrors.assertEquals("Status code", HttpStatus.OK.value(), result.getStatusCode().value());

    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();

            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("asJsonString ->  Exception {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
