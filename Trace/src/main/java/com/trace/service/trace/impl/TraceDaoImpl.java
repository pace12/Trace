package com.trace.service.trace.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import com.trace.service.domain.Trace;
import com.trace.service.trace.TraceDao;


@Repository("traceDaoImpl")
public class TraceDaoImpl implements TraceDao {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}
	
	public TraceDaoImpl(){
		System.out.println("\nCommon :: "+this.getClass()+"\n");
	}
	
	public void jsonAddTrace(Trace trace) throws Exception {
		sqlSession.insert("TraceMapper.jsonAddTrace", trace);
	}
	
	public List<Trace> getPurchaseList(String traceId) throws Exception {
		return sqlSession.selectList("TraceMapper.getTraceList", traceId);
	}

	@Override
	public int updateTrace(Trace trace) throws Exception {
		return sqlSession.update("TraceMapper.updateTrace", trace);
	}
}
