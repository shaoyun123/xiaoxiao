<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>调停课程</title>
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
							<li class="active">调停课程</li>
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
                            <div class="card" style="background-color:#dfffdf;">
                            	<div class="card-body">
                            		<form action="savetiaotingkecheng?id=${kecheng.id}"  method="post">
                            		<input type="hidden" name="shangkeriqi" value="${kecheng.shangkeriqi}">
                            			<input type="hidden" id="kaishiriqi" value="${kaishiriqi}">
                            			<input type="hidden" id="jieshuriqi" value="${jieshuriqi}">
                            			<div class="sub-title">
                                    		<span style="font-weight:bold;">课程：${kecheng.kechengmingcheng}</span>
                                    		<span style="margin-left:50px;">${kecheng.xiaoquming}&emsp;${kecheng.jiaoshiming}</span>
                                    		<span style="margin-left:20px;">
                                    		<c:choose>
                                    			<c:when test="${kecheng.leixing==3 }">
                                    				${kecheng.kaifangyuanxi}
                                    			</c:when>
                                    			<c:when test="${kecheng.leixing==2 }">
                                    				<c:if test="${kecheng.keDaiBiao=='1' }">${kecheng.kaishizhou}~${kecheng.jieshuzhou} 单</c:if>
                                    				<c:if test="${kecheng.keDaiBiao=='2' }">${kecheng.kaishizhou}~${kecheng.jieshuzhou} 双</c:if>
                                    				<c:if test="${kecheng.keDaiBiao=='0' }">${kecheng.kaishizhou}~${kecheng.jieshuzhou} </c:if>
                                    			</c:when>
                                    			<c:otherwise>
                                    				${kecheng.kaishizhou}~${kecheng.jieshuzhou}
                                    			</c:otherwise>
                                    		</c:choose>
                                    		周</span>
                                    		<span style="margin-left:20px;">周
                                    			<c:if test="${kecheng.zhouci==1}">一</c:if>
                                    			<c:if test="${kecheng.zhouci==2}">二</c:if>
                                    			<c:if test="${kecheng.zhouci==3}">三</c:if>
                                    			<c:if test="${kecheng.zhouci==4}">四</c:if>
                                    			<c:if test="${kecheng.zhouci==5}">五</c:if>
                                    			<c:if test="${kecheng.zhouci==6}">六</c:if>
                                    			<c:if test="${kecheng.zhouci==7}">日</c:if>
                                    		</span>
                                    		<span style="margin-left:20px;">${kecheng.kaishijieci}~${kecheng.jieshujieci}节</span>
                                    		<span style="margin-left:20px;">${kecheng.renkejiaoshi} 老师</span>
                                    	</div>
                                    	<div class="sub-title">
                                    		<input type="radio" id="tingke" name="tiaoting"   value="0" onclick="close1_open2('wanshanxinxi','tiaoting')"><label style="color: red" for="tingke">停课</label>
											<input type="radio" id="tiaoke" name="tiaoting"  value="1" onclick="open1_open2('wanshanxinxi','tiaoting')"><label style="color: red" for="tiaoke">调课</label>
											<input type="radio" id="jiake" name="tiaoting"  value="2" onclick="open1_close2('wanshanxinxi','tiaoting')"><label style="color: red" for="jiake">加课</label><br>
                                    	</div>
                                    	<div id="tiaoting" style="display:none;margin-left:30px;">
                                    		<span style="font-weight:bold;">选择调停日期：</span>
                                    		<select style="width:100px;" id="tiaotingriqi" name="tiaotingriqi">
                                    			<option value="">--请选择--</option>
                                    			<c:forEach items="${riqi}" var="riqi">
                                    				<option value="${riqi}">${riqi}</option>
                                    			</c:forEach>
                                    		</select>
                                    	</div>
                                    	<div id="wanshanxinxi" style="display:none;">
                                    	<div  class="sub-title">
                                    		<span style="margin-left:30px;">调加课时间：</span>
                                    		<input id="newriqi" name="newriqi" type="date"/>
                                    		<span style="font-size:13px;font-weight:bold;">（**调加课时间不得超出教学周范围，如有需要请修改教学周）</span><br><br>
                                    		<span style="margin-left:30px;">校区：</span>
                                    		<select id="xiaoqu" name="xiaoqu" style="width:100px;" onchange="show_jiaoshi()">
                                    			<option value="">--请选择--</option>
                                    			<c:forEach items="${xiaoqu}" var="XiaoQu">
                                    				<option value="${XiaoQu.xiaoquid}" <c:if test="${XiaoQu.xiaoquid==kecheng.xiaoqu}">selected="selected"</c:if>>${XiaoQu.mingcheng}</option>
                                    			</c:forEach>
                                    		</select>&emsp;
                                    		<span>教室：</span>
                                    		<select id="shangkejiaoshi" name="shangkejiaoshi" style="width:100px;">
                                    			<c:forEach items="${jiaoshi}" var="jiaoshi">
                                    				<option value="${jiaoshi.jiaoshiid}" <c:if test="${jiaoshi.jiaoshiid==kecheng.shangkejiaoshi}">selected="selected"</c:if>>${jiaoshi.jiaoshiming}</option>
                                    			</c:forEach>
                                    		</select><br><br>
                                    		<span style="margin-left:30px;">节次：</span>
                                    		<select id="kaishijieci" name="kaishijieci" style="width:50px">
                                    			<c:forEach items="${jieci}" var="jieci">
                                    				<option value="${jieci.id}" <c:if test="${kecheng.kaishijieci==jieci.id}">selected="selected"</c:if>>${jieci.jieci}</option>
                                    			</c:forEach>
                                    		</select>
                                    		<span>~</span>
                                    		<select id="jieshujieci" name="jieshujieci" style="width:50px">
                                    			<c:forEach items="${jieci}" var="jieci">
                                    				<option value="${jieci.id}" <c:if test="${kecheng.jieshujieci==jieci.id}">selected="selected"</c:if>>${jieci.jieci}</option>
                                    			</c:forEach>
                                    		</select>
                                    		<span>节</span>
                                    	</div>
                                    	</div>
                                    	<div  class="sub-title">
                                    		<span class="pull-right"><input type="submit" class="flip-link btn btn-info" value="保存" onclick="return save()"></span>
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
		</div>
	</div>
	</div>
	<script type="text/javascript">
	function show_jiaoshi(){
		var xiaoquid=$("#xiaoqu").val();
		$.ajax({
			type: "POST",
			url: 'showjiaoshibyxiaoquid',
	    	data:{CODE:xiaoquid},
			dataType:'json',
			cache:false,
			timeout: 5000,
			async:true, 
			success:function(data){
				var data = eval(data);
				var code = '<option disabled selected value>';
				var defaultValue='';
				for(var i=0;i<data.length;i++){
					defaultValue=data[0].jiaoshiming;
					code += '<option value="'+data[i].jiaoshiid+'">'+data[i].jiaoshiming+'</option>';
				}
				$("#shangkejiaoshi").html(code);
				$("#shangkejiaoshi").val(defaultValue).trigger('change');
			},
	    	error:function()
			{
				alert("登录超时!")
			}
		});
	}
	
	function open1_close2(id1,id2){
		document.getElementById(id1).style.display = "block";
		document.getElementById(id2).style.display = "none";
	}
	function open1_open2(id1,id2){
		document.getElementById(id1).style.display = "block";
		document.getElementById(id2).style.display = "block";
	}
	function close1_open2(id1,id2){
		document.getElementById(id1).style.display = "none";
		document.getElementById(id2).style.display = "block";
	}
	
	function save(){
		var radios = document.getElementsByName("tiaoting");
		if(radios[0].checked==false && radios[1].checked==false && radios[2].checked==false){
			alert("请选择调停课类型！")
			return false;
		}
		if(radios[0].checked==true){
			if($("#tiaotingriqi").val()==""){
				alert("请选择停课日期！")
				return false;
			}
			return true;
		}
		if(radios[1].checked==true){
			var kaishiriqi = $("#kaishiriqi").val();
			var jieshuriqi = $("#jieshuriqi").val();
			if($("#tiaotingriqi").val()==""){
				alert("请选择需要调课的日期！")
				return false;
			}
			if($("#newriqi").val()==""){
				alert("请选择调课后的上课时间！")
				return false;
			}
			if($("#newriqi").val() < kaishiriqi || $("#newriqi").val() > jieshuriqi){
				alert("调课后的上课时间应在学期范围内！！");
				return false;
			}
			if($("#xiaoqu").val()==""){
				alert("请选择校区！")
				return false;
			}
			if($("#shangkejiaoshi").val()==""){
				alert("请选择教室！")
				return false;
			}
			return true;
		}
		if(radios[2].checked==true){
			var kaishiriqi = $("#kaishiriqi").val();
			var jieshuriqi = $("#jieshuriqi").val();
			if($("#newriqi").val()==""){
				alert("请选择加课后的上课时间！")
				return false;
			}
			if($("#newriqi").val() < kaishiriqi || $("#newriqi").val() > jieshuriqi){
				alert("加课后的上课时间应在学期范围内！！");
				return false;
			}
			if($("#xiaoqu").val()==""){
				alert("请选择校区！")
				return false;
			}
			if($("#shangkejiaoshi").val()==""){
				alert("请选择教室！")
				return false;
			}
			return true;
		}
	}
	</script>
</body>
</html>