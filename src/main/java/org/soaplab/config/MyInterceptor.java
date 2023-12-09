package org.soaplab.config;

import static java.util.Optional.ofNullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

public class MyInterceptor implements EndpointInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
  
  @Override
  public boolean handleRequest(MessageContext messageContext, Object endpoint) {
    logger.debug(">>> REQUEST, message = {}, endpoint = {}", messageContext, endpoint);
    return true;
  }

  @Override
  public boolean handleResponse(MessageContext messageContext, Object endpoint) {
    logger.debug("<<< RESPONSE, message = {}, endpoint = {}", messageContext, endpoint);
    return true;
  }

  @Override
  public boolean handleFault(MessageContext messageContext, Object endpoint) {
    logger.debug("!!! FAULT, message = {}, endpoint = {}", messageContext, endpoint);
    return true;
  }

  @Override
  public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) {
    logger.debug("*** AFTER COMPLETION, message = {}, endpoint = {}, exception = {}", messageContext, endpoint, ofNullable(ex));
  }
}
