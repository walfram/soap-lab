package org.soaplab.ws;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaplab.Namespace;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.xml.transform.ResourceSource;

public class MarshallingProcessorTest {

  private static final Logger logger = LoggerFactory.getLogger(MarshallingProcessorTest.class);

  Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

  {
    marshaller.setContextPath(Namespace.CONTEXT_PATH);
    marshaller.setMtomEnabled(true);
  }

  MarshallingPayloadMethodProcessor processor = new MarshallingPayloadMethodProcessor(marshaller);

  @Test
  public void test_xml_to_soap_message() throws IOException {
    ClassPathResource content = new ClassPathResource("ws/upload-payload.xml");
    ResourceSource source = new ResourceSource(content);

    Object unmarshalled = marshaller.unmarshal(source);

    assertNotNull(unmarshalled);

    logger.debug("unmarshalled to {}", unmarshalled.getClass());
  }

  @Test
  public void test() {
    MessageContext messageContext = mock(MessageContext.class);

    SaajSoapMessage saajSoapMessageMock = mock(SaajSoapMessage.class);
    when(messageContext.getRequest()).thenReturn(saajSoapMessageMock);

    SoapEnvelope envelopeMock = mock(SoapEnvelope.class);
    when(saajSoapMessageMock.getEnvelope()).thenReturn(envelopeMock);

    MethodParameter methodParameter = mock(MethodParameter.class);

    assertDoesNotThrow(() -> {
      processor.resolveArgument(messageContext, methodParameter);
    });
  }

}
