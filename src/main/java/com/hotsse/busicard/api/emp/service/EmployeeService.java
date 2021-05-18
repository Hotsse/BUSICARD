package com.hotsse.busicard.api.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotsse.busicard.api.emp.dao.EmployeeDao;
import com.hotsse.busicard.api.emp.vo.DeptVO;
import com.hotsse.busicard.api.emp.vo.EmployeeVO;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	public EmployeeVO getEmployee(String empNo) throws Exception {
		return this.employeeDao.getEmployee(empNo);
	}
	
	public EmployeeVO getEmployeeEn(String empNo) throws Exception {
		return this.employeeDao.getEmployeeEn(empNo);
	}
	
	public boolean insertEmployee(EmployeeVO emp) throws Exception {
		return (this.employeeDao.insertEmployee(emp) > 0);
	}
	
	public List<DeptVO> getDepts() throws Exception {
		return this.employeeDao.getDepts();
	}

}
