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
		resp.setCharacterEncoding("UTF-8");// 设置响应的编码格式
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
			PrintWriter pw = resp.getWriter();// 响应服务器对象
			ResultBean bean = new ResultBean();
			// if (!("翡翠集团".equals(schoolName)) && ("在线老师".equals(role))) {
			// bean.setCode(100);
			// bean.setMsg("大区不能添加在线老师！");
			// JSONObject jsonObject = JSONObject.fromObject(bean);
			// pw.print(jsonObject.toString());
			// return;
			// }

			if ("".equals(teacherName) && "".equals(banzhurenName) && "".equals(workTeacherName)
					&& "".equals(onLineTeacherName)) {
				bean.setCode(200);
				bean.setMsg("角色为空，添加失败！");
				JSONObject jsonObject = JSONObject.fromObject(bean);
				pw.print(jsonObject.toString());
				return;
			}

			String resultString = "";

			try {
				if (!("".equals(teacherName))) {
					int checkIsExists = DataBaseOperaUtil.chekUserIsExists(schoolName, teacherName, className, "讲师",
							majorName);
					if (checkIsExists == 0) {
						String result = insertData(schoolName, teacherName, className, "讲师", majorName, username, resp);
						resultString += result;
					} else {
						resultString += (teacherName + ",已经存在!\n");
					}
				}
				if (!("".equals(banzhurenName))) {
					int checkIsExistsBanzhuren = DataBaseOperaUtil.chekUserIsExists(schoolName, banzhurenName,
							className, "班主任", majorName);
					if (checkIsExistsBanzhuren == 0) {
						String result = insertData(schoolName, banzhurenName, className, "班主任", majorName, username,
								resp);
						resultString += result;
					} else {
						resultString += (banzhurenName + ",已经存在!\n");
					}

				}

				if (!("".equals(workTeacherName))) {
					int checkIsExistsWorkTeacher = DataBaseOperaUtil.chekUserIsExists(schoolName, workTeacherName,
							className, "就业", majorName);
					if (checkIsExistsWorkTeacher == 0) {
						String result = insertData(schoolName, workTeacherName, className, "就业", majorName, username,
								resp);
						resultString += result;
					} else {
						resultString += (workTeacherName + ",已经存在!\n");
					}
				}

				if (!("".equals(onLineTeacherName))) {
					int checkIsExistsOnlineTeacher = DataBaseOperaUtil.chekUserIsExists(schoolName, workTeacherName,
							className, "在线老师", majorName);
					if (checkIsExistsOnlineTeacher == 0) {
						String result = insertData(schoolName, onLineTeacherName, className, "在线老师", majorName,
								username, resp);
						resultString += result;
					} else {
						resultString += (onLineTeacherName + ",已经存在!\n");
					}
				}

				bean.setCode(200);
				bean.setMsg(resultString);
				JSONObject jsonObject = JSONObject.fromObject(bean);
				pw.print(jsonObject.toString());

				// 执行完插入的操作后更新配置表中的数据
				String jsonData = getJsonData(null);
				int result = DataBaseOperaUtil.modifySelectData(jsonData);
				if (result > 0) {
					System.out.println("更新数据成功");
				} else {
					System.out.println("更新数据失败");
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
		// signIsCheck结果为0的话，即sign为1的时候在数据库中也没数据
		if (signIsCheck == 0) {
			// 往数据库中插入数据
			int result = DataBaseOperaUtil.insertEditData(schoolName, teacherName, className, role, majorName,
					username);
			if (result > 0) {
				int checkResult = DataBaseOperaUtil.checkMajorSchool(schoolName, majorName);
				if (checkResult == 0) {
					int insertResult = DataBaseOperaUtil.insertMajorNameAndSchool(schoolName, majorName);
					if (insertResult > 0) {
						return teacherName + "新增成功!\n";
					}
				} else {
					return teacherName + "新增成功!\n";
				}
			} else {
				return teacherName + "新增失败!\n";
			}
		} else {
			// 讲sign1更改为0
			int updateSign = DataBaseOperaUtil.updateSign(schoolName, teacherName, className, role, majorName);
			if (updateSign > 0) {
				return teacherName + "新增成功!\n";
			} else {
				return teacherName + "新增失败!\n";
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

		allData.setCode(200);
		allData.setMsg("请求成功");
		allData.setResults(listBean);
		JSONObject jsonObject = JSONObject.fromObject(allData);
		String jsonData = jsonObject.toString();
		return jsonData;
	}

}
