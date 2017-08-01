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
import javax.servlet.http.HttpSession;

import com.edu.bean.Investigation;
import com.edu.bean.SelectBean;
import com.edu.bean.SelectJsonBean;
import com.edu.bean.SubjectBean;
import com.edu.util.DataBaseOperaUtil;
import com.edu.util.JsonUtil;
import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ��ѯ���ݽ����ҳ��
 * 
 * @author Poppy
 *
 */
@WebServlet(name = "selectServlet", urlPatterns = "/selectServlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;charset=utf-8");

		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		if (!(username == null || "".equals(username))) {

			String jsonString = req.getParameter("name");
			System.out.println("-----------------SelectServlet---------->" + jsonString);
			PrintWriter pw = resp.getWriter();

			SelectJsonBean selectJsonBean = new SelectJsonBean();

			try {
				if (jsonString != null) {
					SelectBean selectBean = JsonUtil.getJsonSelectJson(jsonString);
					List<Investigation> selectInfo = DataBaseOperaUtil.getSelectInfo1(selectBean);

					// for (int i = 0; i < selectInfo.size(); i++) {
					// SubjectBean sBean = new SubjectBean();
					// sBean.setTeacherName(selectInfo.get(i).getTea_Name());
					// sBean.setRole(selectInfo.get(i).getRole_Level());
					// sBean.setMajorName(selectInfo.get(i).getCus_Name());
					// sBean.setSchooName(selectInfo.get(i).getSch_Name());
					// sBean.setClassName(selectInfo.get(i).getStu_Class());
					// DataBaseOperaUtil.insertDataIntoTeacherTab(sBean);
					// }

					selectJsonBean.setCode(200);
					if (selectInfo.size() > 0) {
						selectJsonBean.setMsg("success");
						selectJsonBean.setSelectInfo(selectInfo);
					} else {
						selectJsonBean.setMsg("��ѯû�н��!");
						selectJsonBean.setSelectInfo(new ArrayList<Investigation>());
					}
					JSONObject jsonObject = JSONObject.fromObject(selectJsonBean);
					pw.print(jsonObject.toString());
					System.out.println("SelectServlet  json=" + jsonObject.toString());
				} else {
					selectJsonBean.setCode(100);
					selectJsonBean.setMsg("���س���F5ˢ������!");
					JSONObject jsonObject = JSONObject.fromObject(selectJsonBean);
					pw.print(jsonObject.toString());
				}
			} catch (Exception e) {
				selectJsonBean.setCode(100);
				selectJsonBean.setMsg("fail");
				JSONObject jsonObject = JSONObject.fromObject(selectJsonBean);
				pw.print(jsonObject.toString());
				e.printStackTrace();
			} finally {
				pw.close();
			}
		} else {
			resp.sendRedirect("Login.html");
		}
	}
}
