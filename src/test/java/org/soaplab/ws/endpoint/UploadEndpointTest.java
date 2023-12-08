package org.soaplab.ws.endpoint;

import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.soapEnvelope;
import static org.springframework.ws.test.server.ResponseMatchers.validPayload;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.soaplab.config.WsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;

@WebServiceServerTest(endpoints = {UploadEndpoint.class}, excludeAutoConfiguration = {WebServicesAutoConfiguration.class})
@Import({WsConfig.class})
class UploadEndpointTest {

  @Autowired
  MockWebServiceClient mockClient;
  
  @Test
  void should_upload_file() throws IOException {
    RequestCreator request = RequestCreators.withSoapEnvelope(
        new ClassPathResource("ws/upload-request.xml"));

    mockClient
        .sendRequest(request)
        .andExpect(noFault())
        .andExpect(validPayload(new ClassPathResource("xsd/protocol.xsd")))
        .andExpect(soapEnvelope(new ClassPathResource("ws/upload-response.xml")));
  }
  
}
