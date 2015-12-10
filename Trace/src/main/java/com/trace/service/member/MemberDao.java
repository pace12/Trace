package com.trace.service.member;

import com.trace.service.domain.Member;

public interface MemberDao {

	public Member getMember(String memberId) throws Exception;
	public int jsonAddMember(Member member) throws Exception;
	public int updateMember(Member member) throws Exception;

}
