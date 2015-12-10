package com.trace.service.trace;

import java.util.Map;

import com.trace.service.domain.Trace;

public interface TraceService {
	//SELECT member
	public void jsonAddTrace(Trace trace) throws Exception;
	
	public Map<String, Object> getTraceList(String traceId) throws Exception;
	
	public int updateTrace(Trace trace) throws Exception;


}
