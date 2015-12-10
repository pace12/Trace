package com.trace.web.reply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.trace.service.domain.Reply;
import com.trace.service.reply.ReplyService;



@Controller
@RequestMapping("/reply/*")
public class ReplyController {

	@Autowired
	@Qualifier("replyServiceImpl")
	private ReplyService replyService;
		
	public ReplyController() {
		System.out.println("\nCommon :: "+this.getClass()+"\n");
	}
	
	
	@RequestMapping( value="jsonAddReply", method=RequestMethod.POST )
	public void jsonAddReply(@RequestBody Reply reply,HttpServletRequest request, Model model) throws Exception{
		
		System.out.println("jsonAddTrace : POST");
		System.out.println("값 확인"+reply);
		reply.setId("admin");
		reply.setRepLevel(1);
		reply.setRepNo(1);
		reply.setRepStep(1);
		System.out.println("tostring"+reply.toString());
		replyService.jsonAddReply(reply);
		
		List<Reply> list = replyService.jsonGetReply(reply.getTraceNo());
		model.addAttribute("rlist", list.get(0));

	}
	
	@RequestMapping(value="jsonGetReply/{traceNo}", method=RequestMethod.GET)
	public void jsonGetReply(){
		
	}
	
	
	
	
}
