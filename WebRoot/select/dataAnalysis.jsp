﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>教师评价系统后台操作页面</title>

<link rel="stylesheet" href="css/basic.css" />
<link rel="stylesheet" href="css/style.css" />
<!--<link rel="stylesheet" href="css/style-metro.css"/>-->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-table.min.css" />
<link href="css/font-awesome.css" rel="stylesheet" />
<style type="text/css">
/*.chartstyle {
    		width: 40%;
    		height: 300px;
    		float: left;
    		margin-left: 5%;
    		margin-right: 5%;
    	}*/
.zxchartstyle {
	width: 50%;
	height: 400px;
	float: left;
}

.ldchartstyle {
	width: 50%;
	height: 400px;
	float: left;
	border-left: 1px dashed gray;
}
</style>
<script>"undefined"==typeof CODE_LIVE&&(!function(e){var t={nonSecure:"8123",secure:"8124"},c={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=c[n]+r[n]+":"+t[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document),CODE_LIVE=!0);</script><script>"undefined"==typeof CODE_LIVE&&(!function(e){var t={nonSecure:"8123",secure:"8124"},c={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=c[n]+r[n]+":"+t[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document),CODE_LIVE=!0);</script></head>
<body data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-51" data-genuitec-path="/SearchServer/WebRoot/select/dataAnalysis.jsp" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-51" data-genuitec-path="/SearchServer/WebRoot/select/dataAnalysis.jsp">
	<%
		HttpSession s = request.getSession();
		String username = (String) s.getAttribute("username");
		String password = (String) s.getAttribute("password");
		System.out.println("username1:" + username + ",password1:" + password);
		if (username != null && password != null) {
	%>
	<div id="wrapper" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-51" data-genuitec-path="/SearchServer/WebRoot/select/dataAnalysis.jsp" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-51" data-genuitec-path="/SearchServer/WebRoot/select/dataAnalysis.jsp">
		<nav class="navbar  navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">翡翠教育</a>
			</div>
		</nav>
		<!-- 导航 -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<!--查询主页面的侧边栏按钮-->
					<li><a href="queryExport.jsp"><i class="fa fa-dashboard"></i>查询导出</a>
					</li>
					<!--编辑菜单栏按钮-->
					<li><a href="abnormal.jsp"><i class="fa fa-anchor"></i>异常数据</a>
					</li>
					<li><a href="teacherManage.jsp"><i class="fa fa-edit "></i>老师管理</a>
					</li>
					<li><a class="active-menu" href=" dataAnalysis.jsp"><i
							class="fa fa-bar-chart-o"></i>数据分析</a></li>
				</ul>
			</div>

		</nav>
		<!-- 主页面  -->
		<div id="page-wrapper" style="min-height:660px;">
			<div id="page-inner">
				<!--主页面头部-->
				<div class="portlet box grey">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-user"></i>数据分析
						</div>
					</div>
					<div class="panel panel-default" style="margin-bottom:10px;">
						<div class="panel-heading">查询条件</div>
						<div class="panel-body">
							<form id="formSearch" class="form-horizontal">
								<div class="form-group" style="margin-top: 15px">
									<label class="control-label col-sm-1"
										for="txt_search_departmentname">大&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区</label>
									<div class="col-sm-2">
										<select class="form-control" id="bigArea">
											<option value text="请选择">请选择</option>
										</select>
									</div>
									<label class="control-label col-sm-1" for="txt_search_statu">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校</label>
									<div class="col-sm-2">
										<select class="form-control" id="schools">
											<option value txt="请选择">请选择</option>
										</select>
									</div>
									<label class="control-label col-sm-1" for="txt_search_statu">专业</label>
									<div class="col-sm-2">
										<select class="form-control " id="profession">
											<option value txt="请选择">请选择</option>
										</select>
									</div>
									<label class="control-label col-sm-1" for="txt_search_statu">角色</label>
									<div class="col-sm-2">
										<select class="form-control " id="role">
											<option value='0' text="讲师" selected>讲师</option>
											<option value='1' text="班主任">班主任</option>
											<option value='2' text="就业">就业</option>
											<option value='3' text="在线老师">在线老师</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<!--日期-->
									<label class="control-label col-sm-1"
										for="txt_search_departmentname">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份</label>
									<div class="col-sm-2">
										<select class="form-control" id="year">
											<option value text="2017" selected>2017</option>
											<option value text="2018">2018</option>
											<option value text="2019">2019</option>
											<option value text="2020">2020</option>
											<option value text="2021">2021</option>
											<option value text="2022">2022</option>
											<option value text="2023">2023</option>
											<option value text="2024">2024</option>
										</select>
									</div>
									<!--查询按钮-->
									<div class="col-sm-1">
										<button type="button" id="searchBtn" class="btn btn-primary"
											style="margin-left:13px;">查询</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="tableStyle" style="height: 450px; padding-top: 10px;">
						<div class="chartstyle zxchartstyle" id="zxChart"></div>
						<div class="chartstyle ldchartstyle" id="ldChart"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--尾部-->
	<div id="footer-sec" style="margin-top:-10px;">
		<div class="footer">
			<div class="innerContainer">
				<p style="text-align:center;">总部地址:北京市海淀区小南庄怡秀园甲一号亿德大厦10层
					电话：010-61943240</p>
				<p style="text-align:center;">Copyright © 2005-2020
					北京翡翠教育科技有限公司，All Rights Reserved 京ICP备12036804号-23</p>
			</div>
		</div>
	</div>
	<%
		} else {
			response.sendRedirect("../Login.html");
		}
	%>
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-table.min.js"></script>
	<script src="js/bootstrap-table-zh-CN.min.js"></script>
	<script src="js/WdatePicker.js"></script>
	<script src="js/echarts.min.js"></script>
	<script src="js/src/dataAnalysis.js"></script>
</body>
</html>
