package com.edu.bean;

import java.util.List;

public class AllExceptionBean {

	private int code;
	private String msg;
	private List<ExceptionBean> results;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<ExceptionBean> getResults() {
		return results;
	}

	public void setResults(List<ExceptionBean> results) {
		this.results = results;
	}

}
