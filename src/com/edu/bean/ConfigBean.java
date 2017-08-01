package com.edu.bean;

/**
 * 配置表中的数据
 * 
 * @author Administrator
 *
 */
public class ConfigBean {

	private int id;
	private String selectData;
	private String createDate;
	private String createTime;
	private String modifyDate;
	private String modifyTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSelectData() {
		return selectData;
	}

	public void setSelectData(String selectData) {
		this.selectData = selectData;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

}
