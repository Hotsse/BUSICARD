package com.hotsse.busicard.web.busicard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/busicard")
public class BusicardController {
	
	@GetMapping(value = "")
	public String createBusicard() throws Exception {
		return "pages/busicard/create";
	}
	
	@GetMapping(value = "/parse")
	public String parseBusicard() throws Exception {
		return "pages/busicard/parse";
	}
}
