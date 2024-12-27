package com.hotsse.busicard.api.emp.service;

import java.util.List;

import net.crizin.KoreanCharacter;
import net.crizin.KoreanRomanizer;
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
	
	public boolean insertEmployee(EmployeeVO emp) throws Exception {
		return (this.employeeDao.insertEmployee(emp) > 0);
	}
	
	public List<DeptVO> getDepts() throws Exception {
		return this.employeeDao.getDepts();
	}
	
	public DeptVO getDept(String deptCd) throws Exception {
		return this.employeeDao.getDept(deptCd);
	}

	public String getRomanizedName(String name) throws Exception {
		return KoreanRomanizer.romanize(name, KoreanCharacter.Type.NameTypical);
	}
}
