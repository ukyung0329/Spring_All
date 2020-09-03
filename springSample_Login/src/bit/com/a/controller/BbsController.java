package bit.com.a.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.service.MemberService;

@Controller
public class BbsController {
	
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired	// DI IOC
	MemberService memberService;

	
	@RequestMapping(value = "bbslist.do", method=RequestMethod.GET)
	public String login() {
		logger.info("login " + new Date());
		return "bbslist";
	}



}
