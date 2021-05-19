package com.hotsse.busicard.api.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotsse.busicard.api.common.service.CommonService;
import com.hotsse.busicard.api.common.vo.CmCdVO;
import com.hotsse.busicard.api.emp.service.EmployeeService;
import com.hotsse.busicard.api.emp.vo.DeptVO;
import com.hotsse.busicard.api.emp.vo.EmployeeVO;
import com.hotsse.busicard.trdparty.naver.romanizer.service.NaverRomanizerService;
import com.hotsse.busicard.trdparty.naver.romanizer.vo.NaverRomanizerItem;

@RestController(value = "apiEmpController")
@RequestMapping(value = "/api/emp", produces = "application/json;charset=utf-8")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private NaverRomanizerService romanizerService;
	
	@GetMapping(value = "/romanize")
	public ResponseEntity<List<NaverRomanizerItem>> getRomanizedItems(
			@RequestParam(name = "name", required = true) String name) throws Exception {
		
		List<NaverRomanizerItem> items = this.romanizerService.getItems(name);
		return ResponseEntity.ok(items);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<Void> insertEmployee(EmployeeVO emp) throws Exception {
		System.out.println(emp.toString());
		
		this.employeeService.insertEmployee(emp);
		
		return ResponseEntity.noContent()
				.build();
	}
	
	@GetMapping(value = "/depts")
	public ResponseEntity<List<DeptVO>> getDepts() throws Exception {
		
		List<DeptVO> depts = this.employeeService.getDepts();
		return ResponseEntity.ok(depts);
	}
	
	@GetMapping(value = "/empPoses")
	public ResponseEntity<List<CmCdVO>> getEmpPoses() throws Exception {
		
		List<CmCdVO> cmCds = this.commonService.getCmCds("100");
		return ResponseEntity.ok(cmCds);
	}
}
