package com.hotsse.busicard.api.emp.controller;

import com.hotsse.busicard.api.common.service.CommonService;
import com.hotsse.busicard.api.common.vo.CmCdVO;
import com.hotsse.busicard.api.emp.service.EmployeeService;
import com.hotsse.busicard.api.emp.vo.DeptVO;
import com.hotsse.busicard.api.emp.vo.EmployeeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "apiEmpController")
@RequestMapping(value = "/api/emp")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	private final CommonService commonService;
	
	@GetMapping(value = "/romanize")
	public ResponseEntity<List<String>> getRomanizedItems(
			@RequestParam(name = "name", required = true) String name) throws Exception {

		String romanizedName = this.employeeService.getRomanizedName(name);
		return ResponseEntity.ok(List.of(romanizedName));
	}
	
	@PostMapping(value = "")
	public ResponseEntity<Void> insertEmployee(@RequestBody EmployeeVO emp) throws Exception {
		
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
