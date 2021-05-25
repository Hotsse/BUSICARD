package com.hotsse.busicard.api.busicard.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotsse.busicard.api.busicard.constants.CardTypeEnum;
import com.hotsse.busicard.api.busicard.service.BusicardService;

@RestController(value = "apiBusiCardController")
@RequestMapping(value = "/api/busicard")
public class BusicardController {

	@Autowired
	private BusicardService busicardService;
	
	@GetMapping(value = "/download/{cardType}")
	public void downloadBusicard(
			@RequestParam(name = "empNo", required = true) String empNo
			, @PathVariable(name = "cardType", required = true) String strCardType
			, HttpServletResponse res) throws Exception {
		
		CardTypeEnum cardType = CardTypeEnum.findBy(strCardType);
		if(cardType == null) {
			res.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		
		this.busicardService.downloadBusicard(empNo, cardType, res);
	}
}
