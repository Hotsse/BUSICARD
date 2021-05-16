package com.hotsse.busicard.api.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.hotsse.busicard.api.common.vo.CmCdVO;

@Repository
public class CommonDao {

	@Autowired
    @Qualifier("sqlSessionTemplate")
    protected SqlSession sqlSession;
	
	public List<CmCdVO> getCmCds(String cm) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cm", cm);
		
		return this.sqlSession.selectList("common.common.getCmCds", param);
	}
}
