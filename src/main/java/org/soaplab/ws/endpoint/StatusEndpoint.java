package org.soaplab.ws.endpoint;

import lab.soap.pets.StatusResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StatusEndpoint {
  
  private static final String NAMESPACE = "http://soap.lab/pets";

  @PayloadRoot(localPart = "StatusResponse", namespace = NAMESPACE)
  @ResponsePayload
  public StatusResponse status() {
    StatusResponse statusResponse = new StatusResponse();
    
    statusResponse.setCode("200");
    statusResponse.setMessage("OK");
    
    return statusResponse;
  }
  
}
