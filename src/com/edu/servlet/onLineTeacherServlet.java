package com.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.edu.bean.OnLineTeacherBean;
import com.edu.bean.OnlineBean;
import com.edu.util.DataBaseOperaUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "OnlineTeacherServlet", urlPatterns = "/OnlineTeacherServlet")
public class onLineTeacherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");// 设置响应的编码格式
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;charset=utf-8");

		String schoolName = req.getParameter("schoolName");

		String majorId = req.getParameter("majorName");

		String role_Level = req.getParameter("role_Level");

		System.out.println(schoolName + ":" + majorId + ":" + role_Level);
		OnLineTeacherBean cb = new OnLineTeacherBean();
		PrintWriter pw = resp.getWriter();
		try {
			OnlineBean onlineBean = DataBaseOperaUtil.getOnlineTeacher(schoolName, majorId, role_Level);
			cb.setCode(200);
			cb.setMsg("success");
			cb.setResults(onlineBean);
			JSONObject jsonObject = JSONObject.fromObject(cb);
			pw.println(jsonObject.toString());
			System.out.println(jsonObject.toString());
		} catch (SQLException e) {
			cb.setCode(100);
			cb.setMsg("没有数据");
			JSONObject jsonObject = JSONObject.fromObject(cb);
			pw.println(jsonObject.toString());
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}

}
