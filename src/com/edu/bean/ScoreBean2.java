package com.edu.bean;

import java.util.Arrays;
import java.util.List;

public class ScoreBean2 {

	private String name;
	private String stuclass;
	private Double[] scores;
	private Double[][] radar;
	
	public ScoreBean2(){}
	public ScoreBean2(String name,String stuclass,Double[] scores,Double[][] radar){
		this.name=name;
		this.stuclass=stuclass;
		this.scores=scores;
		this.radar=radar;
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
	public Double[][] getRadar() {
		return radar;
	}
	public void setRadar(Double[][] radar) {
		this.radar = radar;
	}
	public String getStuclass() {
		return stuclass;
	}
	public void setStuclass(String stuclass) {
		this.stuclass = stuclass;
	}
	@Override
	public String toString() {
		return "ScoreBean2 [name=" + name + ", stuclass=" + stuclass + ", scores=" + Arrays.toString(scores)
				+ ", radar=" + Arrays.toString(radar) + "]";
	}
}
