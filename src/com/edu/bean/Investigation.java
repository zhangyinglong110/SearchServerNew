package com.edu.bean;

/**
 * ���ݿ�ʵ����
 * 
 * @author Administrator
 *
 */
public class Investigation {
	// ���
	private int user_Num;
	// �û�ID
	private String user_Id;
	// ��������
	private String large_Area;
	// У������
	private String sch_Name;
	// �༶���
	private String stu_Class;
	// ��ɫ(�����Ρ���ʦ)
	private String role_Level;
	// �û����ǳ�
	private String user_Nick;
	// ��ʦ����
	private String tea_Name;
	// ���ڿγ�
	private String cus_Name;
	// �������
	private String fill_Date;
	// ��ʦ����
	private double tea_Attendance;
	// ���ý���
	private double cls_Explain;
	// ��������
	private double cls_Quesions;
	// �ش�ѧԱ����
	private double ques_Answer;
	// �κ󸨵�
	private double cls_Coach;
	// ���ü���
	private double cls_Discipline;
	// �ڿμ���
	private double cls_Skill;
	// �ڿν���
	private double cls_Progress;
	// ʵ������
	private double exam_Explain;
	// �κ���ҵ
	private double class_Homework;
	// �ܷ�
	private double total_Score;
	// ѧԱ����
	private String stu_Advice;
	// ƽ����
	private double average;
	// ͶƱ������
	private int peopleCount;

	public int getUser_Num() {
		return user_Num;
	}

