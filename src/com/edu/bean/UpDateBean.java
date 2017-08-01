package com.edu.bean;

/**
 * 修改老师信息的实体类
 * 
 * @author Administrator
 *
 */
public class UpDateBean {
	private int id;// id
	private String newTeacherName; // 班级名
	private String newRole;// 校区
	private String newClassName; // 专业名称
	private String newMajorName; // 角色
	private String newSchoolName; // 老师名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNewTeacherName() {
		return newTeacherName;
	}

	public void setNewTeacherName(String newTeacherName) {
		this.newTeacherName = newTeacherName;
	}

	public String getNewRole() {
		return newRole;
	}

	public void setNewRole(String newRole) {
		this.newRole = newRole;
	}

	public String getNewClassName() {
		return newClassName;
	}

	public void setNewClassName(String newClassName) {
		this.newClassName = newClassName;
	}

	public String getNewMajorName() {
		return newMajorName;
	}

	public void setNewMajorName(String newMajorName) {
		this.newMajorName = newMajorName;
	}

	public String getNewSchoolName() {
		return newSchoolName;
	}

	public void setNewSchoolName(String newSchoolName) {
		this.newSchoolName = newSchoolName;
	}

}
