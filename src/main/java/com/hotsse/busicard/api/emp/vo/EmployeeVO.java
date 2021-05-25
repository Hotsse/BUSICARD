package com.hotsse.busicard.api.emp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
