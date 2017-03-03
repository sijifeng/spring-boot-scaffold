package com.season.platform.web.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by jiyc on 2017/3/3.
 */
public class LoginAop {
	private Logger logger = LoggerFactory.getLogger(LoginAop.class);


	@Pointcut(value="execution(* com.season.platform.web.api.controller.LoginController.doLogin(..))))")
	private void loginLog() {
	}

	@Before("loginLog()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
	}

	@After("loginLog()")
	public void doAfter() {
		/*String account = (String)SessionUtil.getAttr("account");
		String msg = (String)SessionUtil.getAttr("msg");
		if(Validator.isNotNullOrEmpty(msg)){
			this.writeLoginLog("登录失败",msg,account);
		}else{
			this.writeLoginLog("登录成功",null,account);
		}*/
	}

}
