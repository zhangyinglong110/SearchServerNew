package com.edu.bean;

import java.util.List;

/**
 * ��װĳУ����ʦ����������
 * 
 * @author Administrator
 *
 */
public class BrokenLine {

	private List<String> xAxis;
	private List<ScoreBean> teachers;
	
	public BrokenLine(){}
	public BrokenLine(List<String> xAxis,List<ScoreBean> teachers){
		this.xAxis=xAxis;
		this.teachers=teachers;
	}

	public List<String> getxAxis() {
		return xAxis;
	}

	public void setxAxis(List<String> xAxis) {
		this.xAxis = xAxis;
	}

	public List<ScoreBean> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<ScoreBean> teachers) {
		this.teachers = teachers;
	}

}
