package org.soaplab.ws.endpoint;

import static java.util.Optional.ofNullable;

import lab.soap.pets.UploadFileRequest;
import lab.soap.pets.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaplab.Namespace;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

@Endpoint
public class UploadEndpoint {

  private static final Logger logger = LoggerFactory.getLogger(UploadEndpoint.class);

  @PayloadRoot(localPart = "UploadFileRequest", namespace = Namespace.NAMESPACE)
  @ResponsePayload
  public UploadFileResponse upload(
      @RequestPayload UploadFileRequest request,
      @SoapHeader("{http://soap.lab/pets}client") SoapHeaderElement clientHeaderElement,
      MessageContext messageContext) {
    logger.debug("file upload request = {}", request);
    logger.debug("file = {}", request.getContent());
    logger.debug("client header = {}", ofNullable(clientHeaderElement).map(SoapHeaderElement::getText).orElse(""));
    logger.debug("message context = {}", messageContext);

    UploadFileResponse uploadFileResponse = new UploadFileResponse();
    uploadFileResponse.setFileId("1234");
    
    return uploadFileResponse;
  }
  
}
