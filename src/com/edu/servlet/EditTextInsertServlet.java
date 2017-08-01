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
import com.edu.util.DataBaseOperaUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "editPageInsertServlet", urlPatterns = "/editPageInsertServlet")
public class EditTextInsertServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");// ������Ӧ�ı����ʽ
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;charset=utf-8");

		HttpSession session = req.getSession();

		String username = (String) session.getAttribute("username");

		if (!(username == null || "".equals(username))) {

			String schoolName = req.getParameter("schoolName");

			String majorName = req.getParameter("majorName");

			String className = req.getParameter("className");

			// String role = req.getParameter("role");

			String teacherName = req.getParameter("teacherName");

			String workTeacherName = req.getParameter("workTeacherName");

			String banzhurenName = req.getParameter("banzhurenName");

			String onLineTeacherName = req.getParameter("onLineTeacherName");

			System.out.println("workTeacherName---" + workTeacherName);
			System.out.println("banzhurenName---" + banzhurenName);
			System.out.println(schoolName + ":" + majorName + ":" + teacherName + ":" + className);
			PrintWriter pw = resp.getWriter();// ��Ӧ����������
			ResultBean bean = new ResultBean();
			// if (!("��伯��".equals(schoolName)) && ("������ʦ".equals(role))) {
			// bean.setCode(100);
			// bean.setMsg("�����������������ʦ��");
			// JSONObject jsonObject = JSONObject.fromObject(bean);
			// pw.print(jsonObject.toString());
			// return;
			// }

			if ("".equals(teacherName) && "".equals(banzhurenName) && "".equals(workTeacherName)
					&& "".equals(onLineTeacherName)) {
				bean.setCode(200);
				bean.setMsg("��ɫΪ�գ����ʧ�ܣ�");
				JSONObject jsonObject = JSONObject.fromObject(bean);
				pw.print(jsonObject.toString());
				return;
			}

			String resultString = "";

			try {
				if (!("".equals(teacherName))) {
					int checkIsExists = DataBaseOperaUtil.chekUserIsExists(schoolName, teacherName, className, "��ʦ",
							majorName);
					if (checkIsExists == 0) {
						String result = insertData(schoolName, teacherName, className, "��ʦ", majorName, username, resp);
						resultString += result;
					} else {
						resultString += (teacherName + ",�Ѿ�����!\n");
					}
				}
				if (!("".equals(banzhurenName))) {
					int checkIsExistsBanzhuren = DataBaseOperaUtil.chekUserIsExists(schoolName, banzhurenName,
							className, "������", majorName);
					if (checkIsExistsBanzhuren == 0) {
						String result = insertData(schoolName, banzhurenName, className, "������", majorName, username,
								resp);
						resultString += result;
					} else {
						resultString += (banzhurenName + ",�Ѿ�����!\n");
					}

				}

				if (!("".equals(workTeacherName))) {
					int checkIsExistsWorkTeacher = DataBaseOperaUtil.chekUserIsExists(schoolName, workTeacherName,
							className, "��ҵ", majorName);
					if (checkIsExistsWorkTeacher == 0) {
						String result = insertData(schoolName, workTeacherName, className, "��ҵ", majorName, username,
								resp);
						resultString += result;
					} else {
						resultString += (workTeacherName + ",�Ѿ�����!\n");
					}
				}

				if (!("".equals(onLineTeacherName))) {
					int checkIsExistsOnlineTeacher = DataBaseOperaUtil.chekUserIsExists(schoolName, workTeacherName,
							className, "������ʦ", majorName);
					if (checkIsExistsOnlineTeacher == 0) {
						String result = insertData(schoolName, onLineTeacherName, className, "������ʦ", majorName,
								username, resp);
						resultString += result;
					} else {
						resultString += (onLineTeacherName + ",�Ѿ�����!\n");
					}
				}

				bean.setCode(200);
				bean.setMsg(resultString);
				JSONObject jsonObject = JSONObject.fromObject(bean);
				pw.print(jsonObject.toString());

				// ִ�������Ĳ�����������ñ��е�����
				String jsonData = getJsonData(null);
				int result = DataBaseOperaUtil.modifySelectData(jsonData);
				if (result > 0) {
					System.out.println("�������ݳɹ�");
				} else {
					System.out.println("��������ʧ��");
				}
			} catch (Exception e) {
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

	public String insertData(String schoolName, String teacherName, String className, String role, String majorName,
			String username, HttpServletResponse resp) throws SQLException, IOException {
		int signIsCheck = DataBaseOperaUtil.InsertChekUserExistsUpdateSign(schoolName, teacherName, className, role,
				majorName);
		// signIsCheck���Ϊ0�Ļ�����signΪ1��ʱ�������ݿ���Ҳû����
		if (signIsCheck == 0) {
			// �����ݿ��в�������
			int result = DataBaseOperaUtil.insertEditData(schoolName, teacherName, className, role, majorName,
					username);
			if (result > 0) {
				int checkResult = DataBaseOperaUtil.checkMajorSchool(schoolName, majorName);
				if (checkResult == 0) {
					int insertResult = DataBaseOperaUtil.insertMajorNameAndSchool(schoolName, majorName);
					if (insertResult > 0) {
						return teacherName + "�����ɹ�!\n";
					}
				} else {
					return teacherName + "�����ɹ�!\n";
				}
			} else {
				return teacherName + "����ʧ��!\n";
			}
		} else {
			// ��sign1����Ϊ0
			int updateSign = DataBaseOperaUtil.updateSign(schoolName, teacherName, className, role, majorName);
			if (updateSign > 0) {
				return teacherName + "�����ɹ�!\n";
			} else {
				return teacherName + "����ʧ��!\n";
			}
		}
		return null;
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
