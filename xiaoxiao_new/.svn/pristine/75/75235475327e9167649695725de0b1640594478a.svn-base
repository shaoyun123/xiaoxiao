<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>学生课程</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Fonts -->
<link href='static/lib/css/google1.css' rel='stylesheet' type='text/css'>
<link href='static/lib/css/google2.css' rel='stylesheet' type='text/css'>
<!-- CSS Libs -->
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/animate.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/bootstrap-switch.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/checkbox3.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/select2.min.css">
<!-- CSS App -->
<link rel="stylesheet" type="text/css" href=" static/css/style.css">
<link rel="stylesheet" type="text/css"
	href=" static/css/themes/flat-blue.css">
</head>

<body class="flat-blue">
	<div class="app-container">
		<div class="row content-container">
			<nav class="navbar navbar-default navbar-fixed-top navbar-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-expand-toggle">
							<i class="fa fa-bars icon"></i>
						</button>
						<ol class="breadcrumb navbar-breadcrumb">
							<li>日程表</li>
							<li class="active">学生课程</li>
						</ol>
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-th icon"></i>
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="addkecheng_fdy" ><span title="新增课程" class="glyphicon glyphicon-plus" style="padding-top:20px;font-size:18px"></span></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="xueshengkecheng_liebiao" ><span title="列表显示" class="glyphicon glyphicon-th-list" style="padding-top:20px;font-size:18px"></span></a>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown profile"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false">${xingming}<span class="caret"></span></a>
							<ul class="dropdown-menu animated fadeInDown">
								<li>
									<div class="profile-info">
										<h4 class="username">${xingming}</h4>
										<c:choose>
											<c:when test="${user.status == 'xuesheng'}">
												<p>学生</p>
											</c:when>
											<c:when test="${user.status == 'jiaoshi'}">
												<p>教师</p>
											</c:when>
											<c:when test="${user.status == 'fudaoyuan'}">
												<p>辅导员</p>
											</c:when>
											<c:when test="${user.status == 'shuji'}">
												<p>书记</p>
											</c:when>
											<c:otherwise>
												<p>管理员</p>
											</c:otherwise>
										</c:choose>
										<div class="btn-group margin-bottom-2x" role="group">
											<a href="info"><button type="button" class="btn btn-default"><i class="fa fa-user"></i>个人信息</button></a>
											<a href="logout"><button type="button"
													class="btn btn-default">
													<i class="fa fa-sign-out"></i>退出
												</button></a>
										</div>
									</div>
								</li>
							</ul></li>
					</ul>
				</div>
			</nav>
			<div class="side-menu sidebar-inverse">
				<nav class="navbar navbar-default" role="navigation">
					<div class="side-menu-container">
						<div class="navbar-header">
							<a class="navbar-brand" href="index"> <span
								class="icon fa fa-rocket"></span> <span class="title">校园助手</span>
							</a>
							<button type="button"
								class="navbar-expand-toggle pull-right visible-xs">
								<i class="fa fa-times icon"></i>
							</button>
						</div>
						<ul class="nav navbar-nav">
							<li><a href="index"> <span class="icon fa fa-star"></span><span
									class="title">首页</span>
							</a></li>
							<c:forEach items="${menu}" var="UserMenu">
								<c:if
									test="${UserMenu.fatherid == -1 and empty UserMenu.action }">
									<li class="panel panel-default dropdown"><a
										data-toggle="collapse" href="#dropdown-${UserMenu.id}"> <span
											class="${UserMenu.icon}"></span><span class="title">${UserMenu.menuname}</span>
									</a>
										<div id="dropdown-${UserMenu.id}"
											class="panel-collapse collapse">
											<div class="panel-body">
												<ul class="nav navbar-nav">
													<c:forEach items="${UserMenu.childMenus}" var="childMenus">
														<c:if test="${not empty childMenus.action}">
															<li><a href="${childMenus.action}">${childMenus.menuname}</a></li>
														</c:if>
													</c:forEach>
												</ul>
											</div>
										</div></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</nav>
			</div>
		<!-- Main Content -->
		<div class="container-fluid">
			<div class="side-body" >
			<form id="xuenianxueqi" name="form" action="chaxunkecheng_fdy" method="post">
				<input type="hidden" name="banji" value="0">
				<div style="text-align:center;">
				 	<span style="color:red;font-weight:bold;">学年</span>
				 	<select id="xuenian" name="xuenian" style="width:10%" onchange="show_zhoushu()" >
				 	<c:forEach items="${xuenians }" var="xuen" >
				 		<option value="${xuen }" <c:if test="${xuenian==xuen}">selected="selected"</c:if>>${xuen }</option>
				 	</c:forEach>
				 	</select>
				 	<span style="color:red;font-weight:bold;margin-left:20px;">学期</span>
				 	<select id="xueqi" name="xueqi" style="width:5%" onchange="show_zhoushu()" >
				 		<option value=1 <c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option>
				 		<option value=2 <c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option>
				 		<option value=3 <c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option>
				 	</select>
				 	<span style="color:red;font-weight:bold;margin-left:20px;">第</span>
				 	<select id="zhou" name="zhou" style="width:5%;">
				 		<c:forEach var="i" begin="1" end="${zhounum}" step="1">
				 			<option value="${i}" <c:if test="${zhou==i}">selected="selected"</c:if>>${i}</option>
				 		</c:forEach>
				 	</select>
				 	<span style="color:red;font-weight:bold;">周</span>
				 	<input type="submit" style="margin-left:70px;width:60px;" value="查询">
				</div>
			</form><br>
			<div class="card" style=""><br>
			<table border="1" style=" table-layout:fixed;width:98%;margin-left:10px">
          <tr>
          	  <th width="5.5%" style="text-align:center;"><span>时间段</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期一</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期二</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期三</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期四</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期五</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期六</span></th>
              <th width="11.5%" style="text-align:center;"><span>星期日</span></th>
          </tr>
          <tr>
          	<td  style="text-align:center;"><span class="time">上午</span></td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${KeCheng.jieshujieci<=shangwunum && KeCheng.zhouci==1}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${KeCheng.jieshujieci<=shangwunum && KeCheng.zhouci==2}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${KeCheng.jieshujieci<=shangwunum && KeCheng.zhouci==3}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${KeCheng.jieshujieci<=shangwunum && KeCheng.zhouci==4}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${KeCheng.jieshujieci<=shangwunum && KeCheng.zhouci==5}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${KeCheng.jieshujieci<=shangwunum && KeCheng.zhouci==6}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${KeCheng.jieshujieci<=shangwunum && KeCheng.zhouci==7}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	</tr>
          <tr>
          	<td style="text-align:center;"><span class="time">下午</span></td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum))&& KeCheng.zhouci==1}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum))&& KeCheng.zhouci==2}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum))&& KeCheng.zhouci==3}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum))&& KeCheng.zhouci==4}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum))&& KeCheng.zhouci==5}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum))&& KeCheng.zhouci==6}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum))&& KeCheng.zhouci==7}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          </tr>
          <tr>
          	<td style="text-align:center;"><span class="time">晚上</span></td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+xiawunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum+wanshangnum))&& KeCheng.zhouci==1}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+xiawunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum+wanshangnum))&& KeCheng.zhouci==2}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+xiawunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum+wanshangnum))&& KeCheng.zhouci==3}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+xiawunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum+wanshangnum))&& KeCheng.zhouci==4}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+xiawunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum+wanshangnum))&& KeCheng.zhouci==5}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+xiawunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum+wanshangnum))&& KeCheng.zhouci==6}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>
          	<td>
          		<c:forEach items="${kechengs }" var="KeCheng">
          			<c:if test="${(KeCheng.jieshujieci>=(shangwunum+xiawunum+1) && KeCheng.jieshujieci<=(shangwunum+xiawunum+wanshangnum))&& KeCheng.zhouci==7}">
          				<span  > 
              				<font style="font-weight:bold">${KeCheng.kechengmingcheng}</font><br>
              					<font><span  ><c:if test="${KeCheng.jieshuzhou!=null && KeCheng.jieshuzhou!='' }">${KeCheng.kaishizhou}-${KeCheng.jieshuzhou}周</c:if><c:if test="${KeCheng.jieshuzhou==null || KeCheng.jieshuzhou==''}">${KeCheng.kaishizhou}周</c:if>
              						(${KeCheng.kaishijieci}-${KeCheng.jieshujieci}节)
              					</span></font>
              				</span><br>
              					<font><span  >
              					${KeCheng.xiaoquming}&emsp;${KeCheng.jiaoXueLouMing}${KeCheng.jiaoshiming }</span></font><br>
              					<font><span ><c:if test="${KeCheng.renkejiaoshi ==null}">待定</c:if><c:if test="${KeCheng.renkejiaoshi !=null}">${KeCheng.renkejiaoshi}老师</c:if></span></font>
          						<br><font><span>上课班级：</span>${KeCheng.banJiMingCheng}</font>
          			<br><br></c:if>
          		</c:forEach>
          	</td>	
          </tr>
          </table>
       </div>
			</div>
		</div>
			

		<!-- Main Content End-->

		<footer class="app-footer">
			<div class="wrapper">
				<span class="pull-right"> <a href="#"><i
						class="fa fa-long-arrow-up"></i></a>
				</span>
			</div>
		</footer>
		<div>
			<!-- Javascript Libs -->
			<script type="text/javascript" src="static/lib/js/jquery.min.js"></script>
			<script type="text/javascript" src="static/lib/js/bootstrap.min.js"></script>
			<script type="text/javascript" src="static/lib/js/Chart.min.js"></script>
			<script type="text/javascript"
				src="static/lib/js/bootstrap-switch.min.js"></script>
			<script type="text/javascript"
				src="static/lib/js/jquery.matchHeight-min.js"></script>
			<script type="text/javascript"
				src="static/lib/js/jquery.dataTables.min.js"></script>
			<script type="text/javascript"
				src="static/lib/js/dataTables.bootstrap.min.js"></script>
			<script type="text/javascript"
				src="static/lib/js/select2.full.min.js"></script>
			<script type="text/javascript" src="static/lib/js/ace/ace.js"></script>
			<script type="text/javascript" src="static/lib/js/ace/mode-html.js"></script>
			<script type="text/javascript"
				src="static/lib/js/ace/theme-github.js"></script>
			<!-- Javascript -->
			<script type="text/javascript" src="static/js/app.js"></script>
			<script type="text/javascript">
			function show_zhoushu(){
				var xuenian = $("#xuenian").val();
				var xueqi = $("#xueqi").val();
				$.ajax({
					type: "POST",
					url: 'show_zhoushu',
			    	data:{
			    		xuenian:xuenian,
			    		xueqi:xueqi,
			    	},
					dataType:'json',
					cache:false,
					timeout: 5000,
					async:true, 
					success:function(data){
						var data = eval(data);
						var code = '';
						var defaultValue='';
						for(var i=1;i<=data.zhounum;i++){
							defaultValue=1;
							code += '<option value="'+i+'">'+i+'</option>';
						}
						$("#zhou").html(code);
						$("#zhou").val(defaultValue).trigger('change');
					},
			    	error:function()
					{
						alert("登录超时!")
					}
				});
			}
			</script>
		</div>
	</div>
	</div>
</body>
</html>