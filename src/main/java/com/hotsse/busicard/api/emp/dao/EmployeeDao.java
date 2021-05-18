package com.hotsse.busicard.api.emp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.hotsse.busicard.api.emp.vo.DeptVO;
import com.hotsse.busicard.api.emp.vo.EmployeeVO;

@Repository
public class EmployeeDao {

	@Autowired
    @Qualifier("sqlSessionTemplate")
    protected SqlSession sqlSession;
	
	public EmployeeVO getEmployee(String empNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("empNo", empNo);
		
		return this.sqlSession.selectOne("emp.employee.getEmployee", param);
	}
	
	public EmployeeVO getEmployeeEn(String empNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("empNo", empNo);
		
		return this.sqlSession.selectOne("emp.employee.getEmployeeEn", param);
	}
	
	public int insertEmployee(EmployeeVO emp) throws Exception {
		return this.sqlSession.insert("emp.employee.insertEmployee", emp);
	}
	
	public List<DeptVO> getDepts() throws Exception {
		return this.sqlSession.selectList("emp.employee.getDepts");
	}
}
