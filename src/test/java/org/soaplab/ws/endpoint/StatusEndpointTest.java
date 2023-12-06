package org.soaplab.ws.endpoint;

import static org.springframework.ws.test.server.ResponseMatchers.noFault;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;

@WebServiceServerTest(StatusEndpoint.class)
class StatusEndpointTest {

  @Autowired
  MockWebServiceClient mockClient;
  
  @Test
  public void should_return_status_200_OK() throws IOException {
    RequestCreator request = RequestCreators.withSoapEnvelope(
        new ClassPathResource("ws/status-endpoint-valid-status-request.xml"));

    mockClient.sendRequest(request)
        .andExpect(noFault());
  }
  

}
