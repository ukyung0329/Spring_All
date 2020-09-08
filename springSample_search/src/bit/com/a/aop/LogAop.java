package bit.com.a.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;

//import bit.com.a.service.BbsService;

@Aspect
public class LogAop {

	/*
	@Autowired
	BbsService service;
	*/
	
	@Around("within(bit.com.a.controller.*) or within(bit.com.a.dao.impl.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint)throws Throwable {
		
		String signatureStr = joinpoint.getSignature().toShortString();		
		//System.out.println(signatureStr + " - 시작");	// 실행전
		
		try {
			System.out.println("loggerAOP:" + signatureStr + " 메소드가 실행되었습니다");
			
			Object obj = joinpoint.proceed();		// 실행시
			
			return obj;
			
		}finally {
			//System.out.println("실행 후:" + System.currentTimeMillis());			
			//System.out.println(signatureStr + " - 종료");	// 실행후
		}		
	}
}








