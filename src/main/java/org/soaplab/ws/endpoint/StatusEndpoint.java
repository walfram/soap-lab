package org.soaplab.ws.endpoint;

import lab.soap.pets.StatusRequest;
import lab.soap.pets.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaplab.Namespace;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StatusEndpoint {

  private static final Logger logger = LoggerFactory.getLogger(StatusEndpoint.class);
  
//  private static final String NAMESPACE = "http://soap.lab/pets";

  @PayloadRoot(localPart = "StatusRequest", namespace = Namespace.NAMESPACE)
  @ResponsePayload
  public StatusResponse status(@RequestPayload StatusRequest request) {
    logger.debug("received status request = {}", request);

    StatusResponse statusResponse = new StatusResponse();
    statusResponse.setCode(200);
    statusResponse.setMessage("OK");
    
    return statusResponse;
  }
  
}
