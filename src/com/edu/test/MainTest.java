package com.edu.test;

import java.sql.SQLException;
import java.util.List;

import com.edu.util.DataBaseOperaUtil;

public class MainTest {
	public static void main(String[] args) {
		try {
			List<SchoolAndMajor> listSchoolAndMajor = DataBaseOperaUtil.getSchoolAndMajors();
			for (int i = 0; i < listSchoolAndMajor.size(); i++) {
				int result = DataBaseOperaUtil.inserSchoolAndMajorData(listSchoolAndMajor.get(i));
				if (result > 0) {
					System.out.println("插入数据成功");
				}else {
					System.out.println("插入数据失败");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
