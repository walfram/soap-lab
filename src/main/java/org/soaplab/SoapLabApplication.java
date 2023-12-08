package org.soaplab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;

@SpringBootApplication(exclude = {WebServicesAutoConfiguration.class})
public class SoapLabApplication {

  public static void main(String[] args) {
    SpringApplication.run(SoapLabApplication.class, args);
  }

}
