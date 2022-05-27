package io.base.coreapi.config;

import com.fasterxml.jackson.databind.JsonNode;
import io.base.coreapi.util.PageSerializer;
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



    @Test
    public void updateElement() {
        ResponseEntity<JsonNode> element=requestToCreateElement();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  this.authToken);
        headers.add("Content-Type",  "application/json");


        JsonNode currentElement=element.getBody();
        String currentId=currentElement.get("id").asText();
        HttpEntity<?> request = new HttpEntity<Object>(bodyForUpdate, headers);

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

    public ResponseEntity<JsonNode>  requestToCreateElement()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  this.authToken);
        headers.add("Content-Type",  "application/json");




        HttpEntity<?> request = new HttpEntity<Object>(bodyForCreate, headers);
        ResponseEntity<JsonNode> result = this.restTemplate.exchange(this.baseUrl, HttpMethod.POST,  request, JsonNode.class);
        return result;
    }

    @Test
    public void testGetElements() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  this.authToken);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        HttpEntity<?> request = new HttpEntity<Object>(body, headers);
        ResponseEntity<PageSerializer> result = this.restTemplate.exchange(this.baseUrl, HttpMethod.GET, request, PageSerializer.class);
        AssertionErrors.assertEquals("Status code", HttpStatus.OK.value(), result.getStatusCode().value());

    }
}
