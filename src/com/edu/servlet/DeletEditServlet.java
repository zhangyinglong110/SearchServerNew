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
import com.edu.bean.SelectDeleteBean;
import com.edu.util.DataBaseOperaUtil;

import net.sf.json.JSONObject;

@WebServlet(name = "deleteEditPageServlet", urlPatterns = "/deleteEditPageServlet")
public class DeletEditServlet extends HttpServlet {

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

		String id = req.getParameter("id");
		ResultBean bean = new ResultBean();

		PrintWriter pw = resp.getWriter();// ��Ӧ����������
		System.out.println("-----DeleteEditServlet----------->" + id);
		try {
			int result = DataBaseOperaUtil.deleteEditPageData(id,username);
			if (result > 0) {
				// ��ѯɾ�����ݵ�רҵID��У��ID
				SelectDeleteBean sBean = DataBaseOperaUtil.checkMajorIdAndSchoolId(id);
				System.out.println("sBean----->" + sBean);
				// ��ѯ��ɾ�����Ƿ񻹴��ڸ�רҵ
				int selectResult = DataBaseOperaUtil.checkIsExistsWithMajorIdAndSchoolId(sBean);
				System.out.println("-------selectResult-" + selectResult);
				if (selectResult == 0) {
					int deleteResult = DataBaseOperaUtil.deleteTabMajorAndSchoolIdData(sBean);
					if (deleteResult > 0) {
						System.out.println("ɾ���ɹ�!");
					} else {
						System.out.println("ɾ��ʧ��!");
					}
				}

				bean.setCode(200);
				bean.setMsg("success");
				JSONObject jsonObject = JSONObject.fromObject(bean);
				pw.print(jsonObject.toString());
			} else {
				bean.setCode(100);
				bean.setMsg("fail");
				JSONObject jsonObject = JSONObject.fromObject(bean);
				pw.print(jsonObject.toString());
			}
			// ִ�������Ĳ�����������ñ��е�����
			String jsonData = getJsonData(null);
			int resultUp = DataBaseOperaUtil.modifySelectData(jsonData);
			if (resultUp > 0) {
				System.out.println("�������ݳɹ�");
			} else {
				System.out.println("��������ʧ��");
			}
		} catch (SQLException e) {
			bean.setCode(100);
			bean.setMsg("����ʧ��");
			JSONObject jsonObject = JSONObject.fromObject(bean);
			pw.print(jsonObject.toString());
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
