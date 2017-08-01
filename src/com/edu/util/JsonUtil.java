package com.edu.util;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edu.bean.Investigation;
import com.edu.bean.SelectBean;
import com.edu.bean.AccessTokenBean;
import com.edu.bean.WXUserBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	/**
	 * �û��������ύ���ݿ�
	 * 
	 * @param json
	 * @return
	 */
	public static Investigation getJsonBean(String json, String unionid, String nickname) {
		Investigation bean = null;
		if (json != null) {
			bean = new Investigation();
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (unionid != null && !"".equals(unionid)) {
				bean.setUser_Id(unionid);
			} else {
				String uid = jsonObj.getString("uid");
				bean.setUser_Id(uid);
			}
			try {
				if (nickname != null && !"".equals(nickname)) {
					bean.setUser_Nick(nickname); // �û��ǳ�
				} else {
					String nickNameString = jsonObj.getString("nickName");
					nickNameString = URLDecoder.decode(nickNameString, "UTF-8");
					bean.setUser_Nick(nickNameString); // �û��ǳ�
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			// ��������
			String large_Area = jsonObj.getString("large_Area");
			// У������
			String sch_Name = jsonObj.getString("sch_Name");
			// ��ʦ����
			String tea_Name = jsonObj.getString("tea_Name");
			// ���ڿγ�
			String cus_Name = jsonObj.getString("cus_Name");
			// ��ɫ
			String role_Level = jsonObj.getString("role_Level");
			// �༶���
			String stu_Class = jsonObj.getString("stu_Class");
			stu_Class = stu_Class.toUpperCase();
			// �������
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fill_Date = sdf.format(date);

			// ��ʦ����
			double tea_Attendance = jsonObj.getDouble("tea_Attendance");
			// ���ý���
			double cls_Explain = jsonObj.getDouble("cls_Explain");
			// ��������
			double cls_Quesions = jsonObj.getDouble("cls_Quesions");
			// �ش�ѧԱ����
			double ques_Answer = jsonObj.getDouble("ques_Answer");
			// �κ󸨵�
			double cls_Coach = jsonObj.getDouble("cls_Coach");
			// ���ü���
			double cls_Discipline = jsonObj.getDouble("cls_Discipline");
			// �ڿμ���
			double cls_Skill = jsonObj.getDouble("cls_Skill");
			// �ڿν���
			double cls_Progress = jsonObj.getDouble("cls_Progress");
			// ʵ������
			double exam_Explain = jsonObj.getDouble("exam_Explain");
			// �κ���ҵ
			double class_Homework = jsonObj.getDouble("class_Homework");
			new FormulaUtil();
			double total_Score = FormulaUtil.getTotalScore(tea_Attendance, cls_Explain, cls_Quesions, ques_Answer,
					cls_Coach, cls_Discipline, cls_Skill, cls_Progress, exam_Explain, class_Homework);
			double average_Score = total_Score / 10;
			// ѧԱ����
			String stu_Advice = jsonObj.getString("stu_Advice");
			bean.setRole_Level(role_Level);// ���۵Ľ�ɫ
			bean.setStu_Class(stu_Class);// �༶���
			bean.setLarge_Area(large_Area);
			bean.setSch_Name(sch_Name);
			bean.setTea_Name(tea_Name);
			bean.setCus_Name(cus_Name);
			bean.setFill_Date(fill_Date);
			bean.setTea_Attendance(tea_Attendance);
			bean.setCls_Explain(cls_Explain);
			bean.setCls_Quesions(cls_Quesions);
			bean.setQues_Answer(ques_Answer);
			bean.setCls_Coach(cls_Coach);
			bean.setCls_Discipline(cls_Discipline);
			bean.setCls_Skill(cls_Skill);
			bean.setCls_Progress(cls_Progress);
			bean.setExam_Explain(exam_Explain);
			bean.setClass_Homework(class_Homework);
			bean.setTotal_Score(total_Score);
			bean.setStu_Advice(stu_Advice);
			bean.setAverage(average_Score);
			System.out.println(bean);
		}
		return bean;
	}

	/**
	 * ������ѯ
	 * 
	 * @param json
	 * @return
	 */
	public static SelectBean getJsonSelectJson(String json) {
		SelectBean selectBean = null;
		if (json != null) {
			selectBean = new SelectBean();
			JSONObject jsonObj = JSONObject.fromObject(json);
			// ��������
			String largeArea = jsonObj.getString("largeArea");
			// У������
			String schName = jsonObj.getString("schName");
			// רҵ
			String cusName = jsonObj.getString("cusName");
			// ��ʼʱ��
			String startDate = jsonObj.getString("startDate");
			// ����ʱ��
			String endDate = jsonObj.getString("endDate");
			// ��ɫ
			String role = jsonObj.getString("role");
			selectBean.setRole_Level(role);
			selectBean.setLargeArea(largeArea);
			selectBean.setSchName(schName);
			selectBean.setMajor(cusName);
			selectBean.setStartDate(startDate);
			selectBean.setEndDate(endDate);

		}
		return selectBean;
	}

	/**
	 * �������
	 * 
	 * @param json
	 * @return
	 */
	/**
	 * ����ǰ̨ҳ�洫����������
	 * 
	 * @param json
	 * @return
	 */
	public static List<Investigation> getJsonExprossBean(String json) {
		Investigation bean = null;
		List<Investigation> list = null;
		if (json != null) {
			list = new ArrayList<Investigation>();
			JSONArray array = JSONArray.fromObject(json);
			for (int i = 0; i < array.size(); i++) {
				bean = new Investigation();
				JSONObject jsonObj = array.getJSONObject(i);
				// ��������
				String large_Area = jsonObj.getString("large_Area");
				// У������
				String sch_Name = jsonObj.getString("sch_Name");
				// ��ʦ����
				String tea_Name = jsonObj.getString("tea_Name");
				// ���ڿγ�
				String cus_Name = jsonObj.getString("cus_Name");

				double total_Score = jsonObj.getDouble("total_Score");
				double average_Score = jsonObj.getDouble("average");
				// ѧԱ����
				String stu_Advice = jsonObj.getString("stu_Advice");
				bean.setLarge_Area(large_Area);
				bean.setSch_Name(sch_Name);
				bean.setTea_Name(tea_Name);
				bean.setCus_Name(cus_Name);
				bean.setTotal_Score(total_Score);
				bean.setStu_Advice(stu_Advice);
				bean.setAverage(average_Score);
				list.add(bean);
			}
		}
		return list;
	}

	/**
	 * ��ȡ΢�����Ƶ���Ϣ
	 * 
	 * @return
	 */
	public static AccessTokenBean getWeiChat(String jsonString) {
		AccessTokenBean weiChat = null;
		try {
			if (jsonString != null) {
				weiChat = new AccessTokenBean();
				JSONObject jsonObj = JSONObject.fromObject(jsonString);
				String access_token = jsonObj.getString("access_token");
				int expires_in = jsonObj.getInt("expires_in");
				String refresh_token = jsonObj.getString("refresh_token");
				String openid = jsonObj.getString("openid");
				String scope = jsonObj.getString("scope");
				String unionid = jsonObj.getString("unionid");

				weiChat.setAccess_token(access_token);
				weiChat.setExpires_in(expires_in);
				weiChat.setOpenid(openid);
				weiChat.setRefresh_token(refresh_token);
				weiChat.setScope(scope);
				weiChat.setUnionid(unionid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����������������������");
		}
		return weiChat;
	}

	/**
	 * ����΢���û�����Ϣ
	 * 
	 * @return
	 */
	public static WXUserBean getWeiXinUserInfo(String weiXinJson) {
		WXUserBean weiXinUserInfo = null;
		try {
			if (weiXinJson != null) {
				weiXinUserInfo = new WXUserBean();
				JSONObject jsonObject = JSONObject.fromObject(weiXinJson);
				String openid = jsonObject.getString("openid");
				String nickname = jsonObject.getString("nickname");
				int sex = jsonObject.getInt("sex");
				String province = jsonObject.getString("province");
				String city = jsonObject.getString("city");
				String country = jsonObject.getString("country");
				String headimgurl = jsonObject.getString("headimgurl");
				String unionid = jsonObject.getString("unionid");
				weiXinUserInfo.setOpenid(openid);
				weiXinUserInfo.setNickname(nickname);
				weiXinUserInfo.setSex(sex);
				weiXinUserInfo.setProvince(province);
				weiXinUserInfo.setCity(city);
				weiXinUserInfo.setCountry(country);
				weiXinUserInfo.setHeadimgurl(headimgurl);
				weiXinUserInfo.setUnionid(unionid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("�����쳣");
		}

		return weiXinUserInfo;
	}

	/**
	 * �ж��Ƿ��ظ�ͶƱ
	 * 
	 * @param json
	 *            ��������������json�ĸ�ʽչʾ
	 * @return Investigation ������ĵ����ݶ���
	 * 
	 */
	public static Investigation getCheckRepeatJson(String json, String unionid) {
		Investigation bean = null;
		if (json != null) {
			bean = new Investigation();
			JSONObject jsonObj = JSONObject.fromObject(json);
			// ��������
			String large_Area = jsonObj.getString("large_Area");
			// У������
			String sch_Name = jsonObj.getString("sch_Name");
			// ��ʦ����
			String tea_Name = jsonObj.getString("tea_Name");
			// ���ڿγ�
			String cus_Name = jsonObj.getString("cus_Name");
			// ��ɫ
			String role_Level = jsonObj.getString("role_Level");
			if (unionid == null) {
				String uid = jsonObj.getString("uid");
				bean.setUser_Id(uid);
			} else {
				bean.setUser_Id(unionid);
			}

			// �������
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fill_Date = sdf.format(date);

			bean.setUser_Id(unionid);
			bean.setLarge_Area(large_Area);
			bean.setSch_Name(sch_Name);
			bean.setTea_Name(tea_Name);
			bean.setRole_Level(role_Level);
			bean.setCus_Name(cus_Name);
			bean.setFill_Date(fill_Date);
		}
		return bean;
	}

//	public static UpDateBean getUpdateJsonData(String updateJson) {
//		UpDateBean uBean = null;
//		if (updateJson != null) {
//			uBean = new UpDateBean();
//			JSONObject jsonObj = JSONObject.fromObject(updateJson);
//			String oldTeacherName = jsonObj.getString("oldTeacherName");
//			String oldRole = jsonObj.getString("oldRole");
//			String oldClassName = jsonObj.getString("oldClassName");
//			String oldMajorName = jsonObj.getString("oldMajorName");
//			String oldSchoolName = jsonObj.getString("oldSchoolName");
//
//			String newTeacherName = jsonObj.getString("newTeacherName");
//			String newRole = jsonObj.getString("newRole");
//			String newClassName = jsonObj.getString("newClassName");
//			String newMajorName = jsonObj.getString("newMajorName");
//			String newSchoolName = jsonObj.getString("newSchoolName");
//
////			uBean.setOldClassName(oldClassName);
////			uBean.setOldMajorName(oldMajorName);
////			uBean.setOldRole(oldRole);
////			uBean.setOldSchoolName(oldSchoolName);
////			uBean.setOldTeacherName(oldTeacherName);
//
//			uBean.setNewClassName(newClassName);
//			uBean.setNewMajorName(newMajorName);
//			uBean.setNewRole(newRole);
//			uBean.setNewSchoolName(newSchoolName);
//			uBean.setNewTeacherName(newTeacherName);
//
//		}
//		return uBean;
//	}

}
