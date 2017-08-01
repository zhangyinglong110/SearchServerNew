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

import com.edu.bean.Investigation;
import com.edu.bean.ResultBean;
import com.edu.util.DataBaseOperaUtil;
import com.edu.util.FormulaUtil;
import com.edu.util.JsonUtil;

import net.sf.json.JSONObject;

/**
 * 检查是否可以重复投票
 * 
 * @author Poppy(张应龙)
 *
 */
@WebServlet(name = "CheckServlet", urlPatterns = "/CheckServlet")
public class CheckRepeatServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");// 设置请求参数的编码
		resp.setCharacterEncoding("UTF-8");// 设置响应的编码格式
		resp.setHeader("Access-Control-Allow-Origin", "*");
		String checkJson = req.getParameter("checkJson");
		System.out.println("CheckServlet传过来的条件---->" + checkJson);
		String unionid = (String) req.getSession().getAttribute("unionid");
		// System.out.println("CheckServlet---unionid---->" + unionid);

		Investigation beans = JsonUtil.getCheckRepeatJson(checkJson, unionid);

		int roleLevel = Integer.valueOf(beans.getRole_Level());
		PrintWriter pw = resp.getWriter();
		
		ResultBean bean = new ResultBean();
		
		// 当前进是对在线老师的一个评论
		try {
			if (roleLevel == 3) {
				int result = DataBaseOperaUtil.chekIsRepeat(beans);
				// 如果大于0说明存在
				if (result > 0) {
					List<Investigation> list = DataBaseOperaUtil.selectOnlineMonthData(beans);
					for (int i = 0; i < list.size(); i++) {
						if (FormulaUtil.isSameDate(list.get(i).getFill_Date(), beans.getFill_Date())) {
							bean.setCode(100);
							bean.setMsg("本周已经评论过！");
							JSONObject jsonObject = JSONObject.fromObject(bean);
							pw.print(jsonObject.toString());
						} else {
							bean.setCode(200);
							bean.setMsg("succcess");
							JSONObject jsonObject = JSONObject.fromObject(bean);
							pw.print(jsonObject.toString());
						}
					}
				} else {
					bean.setCode(200);
					bean.setMsg("succcess");
					JSONObject jsonObject = JSONObject.fromObject(bean);
					pw.print(jsonObject.toString());
				}
			} else {
				int result = DataBaseOperaUtil.chekIsRepeat(beans);
				System.out.println("CheckServlet返回的数据库结果---->" + result);
				// 还没评论
				if (result == 0) {
					bean.setCode(200);
					bean.setMsg("succcess");
					JSONObject jsonObject = JSONObject.fromObject(bean);
					pw.print(jsonObject.toString());
				} else {
					bean.setCode(100);
					bean.setMsg("本月已经评论过！");
					JSONObject jsonObject = JSONObject.fromObject(bean);
					pw.print(jsonObject.toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

}
