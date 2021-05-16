package com.hotsse.busicard.web.emp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/emp")
public class EmpController {

	@GetMapping(value = "/signup")
	public String signUp() throws Exception {
		return "pages/emp/signup";
	}
}
