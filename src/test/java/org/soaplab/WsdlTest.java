package org.soaplab;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WsdlTest {

  private static final Logger logger = LoggerFactory.getLogger(WsdlTest.class);
  
  @Autowired
  TestRestTemplate restTemplate;
  
  @Test
  public void should_return_valid_wsdl() {
    ResponseEntity<String> entity = restTemplate.getForEntity("/ws/soap-lab-service.wsdl", String.class);
    logger.debug("wsdl = {}", entity.getBody());
    boolean isOk = entity.getStatusCode() == HttpStatus.OK;
    assertTrue(isOk);
  }
  
}
