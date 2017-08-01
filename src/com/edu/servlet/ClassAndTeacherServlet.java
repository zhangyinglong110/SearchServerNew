package com.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.bean.ClassBean;
import com.edu.bean.AllClass;
import com.edu.util.DataBaseOperaUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "ClassAndTeacherServlet", urlPatterns = "/ClassAndTeacherServlet")
public class ClassAndTeacherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		// schoolName = new String(schoolName.getBytes("iso8859-1"), "UTF-8");

		String majorId = req.getParameter("majorName");
		// majorId = new String(majorId.getBytes("iso8859-1"), "UTF-8");

		String role_Level = req.getParameter("role_Level");
		// role_Level = new String(role_Level.getBytes("iso8859-1"), "UTF-8");
		System.out.println(schoolName + ":" + majorId + ":" + role_Level);
		AllClass cb = new AllClass();
		PrintWriter pw = resp.getWriter();
		try {
			List<ClassBean> list = DataBaseOperaUtil.getClassList(schoolName, majorId, role_Level);
			cb.setCode(200);
			cb.setMsg("success");
			cb.setResults(list);
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
