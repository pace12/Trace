package com.trace.web.member;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.trace.service.domain.Member;
import com.trace.service.member.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	@Qualifier("memberServiceImpl")
	private MemberService memberService;
		
	public MemberController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="getJsonMember", method=RequestMethod.POST)
	public void getJsonMember(@RequestBody Member member,
														Model model) throws Exception{

		Member members = memberService.getMember(member.getMemberId());
		model.addAttribute("member", members);
	}
	
	@RequestMapping(value="jsonLogin", method=RequestMethod.POST)
	public void jsonLogin(@RequestBody Member member,
								HttpSession session, Model model) throws Exception{
		
		Member dbMember = memberService.getMember(member.getMemberId());
		System.out.println("dbMember :" + dbMember);
		if(dbMember != null && member.getMemberPwd().equals(dbMember.getMemberPwd())){
			session.setAttribute("member", dbMember);
			model.addAttribute("member", dbMember);
		} else {
			model.addAttribute("member", null);
		}
	}
	

	@RequestMapping( value="jsonAddMember", method=RequestMethod.POST)
	public void jsonAddMember(@RequestBody Member member,Model model) throws Exception{
		
		System.out.println("/user/jsonLogin : POST");
		
		System.out.println(member.toString());
		
		int rows = memberService.jsonAddMember(member);
		model.addAttribute("member", rows);
	}
	
	@RequestMapping(value="upload", method=RequestMethod.POST)
	public void upload(@RequestParam String memberId, 
			MultipartHttpServletRequest request) throws Exception{
		
		System.out.println("Member Upload...");
		
		String genId = null;
		String extName = null;
		int fileIndex = 0;
		String filePath = "C:/workspace/Trace/WebContent/traceupload/";
		
		MultipartFile mpf = null;
		
		Iterator<String> itr = request.getFileNames();
				
		while(itr.hasNext()){
			mpf = request.getFile(itr.next());

			genId = UUID.randomUUID().toString();
			fileIndex = mpf.getOriginalFilename().lastIndexOf(".");
			extName = mpf.getOriginalFilename().substring(fileIndex, mpf.getOriginalFilename().length());

			Member member = new Member();
			member.setMemberId(memberId);
			member.setStoImgName(genId + extName);
			
			updateMember(member);
			
			try{
				FileCopyUtils.copy(mpf.getBytes(), 
						new FileOutputStream(filePath + genId + extName));	
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="updateStoredImageName")
	public void updateMember(Member member) throws Exception{
		
		int rows = memberService.updateMember(member);
		System.out.println(rows);
	}
}
