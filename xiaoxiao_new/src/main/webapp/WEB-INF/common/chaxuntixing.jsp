<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>提醒消息</title>
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
							<li class="active">提醒事件</li>
						</ol>
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-th icon"></i>
						</button>
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
			<div class="card" style="background-color:#ffffff;"><br>
				<table class="table table-striped " id="DataTables_Table_0"  style="width: 96%;">
					<thead><tr><th></th><th></th><th></th><th></th></tr></thead>
					<tbody>
						<c:forEach items="${tixing}" var="tixing">
							<tr id="${tixing.id}">
								<td style="width:20%">${tixing.shijian}</td>
								<td style="width:60%">
									<span id="${tixing.id}#" style="<c:if test="${tixing.zhuangtai==0}">color:red;</c:if>">${tixing.neirong}</span>
								</td>
								<td id="${tixing.id}@" style="width:10%">
									<c:if test="${tixing.zhuangtai==1}">已读</c:if>
									<c:if test="${tixing.zhuangtai==0}">
										<input type="button" value="标记为已读" class="btn btn-default" onclick="zhuangtai(${tixing.id})"/>
									</c:if>
								</td>
								<td style="width:10%">
									<input type="button" value="删除" class="btn btn-default" onclick="del(${tixing.id})"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
		<div align="center">
			<ul class="pagination">
				<c:if test="${page >1}">
					<li><a href="chaxuntixing">首页</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li><a href="chaxuntixing?page=${page-1}">上一页</a></li>
				</c:if>
				<li><a href="#">第${page}页</a></li>
				<c:if test="${page < pages}">
					<li><a href="chaxuntixing?page=${page+1}">下一页</a></li>
				</c:if>
				<c:if test="${page < pages}">
					<li><a href="chaxuntixing?page=${pages}">尾页</a></li>
				</c:if>
			</ul>
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
		function cfm() {
			if (confirm("确认到校并销假？") == true) {
				return true;
			} else {
				return false;
			}
		}
		
		function zhuangtai(id){
			$.ajax({
				type:"POST",
				url:"biaojiyidu",
				data:{CODE:id},
				dataType:'text',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data){
					if(data=="success"){
						document.getElementById(id+"@").innerHTML="已读";
						document.getElementById(id+"#").style.color="black";
					}else{
						alert("标记失败！");
					}
				},
				error:function()
				{
					alert("登录超时！")
				}
			});
		}
		
		function del(id){
			$.ajax({
				type:"POST",
				url:"deltixing",
				data:{CODE:id},
				dataType:'text',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data){
					if(data=="success"){
						$("#"+id).remove();
						alert("删除成功!");
					}else{
						alert("删除失败！");
					}
				},
				error:function()
				{
					alert("登录超时！")
				}
			});
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