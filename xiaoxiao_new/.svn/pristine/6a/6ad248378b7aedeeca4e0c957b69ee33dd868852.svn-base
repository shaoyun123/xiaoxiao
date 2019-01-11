<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>自主添加课程</title>
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
							<li class="active"><a href="wodekecheng">我的课程</a></li>
							<li class="active"><a href="addkecheng_">添加课程</a></li>
							<li class="active">自主添加</li>
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
                                </div>
                                <div class="card-body">
                                	<form action="savekecheng_zizhu" class="form-inline" method="post">
                                	<input type="hidden" name="lianxukecheng" id="lianxukecheng">
                                	<input type="hidden" id="zhoushu" value="${zhounum}">
                                	<input type="hidden" name="xuenian" value="${xuenian }">
									<input type="hidden" name="xueqi" value="${xueqi }">
                                		<div class="sub-title">
                                    		<span style="font-weight:bold;">校区：</span>
                                    		<span>${xiaoqu.mingcheng}</span>&emsp;&emsp;
                                    		<input id="xiaoqu" name="xiaoqu" type="hidden" value="${xiaoqu.xiaoquid}"/>
                                    		<span style="font-weight:bold;">教室：</span>
                                    		<span>${jiaoshi.jiaoshiming}</span>&emsp;&emsp;
                                    		<input id="1-shangkejiaoshi" name="jiaoshi" type="hidden" value="${jiaoshi.jiaoshiid}"/>
                                    		<span style="font-weight:bold;">星期：</span>
                                    		<c:if test="${zhouci==1}"><span>一</span></c:if>
                                    		<c:if test="${zhouci==2}"><span>二</span></c:if>
                                    		<c:if test="${zhouci==3}"><span>三</span></c:if>
                                    		<c:if test="${zhouci==4}"><span>四</span></c:if>
                                    		<c:if test="${zhouci==5}"><span>五</span></c:if>
                                    		<c:if test="${zhouci==6}"><span>六</span></c:if>
                                    		<c:if test="${zhouci==7}"><span>日</span></c:if>&emsp;&emsp;
                                    		<input id="1-zhouci" name="zhouci" type="hidden" value="${zhouci}"/>
                                    	</div>
                                    	<div id="wanshanxinxi" style="margin-left:30px;" class="sub-title">
                                    		<span style="font-weight:bold">请继续完善信息……</span><br><br>
                                    		<span>课程名称：</span>
                                    		<input type="text" id="kechengmingcheng" name="kechengmingcheng" size="40px;"/><br><br>
                                    		<span>任课教师：</span>
                                    		<input id="renkejiaoshi" name="renkejiaoshi" type="text" size=30/><br><br>
                                    		
                                    		<span style="font-weight: bold;">课程类型：</span>
											<select id="danshuangzhou" name="danshuangzhou"
												onchange="shows(this)" style="width: 120px">
												<option value="0">请选择</option>
												<option value="1">连续周课</option>
												<option value="2">单次周课</option>
											</select>
                                    		<c:if test="${user.isbanzhang}">
	                                    		<input id="banzhang" type="hidden" value="1">
	                                    		<div>
	                                    			<input type="radio" id="ziji" name="ziji_banji"  value="0"><label style="color: red" for="ziji">自己</label>
													<input type="radio" id="benban" name="ziji_banji"  value="1"><label style="color: red" for="benban">本班</label>
	                                    		</div>
                                    		</c:if>
                                    	
                                    		<br>
											<hr>
											<div id="disp1" style="display: none">
												<div id="disp2">
													<div id="1" class="aa">
													<hr>
													<span>上课周：</span> <span>第</span> <select
													id="1-kaishizhou" name="kaishizhou" style="width: 50px">
													<c:forEach var="i" begin="1" end="${zhounum}" step="1">
														<option value="${i}">${i}</option>
													</c:forEach>
												</select> <span>周——至——第</span> <select id="1-jieshuzhou"
													name="jieshuzhou" style="width: 50px">
													<c:forEach var="i" begin="1" end="${zhounum}" step="1">
														<option value="${i}">${i}</option>
													</c:forEach>
												</select> <span>周</span> <br><br>
														<span style="font-weight: bold;">课程类型：</span> <select
															id="1-danshuang" name="danshuang" style="width: 120px">
															<option value="0">请选择</option>
															<option value="1">每周</option>
															<option value="2">单周</option>
															<option value="3">双周</option>
														</select> &emsp;&emsp;&emsp;<span>上课节次：</span> <span>第</span> <select
															id="1-kaishijieci" name="kaishijieci" style="width: 70px">
															<c:forEach items="${jieci}" var="jieci">
																<option value="${jieci.id}">${jieci.jieci}</option>
															</c:forEach>
														</select> <span>节——至——第</span> <select id="1-jieshujieci"
															name="jieshujieci" style="width: 70px">
															<c:forEach items="${jieci}" var="jieci">
																<option value="${jieci.id}">${jieci.jieci}</option>
															</c:forEach>
														</select> <span>节</span>&emsp;&emsp;&emsp;&emsp;&emsp;
														 <span>&emsp;节数:&emsp;</span>
												<input style="width: 60px;" type="button"
													onclick="add_kecheng()" value="+" />
														
													</div>
												</div>

											</div>
											<br>
											<div id="divp1" style="display: none;">
												<div id="2-1" class="2-aa">
													<span style="font-weight: bold;">上课时间：</span> &emsp;<input
														id="2-1-riqi" type="text" class="Wdate"
														style="height: 25px"
														onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />

													&emsp;&emsp;&emsp;<span>上课节次：</span> <span>第</span> <select
														id="2-1-kaishijieci" style="width: 70px">
														<c:forEach items="${jieci}" var="jieci">
															<option value="${jieci.id}">${jieci.jieci}</option>
														</c:forEach>
													</select> <span>节——至——第</span> <select id="2-1-jieshujieci"
														name="2-1-jieshujieci" style="width: 70px">
														<c:forEach items="${jieci}" var="jieci">
															<option value="${jieci.id}">${jieci.jieci}</option>
														</c:forEach>
													</select> <span>节</span> &emsp;&emsp; <span>&emsp;节数:&emsp;</span> <input
														style="width: 60px;" type="button" onclick="add_jie()"
														value="+" />
													<hr>
												</div>
											</div>
                                    	
                                    	</div>
                                    	<span class="pull-right">
                                    		<input style="margin-left:20px;"type="submit" class="flip-link btn btn-info" value="保存" onclick="return save()">
                                    	</span>
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
	
		function add_kecheng() {
			var zhounum = $("#zhoushu").val();
			var num = $("#disp2 .aa").length;
			var i = num + 1;
			if (i > 2) {
				alert("这位学生，您已经加了两节课了！请您谨慎加课！！");
			}
			var code = '<div id="' + i + '" class="aa" style="width:100%;"><hr>';
			$
					.ajax({
						type : "POST",
						url : 'getjiecishuju_stu',
						data : {
	
						},
						dataType : 'json',
						cache : false,
						timeout : 5000,
						async : true,
						success : function(data) {
							var data = eval(data);
							if ((data.zongjieci != null && data.zongjieci != "") && (data.xiaoqus != null && data.xiaoqus != "")) {
								code += '<div style="width:80%;">';
								code += '<span>上课周：</span> <span>第</span> <select id="'+i+'-kaishizhou" style="width: 50px">';
								for (var a = 1; a <= zhounum; a++) {
									code += '<option value="'+a+'">' + a
											+ '</option>';
								}
								code += '</select> <span>周——至——第</span> <select id="'+i+'-jieshuzhou" style="width: 50px">';
								for (var a = 1; a <= zhounum; a++) {
									code += '<option value="'+a+'">' + a
											+ '</option>';
								}
								code += '</select> <span>周</span> <br><br>';
								code += '<span>课程类型：</span><select style="padding-left:10px;width:120px;" id="'+i+'-danshuang">';
								code += '<option value="0">请选择</option> <option value="1">每周</option> <option value="2">单周</option> <option value="3">双周</option>';
								code += ' </select>&emsp;&emsp;&emsp;';
								code += '<span>上课节次：</span><span>第</span><select style="padding-left:10px;width:70px;" id="'+i+'-kaishijieci">';
								for (var j = 0; j < data.zongjieci.length; j++) {
									code += '<option value="' + data.zongjieci[j].id + '">' + data.zongjieci[j].jieci + '</option>';
								}
								code += ' </select>';
								code += '<span>节——至——第</span><select style="padding-left:10px;width:70px;" id="'+i+'-jieshujieci">';
								for (var j = 0; j < data.zongjieci.length; j++) {
									code += '<option value="' + data.zongjieci[j].id + '">' + data.zongjieci[j].jieci + '</option>';
								}
								code += ' </select><span>节</span>&emsp;&emsp;&emsp;';
								code += '<span>星期：</span><select id="'+i+'-zhouci" style="padding-left:10px;width:70px;">';
								code += '<option value="1">一</option><option value="2">二</option><option value="3">三</option><option value="4">四</option><option value="5">五</option>';
								code += '<option value="6">六</option><option value="7">日</option></select><br><br>';
								code += '<span>校区：</span><select style="padding-left:10px;width:120px;" id="'
										+ i
										+ '-xiaoqu" onchange="show_jiaoxuelou(this)">';
								code += '<option value="0">请选择</option>';
								for (var j = 0; j < data.xiaoqus.length; j++) {
									code += '<option value="' + data.xiaoqus[j].xiaoquid + '">' + data.xiaoqus[j].mingcheng + '</option>';
								}
								code += ' </select>&emsp;&emsp;&emsp;';
								code += '<span>教学楼：</span><select style="padding-left:10px;width:120px;" onchange="show_jiaoshi(this)" id="'
										+ i + '-jiaoxuelou">';
								code += '<option value="0">请选择</option>';
								code += ' </select>&emsp;&emsp;&emsp;';
								code += '<span>教室：</span><select style="padding-left:10px;width:120px;" id="'+i+'-shangkejiaoshi">';
								code += '<option value="0">请选择</option>';
								code += ' </select>';
								// 							code += '<div onclick="deletes('+i+')" style="width:15%;float:left;margin-top:10px;background-color:green;text-align:center;"> 删除 </div>';
								code += '</div></div>';
							$("#disp2").append(code);
							}
						},
						error : function() {
							alert("超时")
						}
					});
		}
		function add_jie() {
			var num = $("#divp1 .2-aa").length;
			var i = num + 1;
			var code = '<div id=2-"' + i
					+ '" class="2-aa" style="width:100%;">';
			$
					.ajax({
						type : "POST",
						url : 'getjiecishuju_stu',
						data : {
	
						},
						dataType : 'json',
						cache : false,
						timeout : 5000,
						async : true,
						success : function(data) {
							var data = eval(data);
							if ((data.zongjieci != null && data.zongjieci != "") && (data.xiaoqus != null && data.xiaoqus != "")) {
								code += '<span  style="width:80%;font-weight: bold;">校区：</span><select style="padding-left:10px;width:120px;" id="2-'
										+ i
										+ '-xiaoqu" onchange="show_jiaoxuelou(this)">';
								code += '<option value="0">请选择</option>';
								for (var j = 0; j < data.xiaoqus.length; j++) {
									code += '<option value="' + data.xiaoqus[j].xiaoquid + '">' + data.xiaoqus[j].mingcheng + '</option>';
								}
								code += ' </select>&emsp;&emsp;&emsp;&emsp;';
								code += '<span style="width:80%;font-weight: bold;">教学楼：</span><select style="padding-left:10px;width:120px;" onchange="show_jiaoshi(this)" id="2-'
										+ i + '-jiaoxuelou">';
								code += '<option value="0">请选择</option>';
								code += ' </select> &emsp;&emsp;&emsp;';
								code += '<span style="width:80%;font-weight: bold;">教室：</span><select style="padding-left:10px;width:120px;"  id="2-'+i+'-shangkejiaoshi">';
								code += '<option value="0">请选择</option>';
								code += ' </select><br><br>';
								code += '<span style="font-weight: bold;">上课时间：</span> &emsp;<input id="2-'
										+ i
										+ '-riqi" type="text" class="Wdate" style="height: 25px"'
										+ 'onclick="WdatePicker({readOnly:true,dateFmt:\'yyyy-MM-dd\'})" />'
								code += '&emsp;&emsp;&emsp;';
								code += '<span>上课节次：</span> <span>第</span><select style="width: 70px;" id="2-'+i+'-kaishijieci">';
								for (var j = 0; j < data.zongjieci.length; j++) {
									code += '<option value="' + data.zongjieci[j].id + '">' + data.zongjieci[j].jieci + '</option>';
								}
								code += ' </select>';
								code += '<span>节——至——第</span><select style="width: 70px;" id="2-'+i+'-jieshujieci">';
								for (var j = 0; j < data.zongjieci.length; j++) {
									code += '<option value="' + data.zongjieci[j].id + '">' + data.zongjieci[j].jieci + '</option>';
								}
								code += ' </select>节</div>';
								// 							code += '<div onclick="deletes('+i+')" style="width:15%;float:left;margin-top:10px;background-color:green;text-align:center;"> 删除 </div>';
								code += '</div><hr>';
								$("#divp1").append(code);
							}
						},
						error : function() {
							alert("超时")
						}
					});
		}
		function show_jiaoxuelou(obj) {
			var id = new Array();
			id = $(obj).attr("id").split("-xiaoqu");
			var xiaoquid = $("#" + $(obj).attr("id")).val();
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
					var code = '<option value="">请选择</option>';
					var defaultValue = '';
					if (data.length != 0) {
						for (var i = 0; i < data.length; i++) {
							defaultValue = [ data[0].jiaoXueLouId ];
							code += '<option value="'+data[i].jiaoXueLouId+'">'
									+ data[i].jiaoXueLouMing + '</option>';
						}
						$("#" + id[0] + "-jiaoxuelou").html(code);
					} else {
						alert("该校区下还没有教学楼!");
						$("#" + id[0] + "-jiaoxuelou").empty();
						$("#" + id[0] + "-jiaoxuelou").val(defaultValue)
								.trigger('change');
					}
	
				},
				error : function() {
					alert("登录超时!")
				}
			});
		}
	
		function show_jiaoshi(obj) {
			var id = new Array();
			id = $(obj).attr("id").split("-jiaoxuelou");
			var jiaoXueLouId = $("#" + $(obj).attr("id")).val();
			if (jiaoXueLouId.length != 0) {
				$
						.ajax({
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
								var code = '<option value="">请选择</option>';
								var defaultValue = '';
								if (data) {
									for (var i = 0; i < data.length; i++) {
										defaultValue = [ data[0].jiaoshiid ];
										code += '<option value="'+data[i].jiaoshiid+'">'
												+ data[i].jiaoshiming
												+ '</option>';
									}
									$("#" + id[0] + "-shangkejiaoshi").html(
											code);
								} else {
									alert("该教学楼下还没有教室!");
									$("#" + id[0] + "-jiaoxuelou").empty();
									$("#" + id[0] + "-jiaoxuelou").val(
											defaultValue).trigger('change');
	
								}
							},
							error : function() {
								alert("登录超时!")
							}
	
						});
			} else {
				code += '<option value="">--请选择--</option>';
				$("#" + id[0] + "-shangkejiaoshi").html(code);
				alert("该教学楼下还没有教室!");
			}
		}
		
		function shows(obj) {//选择其他时，弹出其他说明
			document.getElementById("disp1").style.display = (obj.value == 1) ? "block"
					: "none"
			document.getElementById("divp1").style.display = (obj.value == 2) ? "block"
					: "none"
		}
		function save(){
			if($("#banzhang").val()=="1"){
				var radio = document.getElementsByName("ziji_banji");
				if(radio[0].checked==false && radio[1].checked==false){
					alert("请选择添加对象！自己or本班？")
					return false;
				}
			}
				if($("#kechengmingcheng").val()==""){
					alert("请输入课程名称！");
					return false;
				}
				if($("#renkejiaoshi").val()==""){
					alert("请输入任课教师！");
					return false;
				}
				if ($("#danshuangzhou").val() == "0") {
					alert("请选择课程类型");
					return false;
				}
				if ($("#danshuangzhou").val() == "1") {
					if ($("#danshuang").val() == "0") {
						alert("请选择课程类型");
						return false;
					}

					var da = "";
					var num = $("#disp2 .aa").length;
					for (var a = 1; a <= num; a++) {
						// 					alert("周次："+$("#"+a+"-zhouci").val()+"教室："+$("#"+a+"-shangkejiaoshi").val()+"开始节次："+ $("#"+a+"-kaishijieci").val()+"结束节次："+$("#"+a+"-jieshujieci").val());
						var zhouci = $("#" + a + "-zhouci").val();
						var shangkejiaoshi = $("#" + a + "-shangkejiaoshi").val();
						var kaishijieci = $("#" + a + "-kaishijieci").val();
						var jieshujieci = $("#" + a + "-jieshujieci").val();
						var danshuang = $("#" + a + "-danshuang").val();
						var kaishizhou = $("#" + a + "-kaishizhou").val();
						var jieshuzhou = $("#" + a + "-jieshuzhou").val();
						da += danshuang + "," + zhouci + "," + kaishijieci + ","
								+ jieshujieci + "," + shangkejiaoshi + ","
								+ kaishizhou + "," + jieshuzhou + ";";
					}
					$("#lianxukecheng").val(da);
				}
				if ($("#danshuangzhou").val() == "2") {
					var da = "";
					var num = $("#divp1 .2-aa").length;
					for (var a = 1; a <= num; a++) {
						if (a == 1) {
							var riqi = $("#2-" + a + "-riqi").val();
							var shangkejiaoshi = $("#1-shangkejiaoshi").val();
							var kaishijieci = $("#2-" + a + "-kaishijieci").val();
							var jieshujieci = $("#2-" + a + "-jieshujieci").val();
							da += riqi + "," + kaishijieci + "," + jieshujieci
									+ "," + shangkejiaoshi + ";";
						} else {
							var riqi = $("#2-" + a + "-riqi").val();
							var shangkejiaoshi = $("#2-" + a + "-shangkejiaoshi")
									.val();
							var kaishijieci = $("#2-" + a + "-kaishijieci").val();
							var jieshujieci = $("#2-" + a + "-jieshujieci").val();
							da += riqi + "," + kaishijieci + "," + jieshujieci
									+ "," + shangkejiaoshi + ";";
						}
					}
					$("#lianxukecheng").val(da);
					console.log($("#lianxukecheng").val());
				}
				if(confirm("确定要添加此课程吗？") == false){
					return false;
				}
			return true;
		}
	</script>
</body>
</html>