package org.soaplab;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SoapLabApplicationTests {

	@Autowired
	ApplicationContext context;
	
	@Test
	void contextLoads() {
		assertNotNull(context);
	}

}
