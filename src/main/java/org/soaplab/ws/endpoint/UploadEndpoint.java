package org.soaplab.ws.endpoint;

import java.util.UUID;
import lab.soap.pets.UploadFileRequest;
import lab.soap.pets.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaplab.Namespace;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UploadEndpoint {

  private static final Logger logger = LoggerFactory.getLogger(UploadEndpoint.class);

  @PayloadRoot(localPart = "UploadFileRequest", namespace = Namespace.NAMESPACE)
  @ResponsePayload
  public UploadFileResponse upload(@RequestPayload UploadFileRequest request) {
    logger.debug("file upload request = {}", request);
    logger.debug("file = {}", request.getContent());

    UploadFileResponse uploadFileResponse = new UploadFileResponse();
    uploadFileResponse.setFileId("1234");
    
    return uploadFileResponse;
  }
  
}
