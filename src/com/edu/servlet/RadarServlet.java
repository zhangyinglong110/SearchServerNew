package com.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.bean.RadarBean;
import com.edu.bean.RadarResultBean;
import com.edu.util.DataBaseOperaUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "radarServlet", urlPatterns = "/radarServlet")
public class RadarServlet extends HttpServlet{

	private static final long serialVersionUID = -2487731469870108835L;
	
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
		
		String largeArea=req.getParameter("largeArea");
		String school=req.getParameter("school");
		String name=req.getParameter("name");
		String major=req.getParameter("major");
		String year=req.getParameter("year");
		String month=req.getParameter("month");
		String role=req.getParameter("role");
		String data=year+"-"+month;
		
		RadarBean radarBean=new RadarBean();
		PrintWriter pw = resp.getWriter();
		
		try {
			RadarResultBean radarResultBean = 
					DataBaseOperaUtil.getRadarResultBean(largeArea, school, name, major, data, role);
			radarBean.setCode(200);
			radarBean.setMsg("success");
			if(radarResultBean!=null){
				radarBean.setResult(radarResultBean);
			}else{
				//查询无数据
				radarBean.setResult(new RadarResultBean(new ArrayList<Double>()));
			}
			
			JSONObject jsonObject = JSONObject.fromObject(radarBean);
			pw.print(jsonObject.toString());
		} catch (Exception e) {
			System.out.println("发生异常");
			radarBean.setCode(200);
			radarBean.setMsg("fail");
			radarBean.setResult(new RadarResultBean(new ArrayList<Double>()));
			
			JSONObject jsonObject = JSONObject.fromObject(radarBean);
			pw.print(jsonObject.toString());
			e.printStackTrace();
		}finally {
			if(pw!=null){
				pw.close();
			}
		}
	}
}
