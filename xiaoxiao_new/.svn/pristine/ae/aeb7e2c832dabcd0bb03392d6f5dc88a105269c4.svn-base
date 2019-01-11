<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>修改课程</title>
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
							<li class="active"><a href="kechengliebiao">我的课程</a></li>
							<li class="active">修改课程</li>
						</ol>
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-th icon"></i>
						</button>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown profile"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false">${user.xingming}<span class="caret"></span></a>
							<ul class="dropdown-menu animated fadeInDown">
								<li>
									<div class="profile-info">
										<h4 class="username">${user.xingming}</h4>
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
                <div class="side-body">
                	<div class="row">
                        <div class="col-xs-12">
                            <div class="card" style="background-color:#ffffff;">
                            	<div class="card-header">
                                    <div class="card-title">
                                    	<div class="title">编辑课程</div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <form action="savekecheng_update?id=${kecheng.id}" class="form-inline" method="post">
                                    	<input type="hidden" id="leixing" name="leixing" value="${kecheng.leixing }">
										<div class="sub-title">
											<span style="font-weight: bold;">课程：${kecheng.kechengmingcheng}&emsp;&emsp;</span>
											<span style="font-weight: bold;">上课周： <c:choose>
													<c:when test="${kecheng.leixing==3 }">
														<c:forEach items="${maps }" var="maps" varStatus="in">
															<c:if test="${in.index+1==1 }">${maps.zhoushu }周</c:if>
														</c:forEach></c:when>
													<c:when test="${kecheng.leixing==2 }">
														<c:if test="${maps.ds=='1'}">${kecheng.kaishizhou}~${kecheng.jieshuzhou }单周</c:if>
														<c:if test="${maps.ds=='2'}">${kecheng.kaishizhou}~${kecheng.jieshuzhou }双周</c:if>
													</c:when>
													<c:otherwise>
														${kecheng.kaishizhou}~${kecheng.jieshuzhou }周
													</c:otherwise>
												</c:choose> &emsp;&emsp;
											</span> 
										</div>
										<c:choose>
											<c:when test="${kecheng.leixing==3 }">
												<c:forEach items="${maps }" varStatus="in" var="maps">
													<c:if test="${in.index+1==1 }">
														<div class="sub-title">
															<span style="font-weight: bold;">校区：${maps.xiaoquming}&emsp;&emsp;</span>
															<span style="font-weight: bold;">教学楼：${maps.jiaoxuelouming}&emsp;&emsp;</span>
															<span style="font-weight: bold;">教室：${maps.jiaoshiming}</span>
														</div>
														<div class="sub-title">
															<span style="font-weight: bold;">星期： <c:if
																	test="${maps.zhouci==1}">一</c:if> <c:if
																	test="${maps.zhouci==2}">二</c:if> <c:if
																	test="${maps.zhouci==3}">三</c:if> <c:if
																	test="${maps.zhouci==4}">四</c:if> <c:if
																	test="${maps.zhouci==5}">五</c:if> <c:if
																	test="${maps.zhouci==6}">六</c:if> <c:if
																	test="${maps.zhouci==7}">日</c:if>&emsp;&emsp;
															</span> <span style="font-weight: bold;">节次：${maps.kaishijieci}~${maps.jieshujieci }</span>
														</div>
													</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<div class="sub-title">
													<span style="font-weight: bold;">校区：${maps.xiaoquming}&emsp;&emsp;</span>
													<span style="font-weight: bold;">教学楼：${maps.jiaoxuelouming}&emsp;&emsp;</span>
													<span style="font-weight: bold;">教室：${maps.jiaoshiming}</span>
												</div>
												<div class="sub-title">
													<span style="font-weight: bold;">星期： <c:if
															test="${maps.zhouci==1}">一</c:if> <c:if
															test="${maps.zhouci==2}">二</c:if> <c:if
															test="${maps.zhouci==3}">三</c:if> <c:if
															test="${maps.zhouci==4}">四</c:if> <c:if
															test="${maps.zhouci==5}">五</c:if> <c:if
															test="${maps.zhouci==6}">六</c:if> <c:if
															test="${maps.zhouci==7}">日</c:if>&emsp;&emsp;
													</span> <span style="font-weight: bold;">节次：${maps.kaishijieci}~${maps.jieshujieci }</span>
												</div>
											</c:otherwise>
										</c:choose>

										<div id="zhanshi">
											<span class="pull-right"><input type="button"
												class="flip-link btn btn-info" value="修改" onclick="xiugai()"></span>
										</div>
										<div id="xiugai" style="display: none;">
											<c:choose>
												<c:when test="${kecheng.leixing==3 }">
													<div class="sub-title" style="color: red;">修改课程信息</div>
													<c:forEach items="${maps }" var="maps" varStatus="in">
														<div id="num" class="aa">
															<input type="hidden" id="yuanriqi-${in.index+1 }"
																value="${maps.riqi }/${maps.kaishijieci}/${maps.jieshujieci}/${maps.jiaoshiid}">
															<div class="sub-title">
																<span style="font-weight: bold;">上课日期：</span> <input
																	id="riqi-${in.index+1 }" type="text" class="Wdate"
																	style="height: 25px" value="${maps.riqi }"
																	onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
	
																&emsp;&emsp;&emsp;&emsp; <span style="font-weight: bold;">上课节次：</span>
																<span>第</span> <select id="kaishijieci-${in.index+1 }"
																	name="kaishijieci" style="width: 70px">
																	<c:forEach items="${jieci}" var="jieci">
																		<option value="${jieci.id}"
																			<c:if test="${maps.kaishijieci==jieci.jieci}">selected="selected"</c:if>>${jieci.jieci}</option>
																	</c:forEach>
																</select> <span>节——至——第</span> <select
																	id="jieshujieci-${in.index+1 }" name="jieshujieci"
																	style="width: 70px">
																	<c:forEach items="${jieci}" var="jieci">
																		<option value="${jieci.id}"
																			<c:if test="${maps.jieshujieci==jieci.jieci}">selected="selected"</c:if>>${jieci.jieci}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="sub-title">
																<span style="font-weight: bold;">校区：</span> <select
																	id="xiaoqu-${in.index+1 }" name="xiaoqu"
																	style="width: 100px;"
																	onchange="show_jiaoxuelous(${in.index+1 })">
																	<option value="">--请选择--</option>
																	<c:forEach items="${xiaoqu}" var="XiaoQu">
																		<option value="${XiaoQu.xiaoquid}"
																			<c:if test="${XiaoQu.xiaoquid==maps.xiaoquid}">selected="selected"</c:if>>${XiaoQu.mingcheng}</option>
																	</c:forEach>
																</select>&emsp;&emsp; <span style="font-weight: bold;">教学楼：</span>
																<select id="jiaoxuelou-${in.index+1 }" name="jiaoxuelou"
																	style="width: 100px;"
																	onchange="show_jiaoshis(${in.index+1 })">
																	<c:forEach items="${jiaoxuelou}" var="jiaoxuelou">
																		<option value="${jiaoxuelou.jiaoXueLouId}"
																			<c:if test="${jiaoxuelou.jiaoXueLouId==maps.jiaoxuelouid}">selected="selected"</c:if>>${jiaoxuelou.jiaoXueLouMing}</option>
																	</c:forEach>
	
																</select>&emsp;&emsp; <span style="font-weight: bold;">教室：</span>
																<select id="shangkejiaoshi-${in.index+1 }"
																	name="shangkejiaoshi" style="width: 100px;">
																	<c:forEach items="${jiaoshi}" var="jiaoshi">
																		<option value="${jiaoshi.jiaoshiid}"
																			<c:if test="${jiaoshi.jiaoshiid==maps.jiaoshiid}">selected="selected"</c:if>>${jiaoshi.jiaoshiming}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</c:forEach>
													<input type="hidden" name="yuanriqi" id="yuanriqi">
													<input type="hidden" name="cixinxi" id="cixinxi">
												</c:when>
												<c:otherwise>
													<div class="sub-title" style="color: red;">修改课程信息</div>
													<input type="hidden" name="maps"
														value="${maps.ds}/${maps.zhouci}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.jiaoshiid}/${kecheng.kaishizhou}/${kecheng.jieshuzhou}">
													<div class="sub-title">
														<span style="font-weight: bold;">上课周：</span> <span>第</span>
														<select id="kaishizhou" name="kaishizhou"
															style="width: 50px">
															<c:forEach var="i" begin="1" end="${zhounum}" step="1">
																<option value="${i}"
																	<c:if test="${kecheng.kaishizhou==i}">selected="selected"</c:if>>${i}</option>
															</c:forEach>
														</select> <span>周——至——第</span> <select id="jieshuzhou"
															name="jieshuzhou" style="width: 50px">
															<c:forEach var="i" begin="1" end="${zhounum}" step="1">
																<option value="${i}"
																	<c:if test="${kecheng.jieshuzhou==i}">selected="selected"</c:if>>${i}</option>
															</c:forEach>
														</select> <span>周</span>
													</div>
													<div class="sub-title">
														<span style="font-weight: bold;">校区：</span> <select
															id="xiaoqu" name="xiaoqu" style="width: 100px;"
															onchange="show_jiaoxuelou()">
															<option value="">--请选择--</option>
															<c:forEach items="${xiaoqu}" var="XiaoQu">
																<option value="${XiaoQu.xiaoquid}"
																	<c:if test="${XiaoQu.xiaoquid==maps.xiaoquid}">selected="selected"</c:if>>${XiaoQu.mingcheng}</option>
															</c:forEach>
														</select>&emsp;&emsp; <span style="font-weight: bold;">教学楼：</span> <select
															id="jiaoxuelou" name="jiaoxuelou" style="width: 100px;"
															onchange="show_jiaoshi()">
															<c:forEach items="${jiaoxuelou}" var="jiaoxuelou">
																<option value="${jiaoxuelou.jiaoXueLouId}"
																	<c:if test="${jiaoxuelou.jiaoXueLouId==maps.jiaoxuelouid}">selected="selected"</c:if>>${jiaoxuelou.jiaoXueLouMing}</option>
															</c:forEach>
	
														</select>&emsp;&emsp; <span style="font-weight: bold;">教室：</span> <select
															id="shangkejiaoshi" name="shangkejiaoshi"
															style="width: 100px;">
															<c:forEach items="${jiaoshi}" var="jiaoshi">
																<option value="${jiaoshi.jiaoshiid}"
																	<c:if test="${jiaoshi.jiaoshiid==maps.jiaoshiid}">selected="selected"</c:if>>${jiaoshi.jiaoshiming}</option>
															</c:forEach>
														</select>
													</div>
													<div class="sub-title">
														<span style="font-weight: bold;">星期：</span> <select
															id="zhouci" name="zhouci" style="width: 50px">
															<option value="1"
																<c:if test="${maps.zhouci==1}">selected="selected"</c:if>>一</option>
															<option value="2"
																<c:if test="${maps.zhouci==2}">selected="selected"</c:if>>二</option>
															<option value="3"
																<c:if test="${maps.zhouci==3}">selected="selected"</c:if>>三</option>
															<option value="4"
																<c:if test="${maps.zhouci==4}">selected="selected"</c:if>>四</option>
															<option value="5"
																<c:if test="${maps.zhouci==5}">selected="selected"</c:if>>五</option>
															<option value="6"
																<c:if test="${maps.zhouci==6}">selected="selected"</c:if>>六</option>
															<option value="7"
																<c:if test="${maps.zhouci==7}">selected="selected"</c:if>>日</option>
														</select> &emsp;&emsp;&emsp;&emsp; <span style="font-weight: bold;">上课节次：</span>
														<span>第</span> <select id="kaishijieci" name="kaishijieci"
															style="width: 70px">
															<c:forEach items="${jieci}" var="jieci">
																<option value="${jieci.id}"
																	<c:if test="${maps.kaishijieci==jieci.jieci}">selected="selected"</c:if>>${jieci.jieci}</option>
															</c:forEach>
														</select> <span>节——至——第</span> <select id="jieshujieci"
															name="jieshujieci" style="width: 70px">
															<c:forEach items="${jieci}" var="jieci">
																<option value="${jieci.id}"
																	<c:if test="${maps.jieshujieci==jieci.jieci}">selected="selected"</c:if>>${jieci.jieci}</option>
															</c:forEach>
														</select> <span>节</span> &emsp;&emsp;&emsp;&emsp; <span
															style="font-weight: bold;">单双周：</span> <select id="ds"
															name="ds" style="width: 70px">
															<option value="0"
																<c:if test="${maps.ds=='0' }">selected="selected"</c:if>>每周</option>
															<option value="1"
																<c:if test="${maps.ds=='1' }">selected="selected"</c:if>>单周</option>
															<option value="2"
																<c:if test="${maps.ds=='2' }">selected="selected"</c:if>>双周</option>
														</select>
													</div>
												</c:otherwise>
											</c:choose>
											<span class="pull-right"><input type="submit"
												class="flip-link btn btn-info" value="保存"
												onclick="return save()"></span>
										</div>
                                    </form>
                                </div>
                            </div>
                         </div>
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
			<script type="text/javascript"
					src="static/js/My97DatePicker/WdatePicker.js"></script>
		</div>
	</div>
	</div>
	<script type="text/javascript">
		function xiugai(){
			document.getElementById("xiugai").style.display = 'block';
			document.getElementById("zhanshi").style.display = 'none';
		}
		function show_jiaoshi() {
			var jiaoXueLouId = $("#jiaoxuelou").val();
			$.ajax({
				type : "POST",
				url : 'showjiaoshi',
				data : {
					CODE : jiaoXueLouId
				},
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					var data = eval(data);
					var code = '<option disabled selected value>';
					if (data.length != 0) {
						for (var i = 0; i < data.length; i++) {
							code += '<option value="'+data[i].jiaoshiid+'">'
									+ data[i].jiaoshiming + '</option>';
						}
						$("#shangkejiaoshi").html(code);
					} else {
						code += '<option value="">--请选择--</option>';
						$("#shangkejiaoshi").html(code);
						alert("该校区下还没有教室!");
					}
				},
				error : function() {
					alert("登录超时!")
				}
			});
		}
	
		function show_jiaoxuelou() {
			var xiaoquid = $('#xiaoqu').val();
			$.ajax({
				type : "POST",
				url : 'showjiaoxuelou',
				data : {
					CODE : xiaoquid
				},
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					var data = eval(data);
					var code = '<option disabled selected value>';
					if (data.length != 0) {
						for (var i = 0; i < data.length; i++) {
							code += '<option value="'+data[i].jiaoXueLouId+'">'
									+ data[i].jiaoXueLouMing + '</option>';
							$("#jiaoxuelou").html(code);
						}
					} else {
						code += '<option value="">--请选择--</option>';
						$("#jiaoxuelou").html(code);
						alert("该校区下还没有教学楼!");
					}
	
				},
				error : function() {
					alert("登录超时!")
				}
			});
		}
		function show_jiaoshis(id) {
			var jiaoXueLouId = $("#jiaoxuelou-"+id).val();
			$.ajax({
				type : "POST",
				url : 'showjiaoshi',
				data : {
					CODE : jiaoXueLouId
				},
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					var data = eval(data);
					var code = '<option disabled selected value>';
					if (data.length != 0) {
						for (var i = 0; i < data.length; i++) {
							code += '<option value="'+data[i].jiaoshiid+'">'
									+ data[i].jiaoshiming + '</option>';
						}
						$("#shangkejiaoshi-"+id).html(code);
					} else {
						code += '<option value="">--请选择--</option>';
						$("#shangkejiaoshi-"+id).html(code);
						alert("该校区下还没有教室!");
					}
				},
				error : function() {
					alert("登录超时!")
				}
			});
		}
	
		function show_jiaoxuelous(id) {
			var xiaoquid = $('#xiaoqu-'+id).val();
			$.ajax({
				type : "POST",
				url : 'showjiaoxuelou',
				data : {
					CODE : xiaoquid
				},
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					var data = eval(data);
					var code = '<option disabled selected value>';
					if (data.length != 0) {
						for (var i = 0; i < data.length; i++) {
							code += '<option value="'+data[i].jiaoXueLouId+'">'
									+ data[i].jiaoXueLouMing + '</option>';
							$("#jiaoxuelou-"+id).html(code);
						}
					} else {
						code += '<option value="">--请选择--</option>';
						$("#jiaoxuelou-"+id).html(code);
						alert("该校区下还没有教学楼!");
					}
	
				},
				error : function() {
					alert("登录超时!")
				}
			});
		}
		function save() {
			if($("#leixing").val()!="3"){
				if ($("#xiaoqu").val() == "") {
					alert("请选择校区！");
					return false;
				}
				if ($("#jiaoxuelou").val() == "" || $("#jiaoxuelou").val() == null) {
					alert("请选择教学楼!");
					return false;
				}
				if ($("#shangkejiaoshi").val() == ""
						|| $("#shangkejiaoshi").val() == null) {
					alert("请选择教室！");
					return false;
				}
			}
			if($("#leixing").val()=="3"){
				var num = $("#num ").size();
				var yuanriqi="";
				var cixinxi="";
				for(var a=1;a<=num;a++){
					
				yuanriqi += $("#yuanriqi-"+a).val()+",";
					
				var xiaoqu = $("#xiaoqu-"+a).val();
				var jiaoxuelou = $("#jiaoxuelou-"+a).val();
				var shangkejiaoshi = $("#shangkejiaoshi-"+a).val();
				var kaishijieci = $("#kaishijieci-"+a).val();
				var jieshujieci = $("#jieshujieci-"+a).val();
				var riqi = $("#riqi-"+a).val();
				if(xiaoqu=="" || xiaoqu==null){
					alert("请选择校区！");
					return false;
				}
				if(jiaoxuelou=="" || jiaoxuelou==null){
					alert("请选择教学楼!");
					return false;
				}
				if(shangkejiaoshi=="" || shangkejiaoshi==null){
					alert("请选择教室！");
					return false;
				}
				cixinxi += riqi+","+kaishijieci+","+jieshujieci+","+shangkejiaoshi+";";
				}
				$("#yuanriqi").val(yuanriqi);
				$("#cixinxi").val(cixinxi);
			}
			return true;
		}
	</script>
</body>
</html>