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
import com.edu.bean.ResultBean;
import com.edu.bean.SchoolBean;
import com.edu.bean.UpDateBean;
import com.edu.util.DataBaseOperaUtil;
import com.edu.util.JsonUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "updateEditInfoServlet", urlPatterns = "/updateEditInfoServlet")
public class UpDateEditInfoServlet extends HttpServlet {

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

		String id = req.getParameter("id");
		// if(id==null || id.equals("")){
		// System.out.println("Linda null....");
		// }else{
		// System.out.println("Linda not null");
		// }

		HttpSession session = req.getSession();

		String username = (String) session.getAttribute("username");

		int teacherId = Integer.valueOf(id);
		String newTeacherName = req.getParameter("newTeacherName");
		String newRole = req.getParameter("newRole");
		String newClassName = req.getParameter("newClassName");
		String newMajorName = req.getParameter("newMajorName");
		String newSchoolName = req.getParameter("newSchoolName");

		System.out.println("id:" + id + ",username:" + username + ",newTeacherName:" + newTeacherName + ",newClassName:"
				+ newClassName + ",newMajorName:" + newMajorName + ",newSchoolName:" + newSchoolName);

		UpDateBean bean = new UpDateBean();
		bean.setId(teacherId);
		bean.setNewClassName(newClassName);
		bean.setNewMajorName(newMajorName);
		bean.setNewRole(newRole);
		bean.setNewSchoolName(newSchoolName);
		bean.setNewTeacherName(newTeacherName);
		PrintWriter pw = resp.getWriter();// ��Ӧ����������
		ResultBean resultbean = new ResultBean();
		try {
			int checkIsCheckRepeat = DataBaseOperaUtil.chekUserIsExists(newSchoolName, newTeacherName, newClassName,
					newRole, newMajorName);
			if (checkIsCheckRepeat == 0) {
				int result = DataBaseOperaUtil.updateEditTeacherData(bean, username);

				if (result > 0) {
					resultbean.setCode(200);
					resultbean.setMsg("�޸ĳɹ�");
					JSONObject jsonObject = JSONObject.fromObject(resultbean);
					pw.print(jsonObject.toString());
				} else {
					resultbean.setCode(100);
					resultbean.setMsg("�޸�ʧ��");
					JSONObject jsonObject = JSONObject.fromObject(resultbean);
					pw.print(jsonObject.toString());
				}
			} else {
				resultbean.setCode(100);
				resultbean.setMsg("�޸ĵ���ʦ�Ѿ�����");
				JSONObject jsonObject = JSONObject.fromObject(resultbean);
				pw.print(jsonObject.toString());
			}
			// ִ�������Ĳ�����������ñ��е�����
			String jsonData = getJsonData(null);
			int result = DataBaseOperaUtil.modifySelectData(jsonData);
			if (result > 0) {
				System.out.println("�������ݳɹ�");
			} else {
				System.out.println("��������ʧ��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	public String getJsonData(String unionid) throws SQLException {
		RecentBean recent = new RecentBean();
		ListBean listBean = new ListBean();
		AllData allData = new AllData();
		List<String> recentClass = DataBaseOperaUtil.getRecentInputClass(unionid);
		List<String> recentTeacher = DataBaseOperaUtil.getRecentInputTeacher(unionid);
		List<String> subjects = DataBaseOperaUtil.getSubjects();
		HashMap<Integer, LargeAreaSumBean> largeMap = DataBaseOperaUtil.getSchools();
		List<List<SchoolBean>> sumList = new ArrayList<List<SchoolBean>>();
		List<LargeAreaBean> areaList = new ArrayList<LargeAreaBean>();
		Iterator iter = largeMap.entrySet().iterator(); // ���map��Iterator
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			LargeAreaSumBean bean = (LargeAreaSumBean) entry.getValue();
			sumList.add(bean.getSchools()); // �ֶ�schools�ͳ�����

			LargeAreaBean labean = new LargeAreaBean();
			labean.setName(bean.getName());
			labean.setSchoolcode(bean.getSchoolcode());
			areaList.add(labean);
		}

		recent.setClassList(recentClass);
		recent.setTeacherName(recentTeacher);

		listBean.setHistory(recent); // ��ʷ����
		listBean.setBigarea(areaList);// ���ô����Լ�schoolcode
		listBean.setSchools(sumList); // ����У�����ֶ�
		listBean.setSubject(subjects);

		allData.setCode(200);
		allData.setMsg("����ɹ�");
		allData.setResults(listBean);
		JSONObject jsonObject = JSONObject.fromObject(allData);
		String jsonData = jsonObject.toString();
		return jsonData;
	}

}
