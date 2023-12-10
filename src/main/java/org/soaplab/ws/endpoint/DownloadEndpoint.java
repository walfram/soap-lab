package org.soaplab.ws.endpoint;

import jakarta.activation.DataHandler;
import lab.soap.protocol.DownloadFileItem;
import lab.soap.protocol.DownloadFileRequest;
import lab.soap.protocol.DownloadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaplab.Namespace;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class DownloadEndpoint {

  private static final Logger logger = LoggerFactory.getLogger(DownloadEndpoint.class);

  @PayloadRoot(localPart = "DownloadFileRequest", namespace = Namespace.URI)
  @ResponsePayload
  public DownloadFileResponse download(@RequestPayload DownloadFileRequest request) {
    logger.debug("download file request = {}", request);

    DownloadFileResponse response = new DownloadFileResponse();

    for (String fileId : request.getFileIds()) {
      DownloadFileItem item = new DownloadFileItem();

      item.setFileId(fileId);

      DataHandler content = new DataHandler(new StringDataSource(fileId, "file-content::%s".formatted(fileId)));
      item.setContent(content);

      response.getItems().add(item);
    }

    return response;
  }

}
