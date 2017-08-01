package com.edu.bean;

/**
 * 异常数据的实体类
 * 
 * @author Administrator
 *
 */
public class ExceptionBean {

	private String largeAreaName; // 大区名称
	private String schoolName; // 校区名称
	private String userNick; // 用户昵称
	private String teacherName;// 老师名称
	private String roleLevel; // 角色
	private String majorName;// 专业名称
	private String className;// 班级名称
	private double average; // 平均分
	private String advice; // 学员建议
	private String fillDate; // 填写日期

	public String getLargeAreaName() {
		return largeAreaName;
	}

	public void setLargeAreaName(String largeAreaName) {
		this.largeAreaName = largeAreaName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getFillDate() {
		return fillDate;
	}

	public void setFillDate(String fillDate) {
		this.fillDate = fillDate;
	}

}
