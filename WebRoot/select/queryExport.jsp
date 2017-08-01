﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<title>教师评价系统后台操作页面</title>

<link href="css/basic.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<!--<link href="css/style-metro.css"  rel="stylesheet"/>-->
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/bootstrap-table.min.css" rel="stylesheet" />
<link href="css/font-awesome.css" rel="stylesheet" />
<script>"undefined" == typeof CODE_LIVE && (!function(e) {
		var t = {
				nonSecure : "8123",
				secure : "8124"
			},
			c = {
				nonSecure : "http://",
				secure : "https://"
			},
			r = {
				nonSecure : "127.0.0.1",
				secure : "gapdebug.local.genuitec.com"
			},
			n = "https:" === window.location.protocol ? "secure" : "nonSecure";
		script = e.createElement("script"), script.type = "text/javascript", script.async = !0, script.src = c[n] + r[n] + ":" + t[n] + "/codelive-assets/bundle.js", e.getElementsByTagName("head")[0].appendChild(script)
	}(document), CODE_LIVE = !0);
</script>
<script>"undefined" == typeof CODE_LIVE && (!function(e) {
		var t = {
				nonSecure : "8123",
				secure : "8124"
			},
			c = {
				nonSecure : "http://",
				secure : "https://"
			},
			r = {
				nonSecure : "127.0.0.1",
				secure : "gapdebug.local.genuitec.com"
			},
			n = "https:" === window.location.protocol ? "secure" : "nonSecure";
		script = e.createElement("script"), script.type = "text/javascript", script.async = !0, script.src = c[n] + r[n] + ":" + t[n] + "/codelive-assets/bundle.js", e.getElementsByTagName("head")[0].appendChild(script)
	}(document), CODE_LIVE = !0);
</script>
</head>
<body data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-35"
	data-genuitec-path="/SearchServer/WebRoot/select/queryExport.jsp"
	data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-35"
	data-genuitec-path="/SearchServer/WebRoot/select/queryExport.jsp">
	<%
		HttpSession s = request.getSession();
		String username = (String) s.getAttribute("username");
		String password = (String) s.getAttribute("password");
		if (username != null && password != null) {
	%>
	<div id="wrapper" data-genuitec-lp-enabled="false"
		data-genuitec-file-id="wc1-35"
		data-genuitec-path="/SearchServer/WebRoot/select/queryExport.jsp"
		data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-35"
		data-genuitec-path="/SearchServer/WebRoot/select/queryExport.jsp">
		<nav class="navbar  navbar-cls-top" role="navigation"
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
			<!--<div class="header-right">
                <a href="message-task.html" class="btn btn-info" title="New Message"><b>30 </b><i class="fa fa-envelope-o fa-2x"></i></a>
                <a href="message-task.html" class="btn btn-primary" title="New Task"><b>40 </b><i class="fa fa-bars fa-2x"></i></a>
                <a href="login.html" class="btn btn-danger" title="Logout"><i class="fa fa-exclamation-circle fa-2x"></i></a>
            </div>-->
		</nav>
		<!-- 导航 -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<!--查询主页面的侧边栏按钮-->
					<li><a class="active-menu" href="queryExport.jsp"><i
							class="fa fa-dashboard "></i>查询导出</a></li>
					<li><a href="abnormal.jsp"><i class="fa fa-anchor "></i>异常数据</a>
					</li>
					<li><a href="teacherManage.jsp"><i class="fa fa-edit "></i>老师管理</a>
					</li>
					<li><a href="dataAnalysis.jsp"><i
							class="fa fa-bar-chart-o"></i>数据分析</a></li>
				</ul>
			</div>

		</nav>
		<!-- 主页面  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<!--主页面头部-->
				<div class="portlet box grey">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-user"></i>查询导出
						</div>
					</div>
					<div class="panel panel-default" style="margin-bottom:10px;">
						<div class="panel-heading">查询条件</div>
						<div class="panel-body">
							<form id="formSearch" class="form-horizontal">
								<div class="form-group" style="margin-top: 15px">
									<label class="control-label col-sm-1"
										for="txt_search_departmentname">大&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区</label>
									<div class="col-sm-2">
										<select class="form-control" id="bigArea">
											<option value text="京津冀">京津冀</option>
										</select>
									</div>
									<label class="control-label col-sm-1" for="txt_search_statu">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校</label>
									<div class="col-sm-2">
										<select class="form-control" id="schools">
											<option value txt="请选择">请选择</option>
										</select>
									</div>
									<label class="control-label col-sm-1" for="txt_search_statu">专业</label>
									<div class="col-sm-2">
										<select class="form-control " id="profession">
											<option value>请选择</option>
										</select>
									</div>
									<label class="control-label col-sm-1" for="txt_search_statu">角色</label>
									<div class="col-sm-2">
										<select class="form-control " id="role">
											<!--<option value >请选择</option>-->
											<option value selected>讲师</option>
											<option value>班主任</option>
											<option value>就业</option>
											<option value>在线老师</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<!--日期-->
									<label class="control-label col-sm-1" for="startDate">起始日期</label>
									<div class="col-sm-2">
										<input class="form-control Wdate" style="height:35px;"
											type="text" onclick="WdatePicker()" id="startDate"
											name="startDate">
									</div>
									<label class="control-label col-sm-1" for="endDate">结束日期</label>
									<div class="col-sm-2">
										<input class="form-control Wdate" style="height:35px;"
											type="text" onclick="WdatePicker()" id="endDate"
											name="endDate">
									</div>
									<!--查询按钮-->
									<div class="col-sm-1" style="text-align: left;">
										<button type="button" id="searchBtn" class="btn btn-primary"
											style="margin-left:40px;">查询</button>
									</div>
									<div class="col-sm-1" style="text-align: left;">
										<button type="button" id="export" class="btn btn-primary"
											style="display:none;" style="margin-left:50px;">导出</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div id="toolbar" style="margin-right: 5px; margin-top: -1px;">
						<button id="btn_add" type="button" class="btn btn-default"
							data-toggle="dropdown">
							<span class="glyphicon glyphicon-export icon-share"
								aria-hidden="true"></span> 导出 <span class="caret"
								aria-hidden="true"></span>
						</button>
						<ul class="dropdown-menu">
							<li id="exportli"><a>MS-Excel</a></li>
						</ul>
					</div>
					<div class="tableStyle">
						<table id="tb_departments">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--加载等待-->
	<div class="modal fade" id="myModal"
		style="position:absolute;left:50%;top:40%;">
		<img src="images/loader.gif" alt="等待图标" />
	</div>
	<!--尾部-->
	<div id="footer-sec">

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
	<script src="js/src/queryExport.js"></script>
</body>
</html>
