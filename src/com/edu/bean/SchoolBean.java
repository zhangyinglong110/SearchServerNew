package com.edu.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * �洢У������Ϣ����
 * 
 * @author Administrator
 *
 */
public class SchoolBean {
	private int schID;
	private String sch;
	private List<Integer> subcode = new ArrayList<Integer>();
	public int tempSubcode = -1;//��ʱ��¼�γ�ID
	
	public int getSchID() {
		return schID;
	}

	public void setSchID(int schID) {
		this.schID = schID;
	}

	public String getSch() {
		return sch;
	}

	public void setSch(String sch) {
		this.sch = sch;
	}

	public List<Integer> getSubcode() {
		return subcode;
	}

	public void setSubcode(List<Integer> subcode) {
		this.subcode = subcode;
	}

}
