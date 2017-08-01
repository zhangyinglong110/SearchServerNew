package com.edu.util;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.edu.bean.ClassBean;
import com.edu.bean.ConfigBean;
import com.edu.bean.EditBean;
import com.edu.bean.ExceptionBean;
import com.edu.bean.Investigation;
import com.edu.bean.LargeAreaBean;
import com.edu.bean.LargeAreaSumBean;
import com.edu.bean.OnlineBean;
import com.edu.bean.RadarResultBean;
import com.edu.bean.SchoolBean;
import com.edu.bean.ScoreBean;
import com.edu.bean.ScoreBean2;
import com.edu.bean.SelectBean;
import com.edu.bean.SelectDeleteBean;
import com.edu.bean.SubjectBean;
import com.edu.bean.UpDateBean;
import com.edu.global.Global;
import com.edu.test.SchoolAndMajor;

/**
 * У�����ݿɷ񱻲������ݿ�
 * 
 * @author Administrator
 *
 */
public class DataBaseOperaUtil {

	/**
	 * �����ݿ��������
	 * 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public static int insertData(Investigation bean) throws SQLException {
		int result = 0; // �������ݿ�󷵻صĽ��
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		// sql���
		String sql = "insert into " + Global.TAB_NAME
				+ " (user_Nick,user_Id,large_Area,sch_Name,tea_Name,role_Level,stu_Class,cus_Name,fill_Date,tea_Attendance,cls_Explain,cls_Quesions,ques_Answer,cls_Coach,cls_Discipline,cls_Skill,cls_Progress,exam_Explain,class_Homework,total_Score,stu_Advice,average)"
				+ "value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			// ���PreparedStatement����
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getUser_Nick());// �û��ǳ�
			ps.setString(2, bean.getUser_Id());
			ps.setString(3, bean.getLarge_Area());
			ps.setString(4, bean.getSch_Name());
			ps.setString(5, bean.getTea_Name());
			ps.setString(6, bean.getRole_Level()); // ���۵Ľ�ɫ
			ps.setString(7, bean.getStu_Class()); // �༶���
			ps.setString(8, bean.getCus_Name());
			ps.setString(9, bean.getFill_Date());
			ps.setDouble(10, bean.getTea_Attendance());
			ps.setDouble(11, bean.getCls_Explain());
			ps.setDouble(12, bean.getCls_Quesions());
			ps.setDouble(13, bean.getQues_Answer());
			ps.setDouble(14, bean.getCls_Coach());
			ps.setDouble(15, bean.getCls_Discipline());
			ps.setDouble(16, bean.getCls_Skill());
			ps.setDouble(17, bean.getCls_Progress());
			ps.setDouble(18, bean.getExam_Explain());
			ps.setDouble(19, bean.getClass_Homework());
			ps.setDouble(20, bean.getTotal_Score());
			ps.setString(21, bean.getStu_Advice());
			ps.setDouble(22, bean.getAverage());
			result = ps.executeUpdate();
			System.out.println("insertData--Success--->" + result);
		} catch (Exception e) {
			System.out.println("insertData--fail--->" + e.getMessage());
			e.printStackTrace();

		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

	/**
	 * �û���¼
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public static int chekUserIsExists(String username, String password) throws SQLException {
		int result = 0;
		String sql = "select count(1) from user where username ='" + username + "' and password = '" + password + "'";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rSet = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
			rSet = stmt.executeQuery(sql);
			while (rSet.next()) {
				result = rSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("-------chekUser--------->" + e.getMessage());
			e.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rSet);
		}
		return result;
	}

	/**
	 * ����Ƿ��Ѿ�Ͷ��Ʊ
	 * 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public static synchronized int chekIsRepeat(Investigation bean) throws SQLException {
		int result = 0;
		String sqlString = "SELECT count(1) FROM tab_researchinfo WHERE user_Id = '" + bean.getUser_Id()
				+ "' and large_Area = '" + bean.getLarge_Area() + "' AND sch_Name = '" + bean.getSch_Name()
				+ "' and role_Level = '" + bean.getRole_Level() + "'and cus_Name = '" + bean.getCus_Name()
				+ "' and tea_Name = '" + bean.getTea_Name() + "' and date_format(fill_Date,'%Y-%m') = date_format('"
				+ bean.getFill_Date() + "','%Y-%m')";
		// System.out.println("----��ѯ�����Ƿ��Ѿ�Ͷ����sql----->" + sqlString);
		System.out.println("-------chekIsRepeat---sql------>" + sqlString);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rSet = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
			rSet = stmt.executeQuery(sqlString);
			while (rSet.next()) {
				result = rSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("-------chekIsRepeat---exception------>" + e.getMessage());
			e.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rSet);
		}
		return result;
	}

	/**
	 * ����������ʦͶ��������
	 * 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public static synchronized List<Investigation> selectOnlineMonthData(Investigation bean) throws SQLException {
		String sqlString = "SELECT fill_Date FROM tab_researchinfo WHERE user_Id = '" + bean.getUser_Id()
				+ "' and large_Area = '" + bean.getLarge_Area() + "' AND sch_Name = '" + bean.getSch_Name()
				+ "' and role_Level = '" + bean.getRole_Level() + "'and cus_Name = '" + bean.getCus_Name()
				+ "' and tea_Name = '" + bean.getTea_Name() + "' and date_format(fill_Date,'%Y-%m') = date_format('"
				+ bean.getFill_Date() + "','%Y-%m')";
		System.out.println("-------selectOnlineMonthData---sql------>" + sqlString);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Investigation> list = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlString); // ִ�в�ѯ���ݿ�Ĳ���
			list = new ArrayList<Investigation>();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setFill_Date(rs.getString("fill_Date"));
				list.add(investigation);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			DbPoolConnection.getInstance().close(conn, stmt, rs);
			stmt.close();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return list;
	}

	/**
	 * ����
	 * 
	 * @param large_Area
	 *            ����
	 * @param cus_Name
	 *            רҵ����
	 * @return
	 * @throws SQLException
	 */
	public static List<Investigation> queryRanking(String large_Area, String cus_Name) throws SQLException {
		String sql = "SELECT A.large_Area,A.sch_Name,A.cus_Name,A.tea_Name,SUM(A.average)/COUNT(1) b from "
				+ Global.TAB_NAME + " A where A.large_Area = '" + large_Area
				+ "' GROUP BY A.tea_Name,A.large_Area,A.sch_Name,A.cus_Name ORDER BY b desc;";
		System.out.println("-------queryRanking---sql------>" + sql);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Investigation> list = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			list = new ArrayList<Investigation>();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setLarge_Area(rs.getString("large_Area"));
				investigation.setSch_Name(rs.getString("sch_Name"));
				investigation.setTea_Name(rs.getString("tea_Name"));
				investigation.setCus_Name(rs.getString("cus_Name"));
				investigation.setAverage(rs.getDouble("b"));
				list.add(investigation);
			}
		} catch (Exception exception) {
			System.out.println("-------queryRanking---exception------>" + exception.getMessage());
			exception.printStackTrace();
			// System.out.println("��ѯ���ݴ���~");
			DbPoolConnection.getInstance().close(conn, stmt, rs);
			stmt.close();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return list;
	}

	/**
	 * ��̨ͳ��
	 * 
	 * @param large_Area
	 * @param sch_Name
	 * @param major
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	public static List<Investigation> getSelectInfo(SelectBean selectBean) throws SQLException {
		String sql = "SELECT *,date_format(t.fill_Date,'%Y-%m') formFillDate FROM tab_researchinfo t";
		int role = 0;
		if ("��ʦ".equals(selectBean.getRole_Level())) {
			role = 0;
		} else if ("������".equals(selectBean.getRole_Level())) {
			role = 1;
		} else if ("��ҵ".equals(selectBean.getRole_Level())) {
			role = 2;
		} else if ("������ʦ".equals(selectBean.getRole_Level())) {
			role = 3;
		}

		if ("��ѡ��".equals(selectBean.getLargeArea())) {
			if ("��ѡ��".equals(selectBean.getMajor())) {// רҵ
				sql += " where  date_format(fill_Date,'%Y-%m-%d') >= '" + selectBean.getStartDate()
						+ "' and role_Level = '" + role + "' and date_format(fill_Date,'%Y-%m-%d')<= '"
						+ selectBean.getEndDate() + "' ORDER BY large_Area ASC,sch_Name ASC,tea_Name ASC ,cus_Name ASC";
			} else {
				sql += " where cus_Name = '" + selectBean.getMajor() + "' and role_Level = '" + role
						+ "'and date_format(fill_Date,'%Y-%m-%d') >= '" + selectBean.getStartDate()
						+ "' and date_format(fill_Date,'%Y-%m-%d') <= '" + selectBean.getEndDate()
						+ "' ORDER BY large_Area ASC,sch_Name ASC,tea_Name ASC ,cus_Name ASC";
			}
		} else {
			if ("��ѡ��".equals(selectBean.getSchName())) {
				if ("��ѡ��".equals(selectBean.getMajor())) {
					sql += " where large_Area = '" + selectBean.getLargeArea() + "' and role_Level = '" + role
							+ "' and  date_format(fill_Date,'%Y-%m-%d') >= '" + selectBean.getStartDate()
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + selectBean.getEndDate()
							+ "' ORDER BY large_Area ASC,sch_Name ASC,tea_Name ASC ,cus_Name ASC";
				} else {
					sql += " where large_Area = '" + selectBean.getLargeArea() + "' and role_Level = '" + role
							+ "' and cus_Name = '" + selectBean.getMajor()
							+ "' and date_format(fill_Date,'%Y-%m-%d') >= '" + selectBean.getStartDate()
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + selectBean.getEndDate()
							+ "' ORDER BY large_Area ASC,sch_Name ASC,tea_Name ASC ,cus_Name ASC";
				}
			} else {
				if ("��ѡ��".equals(selectBean.getMajor())) {
					sql += " where large_Area = '" + selectBean.getLargeArea() + "' and sch_Name = '"
							+ selectBean.getSchName() + "' and role_Level = '" + role
							+ "' and date_format(fill_Date,'%Y-%m-%d') >= '" + selectBean.getStartDate()
							+ "' and date_format(fill_Date,'%Y-%m-%d') <= '" + selectBean.getEndDate()
							+ "' ORDER BY large_Area ASC,sch_Name ASC,tea_Name ASC ,cus_Name ASC";
				} else {
					sql += " where large_Area = '" + selectBean.getLargeArea() + "' and sch_Name = '"
							+ selectBean.getSchName() + "' and role_Level = '" + role + "' and cus_Name = '"
							+ selectBean.getMajor() + "' and date_format(fill_Date,'%Y-%m-%d') >= '"
							+ selectBean.getStartDate() + "' and date_format(fill_Date,'%Y-%m-%d')<= '"
							+ selectBean.getEndDate()
							+ "' ORDER BY large_Area ASC,sch_Name ASC,tea_Name ASC ,cus_Name ASC";
				}
			}
		}
		System.out.println("----------getSelectInfo----sql------>" + sql);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Investigation> list = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			list = new ArrayList<Investigation>();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setStu_Class(rs.getString("stu_Class"));
				investigation.setLarge_Area(rs.getString("large_Area"));
				if (rs.getString("role_Level").equals("0")) {
					investigation.setRole_Level("��ʦ"); // ��ɫ
				} else if (rs.getString("role_Level").equals("1")) {
					investigation.setRole_Level("������");
				} else if (rs.getString("role_Level").equals("2")) {
					investigation.setRole_Level("��ҵ");
				} else if (rs.getString("role_Level").equals("3")) {
					investigation.setRole_Level("������ʦ");
				}
				investigation.setFill_Date(rs.getString("formFillDate"));
				investigation.setSch_Name(rs.getString("sch_Name"));
				investigation.setTea_Name(rs.getString("tea_Name"));
				investigation.setCus_Name(rs.getString("cus_Name"));
				investigation.setTea_Attendance(rs.getDouble("tea_Attendance"));
				investigation.setCls_Explain(rs.getDouble("cls_Explain"));
				investigation.setCls_Quesions(rs.getDouble("cls_Quesions"));
				investigation.setQues_Answer(rs.getDouble("ques_Answer"));
				investigation.setCls_Coach(rs.getDouble("cls_Coach"));
				investigation.setCls_Discipline(rs.getDouble("cls_Discipline"));
				investigation.setCls_Skill(rs.getDouble("cls_Skill"));
				investigation.setCls_Progress(rs.getDouble("cls_Progress"));
				investigation.setExam_Explain(rs.getDouble("exam_Explain"));
				investigation.setClass_Homework(rs.getDouble("class_Homework"));
				investigation.setTotal_Score(rs.getDouble("total_Score"));
				investigation.setAverage(rs.getDouble("average"));
				investigation.setStu_Advice(rs.getString("stu_Advice"));
				list.add(investigation);
			}
		} catch (Exception exception) {
			System.out.println("----------getSelectInfo----exception------>" + exception.getMessage());
			exception.printStackTrace();
			// System.out.println("��ѯ���ݴ���~");
			DbPoolConnection.getInstance().close(conn, stmt, rs);
			stmt.close();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
			stmt.close();
		}
		return list;
	}

	/**
	 * ��̨ͳ��(ǰ����������)
	 * 
	 * @param large_Area
	 * @param sch_Name
	 * @param major
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	public static List<Investigation> getSelectInfo1(SelectBean selectBean) throws SQLException {
		String sql = "SELECT t.large_Area,t.sch_Name,t.tea_Name,t.role_Level,t.cus_Name,t.stu_Class,t.tea_Attendance,t.cls_Explain,t.cls_Quesions,t.ques_Answer,t.cls_Coach,t.cls_Discipline,t.cls_Skill,t.cls_Progress,t.exam_Explain,t.class_Homework,t.total_Score,t.average,t.stu_Advice,COUNT(t.tea_Name) a,SUM(t.average)/COUNT(1) b,date_format(t.fill_Date,'%Y-%m') fillDate FROM tab_researchinfo t ";
		int role = 0;
		if ("��ʦ".equals(selectBean.getRole_Level())) {
			role = 0;
		} else if ("������".equals(selectBean.getRole_Level())) {
			role = 1;
		} else if ("��ҵ".equals(selectBean.getRole_Level())) {
			role = 2;
		} else if ("������ʦ".equals(selectBean.getRole_Level())) {
			role = 3;
		}

		if ("��ѡ��".equals(selectBean.getLargeArea()) || "��伯��".equals(selectBean.getLargeArea())) {
			if ("��ѡ��".equals(selectBean.getMajor()) || "��伯��".equals(selectBean.getLargeArea())) {// רҵ
				sql += " where date_format(fill_Date,'%Y-%m-%d') >= '" + selectBean.getStartDate()
						+ "' and role_Level = '" + role + "' and date_format(fill_Date,'%Y-%m-%d')<= '"
						+ selectBean.getEndDate()
						+ "' GROUP BY tea_Name,fillDate,t.stu_Class ORDER BY large_Area ASC ,sch_Name ASC,fillDate ASC";
			} else {
				sql += " where cus_Name = '" + selectBean.getMajor() + "' and role_Level = '" + role
						+ "'and date_format(fill_Date,'%Y-%m-%d') >= '" + selectBean.getStartDate()
						+ "' and date_format(fill_Date,'%Y-%m-%d') <= '" + selectBean.getEndDate()
						+ "' GROUP BY tea_Name,fillDate,t.stu_Class ORDER BY large_Area ASC ,sch_Name ASC,fillDate ASC";
			}
		} else {
			if ("��ѡ��".equals(selectBean.getSchName())) {
				if ("��ѡ��".equals(selectBean.getMajor())) {
					sql += " where large_Area = '" + selectBean.getLargeArea() + "' and role_Level = '" + role
							+ "' and  date_format(fill_Date,'%Y-%m-%d') >= '" + selectBean.getStartDate()
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + selectBean.getEndDate()
							+ "' GROUP BY tea_Name,fillDate,t.stu_Class ORDER BY large_Area ASC ,sch_Name ASC,fillDate ASC";
				} else {
					sql += " where large_Area = '" + selectBean.getLargeArea() + "' and role_Level = '" + role
							+ "' and cus_Name = '" + selectBean.getMajor()
							+ "' and date_format(fill_Date,'%Y-%m-%d') >= '" + selectBean.getStartDate()
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + selectBean.getEndDate()
							+ "' GROUP BY tea_Name,fillDate,t.stu_Class ORDER BY large_Area ASC ,sch_Name ASC,fillDate ASC";
				}
			} else {
				if ("��ѡ��".equals(selectBean.getMajor())) {
					sql += " where large_Area = '" + selectBean.getLargeArea() + "' and sch_Name = '"
							+ selectBean.getSchName() + "' and role_Level = '" + role
							+ "' and date_format(fill_Date,'%Y-%m-%d') >= '" + selectBean.getStartDate()
							+ "' and date_format(fill_Date,'%Y-%m-%d') <= '" + selectBean.getEndDate()
							+ "' GROUP BY tea_Name,fillDate,t.stu_Class ORDER BY large_Area ASC ,sch_Name ASC,fillDate ASC";
				} else {
					sql += " where large_Area = '" + selectBean.getLargeArea() + "' and sch_Name = '"
							+ selectBean.getSchName() + "' and role_Level = '" + role + "' and cus_Name = '"
							+ selectBean.getMajor() + "' and date_format(fill_Date,'%Y-%m-%d') >= '"
							+ selectBean.getStartDate() + "' and date_format(fill_Date,'%Y-%m-%d') <= '"
							+ selectBean.getEndDate()
							+ "' GROUP BY tea_Name,fillDate,t.stu_Class ORDER BY large_Area ASC ,sch_Name ASC,fillDate ASC";
				}
			}
		}
		System.out.println("-----------��ѯ--------->" + sql);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Investigation> list = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			list = new ArrayList<Investigation>();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setStu_Class(rs.getString("stu_Class"));// �༶
				investigation.setLarge_Area(rs.getString("large_Area")); // ����
				if (rs.getString("role_Level").equals("0")) {
					investigation.setRole_Level("��ʦ"); // ��ɫ
				} else if (rs.getString("role_Level").equals("1")) {
					investigation.setRole_Level("������");
				} else if (rs.getString("role_Level").equals("2")) {
					investigation.setRole_Level("��ҵ");
				} else if (rs.getString("role_Level").equals("3")) {
					investigation.setRole_Level("������ʦ");
				}
				investigation.setSch_Name(rs.getString("sch_Name")); // У��
				investigation.setTea_Name(rs.getString("tea_Name")); // ��ʦ����
				investigation.setCus_Name(rs.getString("cus_Name"));// רҵ
				investigation.setPeopleCount(rs.getInt("a")); // ͶƱ����
				investigation.setFill_Date(rs.getString("fillDate"));
				/********* ������������ *********/
				double average = rs.getDouble("b");
				BigDecimal b = new BigDecimal(average);
				double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				investigation.setAverage(f1);// ƽ����
				/********* ������������ *********/
				investigation.setTea_Attendance(rs.getDouble("tea_Attendance"));
				investigation.setCls_Explain(rs.getDouble("cls_Explain"));
				investigation.setCls_Quesions(rs.getDouble("cls_Quesions"));
				investigation.setQues_Answer(rs.getDouble("ques_Answer"));
				investigation.setCls_Coach(rs.getDouble("cls_Coach"));
				investigation.setCls_Discipline(rs.getDouble("cls_Discipline"));
				investigation.setCls_Skill(rs.getDouble("cls_Skill"));
				investigation.setCls_Progress(rs.getDouble("cls_Progress"));
				investigation.setExam_Explain(rs.getDouble("exam_Explain"));
				investigation.setClass_Homework(rs.getDouble("class_Homework"));
				investigation.setTotal_Score(rs.getDouble("total_Score"));
				list.add(investigation);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			// System.out.println("��ѯ���ݴ���~");
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return list;
	}

	/**
	 * �����û�ID���������д�İ༶����ʦ
	 * 
	 * @param unionid
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getRecentInputClass(String unionid) throws SQLException {
		String sql = "SELECT DISTINCT t.stu_Class FROM " + Global.TAB_NAME + " t WHERE t.user_Id = '" + unionid + "'";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<String> classList = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			classList = new ArrayList<String>();
			while (rs.next()) {
				classList.add(rs.getString("stu_Class"));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return classList;
	}

	/**
	 * �����û�ID���������д�İ༶����ʦ
	 * 
	 * @param unionid
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getRecentInputTeacher(String unionid) throws SQLException {
		String sql = "SELECT DISTINCT t.tea_Name FROM " + Global.TAB_NAME + " t WHERE t.user_Id = '" + unionid + "'";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<String> teacherList = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			teacherList = new ArrayList<String>();
			while (rs.next()) {
				teacherList.add(rs.getString("tea_Name"));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return teacherList;
	}

	/**
	 * ������д�����code�Լ�����������
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<LargeAreaBean> getAllLargeAreaBean() throws SQLException {
		String sql = "SELECT id,largeAreaName FROM " + Global.TAB_AREALARGE;
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<LargeAreaBean> areaLargeList = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			areaLargeList = new ArrayList<LargeAreaBean>();

			while (rs.next()) {
				LargeAreaBean bean = new LargeAreaBean();
				bean.setSchoolcode(rs.getInt("id"));
				bean.setName(rs.getString("largeAreaName"));
				areaLargeList.add(bean);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return areaLargeList;
	}

	public static HashMap<Integer, LargeAreaSumBean> getSchools() throws SQLException {
		HashMap<Integer, LargeAreaSumBean> largeMap = new HashMap<Integer, LargeAreaSumBean>();
		// ��ѯ�����е�У������Ӧ��רҵ
		// String sql = " SELECT l.id,l.largeAreaName,s.id
		// schID,s.schoolName,m.id majorID "
		// + " from tab_large l,tab_school s,tab_major m ,tab_sch_and_major zh "
		// + " WHERE zh.majorId = m.id AND zh.schoolId = s.id AND s.largeAreaId
		// = l.id " + " ORDER BY l.id";
		String sql = "select tmp.id,tmp.largeAreaName,tmp.schID,tmp.schoolName,zh.majorId from (SELECT l.id,l.largeAreaName,s.id schID,s.schoolName from tab_large l,tab_school s WHERE  s.largeAreaId = l.id ) tmp LEFT JOIN tab_sch_and_major zh on tmp.schID = zh.schoolId order by tmp.id";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		// *********************************************************//

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			while (rs.next()) {
				int tmpLargeID = rs.getInt("id"); // ������id
				String tmpLargeName = rs.getString("largeAreaName"); // ����������
				LargeAreaSumBean lasbAreaSumBean = null;
				if (largeMap.containsKey(tmpLargeID)) {
					lasbAreaSumBean = largeMap.get(tmpLargeID);
				} else {
					lasbAreaSumBean = new LargeAreaSumBean();
					lasbAreaSumBean.setSchoolcode(tmpLargeID);
					lasbAreaSumBean.setName(tmpLargeName);
					largeMap.put(tmpLargeID, lasbAreaSumBean);
				}

				SchoolBean sb = new SchoolBean();
				sb.setSchID(rs.getInt("schID"));
				sb.setSch(rs.getString("schoolName"));
				sb.tempSubcode = rs.getInt("majorID");

				lasbAreaSumBean.addSchool(sb);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}

		/************************************************************/
		return largeMap;
	}

	/**
	 * ��ѯ������У����רҵ�󶨵�����
	 */
	public static HashMap<Integer, LargeAreaSumBean> getAreaAndSchoolAndMajor(int areaId) throws SQLException {
		HashMap<Integer, LargeAreaSumBean> largeMap = new HashMap<Integer, LargeAreaSumBean>();
		String sql = " select * from (select tmp.id,tmp.largeAreaName,tmp.schID,tmp.schoolName,zh.majorId "
				+ " from (SELECT l.id,l.largeAreaName,s.id schID,s.schoolName from tab_large l,tab_school s WHERE  s.largeAreaId = l.id ) tmp LEFT JOIN tab_sch_and_major zh on tmp.schID = zh.schoolId  order by tmp.id) temp where temp.id = '"
				+ areaId + "'";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		// *********************************************************//

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			while (rs.next()) {
				int tmpLargeID = rs.getInt("id"); // ������id
				String tmpLargeName = rs.getString("largeAreaName"); // ����������
				LargeAreaSumBean lasbAreaSumBean = null;
				if (largeMap.containsKey(tmpLargeID)) {
					lasbAreaSumBean = largeMap.get(tmpLargeID);
				} else {
					lasbAreaSumBean = new LargeAreaSumBean();
					lasbAreaSumBean.setSchoolcode(tmpLargeID);
					lasbAreaSumBean.setName(tmpLargeName);
					largeMap.put(tmpLargeID, lasbAreaSumBean);
				}

				SchoolBean sb = new SchoolBean();
				sb.setSchID(rs.getInt("schID"));
				sb.setSch(rs.getString("schoolName"));
				sb.tempSubcode = rs.getInt("majorID");

				lasbAreaSumBean.addSchool(sb);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}

		/************************************************************/
		return largeMap;
	}

	/**
	 * ��ѯרҵ����
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getSubjects() throws SQLException {
		String sql = "SELECT m.majorName FROM tab_major m";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<String> subjectList = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			subjectList = new ArrayList<String>();
			while (rs.next()) {
				subjectList.add(rs.getString("majorName"));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return subjectList;
	}

	/**
	 * ��ѯ�༶����Ӧ����ʦ
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<ClassBean> getClassList(String schoolName, String majorName, String role_Level)
			throws SQLException {
		String sql = "SELECT t.className ,t.teacherName FROM tab_teacher t WHERE t.schoolId = (SELECT s.id FROM tab_school s WHERE s.schoolName = '"
				+ schoolName + "')  AND t.majorId = (SELECT m.id from tab_major m WHERE m.majorName = '" + majorName
				+ "')  AND t.role = '" + role_Level + "' AND t.sign = 0 ORDER BY className";
		System.out.println(sql);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<ClassBean> allClasses = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			HashMap<String, List<String>> map = new HashMap<String, List<String>>();
			String curCla = "";
			List<String> list = new ArrayList<String>();
			allClasses = new ArrayList<ClassBean>();
			while (rs.next()) {
				String cla = rs.getString("className");
				String name = rs.getString("teacherName");
				if (!curCla.equals(cla)) {
					if (!"".equals(curCla)) {
						map.put(curCla, list);
					}
					list = new ArrayList<String>();
					curCla = cla;
					list.add(name);
				} else {
					list.add(name);
				}
			}
			map.put(curCla, list);

			int i = 1;
			int curFatherId = 0;
			Iterator iter = map.entrySet().iterator(); // ���map��Iterator
			while (iter.hasNext()) {
				Entry entry = (Entry) iter.next();
				String cla = (String) entry.getKey();
				List<String> bean = (List<String>) entry.getValue();

				ClassBean allClass = new ClassBean();
				allClass.setTitle(cla);
				allClass.setFatherid("0");
				allClass.setUuid(i + "");
				allClasses.add(allClass);
				curFatherId = i;
				i++;
				for (String name : bean) {
					ClassBean allName = new ClassBean();
					allName.setTitle(name);
					allName.setFatherid(curFatherId + "");
					allName.setUuid(i + "");
					allClasses.add(allName);
					i++;
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return allClasses;
	}

	/**
	 * ������ߵ���ʦ
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static OnlineBean getOnlineTeacher(String schoolName, String majorName, String role_Level)
			throws SQLException {

		String sql = "select  a1.teacherName,a2.className  FROM (select  t.teacherName  "
				+ " from tab_teacher t where t.majorId = (SELECT m.id from tab_major m WHERE m.majorName = '"
				+ majorName + "') AND t.role = '" + role_Level + "') a1 ,"
				+ " (select DISTINCT t2.className from tab_teacher t2 where t2.schoolId =(SELECT s.id FROM tab_school s WHERE s.schoolName ='"
				+ schoolName + "') and t2.majorId  = (SELECT m.id from tab_major m WHERE m.majorName = '" + majorName
				+ "') AND t2.sign = 0 ORDER BY className) a2";
		System.out.println(sql);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		OnlineBean onlineBean = new OnlineBean();
		List<String> className = null;
		List<String> teacherName = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			className = new ArrayList<String>();
			teacherName = new ArrayList<String>();
			while (rs.next()) {
				String cla = rs.getString("className");
				String name = rs.getString("teacherName");
				if (!className.contains(cla)) {
					className.add(cla);
				}
				if (!teacherName.contains(name)) {
					teacherName.add(name);
				}
			}
			onlineBean.setClassName(className);
			onlineBean.setTeacherName(teacherName);
		} catch (

		Exception exception)

		{
			exception.printStackTrace();
		} finally

		{
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return onlineBean;

	}

	/**
	 * ��ѯ�����¶�Ӧ����ʦ�Ͱ༶�Լ�רҵ����Ϣ(�༭ҳ����ӵ�ʱ��չʾ������)
	 * 
	 * @param largeName
	 * @param schoolName
	 * @param majorName
	 * @param roleLevel
	 * @return
	 * @throws SQLException
	 */
	public static List<EditBean> getEditTextBean(String largeName, String schoolName, String majorName,
			String roleLevel) throws SQLException {
		String sql = "SELECT DISTINCT l.largeAreaName, t.id,s.schoolName,t.teacherName,t.role,t.className,m.majorName,t.majorId,t.schoolId FROM tab_teacher t,tab_major m ,tab_school s ,tab_large l ";
		// int role = 0;
		// if ("��ʦ".equals(roleLevel)) {
		// role = 0;
		// } else if ("������".equals(roleLevel)) {
		// role = 1;
		// } else if ("��ҵ".equals(roleLevel)) {
		// role = 2;
		// } else if ("������ʦ".equals(roleLevel)) {
		// role = 3;
		// } else if ("".equals(roleLevel)) {
		// role = 4;
		// }

		// ������Ϊ�յ�ʱ�򣬲�ѯ��������ȫ�����е���ʦ����Ϣ
		if (largeName == null || "\"\"".equals(largeName) || "".equals(largeName)) {
			if (majorName == null || "\"\"".equals(majorName) || "".equals(majorName)) {
				if ("".equals(roleLevel)) {
					sql += " WHERE sign = 0 and l.id = s.largeAreaId  and s.id = t.schoolId  and t.majorId = m.id ORDER BY t.id";
				} else {
					sql += " WHERE sign = 0 and l.id = s.largeAreaId  and s.id = t.schoolId  and t.majorId = m.id and t.role = '"
							+ roleLevel + "' ORDER BY t.id";
				}
			} else {
				if ("".equals(roleLevel)) {
					sql += "WHERE m.majorName = '" + majorName
							+ "' and sign = 0 and l.id = s.largeAreaId and  s.id = t.schoolId  and t.majorId = m.id ORDER BY t.id";
				} else {
					sql += "WHERE m.majorName = '" + majorName
							+ "' and sign = 0 and l.id = s.largeAreaId and  s.id = t.schoolId  and t.majorId = m.id and t.role = '"
							+ roleLevel + "' ORDER BY t.id";
				}
			}
		} else if (schoolName == null || "\"\"".equals(schoolName) || "".equals(schoolName)) { // ��������Ϊ�գ�У��Ϊ�յ�ʱ��
			if (majorName == null || "\"\"".equals(majorName) || "".equals(majorName)) { // ��ѯ�������Ǵ���������רҵ��ʦ����Ϣ
				if ("".equals(roleLevel)) {
					sql += "WHERE sign = 0 and l.largeAreaName = '" + largeName
							+ "' AND  l.id = s.largeAreaId and s.id = t.schoolId  and t.majorId = m.id ORDER BY t.id";
				} else {
					sql += "WHERE sign = 0 and l.largeAreaName = '" + largeName
							+ "' AND  l.id = s.largeAreaId and s.id = t.schoolId  and t.majorId = m.id and t.role = '"
							+ roleLevel + "' ORDER BY t.id";
				}
			} else {// ��ѯ�������� �����£���ѡ���רҵ��ȫ����ʦ����Ϣ
				if ("".equals(roleLevel)) {
					sql += "WHERE sign = 0 and l.largeAreaName = '" + largeName + "' and m.majorName = '" + majorName
							+ "' AND l.id = s.largeAreaId and s.id = t.schoolId  and t.majorId = m.id ORDER BY t.id";
				} else {
					sql += "WHERE sign = 0 and l.largeAreaName = '" + largeName + "' and m.majorName = '" + majorName
							+ "' AND l.id = s.largeAreaId and s.id = t.schoolId  and t.majorId = m.id and t.role ='"
							+ roleLevel + "' ORDER BY t.id";
				}
			}
		} else {// ��������У������Ϊ�յ�ʱ��
			if (majorName == null || "\"\"".equals(majorName) || "".equals(majorName)) { // ��רҵΪ�յ�ʱ��
				if ("".equals(roleLevel)) {
					sql += "WHERE sign = 0 and l.largeAreaName = '" + largeName + "' and s.schoolName = '" + schoolName
							+ "' AND l.id = s.largeAreaId and s.id = t.schoolId  and t.majorId = m.id ORDER BY t.id";
				} else {
					sql += "WHERE sign = 0 and l.largeAreaName = '" + largeName + "' and s.schoolName = '" + schoolName
							+ "' AND l.id = s.largeAreaId and s.id = t.schoolId  and t.majorId = m.id and t.role = '"
							+ roleLevel + "' ORDER BY t.id";
				}
			} else { // ������У����רҵ�����յ�ʱ�򣬲�ѯ�������е���ʦ����Ϣ
				if ("".equals(roleLevel)) {
					sql += " WHERE sign = 0 and l.largeAreaName = '" + largeName + "' and s.schoolName = '" + schoolName
							+ "' and m.majorName = '" + majorName
							+ "' AND l.id = s.largeAreaId and s.id = t.schoolId  and t.majorId = m.id ORDER BY t.id";
				} else {
					sql += " WHERE sign = 0 and l.largeAreaName = '" + largeName + "' and s.schoolName = '" + schoolName
							+ "' and m.majorName = '" + majorName
							+ "' AND l.id = s.largeAreaId and s.id = t.schoolId  and t.majorId = m.id and t.role = '"
							+ roleLevel + "' ORDER BY t.id";
				}
			}
		}
		System.out.println("��ѯҳ���ϵ���ϢgetEditTextBean----->" + sql);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<EditBean> editTextList = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			editTextList = new ArrayList<EditBean>();

			while (rs.next()) {
				EditBean bean = new EditBean();
				bean.setId(rs.getInt("id"));
				bean.setTeacherName(rs.getString("teacherName"));
				bean.setClassName(rs.getString("className"));
				bean.setMajorName(rs.getString("majorName"));
				bean.setRole(rs.getString("role"));
				bean.setMajorId(rs.getInt("majorId"));
				bean.setSchoolId(rs.getInt("schoolId"));
				bean.setSchoolName(rs.getString("schoolName"));
				bean.setLargeAreaName(rs.getString("largeAreaName"));
				editTextList.add(bean);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return editTextList;
	}

	/**
	 * �༭ҳ�������������
	 * 
	 * @param schoolName
	 * @param teacherName
	 * @param className
	 * @param role
	 * @param majorName
	 * @throws SQLException
	 */
	public static int insertEditData(String schoolName, String teacherName, String className, String role,
			String majorName, String username) throws SQLException {
		// -------------��ӵ�ʱ��----------------------
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String createTime = sdf.format(date);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String createDate = sdf1.format(date);
		// -------------��ӵ�ʱ��----------------------
		String sql = "INSERT INTO tab_teacher (teacherName,role,className,majorId,schoolId,sign,createDate,createTime,createUser) VALUES "
				+ " ('" + teacherName + "','" + role + "','" + className
				+ "',(SELECT id FROM tab_major  WHERE majorName = '" + majorName
				+ "'),(SELECT id FROM tab_school  WHERE schoolName = '" + schoolName + "'),0,'" + createDate + "','"
				+ createTime + "','" + username + "')";
		System.out.println("insertEditData----->" + sql);
		int result = 0; // ������ݿ�󷵻صĽ��
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(sql);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

	/**
	 * �������ʦ��ʱ����
	 * 
	 * @param schoolName
	 * @param teacherName
	 * @param className
	 * @param role
	 * @param majorName
	 * @return
	 * @throws SQLException
	 */
	public static int chekUserIsExists(String schoolName, String teacherName, String className, String role,
			String majorName) throws SQLException {
		String sql = "SELECT t.id,t.teacherName,t.schoolId,t.className,t.majorId FROM tab_teacher t WHERE t.teacherName = '"
				+ teacherName + "' AND t.className = '" + className
				+ "' AND t.schoolId = (SELECT id FROM tab_school  WHERE schoolName = '" + schoolName
				+ "') AND t.majorId = (SELECT id FROM tab_major  WHERE majorName = '" + majorName
				+ "') and t.sign = 0 and role = '" + role + "'";
		int result = 0; // ������ݿ�󷵻صĽ��
		System.out.println("chekUserIsExists------->" + sql);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rSet = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
			rSet = stmt.executeQuery(sql);
			while (rSet.next()) {
				result = rSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rSet);
		}
		return result;

	}

	/**
	 * �������ʦ��ʱ������Ѿ����ڣ���sign�ı��Ϊ1��ʱ�򣬽����ݿ��е�sign�޸�Ϊ0����
	 * 
	 * @param schoolName
	 * @param teacherName
	 * @param className
	 * @param role
	 * @param majorName
	 * @return
	 * @throws SQLException
	 */
	public static int InsertChekUserExistsUpdateSign(String schoolName, String teacherName, String className,
			String role, String majorName) throws SQLException {
		String sql = "SELECT t.id,t.teacherName,t.schoolId,t.className,t.majorId FROM tab_teacher t WHERE t.teacherName = '"
				+ teacherName + "' AND t.className = '" + className
				+ "' AND t.schoolId = (SELECT id FROM tab_school  WHERE schoolName = '" + schoolName
				+ "') AND t.majorId = (SELECT id FROM tab_major  WHERE majorName = '" + majorName + "') and t.sign = 1";
		int result = 0; // ������ݿ�󷵻صĽ��
		System.out.println("InsertChekUserExistsUpdateSign------->" + sql);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rSet = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
			rSet = stmt.executeQuery(sql);
			while (rSet.next()) {
				result = rSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rSet);
		}
		return result;
	}

	/**
	 * ɾ��ĳ��У������ʦ�ĵ�����
	 * 
	 * @param schoolName
	 * @param teacherName
	 * @param majorName
	 * @param className
	 * @param role
	 * @return
	 * @throws SQLException
	 */
	public static int updateSign(String schoolName, String teacherName, String className, String role, String majorName)
			throws SQLException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String modifyTime = sdf.format(date);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String modifyDate = sdf1.format(date);

		String sql = "UPDATE tab_teacher SET sign = 0,modifyDate = '" + modifyDate + "',modifyTime = '" + modifyTime
				+ "' where teacherName = '" + teacherName
				+ "' and schoolId = (SELECT id from tab_school WHERE schoolName = '" + schoolName
				+ "') and className = '" + className + "' and  majorId = (SELECT id from tab_major WHERE majorName = '"
				+ majorName + "') and role = '" + role + "'";

		System.out.println("deleteEditPageData----->" + sql);
		int result = 0;// �޸����ݿ��Ľ��
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(sql);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

	/**
	 * ��ѯ�Ƿ���ڴ���
	 * 
	 * @param schoolName
	 * @param majorName
	 * @return
	 * @throws SQLException
	 */
	public static int checkMajorSchool(String schoolName, String majorName) throws SQLException {
		int result = 0;
		String sql = "select schoolId,majorId from tab_sch_and_major th where schoolID = (SELECT id FROM tab_school  WHERE schoolName = '"
				+ schoolName + "') and majorId = (SELECT id FROM tab_major  WHERE majorName = '" + majorName + "')";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rSet = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
			rSet = stmt.executeQuery(sql);
			while (rSet.next()) {
				result = rSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rSet);
		}
		return result;
	}

	public static int insertMajorNameAndSchool(String schoolName, String majorName) throws SQLException {
		String sql = "insert into tab_sch_and_major (schoolId,majorId) values((SELECT id FROM tab_school  WHERE schoolName = '"
				+ schoolName + "'),(SELECT id FROM tab_major  WHERE majorName = '" + majorName + "'))";
		int result = 0; // �������ݿ�󷵻صĽ��
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

	/**
	 * ɾ��ĳ��У������ʦ�ĵ�����
	 * 
	 * @param schoolName
	 * @param teacherName
	 * @param majorName
	 * @param className
	 * @param role
	 * @return
	 * @throws SQLException
	 */
	public static int deleteEditPageData(String id, String username) throws SQLException {
		// String sql = "DELETE FROM tab_teacher WHERE id = '" + id + "'";

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String modifyTime = sdf.format(date);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String modifyDate = sdf1.format(date);

		String sql = "UPDATE tab_teacher SET sign = 1,modifyDate = '" + modifyDate + "',modifyTime = '" + modifyTime
				+ "',modifyUser = '" + username + "' where id = " + id;

		System.out.println("deleteEditPageData----->" + sql);
		int result = 0;// ɾ�����ݿ��Ľ��
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(sql);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

	/**
	 * ��ѯɾ��רҵ��רҵID���Լ�У��ID
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static SelectDeleteBean checkMajorIdAndSchoolId(String id) throws SQLException {
		String sql = "SELECT t.majorId,t.schoolId FROM tab_teacher t WHERE t.id = '" + id + "' ";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		SelectDeleteBean selectDeleteBean = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			selectDeleteBean = new SelectDeleteBean();
			while (rs.next()) {
				selectDeleteBean.setMajorId(rs.getInt("majorId"));
				selectDeleteBean.setSchoolId(rs.getInt("schoolId"));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			// System.out.println("��ѯ���ݴ���~");
			DbPoolConnection.getInstance().close(conn, stmt, rs);
			stmt.close();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return selectDeleteBean;
	}

	/**
	 * ���ɾ�������ݵ�רҵID��У��ID�Ƿ񻹴���
	 * 
	 * @param s
	 * @return
	 * @throws SQLException
	 */
	public static int checkIsExistsWithMajorIdAndSchoolId(SelectDeleteBean s) throws SQLException {
		String sql = "SELECT count(1) FROM tab_teacher t WHERE t.majorId = '" + s.getMajorId() + "' AND t.schoolId = '"
				+ s.getSchoolId() + "' AND t.sign = 0";
		System.out.println("----checkIsExistsWithMajorIdAndSchoolId---->" + sql);
		int result = 0;
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rSet = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
			rSet = stmt.executeQuery(sql);
			while (rSet.next()) {
				result = rSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rSet);
		}
		return result;
	}

	/**
	 * ����ʦ���в��������ݵ�ʱ�򣬰�tab_major_school���е�����ɾ��
	 * 
	 * @param s
	 * @return
	 * @throws SQLException
	 */
	public static int deleteTabMajorAndSchoolIdData(SelectDeleteBean s) throws SQLException {
		String sql = "DELETE FROM tab_sch_and_major WHERE schoolId = '" + s.getSchoolId() + "' AND majorId = '"
				+ s.getMajorId() + "'";
		int result = 0;// ɾ�����ݿ��Ľ��
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(sql);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

	/**
	 * �޸���ʦ����Ϣ
	 * 
	 * @param uBean
	 * @return
	 * @throws SQLException
	 */
	public static int updateEditTeacherData(UpDateBean uBean, String username) throws SQLException {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String modifyTime = sdf.format(date);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String modifyDate = sdf1.format(date);

		String sql = "UPDATE tab_teacher SET teacherName = '" + uBean.getNewTeacherName() + "' ,role = '"
				+ uBean.getNewRole() + "' , className = '" + uBean.getNewClassName()
				+ "' , majorId = (SELECT id from tab_major WHERE majorName = '" + uBean.getNewMajorName()
				+ "'), schoolId = (SELECT id from tab_school WHERE schoolName = '" + uBean.getNewSchoolName()
				+ "'), modifyDate = '" + modifyDate + "',modifyTime = '" + modifyTime + "',modifyUser = '" + username
				+ "' WHERE id = '" + uBean.getId() + "'";
		System.out.println("����updateEditTeacherData-----��" + sql);
		int result = 0;// ɾ�����ݿ��Ľ��
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(sql);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

	/**
	 * ��ȡ�쳣���ݵķ���
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<ExceptionBean> getExceptionBeans(String largeName, String schoolName, String roleLevel,
			String majorName, String startDate, String endDate) throws SQLException {
		String sql = "SELECT t.large_Area,t.sch_Name,t.user_Nick,t.tea_Name,t.role_Level,t.cus_Name,t.stu_Class,t.average,t.stu_Advice,date_format(t.fill_Date,'%Y-%m') formFillDate FROM tab_researchinfo t ";
		int role = 0;
		if ("��ʦ".equals(roleLevel)) {
			role = 0;
		} else if ("������".equals(roleLevel)) {
			role = 1;
		} else if ("��ҵ".equals(roleLevel)) {
			role = 2;
		} else if ("������ʦ".equals(roleLevel)) {
			role = 3;
		} else if ("".equals(roleLevel)) {
			role = 4;
		}

		// ������Ϊ�յ�ʱ�򣬲�ѯ��������ȫ�����е���ʦ����Ϣ
		if (largeName == null || "\"\"".equals(largeName) || "".equals(largeName)) {
			if (majorName == null || "\"\"".equals(majorName) || "".equals(majorName)) {
				if (role == 4) {
					sql += " WHERE t.average <= 3.0 and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "'";
				} else {
					sql += " WHERE t.average <= 3.0 and role_Level = '" + role
							+ "' and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "'";
				}
			} else {
				if (role == 4) {
					sql += " WHERE t.cus_Name = '" + majorName
							+ "' and t.average < 3.0 and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "'";
				} else {
					sql += " WHERE t.cus_Name = '" + majorName + "' and t.average < 3.0 and role_Level = '" + role
							+ "' and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "'";
				}
			}
		} else if (schoolName == null || "\"\"".equals(schoolName) || "".equals(schoolName)) { // ��������Ϊ�գ�У��Ϊ�յ�ʱ��
			if (majorName == null || "\"\"".equals(majorName) || "".equals(majorName)) { // ��ѯ�������Ǵ���������רҵ��ʦ����Ϣ
				if (role == 4) {
					sql += " WHERE t.large_Area = '" + largeName
							+ "' AND  t.average < 3.0 and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "'";
				} else {
					sql += " WHERE t.large_Area = '" + largeName + "' AND  t.average < 3.0 and role_Level = '" + role
							+ "' and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "'";
				}
			} else {// ��ѯ�������� �����£���ѡ���רҵ��ȫ����ʦ����Ϣ
				if (role == 4) {
					sql += " WHERE t.large_Area = '" + largeName + "' and t.cus_Name = '" + majorName
							+ "' AND  t.average < 3.0 and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "' ";
				} else {
					sql += " WHERE t.large_Area = '" + largeName + "' and t.cus_Name = '" + majorName
							+ "' and role_Level = '" + role
							+ "' AND  t.average < 3.0 and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "' ";
				}
			}
		} else {// ��������У������Ϊ�յ�ʱ��
			if (majorName == null || "\"\"".equals(majorName) || "".equals(majorName)) { // ��רҵΪ�յ�ʱ��
				if (role == 4) {
					sql += " WHERE t.large_Area = '" + largeName + "' and t.sch_Name = '" + schoolName
							+ "' AND t.average < 3.0 and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "'";
				} else {
					sql += " WHERE t.large_Area = '" + largeName + "' and t.sch_Name = '" + schoolName
							+ "' AND t.average < 3.0 and role_Level = '" + role
							+ "' and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "'";
				}
			} else { // ������У����רҵ�����յ�ʱ�򣬲�ѯ�������е���ʦ����Ϣ
				if (role == 4) {
					sql += " WHERE t.large_Area = '" + largeName + "' and t.sch_Name = '" + schoolName
							+ "' and t.cus_Name = '" + majorName
							+ "' AND t.average < 3.0 and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "'";
				} else {
					sql += " WHERE t.large_Area = '" + largeName + "' and t.sch_Name = '" + schoolName
							+ "' and t.cus_Name = '" + majorName + "' AND t.average < 3.0 and role_Level = '" + role
							+ "' and date_format(fill_Date,'%Y-%m-%d') >= '" + startDate
							+ "' and date_format(fill_Date,'%Y-%m-%d')<= '" + endDate + "'";
				}
			}
		}

		System.out.println("getExceptionBeans------>" + sql);
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<ExceptionBean> exceptionBeans = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			exceptionBeans = new ArrayList<ExceptionBean>();

			while (rs.next()) {
				ExceptionBean bean = new ExceptionBean();
				bean.setAdvice(rs.getString("stu_Advice"));
				bean.setAverage(rs.getDouble("average"));
				bean.setClassName(rs.getString("stu_Class"));
				bean.setLargeAreaName(rs.getString("large_Area"));
				bean.setMajorName(rs.getString("cus_Name"));
				if (rs.getString("role_Level").equals("0")) {
					bean.setRoleLevel("��ʦ");
				} else if (rs.getString("role_Level").equals("1")) {
					bean.setRoleLevel("������");
				} else if (rs.getString("role_Level").equals("2")) {
					bean.setRoleLevel("��ҵ");
				}
				bean.setSchoolName(rs.getString("sch_Name"));
				bean.setTeacherName(rs.getString("tea_Name"));
				bean.setUserNick(rs.getString("user_Nick"));
				bean.setFillDate(rs.getString("formFillDate"));
				exceptionBeans.add(bean);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return exceptionBeans;
	}

	/**
	 * ����ͼ ��Ϣ
	 * 
	 * @param largeArea
	 * @param schoolName
	 * @param role
	 * @param major
	 * @return
	 * @throws SQLException
	 */
	public static List<ScoreBean> getScoreBeans(String largeArea, String schoolName, String role, String major)
			throws SQLException {
		String sql = "SELECT t.tea_Name,COUNT(t.tea_Name) a,SUM(t.average)/COUNT(1) average,date_format(t.fill_Date,'%Y-%m') fillDate  FROM tab_researchinfo t ";
		if (largeArea == null || "".equals(largeArea)) {
			if (schoolName == null || "".equals(schoolName)) {
				if (role == null || "".equals(role)) {
					if (!(major == null || "".equals(major))) {
						sql = sql + "where t.cus_Name = '" + major + "' ";
					}
				} else {
					sql = sql + "where t.role_Level = '" + role + "' ";
					if (!(major == null || "".equals(major))) {
						sql = sql + "AND t.cus_Name = '" + major + "' ";
					}
				}
			} else {
				sql = sql + "where t.sch_Name = '" + schoolName + "' ";
				if (!(role == null || "".equals(role))) {
					sql = sql + "AND t.role_Level = '" + role + "' ";
				}
				if (!(major == null || "".equals(major))) {
					sql = sql + "AND t.cus_Name = '" + major + "' ";
				}
			}
		} else {
			sql = sql + " where t.large_Area= '" + largeArea + "' ";
			if (!(schoolName == null || "".equals(schoolName))) {
				sql = sql + "AND t.sch_Name = '" + schoolName + "' ";
			}
			if (!(role == null || "".equals(role))) {
				sql = sql + "AND t.role_Level = '" + role + "' ";
			}
			if (!(major == null || "".equals(major))) {
				sql = sql + "AND t.cus_Name = '" + major + "' ";
			}
		}
		sql = sql + "GROUP BY tea_Name,fillDate";
		System.out.println("getScoreBeans sql=" + sql);

		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<ScoreBean> scoreBeanList = new ArrayList<ScoreBean>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			String preName = ""; // ��һλ��ʦ
			Double[] scoreArray = new Double[12];
			for (int i = 0; i < scoreArray.length; i++) {
				scoreArray[i] = -1.0;
			}
			boolean isFirst = true;
			boolean noData = true;
			while (rs.next()) {
				if (noData) {
					noData = false;
				}
				String teacherName = rs.getString("tea_Name");
				Double average = rs.getDouble("average");
				String fillDate = rs.getString("fillDate"); // 2017-05
				int month = Integer.parseInt((fillDate.split("-"))[1]);
				System.out.println("preName=" + preName + ",fillDate=" + fillDate + ",month=" + month + ",teacherName="
						+ teacherName);
				if (isFirst) {
					preName = teacherName;
					isFirst = false;
				}
				if (!teacherName.equals(preName)) { // ��һλ��ʦ
					ScoreBean scoreBean = new ScoreBean(preName, scoreArray);
					scoreBeanList.add(scoreBean); // �����һλ��ʦ����Ϣ
					preName = teacherName;
					scoreArray = new Double[12];
					for (int i = 0; i < scoreArray.length; i++) {
						scoreArray[i] = -1.0;
					}
					scoreArray[month - 1] = average;
				} else { // ͬһλ��ʦ��ֱ�ӽ������������ĵ÷ּ���
					scoreArray[month - 1] = average;
				}
			}
			if (!noData) {
				// ���һλ��ʦ
				ScoreBean scoreBean = new ScoreBean(preName, scoreArray);
				scoreBeanList.add(scoreBean); // �����һλ��ʦ����Ϣ
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return scoreBeanList;
	}

	/**
	 * �״� ��Ϣ
	 * 
	 * @param largeArea
	 * @param school
	 * @param name
	 * @param major
	 * @param month
	 * @param role
	 * @return
	 * @throws SQLException
	 */
	public static RadarResultBean getRadarResultBean(String largeArea, String school, String name, String major,
			String month, String role) throws SQLException {
		String sql = "select t.tea_Name,AVG(t.tea_Attendance) attend,AVG(t.cls_Explain) cexplain,AVG(t.cls_Quesions) que,"
				+ "AVG(t.ques_Answer) ans,AVG(t.cls_Coach) coach,AVG(t.cls_Discipline) dis,AVG(t.cls_Skill) skill,"
				+ "AVG(t.cls_Progress) prog,AVG(t.exam_Explain) eexplain,AVG(t.class_Homework) home "
				+ "from tab_researchinfo t " + "where t.large_Area='" + largeArea + "' and t.sch_Name='" + school
				+ "' and t.tea_Name='" + name + "' " + "and t.cus_Name='" + major + "' and t.fill_Date like '" + month
				+ "%' and t.role_Level='" + role + "'";
		// month:2017-04

		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		RadarResultBean radarResultBean = null;
		DecimalFormat df = new DecimalFormat("#.00");

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Double attend = Double.parseDouble(df.format(rs.getDouble("attend")));
				Double cexplain = Double.parseDouble(df.format(rs.getDouble("cexplain")));
				Double que = Double.parseDouble(df.format(rs.getDouble("que")));
				Double ans = Double.parseDouble(df.format(rs.getDouble("ans")));
				Double coach = Double.parseDouble(df.format(rs.getDouble("coach")));
				Double dis = Double.parseDouble(df.format(rs.getDouble("dis")));
				Double skill = Double.parseDouble(df.format(rs.getDouble("skill")));
				Double prog = Double.parseDouble(df.format(rs.getDouble("prog")));
				Double eexplain = Double.parseDouble(df.format(rs.getDouble("eexplain")));
				Double home = Double.parseDouble(df.format(rs.getDouble("home")));
				System.out.println("attend=" + attend + ",cexplain=" + cexplain + ",que=" + que + ",ans=" + ans
						+ ",coach=" + coach + ",dis=" + dis + ",skill=" + skill + ",prog=" + prog + ",eexplain="
						+ eexplain + ",home=" + home);

				List<Double> scores = new ArrayList<Double>();
				scores.add(attend);
				scores.add(cexplain);
				scores.add(que);
				scores.add(ans);
				scores.add(coach);
				scores.add(dis);
				scores.add(skill);
				scores.add(prog);
				scores.add(eexplain);
				scores.add(home);

				radarResultBean = new RadarResultBean(scores);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}

		return radarResultBean;
	}

	/**
	 * ����ͼ���״�ͼ
	 * 
	 * @param largeArea
	 * @param schoolName
	 * @param role
	 * @param major
	 * @param year
	 * @return
	 * @throws SQLException
	 */
	public static List<ScoreBean2> getScoreBeans2(String largeArea, String schoolName, String role, String major,
			String year) throws SQLException {
		// String sql = "SELECT t.tea_Name,COUNT(t.tea_Name)
		// a,SUM(t.average)/COUNT(1) average,date_format(t.fill_Date,'%Y-%m')
		// fillDate FROM tab_researchinfo t ";
		String sql = "SELECT t.tea_Name,stu_class,COUNT(t.tea_Name) a,SUM(t.average)/COUNT(1) average,YEAR(fill_Date) tyear,"
				+ "DATE_FORMAT(t.fill_Date,'%m') fillDate,AVG(t.tea_Attendance) attend,"
				+ "AVG(t.cls_Explain) cexplain,AVG(t.cls_Quesions) que,AVG(t.ques_Answer) ans,"
				+ "AVG(t.cls_Coach) coach,AVG(t.cls_Discipline) dis,AVG(t.cls_Skill) skill,"
				+ "AVG(t.cls_Progress) prog,AVG(t.exam_Explain) eexplain,AVG(t.class_Homework) home "
				+ "FROM tab_researchinfo t ";
		if (largeArea == null || "".equals(largeArea)) {
			if (schoolName == null || "".equals(schoolName)) {
				if (role == null || "".equals(role)) {
					if (major == null || "".equals(major)) {
						if (!(year == null || "".equals(year))) {
							sql = sql + "where YEAR(fill_Date) = '" + year + "' ";
						}
					} else {
						sql = sql + "where t.cus_Name = '" + major + "' ";
						if (!(year == null || "".equals(year))) {
							sql = sql + "AND YEAR(fill_Date) ='" + year + "' ";
						}
					}
				} else {
					sql = sql + "where t.role_Level = '" + role + "' ";
					if (!(major == null || "".equals(major))) {
						sql = sql + "AND t.cus_Name = '" + major + "' ";
					}
					if (!(year == null || "".equals(year))) {
						sql = sql + "AND YEAR(fill_Date) = '" + year + "' ";
					}
				}
			} else {
				sql = sql + "where t.sch_Name = '" + schoolName + "' ";
				if (!(role == null || "".equals(role))) {
					sql = sql + "AND t.role_Level = '" + role + "' ";
				}
				if (!(major == null || "".equals(major))) {
					sql = sql + "AND t.cus_Name = '" + major + "' ";
				}
				if (!(year == null || "".equals(year))) {
					sql = sql + "AND YEAR(fill_Date) = '" + year + "' ";
				}
			}
		} else {
			sql = sql + " where t.large_Area= '" + largeArea + "' ";
			if (!(schoolName == null || "".equals(schoolName))) {
				sql = sql + "AND t.sch_Name = '" + schoolName + "' ";
			}
			if (!(role == null || "".equals(role))) {
				sql = sql + "AND t.role_Level = '" + role + "' ";
			}
			if (!(major == null || "".equals(major))) {
				sql = sql + "AND t.cus_Name = '" + major + "' ";
			}
			if (!(year == null || "".equals(year))) {
				sql = sql + "AND YEAR(fill_Date) = '" + year + "' ";
			}
		}
		sql = sql + "GROUP BY tea_Name,stu_class,fillDate";
		/*
		 * String sql=
		 * "SELECT t.tea_Name,stu_class,COUNT(t.tea_Name) a,SUM(t.average)/COUNT(1) average,"
		 * +
		 * "DATE_FORMAT(t.fill_Date,'%m') fillDate,AVG(t.tea_Attendance) attend,"
		 * +
		 * "AVG(t.cls_Explain) cexplain,AVG(t.cls_Quesions) que,AVG(t.ques_Answer) ans,"
		 * +
		 * "AVG(t.cls_Coach) coach,AVG(t.cls_Discipline) dis,AVG(t.cls_Skill) skill,"
		 * +
		 * "AVG(t.cls_Progress) prog,AVG(t.exam_Explain) eexplain,AVG(t.class_Homework) home "
		 * + "FROM tab_researchinfo t " + "WHERE t.large_Area='"+largeArea+
		 * "' AND t.sch_Name = '"+schoolName+"' AND t.role_Level = '"+role +
		 * "' AND t.cus_Name = '"+major+"' AND fill_Date LIKE '"+year+
		 * "%' GROUP BY tea_Name,stu_class,fillDate";
		 */
		System.out.println("getScoreBeans sql=" + sql);

		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<ScoreBean2> scoreBeanList = new ArrayList<ScoreBean2>();

		DecimalFormat df = new DecimalFormat("#.00");

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			String preName = ""; // ��һλ��ʦ
			String preStuClass = ""; // ��һ���༶
			Double[] scoreArray = new Double[12];
			Double[][] radar = new Double[12][]; // ��¼��ʦ��ĳ���·�ÿ�����ƽ���÷�
			for (int i = 0; i < scoreArray.length; i++) {
				scoreArray[i] = -1.0;
			}
			boolean isFirst = true;
			boolean noData = true;
			while (rs.next()) {
				if (noData) {
					noData = false;
				}
				String teacherName = rs.getString("tea_Name");
				String stuClass = rs.getString("stu_class");
				Double average = Double.parseDouble(df.format(rs.getDouble("average")));
				int month = Integer.parseInt(rs.getString("fillDate")); // 04
				// int month = Integer.parseInt((fillDate.split("-"))[1]);

				Double attend = Double.parseDouble(df.format(rs.getDouble("attend")));
				Double cexplain = Double.parseDouble(df.format(rs.getDouble("cexplain")));
				Double que = Double.parseDouble(df.format(rs.getDouble("que")));
				Double ans = Double.parseDouble(df.format(rs.getDouble("ans")));
				Double coach = Double.parseDouble(df.format(rs.getDouble("coach")));
				Double dis = Double.parseDouble(df.format(rs.getDouble("dis")));
				Double skill = Double.parseDouble(df.format(rs.getDouble("skill")));
				Double prog = Double.parseDouble(df.format(rs.getDouble("prog")));
				Double eexplain = Double.parseDouble(df.format(rs.getDouble("eexplain")));
				Double home = Double.parseDouble(df.format(rs.getDouble("home")));

				// (month+1)��ÿ�����ƽ����
				Double[] scores = new Double[10];
				scores[0] = attend;
				scores[1] = cexplain;
				scores[2] = que;
				scores[3] = ans;
				scores[4] = coach;
				scores[5] = dis;
				scores[6] = skill;
				scores[7] = prog;
				scores[8] = eexplain;
				scores[9] = home;

				if (isFirst) {
					preName = teacherName;
					preStuClass = stuClass;
					isFirst = false;
				}
				// ��һλ��ʦ or ��һ���༶
				if ((!teacherName.equals(preName)) || (!stuClass.equals(preStuClass))) {
					ScoreBean2 scoreBean = new ScoreBean2(preName, preStuClass, scoreArray, radar);
					scoreBeanList.add(scoreBean); // �����һλ��ʦ����Ϣ
					preName = teacherName;
					preStuClass = stuClass;
					scoreArray = new Double[12];
					for (int i = 0; i < scoreArray.length; i++) {
						scoreArray[i] = -1.0;
					}
					scoreArray[month - 1] = average;
					radar = new Double[12][];
					radar[month - 1] = scores;
				} else { // ͬһλ��ʦ��ֱ�ӽ������������ĵ÷ּ���
					scoreArray[month - 1] = average;
					radar[month - 1] = scores;
				}
			}
			if (!noData) {
				// ���һλ��ʦ
				ScoreBean2 scoreBean = new ScoreBean2(preName, preStuClass, scoreArray, radar);
				scoreBeanList.add(scoreBean); // �����һλ��ʦ����Ϣ
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return scoreBeanList;
	}

	/**
	 * �������ñ��е�����
	 * 
	 * @param jsonData
	 * @param createDate
	 * @param createTime
	 * @param modifyDate
	 * @param modifyTime
	 * @return
	 * @throws SQLException
	 */
	public static int insertSelectData(String jsonData, String createDate, String createTime, String modifyDate,
			String modifyTime) throws SQLException {
		String sql = "insert into data_config (selectData,createDate,createTime,modifyDate,modifyTime) value(?,?,?,?,?)";
		int result = 0; // �������ݿ�󷵻صĽ��
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		try {
			// ���PreparedStatement����
			ps = conn.prepareStatement(sql);
			ps.setString(1, jsonData);
			ps.setString(2, createDate);
			ps.setString(3, createTime);
			ps.setString(4, modifyDate);
			ps.setString(5, modifyTime);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

	/**
	 * ��ѯ���ñ��е�����
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static ConfigBean seletConfigData() throws SQLException {
		String sql = "select id,selectData,createDate,createTime,modifyDate,modifyTime from data_config";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ConfigBean configBean = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			configBean = new ConfigBean();
			while (rs.next()) {
				configBean.setId(rs.getInt("id"));
				configBean.setSelectData(rs.getString("selectData"));
				configBean.setCreateDate(rs.getString("createDate"));
				configBean.setCreateTime(rs.getString("createTime"));
				configBean.setModifyDate(rs.getString("modifyDate"));
				configBean.setModifyTime(rs.getString("modifyTime"));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("��ѯ���ݴ���~");
			DbPoolConnection.getInstance().close(conn, stmt, rs);
			stmt.close();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return configBean;
	}

	/**
	 * �޸����ñ��е�json����
	 * 
	 * @param jsonData
	 * @return
	 * @throws SQLException
	 */
	public static int modifySelectData(String jsonData) throws SQLException {
		int result = 0;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String modifyTime = sdf.format(date);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String modifyDate = sdf1.format(date);
		String sql = "UPDATE data_config SET selectData = '" + jsonData + "',modifyDate = '" + modifyDate
				+ "',modifyTime= '" + modifyTime + "'";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(sql);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

	// ----------------------�������ݵķ���------------------------------------------

	public static List<SchoolAndMajor> getSchoolAndMajors() throws SQLException {
		String sql = "SELECT DISTINCT t.sch_Name,t.cus_Name from tab_researchinfo t";
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<SchoolAndMajor> list = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // ִ�в�ѯ���ݿ�Ĳ���
			list = new ArrayList<SchoolAndMajor>();
			while (rs.next()) {
				SchoolAndMajor schoolAndMajor = new SchoolAndMajor();
				schoolAndMajor.setMajorName(rs.getString("cus_Name"));
				schoolAndMajor.setSchoolName(rs.getString("sch_Name"));
				list.add(schoolAndMajor);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			// System.out.println("��ѯ���ݴ���~");
			DbPoolConnection.getInstance().close(conn, stmt, rs);
			stmt.close();
		} finally {
			DbPoolConnection.getInstance().close(conn, stmt, rs);
		}
		return list;
	}

	public static int inserSchoolAndMajorData(SchoolAndMajor schoolAndMajor) throws SQLException {
		String sql = "INSERT INTO tab_sch_and_major (schoolId,majorId) VALUES((SELECT s.id FROM tab_school s WHERE s.schoolName = '"
				+ schoolAndMajor.getSchoolName() + "'),(SELECT m.id FROM tab_major m WHERE m.majorName = '"
				+ schoolAndMajor.getMajorName() + "'))";
		int result = 0; // �������ݿ�󷵻صĽ��
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

	public static int insertDataIntoTeacherTab(SubjectBean subjectBean) throws SQLException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String createTime = sdf.format(date);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String createDate = sdf1.format(date);

		String sql = "insert into tab_teacher (teacherName,role,className,majorId,schoolId,sign,createDate,createTime) value("
				+ " '" + subjectBean.getTeacherName() + "','" + subjectBean.getRole() + "','"
				+ subjectBean.getClassName() + "',(SELECT m.id FROM tab_major m WHERE m.majorName = '"
				+ subjectBean.getMajorName() + "') ,(SELECT s.id FROM tab_school s WHERE s.schoolName = '"
				+ subjectBean.getSchooName() + "'),0,'" + createDate + "','" + createTime + "')";
		int result = 0; // �������ݿ�󷵻صĽ��
		DruidPooledConnection conn = DbPoolConnection.getInstance().getConnection();// ��ȡ���ݿ������
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DbPoolConnection.getInstance().close(conn, ps, null);
		}
		return result;
	}

}
