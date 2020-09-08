package bit.com.a.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.dto.MemberDto;
import bit.com.a.service.MemberService;

@Controller		// view 이동, service data 
public class MemberController {

	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired	// DI IOC
	MemberService memberService;
	/*
	@RequestMapping(value = "allMember.do", method = RequestMethod.GET)
	public String allmember(Model model) {
		logger.info(" allmember " + new Date());
		
		List<MemberDto> list = memberService.allMember();
		model.addAttribute("memlist", list);
		
		return "allMember";
	}
	*/
	
	@RequestMapping(value = "login.do", method=RequestMethod.GET)
	public String login() {
	//	logger.info("login " + new Date());
		return "login";
	}
	
	@RequestMapping(value = "regi.do", method=RequestMethod.GET)
	public String regi() {
	//	logger.info("regi " + new Date());
		return "regi";
	}
	
	@ResponseBody
	@RequestMapping(value="idcheck.do", method=RequestMethod.POST)
	public String idcheck(MemberDto mem) {
	//	logger.info("getId " + new Date());
		
		int count = memberService.getId(mem);
		
		logger.info("count = " + count);
		String msg = "";
		if(count > 0) {
			msg = "NO";
		}else {
			msg = "YES";
		}
		return msg;
	}
	
	@RequestMapping(value = "regiAf.do", method=RequestMethod.POST)
	public String regiAf(MemberDto dto) {
	//	logger.info("regiAf " + new Date());
		
		boolean b = memberService.addmember(dto);
		if(b) {
			logger.info("회원 가입되었습니다 " + new Date());
			return "login";
		}
		logger.info("가입되지 않았습니다 " + new Date());
		
		return "regi";
	}
	
	@RequestMapping(value = "loginAf.do", method=RequestMethod.POST)
//	public String loginAf(String id, String pwd) {
	public String loginAf(MemberDto dto, HttpServletRequest req, Model model) {	
	//	logger.info("loginAf " + new Date());
		logger.info(dto.toString());
		
		MemberDto mem = memberService.login(dto);
		// logger.info(mem.toString());
		// 성공 시	 -> 게시판
		if(mem != null && !mem.getId().equals("")) {
			// session 저장
			req.getSession().setAttribute("login", mem);
			// req.getSession().setMaxInactiveInterval(60);
			
			return "redirect:/bbslist.do";
		}				
		// 실패 시 -> alert
		else {
			// redirectMove.jsp(alert, location)
			model.addAttribute("msg", "아이디나 패스워드가 틀렸습니다");
			model.addAttribute("url", "login.do");
			
			return "redirectMove";
		}
		
		// return "login";	// 임시로.. 설정		
	}
	
	@ResponseBody
	@RequestMapping(value = "loginCheck.do", method=RequestMethod.POST)
	public String loginCheck(MemberDto dto, HttpServletRequest req) {
	//	logger.info("loginCheck " + new Date());
		logger.info(dto.toString());
		
		MemberDto mem = memberService.login(dto);
		if(mem != null && !mem.getId().equals("")) {
			// session 저장
			req.getSession().setAttribute("login", mem);			
			return "YES";
		}
		
		return "NO";
	}
	
	
	
	
}











