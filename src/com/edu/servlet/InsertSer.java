package com.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.bean.Investigation;
import com.edu.bean.ResultBean;
import com.edu.util.DataBaseOperaUtil;
import com.edu.util.JsonUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "SaveServlet", urlPatterns = "/SaveServlet")
public class InsertSer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=utf-8");

		String jsonStirng = req.getParameter("name");// ��ȡ�������ݵ�json
		String unionid = (String) req.getSession().getAttribute("unionid");
		String nickname = (String) req.getSession().getAttribute("nickname");
		System.out.println(jsonStirng);
		System.out.println("insert unionid----->" + unionid);
		System.out.println("insert nickname----->" + nickname);
		PrintWriter pw = resp.getWriter();// ��Ӧ����������

		if ("".equals(jsonStirng) || jsonStirng == null) {
			pw.println("��¼��Ϣ��ȫ��");
		} else {
			// ��ȡ�����������
			Investigation beanData = JsonUtil.getJsonBean(jsonStirng, unionid, nickname);

			int roleLevel = Integer.valueOf(beanData.getRole_Level());
			// ������ݿ��Ƿ��������
			int checkResult;
			ResultBean bean = new ResultBean();
			try {

				if (roleLevel == 3) {
					// ִ�в������ݿ����
					int inserResult = DataBaseOperaUtil.insertData(beanData);
					if (inserResult > 0) {
						bean.setCode(200);
						bean.setMsg("�����ɹ�");
						JSONObject jsonObject = JSONObject.fromObject(bean);
						pw.print(jsonObject.toString());
					} else {
						bean.setCode(100);
						bean.setMsg("����ʧ��");
						JSONObject jsonObject = JSONObject.fromObject(bean);
						pw.print(jsonObject.toString());
					}
				} else {
					checkResult = DataBaseOperaUtil.chekIsRepeat(beanData);
					if (checkResult == 0) {
						// ִ�в������ݿ����
						int inserResult = DataBaseOperaUtil.insertData(beanData);
						System.out.println("�������ݵ�ִ�н����----��" + checkResult);
						if (inserResult > 0) {
							bean.setCode(200);
							bean.setMsg("�����ɹ�");
							JSONObject jsonObject = JSONObject.fromObject(bean);
							pw.print(jsonObject.toString());
						} else {
							bean.setCode(100);
							bean.setMsg("����ʧ��");
							JSONObject jsonObject = JSONObject.fromObject(bean);
							pw.print(jsonObject.toString());
						}
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

		// try {
		// if (checkResult == 0) {// ��һ�β������ݿ�
		// int inserResult = new DataBaseOperaUtil().insertData(beanData);
		// if (inserResult > 0) {
		// �����ݿ��в�ѯ����������
		// List<Investigation> list = new
		// DataBaseOperaUtil().queryRanking(beanData.getLarge_Area(),
		// beanData.getCus_Name());
		// ��listת����json����
		// JSONArray jsonArray = JSONArray.fromObject(list);
		// PrintWriter pw = resp.getWriter();
		// pw.println(200);
		// System.out.println(jsonArray);
		// req.getSession().removeAttribute("unionid");
		// } else {
		// PrintWriter pw = resp.getWriter();
		// pw.println(100);
		// }
		// } else {// �Ѿ����������
		// PrintWriter pw = resp.getWriter();
		// pw.println(101);
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
	}
}
