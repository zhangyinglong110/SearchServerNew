package com.edu.bean;

/**
 * ��ѯ��ɾ����ʱ����ʦ�����Ƿ񻹴��ڵ�ǰ��רҵ
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
