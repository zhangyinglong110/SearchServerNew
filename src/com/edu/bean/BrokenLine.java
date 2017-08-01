package com.edu.bean;

import java.util.List;

/**
 * 封装某校区老师的所以数据
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
