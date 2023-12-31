package org.soaplab.ws.endpoint;

import lab.soap.protocol.StatusRequest;
import lab.soap.protocol.StatusResponse;
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
  
  @PayloadRoot(localPart = "StatusRequest", namespace = Namespace.URI)
  @ResponsePayload
  public StatusResponse status(@RequestPayload StatusRequest request) {
    logger.debug("received status request = {}", request);

    StatusResponse statusResponse = new StatusResponse();
    statusResponse.setCode(200);
    statusResponse.setMessage("OK");
    
    return statusResponse;
  }
  
}
