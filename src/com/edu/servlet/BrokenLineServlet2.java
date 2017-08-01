package com.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.bean.AllBrokenLine2;
import com.edu.bean.BrokenLine2;
import com.edu.bean.ScoreBean2;
import com.edu.util.DataBaseOperaUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "brokenLineServlet2", urlPatterns = "/brokenLineServlet2")
public class BrokenLineServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;charset=utf-8");

		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");

		if (!(username == null || "".equals(username))) {

			String largeArea = req.getParameter("largeArea");
			String school = req.getParameter("school");
			String major = req.getParameter("major");
			String role = req.getParameter("role");
			String year = req.getParameter("year");

			AllBrokenLine2 allBrokenLine = new AllBrokenLine2();

			BrokenLine2 bean = new BrokenLine2();
			List<String> xAxis = new ArrayList<String>();
			for (int i = 1; i <= 12; i++) {
				xAxis.add(i + "月");
			}
			bean.setxAxis(xAxis);

			PrintWriter pw = resp.getWriter();
			try {
				List<ScoreBean2> scoreBeanList = DataBaseOperaUtil.getScoreBeans2(largeArea, school, role, major, year);
				for (ScoreBean2 scoreBean : scoreBeanList) {
					System.out.println(scoreBean);
				}
				allBrokenLine.setCode(200);
				allBrokenLine.setMsg("success");
				if (scoreBeanList.size() > 0) {
					for (int i = 0; i < scoreBeanList.size(); i++) {
						Double[] scores = scoreBeanList.get(i).getScores();
						Double[][] radar = scoreBeanList.get(i).getRadar();
						for (int j = 0; j < scores.length; j++) {
							if (scores[j] == -1.0) {
								radar[j] = new Double[0];
							}
						}
					}
					bean.setTeachers(scoreBeanList);
				} else {
					bean.setTeachers(new ArrayList<ScoreBean2>());
				}
				allBrokenLine.setResult(bean);

				JSONObject jsonObject = JSONObject.fromObject(allBrokenLine);
				pw.print(jsonObject.toString());
			} catch (SQLException e) {
				allBrokenLine.setCode(100);
				allBrokenLine.setMsg("fail");
				allBrokenLine.setResult(new BrokenLine2(new ArrayList<String>(), new ArrayList<ScoreBean2>()));

				JSONObject jsonObject = JSONObject.fromObject(allBrokenLine);
				pw.print(jsonObject.toString());
				e.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		} else {
			resp.sendRedirect("Login.html");
		}
	}
}
