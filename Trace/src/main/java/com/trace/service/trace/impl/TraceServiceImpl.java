package com.trace.service.trace.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trace.service.domain.Trace;
import com.trace.service.trace.TraceDao;
import com.trace.service.trace.TraceService;



@Service("traceServiceImpl")
public class TraceServiceImpl implements TraceService {
	
	@Autowired
	@Qualifier("traceDaoImpl")
	private TraceDao traceDao;
	public void setTraceDao(TraceDao traceDao){
		this.traceDao = traceDao;
	}
	
	public TraceServiceImpl() {
		System.out.println("\nCommon :: "+this.getClass()+"\n");
	}
	
	
	@Override
	public void jsonAddTrace(Trace trace) throws Exception {
		traceDao.jsonAddTrace(trace);
	}
	
	@Override
	public Map<String, Object> getTraceList(String traceId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Trace> list = traceDao.getPurchaseList(traceId);
		map.put("list", list);
		return map;
	}

	@Override
	public int updateTrace(Trace trace) throws Exception {
		return traceDao.updateTrace(trace);
	}
}
