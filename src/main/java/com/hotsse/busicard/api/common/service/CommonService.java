package com.hotsse.busicard.api.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotsse.busicard.api.common.dao.CommonDao;
import com.hotsse.busicard.api.common.vo.CmCdVO;

@Service
public class CommonService {

	@Autowired
	private CommonDao commonDao;
	
	public List<CmCdVO> getCmCds(String cm) throws Exception {
		return this.commonDao.getCmCds(cm);
	}
}
