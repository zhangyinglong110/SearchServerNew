package com.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.bean.AllData;
import com.edu.bean.LargeAreaBean;
import com.edu.bean.LargeAreaSumBean;
import com.edu.bean.ListBean;
import com.edu.bean.RecentBean;
import com.edu.bean.SchoolBean;
import com.edu.util.DataBaseOperaUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "recentServlet2", urlPatterns = "/recentServlet2")
public class RecentInputServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int areaId = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");// 设置响应的编码格式
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;charset=utf-8");
		String unionid = null;
		HttpSession session = req.getSession();
		unionid = (String) session.getAttribute("unionid");// 用户ID
		if (unionid == null || unionid == "") {
			unionid = req.getParameter("uid");
		}
		String username = (String) session.getAttribute("username");
		if ("jjj".equals(username)) {
			areaId = 0;
		} else if ("sh".equals(username)) {
			areaId = 1;
		} else if ("xb".equals(username)) {
			areaId = 2;
		} else if ("hz".equals(username)) {
			areaId = 3;
		} else if ("hb".equals(username)) {
			areaId = 4;
		} else if ("hn".equals(username)) {
			areaId = 5;
		} else if ("hd".equals(username)) {
			areaId = 6;
		} else if ("xn".equals(username)) {
			areaId = 7;
		} else {
			areaId = 8;
		}

		PrintWriter pw = resp.getWriter();
		try {
			String jsonData = getJsonData(unionid, areaId);
			System.out.println(jsonData);
			pw.print(jsonData);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	public String getJsonData(String unionid, int areaId) throws SQLException {
		RecentBean recent = new RecentBean();
		HashMap<Integer, LargeAreaSumBean> largeMap = null;
		ListBean listBean = new ListBean();
		AllData allData = new AllData();
		List<String> recentClass = DataBaseOperaUtil.getRecentInputClass(unionid);
		List<String> recentTeacher = DataBaseOperaUtil.getRecentInputTeacher(unionid);

		List<String> subjects = DataBaseOperaUtil.getSubjects();
		if (areaId == 8) {
			largeMap = DataBaseOperaUtil.getSchools();
		} else {
			largeMap = DataBaseOperaUtil.getAreaAndSchoolAndMajor(areaId);
		}
		List<List<SchoolBean>> sumList = new ArrayList<List<SchoolBean>>();
		List<LargeAreaBean> areaList = new ArrayList<LargeAreaBean>();
		Iterator iter = largeMap.entrySet().iterator(); // 获得map的Iterator
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			LargeAreaSumBean bean = (LargeAreaSumBean) entry.getValue();
			sumList.add(bean.getSchools()); // 字段schools就出来了

			LargeAreaBean labean = new LargeAreaBean();
			labean.setName(bean.getName());
			labean.setSchoolcode(bean.getSchoolcode());
			areaList.add(labean);
		}

		recent.setClassList(recentClass);
		recent.setTeacherName(recentTeacher);

		listBean.setHistory(recent); // 历史数据
		listBean.setBigarea(areaList);// 设置大区以及schoolcode
		listBean.setSchools(sumList); // 设置校区的字段
		listBean.setSubject(subjects);
		listBean.setDataSize(areaList.size());
		allData.setCode(200);
		allData.setMsg("请求成功");
		allData.setResults(listBean);
		JSONObject jsonObject = JSONObject.fromObject(allData);
		String jsonData = jsonObject.toString();
		return jsonData;
	}

}
