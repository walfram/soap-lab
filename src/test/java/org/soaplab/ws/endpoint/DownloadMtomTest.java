package org.soaplab.ws.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import lab.soap.protocol.DownloadFileItem;
import lab.soap.protocol.DownloadFileRequest;
import lab.soap.protocol.DownloadFileResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaplab.MtomClientConfig;
import org.soaplab.SaajMtomClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(MtomClientConfig.class)
class DownloadMtomTest {

  private static final Logger logger = LoggerFactory.getLogger(DownloadMtomTest.class);
  
  @LocalServerPort
  int port;
  
  @Autowired
  SaajMtomClient client;
  
  @BeforeEach
  void setUp() {
    client.setDefaultUri("http://localhost:" + port + "/ws");
  }
  
  @Test
  void should_download_multiple_files() {
    List<String> fileIds = List.of("1", "2", "3");
    
    DownloadFileRequest request = new DownloadFileRequest();
    request.getFileIds().addAll(fileIds);

    DownloadFileResponse response = (DownloadFileResponse) client.getWebServiceTemplate().marshalSendAndReceive(request);
    
    assertNotNull(response);
    logger.debug("response = {}", response);

    List<DownloadFileItem> items = response.getItems();
    
    assertNotNull(items);
    assertEquals(3, items.size());

    List<String> actualFileIds = items.stream().map(DownloadFileItem::getFileId).toList();
    assertEquals(fileIds, actualFileIds);
  }
  
}
