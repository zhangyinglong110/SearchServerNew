package com.edu.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.edu.global.Global;

/**
 * ΢����Ȩ��¼��Servlet
 * 
 * @author Poppy(��Ӧ��)
 *
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		Random random = new Random();
		String randomValue = random.nextInt() + "";
		// ����̨������ɵ�state
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Global.APP_ID
				+ "&redirect_uri=http%3A%2F%2Ftp.feicuiedu.com%3A8081%2FWebRoot%2FcallBackServlet&response_type=code&scope=snsapi_login&state="
				+ randomValue + "#wechat_redirect";
		resp.sendRedirect(url);
	}

}
