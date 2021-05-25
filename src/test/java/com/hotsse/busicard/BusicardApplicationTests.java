package com.hotsse.busicard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.hotsse.busicard.api.busicard.constants.CardTypeEnum;
import com.hotsse.busicard.api.busicard.service.BusicardService;
import com.hotsse.busicard.api.emp.vo.EmployeeVO;

@SpringBootTest
class BusicardApplicationTests {
	
	@Autowired
	private BusicardService busicardService;
		
	@Test
	void contextLoads() throws Exception {
		
		String empNo = "24687";
		CardTypeEnum cardType = CardTypeEnum.KO;
		
		EmployeeVO emp = this.busicardService.parseBusicard(empNo, cardType);
		System.out.println(emp.toString());
	}
}
