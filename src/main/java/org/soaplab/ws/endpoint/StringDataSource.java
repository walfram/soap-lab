package org.soaplab.ws.endpoint;

import jakarta.activation.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StringDataSource implements DataSource {

  private final String name;
  private final String source;

  public StringDataSource(String name, String source) {
    this.name = name;
    this.source = source;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public OutputStream getOutputStream() throws IOException {
    return new ByteArrayOutputStream(1024);
  }

  @Override
  public String getContentType() {
    return "text/plain";
  }

  @Override
  public String getName() {
    return name;
  }
}
