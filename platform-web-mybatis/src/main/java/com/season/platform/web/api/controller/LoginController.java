package com.season.platform.web.api.controller;

import com.season.platform.web.util.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jiyc on 2017/3/3.
 */
@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login() {
		return "login";
	}


	@RequestMapping("/do_login")
	public String doLogin(HttpServletRequest request, Model model) {

		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");

		boolean rememberMe = false;

		String md5Pwd = Md5Util.generatePassword(pwd);

		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, md5Pwd, rememberMe);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);


			return "redirect:/user/list";
		} catch (LockedAccountException lae) {
			lae.printStackTrace();
			model.addAttribute("msg", "账号已被禁用");
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			model.addAttribute("msg", "账号或密码错误");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "登录异常");
		}
		return "login";
	}

	@RequestMapping("/login_out")
	public String loginOut(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login";
	}
}
