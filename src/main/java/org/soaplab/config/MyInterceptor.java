package org.soaplab.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

public class MyInterceptor implements EndpointInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
  
  @Override
  public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
    logger.debug(">>> REQUEST, message = {}, endpoint = {}", messageContext, endpoint);
    return true;
  }

  @Override
  public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
    logger.debug("<<< RESPONSE, message = {}, endpoint = {}", messageContext, endpoint);
    return true;
  }

  @Override
  public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
    logger.debug("!!! FAULT, message = {}, endpoint = {}", messageContext, endpoint);
    return true;
  }

  @Override
  public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
    logger.debug("*** AFTER COMPLETION, message = {}, endpoint = {}, exception = {}", messageContext, endpoint, ex.getMessage());
  }
}
