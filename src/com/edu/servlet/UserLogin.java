package com.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.bean.ResultBean;
import com.edu.util.DataBaseOperaUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "userLogin", urlPatterns = "/userLogin")
public class UserLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=utf-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		System.out.println("username:" + username + ",password:" + password);
		PrintWriter pw = resp.getWriter();// 响应服务器对象
		ResultBean bean = new ResultBean();
		if (!((username == null || "".equals(username)) || (password == null || "".equals(password)))) {
			try {
				int result = DataBaseOperaUtil.chekUserIsExists(username, password);
				if (result > 0) {
					bean.setCode(200);
					bean.setMsg("登录成功");
					JSONObject jsonObject = JSONObject.fromObject(bean);
					pw.print(jsonObject.toString());
				} else {
					bean.setCode(100);
					bean.setMsg("用户名或密码错误");
					JSONObject jsonObject = JSONObject.fromObject(bean);
					pw.print(jsonObject.toString());
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
