package org.soaplab;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

public class SaajMtomClient extends WebServiceGatewaySupport {

  public SaajMtomClient(SaajSoapMessageFactory messageFactory) {
    super(messageFactory);
  }
}
