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

import com.edu.bean.AllBrokenLine;
import com.edu.bean.BrokenLine;
import com.edu.bean.ResultBean;
import com.edu.bean.ScoreBean;
import com.edu.util.DataBaseOperaUtil;
import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

@WebServlet(name = "brokenLineServlet", urlPatterns = "/brokenLineServlet")
public class BrokenLineServlet extends HttpServlet {

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

		String largeArea=req.getParameter("largeArea");
		String school=req.getParameter("school");
		String major=req.getParameter("major");
		String role=req.getParameter("role");
		System.out.println("largeArea="+largeArea+",school="+school+",major="+major+",role="+role);
		
		AllBrokenLine allBrokenLine=new AllBrokenLine();
		
		BrokenLine bean = new BrokenLine();
		List<String> xAxis = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			xAxis.add(i+"月");
		}
		bean.setxAxis(xAxis);
		
		PrintWriter pw = resp.getWriter();
		try {
			List<ScoreBean> scoreBeanList = DataBaseOperaUtil.getScoreBeans(largeArea,school,role,major);
			for(ScoreBean scoreBean:scoreBeanList){
				System.out.println(scoreBean);
			}
			//有数据则返回数据，无数据则返回[]
			allBrokenLine.setCode(200);
			allBrokenLine.setMsg("success");
			if(scoreBeanList.size()>0){
				bean.setTeachers(scoreBeanList);
			}else{
				bean.setTeachers(new ArrayList<ScoreBean>());
			}
			allBrokenLine.setResult(bean);
			
			JSONObject jsonObject = JSONObject.fromObject(allBrokenLine);
			pw.print(jsonObject.toString());
		} catch (SQLException e) {
			allBrokenLine.setCode(100);
			allBrokenLine.setMsg("fail");
			allBrokenLine.setResult(new BrokenLine(new ArrayList<String>(), new ArrayList<ScoreBean>()));
			
			JSONObject jsonObject = JSONObject.fromObject(allBrokenLine);
			pw.print(jsonObject.toString());
			e.printStackTrace();
		}finally {
			if(pw!=null){
				pw.close();
			}
		}
	}
}
