package org.soaplab.ws;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.URLDataSource;
import java.io.IOException;
import javax.xml.namespace.QName;
import lab.soap.pets.UploadFileRequest;
import lab.soap.pets.UploadFileResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaplab.MtomClientConfig;
import org.soaplab.Namespace;
import org.soaplab.SaajMtomClient;
import org.soaplab.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(MtomClientConfig.class)
class UploadTest {

  private static final Logger logger = LoggerFactory.getLogger(UploadTest.class);

  @MockBean
  UploadService uploadService;
  
  @Autowired
  SaajMtomClient client;

  @Test
  void should_upload_file_using_mtom_without_exception() throws IOException {
    when(uploadService.upload(any())).thenReturn("upload-test.txt");
    
    UploadFileRequest request = new UploadFileRequest();

    DataSource dataSource = new URLDataSource(new ClassPathResource("data/upload-test.txt").getURL());
    DataHandler content = new DataHandler(dataSource);
    request.setContent(content);

    UploadFileResponse response = (UploadFileResponse) client.getWebServiceTemplate().marshalSendAndReceive(request, message -> {
      SoapMessage soapMessage = (SoapMessage)message;
      SoapHeader header = soapMessage.getSoapHeader();

      SoapHeaderElement clientHeader = header.addHeaderElement(new QName(Namespace.URI, "client"));
      clientHeader.setText("some-client-name");
    });
    
    logger.debug("response = {}", response);
    
    assertNotNull(response);
    assertNotNull(response.getFileId());
  }

}
