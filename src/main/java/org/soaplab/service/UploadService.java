package org.soaplab.service;

import jakarta.activation.DataHandler;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

  public String upload(DataHandler content) {
    return content.getName();
  }
}
