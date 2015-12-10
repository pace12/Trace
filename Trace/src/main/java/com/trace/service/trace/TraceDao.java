package com.trace.service.trace;

import java.util.List;

import com.trace.service.domain.Trace;

public interface TraceDao {
	//SELECT member
	public void jsonAddTrace(Trace Trace) throws Exception;
	
	public List<Trace> getPurchaseList(String traceId) throws Exception;
	
	public int updateTrace(Trace trace) throws Exception;
}
