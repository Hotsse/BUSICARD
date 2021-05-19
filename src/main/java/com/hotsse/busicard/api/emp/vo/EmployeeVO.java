package com.hotsse.busicard.api.emp.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeVO {

	private String empNo;
	private String empNm;
	private String empNmEn;
	private String deptCd;
	private String empPosCd;
	private String empPosNm;
	private String empPosNmEn;
	
	private String hp;
	private String tel;
	private String email;
}
