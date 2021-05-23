package com.hotsse.busicard.web.busicard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotsse.busicard.api.busicard.service.BusicardService;

@Controller
@RequestMapping(value = "/busicard")
public class BusicardController {

	@Autowired
	private BusicardService busicardServie;
	
	@GetMapping(value = "")
	public String createBusicard() throws Exception {
		return "pages/busicard/create";
	}
	
}
