package com.edu.bean;

import java.util.Arrays;
import java.util.List;

/**
 * 老师的数据类
 * 
 */
public class ScoreBean {

	private String name;
	private Double[] scores;
	
	public ScoreBean(){}
	public ScoreBean(String name,Double[] scores){
		this.name=name;
		this.scores=scores;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double[] getScores() {
		return scores;
	}
	public void setScores(Double[] scores) {
		this.scores = scores;
	}
	@Override
	public String toString() {
		return "ScoreBean [name=" + name + ", scores=" + Arrays.toString(scores) + "]";
	}
}
