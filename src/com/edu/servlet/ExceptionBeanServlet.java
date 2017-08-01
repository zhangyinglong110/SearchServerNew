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
import javax.servlet.http.HttpSession;

import com.edu.bean.AllExceptionBean;
import com.edu.bean.ExceptionBean;
import com.edu.util.DataBaseOperaUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "exceptionBeanServlet", urlPatterns = "/exceptionBeanServlet")
public class ExceptionBeanServlet extends HttpServlet {

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

		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");

		if (!(username == null || "".equals(username))) {

			AllExceptionBean allExceptionBean = new AllExceptionBean();
			PrintWriter pw = resp.getWriter();
			// 大区
			String largeName = req.getParameter("largeName");
			// 校区
			String schoolName = req.getParameter("schoolName");
			// 专业
			String majorName = req.getParameter("majorName");
			// 专业
			String roleLevel = req.getParameter("roleLevel");

			String startDate = req.getParameter("startDate");

			String endDate = req.getParameter("endDate");

			System.out.println("exceptionBeanServlet------" + largeName + ":" + ":" + schoolName + ":" + majorName + ":"
					+ roleLevel);
			if (startDate == null || endDate == null || roleLevel == null) {
				allExceptionBean.setCode(100);
				allExceptionBean.setMsg("参数不全");
				JSONObject jsonObject = JSONObject.fromObject(allExceptionBean);
				pw.println(jsonObject.toString());
			} else {
				try {
					List<ExceptionBean> listBean = DataBaseOperaUtil.getExceptionBeans(largeName, schoolName, roleLevel,
							majorName, startDate, endDate);
					if (listBean != null) {
						allExceptionBean.setResults(listBean);
						allExceptionBean.setCode(200);
						allExceptionBean.setMsg("success");
						JSONObject jsonObject = JSONObject.fromObject(allExceptionBean);
						pw.println(jsonObject.toString());
						System.out.println(jsonObject.toString());
					}
				} catch (SQLException e) {
					allExceptionBean.setCode(100);
					allExceptionBean.setMsg("没有数据");
					JSONObject jsonObject = JSONObject.fromObject(allExceptionBean);
					pw.println(jsonObject.toString());
					e.printStackTrace();
				}
			}
		} else {
			resp.sendRedirect("Login.html");
		}
	}

}
