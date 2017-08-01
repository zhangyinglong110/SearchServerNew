package com.edu.bean;

import java.util.List;

public class RadarResultBean {
	private List<Double> scores;
	
	public RadarResultBean(){}
	public RadarResultBean(List<Double> scores){
		this.scores=scores;
	}
	public List<Double> getScores() {
		return scores;
	}
	public void setScores(List<Double> scores) {
		this.scores = scores;
	}
	@Override
	public String toString() {
		return "RadarResultBean [scores=" + scores + "]";
	}
}
