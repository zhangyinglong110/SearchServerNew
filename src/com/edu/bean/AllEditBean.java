package com.edu.bean;

import java.util.List;

/**
 * �༭ҳ���json
 * 
 * @author Administrator
 *
 */
public class AllEditBean {
	private int code;
	private String msg;
	private List<EditBean> results;

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

	public List<EditBean> getResults() {
		return results;
	}

	public void setResults(List<EditBean> results) {
		this.results = results;
	}

}
