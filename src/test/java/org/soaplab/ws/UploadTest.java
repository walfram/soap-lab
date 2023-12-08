package org.soaplab.ws;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.URLDataSource;
import java.io.IOException;
import java.util.List;
import lab.soap.pets.UploadFileRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaplab.MtomClientConfig;
import org.soaplab.SaajMtomClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.server.EndpointAdapter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(MtomClientConfig.class)
class UploadTest {

  private static final Logger logger = LoggerFactory.getLogger(UploadTest.class);

  @Autowired
  SaajMtomClient client;

  @Autowired
  List<EndpointAdapter> adapters;
  
  @Test
  void should_list_adapters() {
    logger.debug("adapters = {}", adapters);
  }
  
  @Test
  void should_upload_file_using_mtom_without_exception() throws IOException {
    UploadFileRequest request = new UploadFileRequest();

    DataSource dataSource = new URLDataSource(new ClassPathResource("data/upload-test.txt").getURL());
    DataHandler content = new DataHandler(dataSource);
    request.setContent(content);

    Object response = client.getWebServiceTemplate().marshalSendAndReceive(request);
    logger.debug("response = {}", response);

    assertNotNull(response);
  }

}
