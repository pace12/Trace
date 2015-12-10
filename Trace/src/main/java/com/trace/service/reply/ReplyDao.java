package com.trace.service.reply;

import java.util.List;

import com.trace.service.domain.Reply;

public interface ReplyDao {
	//SELECT member
	public void jsonAddReply(Reply reply) throws Exception;
	
	public List<Reply> jsonGetReply(int traceNo) throws Exception;
	
}