	public void setUser_Num(int user_Num) {
		this.user_Num = user_Num;
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getLarge_Area() {
		return large_Area;
	}

	// ���ô���������
	public void setLarge_Area(String large_Area) {
		this.large_Area = large_Area;
	}

	public String getSch_Name() {
		return sch_Name;
	}

	// ����У������
	public void setSch_Name(String sch_Name) {
		if (sch_Name != null && stu_Advice != "") {
			this.sch_Name = sch_Name;
		}
	}

	public String getTea_Name() {
		return tea_Name;
	}

	// ���ý�ʦ����
	public void setTea_Name(String tea_Name) {
		if (tea_Name != null && stu_Advice != "") {
			this.tea_Name = tea_Name;
		}
	}

	public String getCus_Name() {
		return cus_Name;
	}

	// �����ڿ�����
	public void setCus_Name(String cus_Name) {
		if (cus_Name != null && stu_Advice != "") {
			this.cus_Name = cus_Name;
		}
	}

	public String getFill_Date() {
		return fill_Date;
	}

	// ����������ڵ�ʱ��
	public void setFill_Date(String fill_Date) {
		if (fill_Date != null && stu_Advice != "") {
			this.fill_Date = fill_Date;
		}
	}

	public double getTea_Attendance() {
		return tea_Attendance;
	}

	// ���ó���ֵ
	public void setTea_Attendance(double tea_Attendance) {
		if (tea_Attendance >= 0 && tea_Attendance <= 5) {
			this.tea_Attendance = tea_Attendance;
		}
	}

	public double getCls_Explain() {
		return cls_Explain;
	}

	// ���ÿ��ý����ֵ
	public void setCls_Explain(double cls_Explain) {
		if (cls_Explain >= 0 && cls_Explain <= 5) {
			this.cls_Explain = cls_Explain;
		}
	}

	public double getCls_Quesions() {
		return cls_Quesions;
	}

	// ���ÿ������ʵ�ֵ
	public void setCls_Quesions(double cls_Quesions) {
		if (cls_Quesions >= 0 && cls_Quesions <= 5) {
			this.cls_Quesions = cls_Quesions;
		}
	}

	public double getQues_Answer() {
		return ques_Answer;
	}

	// ���ûش�ѧԱ����ֵ
	public void setQues_Answer(double ques_Answer) {
		if (ques_Answer >= 0 && ques_Answer <= 5) {
			this.ques_Answer = ques_Answer;
		}
	}

	public double getCls_Coach() {
		return cls_Coach;
	}

	// ���ÿκ󸨵���ֵ
	public void setCls_Coach(double cls_Coach) {
		if (cls_Coach >= 0 && cls_Coach <= 5) {
			this.cls_Coach = cls_Coach;
		}
	}

	public double getCls_Discipline() {
		return cls_Discipline;
	}

	// ���ü���
	public void setCls_Discipline(double cls_Discipline) {
		if (cls_Discipline >= 0 && cls_Discipline <= 5) {
			this.cls_Discipline = cls_Discipline;
		}
	}

	public double getCls_Skill() {
		return cls_Skill;
	}

	// �ڿμ���
	public void setCls_Skill(double cls_Skill) {
		if (cls_Skill >= 0 && cls_Skill <= 5) {
			this.cls_Skill = cls_Skill;
		}
	}

	public double getCls_Progress() {
		return cls_Progress;
	}

	// �ڿν���
	public void setCls_Progress(double cls_Progress) {
		if (cls_Progress >= 0 && cls_Progress <= 5) {
			this.cls_Progress = cls_Progress;
		}
	}

	public double getExam_Explain() {
		return exam_Explain;
	}

	// ʵ������
	public void setExam_Explain(double exam_Explain) {
		if (exam_Explain >= 0 && exam_Explain <= 5) {
			this.exam_Explain = exam_Explain;
		}
	}

	public double getClass_Homework() {
		return class_Homework;
	}

	// �κ���ҵ
	public void setClass_Homework(double class_Homework) {
		if (class_Homework >= 0 && class_Homework <= 5) {
			this.class_Homework = class_Homework;
		}
	}

	public double getTotal_Score() {
		return total_Score;
	}

	// �ܷ�
	public void setTotal_Score(double total_Score) {
		if (class_Homework >= 0 && class_Homework <= 5) {
			this.total_Score = total_Score;
		}
	}

	public String getStu_Advice() {
		return stu_Advice;
	}

	// ����
	public void setStu_Advice(String stu_Advice) {
		if (stu_Advice != null && stu_Advice != "") {
			this.stu_Advice = stu_Advice;
		}
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public int getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}

	public String getStu_Class() {
		return stu_Class;
	}

	public void setStu_Class(String stu_Class) {
		this.stu_Class = stu_Class;
	}

	public String getRole_Level() {
		return role_Level;
	}

	public void setRole_Level(String role_Level) {
		this.role_Level = role_Level;
	}

	public String getUser_Nick() {
		return user_Nick;
	}

	public void setUser_Nick(String user_Nick) {
		this.user_Nick = user_Nick;
	}

	@Override
	public String toString() {
		return "Investigation [user_Num=" + user_Num + ", user_Id=" + user_Id + ", large_Area=" + large_Area
				+ ", sch_Name=" + sch_Name + ", stu_Class=" + stu_Class + ", role_Level=" + role_Level + ", user_Nick="
				+ user_Nick + ", tea_Name=" + tea_Name + ", cus_Name=" + cus_Name + ", fill_Date=" + fill_Date
				+ ", tea_Attendance=" + tea_Attendance + ", cls_Explain=" + cls_Explain + ", cls_Quesions="
				+ cls_Quesions + ", ques_Answer=" + ques_Answer + ", cls_Coach=" + cls_Coach + ", cls_Discipline="
				+ cls_Discipline + ", cls_Skill=" + cls_Skill + ", cls_Progress=" + cls_Progress + ", exam_Explain="
				+ exam_Explain + ", class_Homework=" + class_Homework + ", total_Score=" + total_Score + ", stu_Advice="
				+ stu_Advice + ", average=" + average + ", peopleCount=" + peopleCount + "]";
	}

//	@Override
//	public String toString() {
//		return tea_Name + ":" + sch_Name + ":" + role_Level + ":" + total_Score+":"+average;
//	}
	
	

}
