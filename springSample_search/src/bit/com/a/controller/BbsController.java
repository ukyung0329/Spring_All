package bit.com.a.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.BbsParam;
import bit.com.a.service.BbsService;

@Controller
public class BbsController {

	private static Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	BbsService service;
	
	@RequestMapping(value = "bbslist.do", method = RequestMethod.GET)
	public String bbslist(Model model) {
		logger.info("bbslist " + new Date());
		
		List<BbsDto> list = service.getBbsList();
		model.addAttribute("list", list);
		
		return "bbslist";
	}
	
	@RequestMapping(value = "bbswrite.do", method = RequestMethod.GET)
	public String bbswrite() {
		logger.info("bbswrite " + new Date());
		return "bbswrite";
	}
	
	@RequestMapping(value = "bbswriteAf.do", method = RequestMethod.POST)
	public String bbswriteAf(BbsDto dto) {
		logger.info("bbswriteAf " + new Date());
		logger.info(dto.toString());
		
		boolean b = service.writeBbs(dto);
		if(b) {
			return "redirect:/bbslist.do";
		}else {
			return "redirect:/bbswrite.do";
		}		
	}	
	
	@RequestMapping(value = "bbsdetail.do", method = RequestMethod.GET)
	public String bbsdetail(int seq, Model model) {
		logger.info("bbsdetail " + new Date());
		logger.info("seq:" + seq);
		
		// 접속 회수 가산
		service.readCount(seq);
		
		BbsDto bbs = service.getBbs(seq);
		model.addAttribute("bbs", bbs);		
				
		return "bbsdetail";
	}
	
	@RequestMapping(value = "bbsSearchList.do", method=RequestMethod.GET)
	public String bbsSearchList(Model model, BbsParam param) {
		logger.info("BbsController bbsSearchList " + new Date());
		
		List<BbsDto> list = service.getBbsList(param);
		model.addAttribute("bbslist", list);
		
		return "bbslist";
	}
	
}









