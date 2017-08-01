package com.edu.bean;

import java.util.List;

/**
 * SelectServlet ÖÐjson
 * @author Administrator
 *
 */
public class SelectJsonBean {
	private int code;
	private String msg;
	private List<Investigation> selectInfo;
	
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
	public List<Investigation> getSelectInfo() {
		return selectInfo;
	}
	public void setSelectInfo(List<Investigation> selectInfo) {
		this.selectInfo = selectInfo;
	}
}
