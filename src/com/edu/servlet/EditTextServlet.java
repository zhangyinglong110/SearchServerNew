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

import com.edu.bean.AllEditBean;
import com.edu.bean.EditBean;
import com.edu.util.DataBaseOperaUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "editPageServlet", urlPatterns = "/editPageServlet")
public class EditTextServlet extends HttpServlet {

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
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");

		if (!(username == null || "".equals(username))) {
			// 大区
			String largeName = req.getParameter("largeName");
			// largeName = new String(largeName.getBytes("ISO8859-1"), "utf-8");
			// 校区
			String schoolName = req.getParameter("schoolName");

			// 专业
			String majorName = req.getParameter("majorName");

			String roleLevel = req.getParameter("roleLevel");

			System.out.println(schoolName + ":" + majorName + ":" + largeName + ":" + roleLevel);

			AllEditBean editBean = new AllEditBean();
			PrintWriter pw = resp.getWriter();
			try {
				List<EditBean> list = DataBaseOperaUtil.getEditTextBean(largeName, schoolName, majorName, roleLevel);
				editBean.setCode(200);
				editBean.setMsg("success");
				editBean.setResults(list);
				JSONObject jsonObject = JSONObject.fromObject(editBean);
				pw.println(jsonObject.toString());
				System.out.println(jsonObject.toString());
			} catch (SQLException e) {
				editBean.setCode(100);
				editBean.setMsg("没有数据");
				JSONObject jsonObject = JSONObject.fromObject(editBean);
				pw.println(jsonObject.toString());
				e.printStackTrace();
			}
		} else {
			resp.sendRedirect("Login.html");
		}
	}

}
