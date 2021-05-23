package com.hotsse.busicard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotsse.busicard.api.busicard.service.BusicardService;

@SpringBootTest
class BusicardApplicationTests {
	
	
	
	@Autowired
	private BusicardService busicardService;
	
	@Test
	void contextLoads() throws Exception {
		
		String empNo = "24687";
		this.busicardService.createBusicard(empNo);
	}
	
	
}
