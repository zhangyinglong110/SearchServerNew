package com.edu.bean;

import java.util.List;

/**
 * ��װĳУ����ʦ����������
 * 
 * @author Administrator
 *
 */
public class BrokenLine2 {

	private List<String> xAxis;
	private List<ScoreBean2> teachers;
	
	public BrokenLine2(){}
	public BrokenLine2(List<String> xAxis,List<ScoreBean2> teachers){
		this.xAxis=xAxis;
		this.teachers=teachers;
	}

	public List<String> getxAxis() {
		return xAxis;
	}

	public void setxAxis(List<String> xAxis) {
		this.xAxis = xAxis;
	}

	public List<ScoreBean2> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<ScoreBean2> teachers) {
		this.teachers = teachers;
	}

}
