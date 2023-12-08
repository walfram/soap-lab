package org.soaplab.config;

import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaplab.Namespace;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.adapter.method.MessageContextMethodArgumentResolver;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class WsConfig extends WsConfigurerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(WsConfig.class);
  
  @Override
  public void addInterceptors(List<EndpointInterceptor> interceptors) {
    interceptors.add(new MyInterceptor());
    interceptors.add(new SoapEnvelopeLoggingInterceptor());
    logger.debug("interceptors = {}", interceptors);
  }
  
  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(servlet, "/ws/*");
  }

  // https://docs.spring.io/spring-ws/docs/current/reference/html/#server-automatic-wsdl-exposure
  @Bean(name = "pets-service")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("PetsPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://soap.lab/pets");
    wsdl11Definition.setSchema(schema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema petsSchema() {
    return new SimpleXsdSchema(new ClassPathResource("xsd/protocol.xsd"));
  }

  // https://github.com/spring-projects/spring-ws-samples/blob/main/mtom/server/src/main/java/org/springframework/ws/samples/mtom/config/MtomServerConfiguration.java
  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath(Namespace.CONTEXT_PATH);
    marshaller.setMtomEnabled(true);
    return marshaller;
  }

  @Bean
  public MarshallingPayloadMethodProcessor methodProcessor(Jaxb2Marshaller marshaller) {
    return new MarshallingPayloadMethodProcessor(marshaller);
  }

  @Bean
  public DefaultMethodEndpointAdapter endpointAdapter(MarshallingPayloadMethodProcessor methodProcessor) {
    DefaultMethodEndpointAdapter adapter = new DefaultMethodEndpointAdapter();
    
    adapter.setMethodArgumentResolvers(List.of(methodProcessor, new MessageContextMethodArgumentResolver()));
    adapter.setMethodReturnValueHandlers(List.of(methodProcessor));
    
    logger.info("method args resolvers = {}", adapter.getMethodArgumentResolvers());
    
    return adapter;
  }
  
}
