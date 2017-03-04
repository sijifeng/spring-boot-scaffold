package com.season.platform.web.api.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.season.platform.web.util.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by jiyc on 2017/3/3.
 */
@Controller
public class LoginController {
	@Autowired
	private Producer captchaProducerMain;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/abc")
	public String abc() {
		return "abc";
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

	@RequestMapping("/captchaMain-image")
	public ModelAndView getKaptchaImageMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 禁止服务器端缓存
		response.setDateHeader("Expires", 0);

		// 设置标准的 HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		// 设置IE扩展 HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// 设置标准 HTTP/1.0 不缓存图片
		response.setHeader("Pragma", "no-cache");

		// 返回一个 jpeg 图片，默认是text/html(输出文档的MIMI类型)
		response.setContentType("image/jpeg");

		// 为图片创建文本
		String capText = captchaProducerMain.createText();

		// 将文本保存在session中，这里就使用包中的静态变量吧
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		// 创建带有文本的图片
		BufferedImage bi = captchaProducerMain.createImage(capText);
		ServletOutputStream out = response.getOutputStream();

		// 图片数据输出
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
}
