package com.trace.web.trace;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.trace.service.domain.Trace;
import com.trace.service.trace.TraceService;



@Controller
@RequestMapping("/trace/*")
public class TraceController {
	@Autowired ServletContext servletCtx;

	@Autowired
	@Qualifier("traceServiceImpl")
	private TraceService traceService;
		
	public TraceController() {
		System.out.println("\nCommon :: "+this.getClass()+"\n");
	}
		
	@RequestMapping( value="jsonAddTrace", method=RequestMethod.POST )
	public @ResponseBody Trace jsonAddTrace(@RequestBody Trace trace) throws Exception{
		
		System.out.println("jsonAddTrace : POST");
		
		System.out.println("trace : "+trace.getText());
		trace.setTraceId("user01");
		trace.setLatitude("100");
		trace.setLongtitude("100");
		System.out.println(trace.toString());
		
		traceService.jsonAddTrace(trace);
		return trace;
	}
	
	@RequestMapping(value="upload", method=RequestMethod.POST)
	public void upload(@RequestParam("files") List<MultipartFile> images, @RequestParam("traceNo") int traceNo) throws Exception{
		
		System.out.println("Trace Upload...");
		
		int fileIndex = 0;
		int totalFile = 0;
		
		String genId = null;
		String extName = null;
		String stoImageFileName = "";

		String filePath = servletCtx.getRealPath("/traceupload") + "/";

		for(MultipartFile mpf: images){
			
			totalFile++;
			genId = UUID.randomUUID().toString();
			fileIndex = mpf.getOriginalFilename().lastIndexOf(".");
			extName = mpf.getOriginalFilename().substring(fileIndex, mpf.getOriginalFilename().length());
			
			if(totalFile != images.size()){
				stoImageFileName += genId + extName + ",";
			} else{
				stoImageFileName += genId + extName;
			}
			
			try{
				FileCopyUtils.copy(mpf.getBytes(), 

						new FileOutputStream(filePath + genId + extName));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		Trace trace = new Trace();
		trace.setTraceNo(traceNo);
		trace.setStoImgName(stoImageFileName);
		
		updateTrace(trace);
	}
	
	@RequestMapping(value="listTrace")
	public void listTrace(Model model) throws Exception{
		System.out.println("/trace/listTrace");

		Trace trace = new Trace();
		trace.setTraceId("user01");
		
		Map<String, Object> map = traceService.getTraceList(trace.getTraceId());

		model.addAttribute("list", map.get("list"));		
	}
	
	@RequestMapping(value="updateTrace")
	public int updateTrace(Trace trace) throws Exception{
		return traceService.updateTrace(trace);
	}
	
}
