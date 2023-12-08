package org.soaplab;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

@Configuration
public class MtomClientConfig {
  @Bean
  public SaajMtomClient saajClient(SaajSoapMessageFactory messageFactory, Jaxb2Marshaller marshaller) {
    SaajMtomClient client = new SaajMtomClient(messageFactory);
    client.setDefaultUri("http://localhost:8080/ws");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }

  @Bean
  public SaajSoapMessageFactory saajSoapMessageFactory() {
    return new SaajSoapMessageFactory();
  }

//  @Bean
//  public Jaxb2Marshaller marshaller() {
//    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//    marshaller.setContextPath(Namespace.CONTEXT_PATH);
//    marshaller.setMtomEnabled(true);
//    return marshaller;
//  }
}
