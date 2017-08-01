<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>教师评价系统后台操作页面</title>

<link href="css/basic.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
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
<body data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-50"
	data-genuitec-path="/SearchServer/WebRoot/select/teacherManage.jsp"
	data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-50"
	data-genuitec-path="/SearchServer/WebRoot/select/teacherManage.jsp">
	<%
		HttpSession s = request.getSession();
		String username = (String) s.getAttribute("username");
		String password = (String) s.getAttribute("password");
		System.out.println("username1:" + username + ",password1:" + password);
		if (username != null && password != null) {
	%>
	<div id="wrapper" data-genuitec-lp-enabled="false"
		data-genuitec-file-id="wc1-50"
		data-genuitec-path="/SearchServer/WebRoot/select/teacherManage.jsp"
		data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-50"
		data-genuitec-path="/SearchServer/WebRoot/select/teacherManage.jsp">
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
					<li><a href="queryExport.jsp"><i class="fa fa-dashboard "></i>查询导出</a>
					</li>
					<!--编辑菜单栏按钮-->
					<li><a href="abnormal.jsp"><i class="fa fa-anchor "></i>异常数据</a>
					</li>
					<li><a class="active-menu" href="teacherManage.jsp"><i
							class="fa fa-edit "></i>老师管理</a></li>
					<li><a href=" dataAnalysis.jsp"><i
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
							<i class="icon-user"></i>老师管理
						</div>
						<!--<div class="actions">
						    <a href="#" id="btnAdd" class="btn btn-success">
						    	<i class="icon-add"></i>新增老师
						    </a>
		                </div>-->
					</div>
					<div class="panel panel-default" style="margin-bottom:10px;">
						<div class="panel-heading">查询条件</div>
						<div class="panel-body">
							<form id="formSearch" class="form-horizontal">
								<div class="form-group" style="margin-top: 15px">
									<div class="col-lg-11 col-md-11">
										<label class="control-label col-lg-1  col-md-1"
											for="txt_search_departmentname">大区</label>
										<div class="col-lg-2 col-md-2">
											<select class="form-control" id="bigArea">
												<option value text="请选择">请选择</option>
											</select>
										</div>
										<label class="control-label col-lg-1  col-md-1"
											for="txt_search_statu">学校</label>
										<div class="col-lg-2  col-md-2">
											<select class="form-control" id="schools">
												<option value txt="请选择">请选择</option>
											</select>
										</div>
										<label class="control-label col-lg-1  col-md-1"
											for="txt_search_statu">专业</label>
										<div class="col-lg-2 col-md-2">
											<select class="form-control " id="profession">
												<option value>请选择</option>
											</select>
										</div>
										<label class="control-label col-lg-1  col-md-1"
											for="txt_search_statu">角色</label>
										<div class="col-lg-2 col-md-2">
											<select class="form-control " id="jueSe">
												<option value>请选择</option>
												<option value>讲师</option>
												<option value>班主任</option>
												<option value>就业</option>
												<option value>在线老师</option>
											</select>
										</div>
									</div>
									<div class="col-lg-1 col-md-1">
										<button type="button" id="searchBtn" class="btn btn-primary">查询</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div id="btnAdd" style="margin-right: 5px; margin-top: -1px;">
						<button href="#" class="btn btn-default">
							<span class="glyphicon glyphicon-plus"></span>新增
						</button>
					</div>
					<div class="tableStyle">
						<table id="tb_departments">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增的模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<div class="modal-title" id="myModalLabel">修改信息</div>
				</div>
				<div class="modal-body">
					<div class="row" style="margin-bottom:20px;">
						<span id="teacherId" style="display:none;"></span>
						<div class="col-xs-1"></div>
						<div class="col-xs-5">
							大区：<select name="daqu" id="bigRegion"
								style="width:174px;height:28px;">
								<option value="" text="请选择">请选择</option>
							</select>
						</div>
						<div class="col-xs-5">
							学校：<select name="school" id="school"
								style="width:174px; height:28px;">
								<option value="" text="请选择" txt="请选择">请选择</option>
							</select>
						</div>
					</div>
					<div class="row" style="margin-bottom:20px;">
						<div class="col-xs-1"></div>
						<div class="col-xs-5">
							专业：<select name="profession" id="prof"
								style="width:174px; height:28px;">
								<option value="请选择" text="请选择">请选择</option>
							</select>
						</div>
						<div class="col-xs-5" id="myclass">
							班级：<input type="text" name="class" id="banji" maxlength="10" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-1"></div>
						<div class="col-xs-8">
							<div style="float: left;">
								<input type="checkbox" name="checkbox" id="checkbox1"
									class="Taisyo" value="讲师"> <label for="checkbox1">讲师</label>
							</div>
							<div style="float: left;">
								<input type="checkbox" name="checkbox" id="checkbox2"
									class="Taisyo" value="班主任" style="margin-left:40px;"> <label
									for="checkbox2">班主任</label>
							</div>
							<div style="float: left;">
								<input type="checkbox" name="checkbox" id="checkbox3"
									class="Taisyo" value="就业" style="margin-left:28px;"> <label
									for="checkbox3">就业</label>
							</div>
							<div id="myonlineTeacher" style="float: left;display: none">
								<input type="checkbox" name="checkbox" id="checkbox4"
									class="Taisyo" value="在线老师" style="margin-left:28px;">
								<label for="checkbox4">在线老师</label>
							</div>
						</div>
					</div>
					<div class="div1 clear"
						style="display:none;width:679px;height:30px;padding-top="8px;">
						<div class="col-xs-1"></div>
						<div class="col-xs-5" style="width:679px;margin-left:46px;">
							讲师
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
								type="text" name="role_teacher" id="role_teacher">
						</div>
					</div>
					<div class="div2 clear"
						style="display:none;width:679px;height:30px;padding-top="8px;">
						<div class="col-xs-1"></div>
						<div class="col-xs-5" style="width:679px;margin-left:46px;">
							班主任&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
								type="text" name="role_banzhuren" id="role_banzhuren"
								style="margin-left:-2px;">
						</div>
					</div>
					<div class="div3 clear"
						style="display:none;width:679px;height:30px;padding-top="8px;">
						<div class="col-xs-1"></div>
						<div class="col-xs-5" style="width:679px;margin-left:46px;">
							就业
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
								type="text" name="role_work" id="role_work">

						</div>
					</div>
					<div class="div4 clear"
						style="display:none;width:679px;height:30px;padding-top="8px;">
						<div class="col-xs-1"></div>
						<div class="col-xs-5" style="width:679px;margin-left:46px;">
							在线老师 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
								name="role_online" id="role_online">
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-ban-circle"></span> 取消
					</button>
					<button type="button" class="btn btn-primary" id="subm">
						<span class="glyphicon glyphicon-ok-circle"></span> 提交
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 编辑的模态框---------start -->
	<div class="modal fade" id="myEditModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<div class="modal-title" id="myModalLabel">修改信息</div>
				</div>
				<div class="modal-body">
					<div class="row" style="margin-bottom:20px;">
						<span id="teacherId" style="display:none;"></span>
						<div class="col-xs-1"></div>
						<div class="col-xs-5">
							大区：<select name="daquEdit" id="bigRegionEdit"
								style="width:174px;height:28px;">
								<option value="" text="请选择">请选择</option>
							</select>
						</div>
						<div class="col-xs-5">
							学校：<select name="school" id="schoolEdit"
								style="width:174px; height:28px;">
								<option value="" text="请选择" txt="请选择">请选择</option>
							</select>
						</div>
					</div>
					<div class="row" style="margin-bottom:20px;">
						<div class="col-xs-1"></div>
						<div class="col-xs-5">
							专业：<select name="profession" id="profEdit"
								style="width:174px; height:28px;">
								<option value="请选择" text="请选择">请选择</option>
							</select>
						</div>
						<div class="col-xs-6">
							角色：<select name="style" id="leixing"
								style="width:174px;height:28px;">
								<option value="" text="请选择">请选择</option>
								<option value="0" text="讲师">讲师</option>
								<option value="1" text="班主任">班主任</option>
								<option value="2" text="就业">就业</option>
								<option value="3" text="在线老师">在线老师</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-1"></div>
						<div class="col-xs-5">
							姓名：<input type="text" name="name" id="nameEdit" maxlength="5" />
						</div>
						<div class="col-xs-5" id="myclassEdit">
							班级：<input type="text" name="class" id="banjiEdit" maxlength="10" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-ban-circle"></span> 取消
					</button>
					<button type="button" class="btn btn-primary" id="submEdit">
						<span class="glyphicon glyphicon-ok-circle"></span> 提交
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 编辑的模态框---------ending -->
	<!--加载等待-->
	<div class="modal fade" id="loadingImg"
		style="position:absolute;left:50%;top:40%;">
		<img src="images/loader.gif" alt="等待图标" />
	</div>
	<!--尾部-->
	<div id="footer-sec">
		<div class="footer">
			<div>
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
	<script src="js/src/teacherManage.js"></script>
</body>
</html>
