package com.edu.bean;

/**
 * 查询当删除的时候老师表中是否还存在当前的专业
 * 
 * @author Administrator
 *
 */
public class SelectDeleteBean {
	private int majorId;
	private int schoolId;

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	@Override
	public String toString() {
		return "SelectDeleteBean [majorId=" + majorId + ", schoolId=" + schoolId + "]";
	}

}
