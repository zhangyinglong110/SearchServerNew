package com.edu.bean;

import java.util.ArrayList;
import java.util.List;

public class LargeAreaSumBean {
	private int schoolcode; // ������code��
	private String name; // ����������
	private List<SchoolBean> schools = new ArrayList<SchoolBean>();

	public List<SchoolBean> getSchools() {
		return schools;
	}

	public void setSchools(List<SchoolBean> schools) {
		this.schools = schools;
	}

	public int getSchoolcode() {
		return schoolcode;
	}

	public void setSchoolcode(int schoolcode) {
		this.schoolcode = schoolcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���һ��У�� ͬʱ��ǰУ���Ƿ���� ���������½�У��
	 */
	public void addSchool(SchoolBean sb) {
		SchoolBean tmpBean = getSchoolBeanByName(sb.getSchID());
		if (tmpBean == null) {
			tmpBean = sb;
			schools.add(tmpBean);
		}
		tmpBean.getSubcode().add(sb.tempSubcode - 1);
	}

	public SchoolBean getSchoolBeanByName(int schid) {
		for (SchoolBean schoolBean : schools) {
			if (schoolBean.getSchID() == schid) {
				return schoolBean;
			}
		}
		return null;
	}

}
