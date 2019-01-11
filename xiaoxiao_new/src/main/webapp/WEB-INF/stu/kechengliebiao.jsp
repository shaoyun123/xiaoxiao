<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="addkecheng_"><span title="新增课程"
							class="glyphicon glyphicon-plus"
							style="padding-top: 20px; font-size: 18px"></span></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="wodekecheng"><span
							title="课程格子显示" class="glyphicon glyphicon-th"
							style="padding-top: 20px; font-size: 18px"></span></a>
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
											<a href="info"><button type="button"
													class="btn btn-default">
													<i class="fa fa-user"></i>个人信息
												</button></a> <a href="logout"><button type="button"
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
				<div class="side-body">
					<form id="xuenianxueqi" name="form" action="kechengliebiao"
						method="post">
						<div style="text-align: center;">
							<span style="color: red; font-weight: bold;">学年</span> <select
								id="xuenian" name="xuenian" style="width: 10%"
								onchange="show_zhounum()">
								<c:forEach items="${xuenians }" var="xuen">
									<option value="${xuen }"
										<c:if test="${xuenian==xuen}">selected="selected"</c:if>>${xuen }</option>
								</c:forEach>
							</select> <span style="color: red; font-weight: bold; margin-left: 20px;">学期</span>
							<select id="xueqi" name="xueqi" style="width: 5%"
								onchange="show_zhounum()">
								<option value=1
									<c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option>
								<option value=2
									<c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option>
								<option value=3
									<c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option>
							</select>
							<!-- 							<span style="color: red; font-weight: bold; margin-left: 20px;">第</span> -->
							<!-- 							<select id="zhou" name="zhou" style="width: 8%;"> -->
							<%-- 								<c:forEach var="i" begin="1" end="${zhounum}" step="1"> --%>
							<%-- 									<option value="${i}" --%>
							<%-- 										<c:if test="${i==zhou}">selected="selected"</c:if>>${i}</option> --%>
							<%-- 								</c:forEach> --%>
							<!-- 							</select>  -->
							<!-- 							<span style="color: red; font-weight: bold; margin-left: 20px;">周</span> -->
							<input type="submit" style="margin-left: 70px; width: 60px;"
								value="查询">
						</div>
					</form>
					<br>
					<c:choose>
						<c:when test="${not empty kecheng }">
							<table class="table table-striped " id="DataTables_Table_0"
								style="width: 100%;">
								<thead style="background-color: #ffffff; width: 100%;">
									<tr>
										<th style="width: 10%; text-align: center;">课程名称</th>
										<th style="width: 10%; text-align: center;">任课教师</th>
										<th style="width: 49%; text-align: center;" colspan="3">时间地点</th>
										<th style="width: 31%; text-align: center;">操作</th>
									</tr>
									<tr>
										<th style="width: 10%; text-align: center;"></th>
										<th style="width: 10%; text-align: center;"></th>
										<th style="width: 9%; text-align: center;">上课周次</th>
										<th style="width: 20%; text-align: center;">上课时间</th>
										<th style="width: 20%; text-align: center;">上课地点</th>
										<th style="width: 31%; text-align: center;"></th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${kecheng}" var="KeCheng">
										<c:set value="${fn:length(KeCheng.maps) }" var="i"></c:set>
										<c:forEach begin="1" end="${i }" step="1" varStatus="go">
											<c:if test="${go.first}">
												<tr>
													<td style="text-align: center; padding-top: 35px;">${KeCheng.kechengmingcheng}</td>
													<td style="text-align: center; padding-top: 35px;"><c:if
															test="${KeCheng.renkejiaoshi != ''}">${KeCheng.renkejiaoshi}</c:if>
														<c:if test="${KeCheng.renkejiaoshi==''}">待定</c:if></td>
													<td colspan="6" style="width: 80%;"><c:forEach
															items="${KeCheng.maps }" var="maps" varStatus="in">
															<table style="width: 100%; height: 30px;">
																<tbody>
																	<tr>
																		<c:choose>
																			<c:when test="${KeCheng.leixing==3 }">
																				<c:choose >
																					<c:when test="${not empty KeCheng.kaishizhou }">
																						<td style="width: 9%; text-align: center;">${KeCheng.kaishizhou } 调课周</td>
																					</c:when>
																					<c:otherwise>
																						<td style="width: 9%; text-align: center;">${maps.zhoushu }周</td>
																					</c:otherwise>
																				</c:choose>
																				<td style="width: 19%; text-align: center;"><c:if
																						test="${maps.zhouci==1}">周一</c:if> <c:if
																						test="${maps.zhouci==2}">周二</c:if> <c:if
																						test="${maps.zhouci==3}">周三</c:if> <c:if
																						test="${maps.zhouci==4}">周四</c:if> <c:if
																						test="${maps.zhouci==5}">周五</c:if> <c:if
																						test="${maps.zhouci==6}">周六</c:if> <c:if
																						test="${maps.zhouci==7}">周日</c:if>&emsp;&emsp;
																					${maps.kaishijieci}~${maps.jieshujieci}节</td>
																				<td style="width: 19%; text-align: center;"><c:choose>
																						<c:when test="${maps.jiaoshiming == '运动场新'}">${maps.xiaoquming}&emsp;运动场新</c:when>
																						<c:when test="${maps.jiaoshiming =='体育馆体育场1'}">${maps.xiaoquming}&emsp;体育馆体育场1</c:when>
																						<c:when test="${maps.jiaoshiming == '待定'}">${maps.jiaoshiming}</c:when>
																						<c:when test="${maps.jiaoshiming == '现场参观'}">${maps.jiaoshiming}</c:when>
																						<c:otherwise>${maps.xiaoquming}&emsp;${maps.jiaoXueLouMing}${maps.jiaoshiming }</c:otherwise>
																					</c:choose></td>
																				<c:choose>
																					<c:when test="${in.index+1==1 }">
																						<c:choose>
																							<c:when test="${KeCheng.tianjiarenid == user.xueshengid }">
																								<td style="width: 7%; text-align: center; padding-top: 15px;"><a
																									href="chakancanyuren_kecheng_inkecheng?id=${KeCheng.id}"><input
																										type="button" class="btn btn-default"
																										value="查看参与人"></a></td>
																							</c:when>
																							<c:otherwise>
																								<td style="width: 7%; text-align: center; padding-top: 15px;">--</td>
																							</c:otherwise>
																						</c:choose>
																					</c:when>
																					<c:otherwise>
																						<td style="width: 7%; text-align: center;"></td>
																					</c:otherwise>
																				</c:choose>
																				<c:choose>
																					<c:when test="${KeCheng.tianjiarenid == user.xueshengid }">
																						<c:choose >
																							<c:when test="${not empty KeCheng.kaishizhou }">
																								<td style="width: 7%; text-align: center;">--</td>
																								<td style="width: 7%; text-align: center;">--</td>
																								<td style="width: 7%; text-align: center;"><a
																									href="delkecheng?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${maps.zhouci}/${KeCheng.kaishizhou}/${maps.kaishijieci}/${maps.jieshujieci}"
																									onclick="return delkecheng()"><input
																										type="button" class="btn btn-default" value="删除"></a></td>
																							</c:when>
																							<c:otherwise>
																								<td style="width: 7%; text-align: center;"><a
																									href="tiaotingkecheng?id=${KeCheng.id}/${KeCheng.leixing }/${maps.zhoushu}/${maps.zhouci}/${maps.kaishijieci}/${maps.jieshujieci}"><input
																										type="button" class="btn btn-default" value="调停"></a>
																								</td>
																								<td style="width: 7%; text-align: center;"><a
																									href="xiugaikecheng?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${maps.zhouci}/${maps.zhoushu}/${maps.kaishijieci}/${maps.jieshujieci}"
																									onclick="return xiugaikecheng()"><input
																										type="button" class="btn btn-default" value="修改"></a></td>
																								<td style="width: 7%; text-align: center;"><a
																									href="delkecheng?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${maps.zhouci}/${maps.zhoushu}/${maps.kaishijieci}/${maps.jieshujieci}"
																									onclick="return delkecheng()"><input
																										type="button" class="btn btn-default" value="删除"></a></td>
																							</c:otherwise>
																						</c:choose>
																					</c:when>
																					<c:otherwise>
																						<td style="width: 7%; text-align: center;">--</td>
																						<td style="width: 7%; text-align: center;">--</td>
																						<c:choose>
																							<c:when test="${in.index+1==1 }">
																								<td style="width: 7%; text-align: center; padding-top: 15px;"><a
																									href="mianxiukecheng?id=${KeCheng.id}"><input
																										type="button" class="btn btn-default"
																										value="免修"></a></td>
																							</c:when>
																							<c:otherwise>
																								<td style="width: 7%; text-align: center;"></td>
																							</c:otherwise>
																						</c:choose>
																					</c:otherwise>
																				</c:choose>
																			</c:when>
																			<c:otherwise>
																				<td
																					style="width: 9%; text-align: center; padding-top: 10px;">${maps.kaishizhou}~${maps.jieshuzhou}周</td>
																				<td style="width: 19%; text-align: center;"><c:choose>
																						<c:when test="${maps.ds=='1' }">
																						单周&emsp;
																					</c:when>
																						<c:when test="${maps.ds=='2' }">
																						双周&emsp;
																					</c:when>
																						<c:otherwise>
																							&emsp;
																						</c:otherwise>
																					</c:choose> <c:if test="${maps.zhouci==1}">周一</c:if> <c:if
																						test="${maps.zhouci==2}">周二</c:if> <c:if
																						test="${maps.zhouci==3}">周三</c:if> <c:if
																						test="${maps.zhouci==4}">周四</c:if> <c:if
																						test="${maps.zhouci==5}">周五</c:if> <c:if
																						test="${maps.zhouci==6}">周六</c:if> <c:if
																						test="${maps.zhouci==7}">周日</c:if>
																					&emsp;&emsp;${maps.kaishijieci}~${maps.jieshujieci}节</td>
																				<td style="width: 19%; text-align: center;"><c:choose>
																						<c:when test="${maps.jiaoshiming == '运动场新'}">${maps.xiaoquming}&emsp;运动场新</c:when>
																						<c:when test="${maps.jiaoshiming =='体育馆体育场1'}">${maps.xiaoquming}&emsp;体育馆体育场1</c:when>
																						<c:when test="${maps.jiaoshiming == '待定'}">${maps.jiaoshiming}</c:when>
																						<c:when test="${maps.jiaoshiming == '现场参观'}">${maps.jiaoshiming}</c:when>
																						<c:otherwise>${maps.xiaoquming}&emsp;${maps.jiaoXueLouMing}${maps.jiaoshiming }</c:otherwise>
																					</c:choose></td>
																				<c:choose>
																					<c:when test="${in.index+1==1 }">
																						<c:choose>
																							<c:when test="${KeCheng.tianjiarenid == user.xueshengid}">
																								<td style="width: 7%; text-align: center; padding-top: 15px;"><a
																									href="chakancanyuren_kecheng_inkecheng?id=${KeCheng.id}"><input
																										type="button" class="btn btn-default"
																										value="查看参与人"></a></td>
																							</c:when>
																							<c:otherwise>
																								<td style="width: 7%; text-align: center; padding-top: 15px;">--</td>
																							</c:otherwise>
																						</c:choose>
																					</c:when>
																					<c:otherwise>
																						<td style="width: 7%; text-align: center;"></td>
																					</c:otherwise>
																				</c:choose>
																				<c:choose>
																					<c:when test="${KeCheng.tianjiarenid == user.xueshengid }">
																						<td style="width: 7%; text-align: center;"><a
																							href="tiaotingkecheng?id=${KeCheng.id}/${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishizhou}/${maps.jieshuzhou}/${maps.kaishijieci}/${maps.jieshujieci}"><input
																								type="button" class="btn btn-default" value="调停"></a>
																						</td>
																						<td style="width: 7%; text-align: center;"><a
																							href="xiugaikecheng?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.kaishizhou}/${maps.jieshuzhou}"
																							onclick="return xiugaikecheng()"><input
																								type="button" class="btn btn-default" value="修改"></a></td>
																						<td style="width: 7%; text-align: center;"><a
																							href="delkecheng?id=${KeCheng.id}&xinxi=${KeCheng.leixing}/${ maps.zhouci}/${maps.ds}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.kaishizhou}/${maps.jieshuzhou}"
																							onclick="return delkecheng()"><input
																								type="button" class="btn btn-default" value="删除"></a></td>
																					</c:when>
																					<c:otherwise>
																						<td style="width: 7%; text-align: center;">--</td>
																						<td style="width: 7%; text-align: center;">--</td>
																						<c:choose>
																							<c:when test="${in.index+1==1 }">
																								<td style="width: 7%; text-align: center; padding-top: 15px;"><a
																									href="mianxiukecheng?id=${KeCheng.id}"><input
																										type="button" class="btn btn-default"
																										value="免修"></a></td>
																							</c:when>
																							<c:otherwise>
																								<td style="width: 7%; text-align: center;"></td>
																							</c:otherwise>
																						</c:choose>
																					</c:otherwise>
																				</c:choose>
																			</c:otherwise>
																		</c:choose>

																	</tr>
																</tbody>
															</table>
														</c:forEach></td>
												</tr>
												<c:remove var="i" />
											</c:if>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<span style="padding-left: 500px; padding-top: 60px; color: red;">本学期无课程安排</span>
						</c:otherwise>
					</c:choose>
					<div align="center">
						<ul class="pagination">
							<c:if test="${page >1}">
								<li><a
									href="kechengliebiao?xuenian=${xuenian}&xueqi=${xueqi}&page=1">首页</a></li>
							</c:if>
							<c:if test="${page > 1}">
								<li><a
									href="kechengliebiao?xuenian=${xuenian}&xueqi=${xueqi}&page=${page-1}">上一页</a></li>
							</c:if>
							<li><a href="#">第${page}页</a></li>
							<c:if test="${page < pages}">
								<li><a
									href="kechengliebiao?xuenian=${xuenian}&xueqi=${xueqi}&page=${page+1}">下一页</a></li>
							</c:if>
							<c:if test="${page < pages}">
								<li><a
									href="kechengliebiao?xuenian=${xuenian}&xueqi=${xueqi}&page=${pages}">尾页</a></li>
							</c:if>
						</ul>
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
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function delkecheng() {
			if (confirm("删除课程会影响到很多学生并且无法恢复！确认删除吗？") == true) {
				return true;
			} else {
				return false;
			}
		}
		function xiugaikecheng() {
			if (confirm("修改课程会影响到很多学生并且无法恢复！确认修改吗？") == true) {
				return true;
			} else {
				return false;
			}
		}
		function searchResult() {
			var xuenian = document.getElementById("xuenian").value;
			var xueqi = document.getElementById("xueqi").value;
			if (xuenian == "") {
				alert("请选择要查询的学年");
				return false;
			} else if (xueqi == "") {
				alert("请选择要查询的学期");
				return false;
			} else {
				return true;
			}
		}
	</script>
	<script type="text/javascript">
		/*$(function () {
		    $("#DataTables_Table_0").dataTable({
		        //lengthMenu: [5, 10, 20, 30],//这里也可以设置分页，但是不能设置具体内容，只能是一维或二维数组的方式，所以推荐下面language里面的写法。
		        paging: true,//分页
		        ordering: false,//是否启用排序
		        searching: true,//搜索
		        language: {
		            lengthMenu: '显示&emsp;<select style="width:50px;height:30px;">' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option>' + '</select>&emsp;条记录',//左上角的分页大小显示。
		            search: '<span>搜索：</span>',//右上角的搜索文本，可以写html标签
		            paginate: {//分页的样式内容。
		                previous: "上一页",
		                next: "下一页",
		                first: "第一页",
		                last: "最后"
		            },

		            zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
		            //下面三者构成了总体的左下角的内容。
		            info: "总共_PAGES_ 页，显示第_START_ 到第 _END_条 ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
		            infoEmpty: "0条记录",//筛选为空时左下角的显示。
		            infoFiltered: ""//筛选之后的左下角筛选提示，
		        },
		        paging: true,
		        pagingType: "full_numbers",//分页样式的类型

		    });
		    $("#table_local_filter input[type=search]").css({ width: "auto" });//右上角的默认搜索文本框，不写这个就超出去了。
		});*/
	</script>
</body>
</html>