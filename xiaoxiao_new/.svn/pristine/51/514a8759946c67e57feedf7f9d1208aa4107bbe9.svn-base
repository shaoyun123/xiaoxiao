<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>添加课程</title>
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
							<li class="active"><a href="wodekecheng_jiaoshi">我的课程</a></li>
							<li class="active">添加课程</li>
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
                                    <form action="savekecheng_jiaoshi" class="form-inline" method="post">
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">课程：</span>
                                    		<select id="kecheng" name="kecheng">
                                    			<option value="">--请选择--</option>
                                    			<c:forEach items="${kecheng}" var="KeCheng">
                                    				<option value="${KeCheng.kechengid}">${KeCheng.kechengmingcheng}(${KeCheng.renkejiaoshi})</option>
                                    			</c:forEach>
                                    		</select>
                                    	</div>
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">校区：</span>
                                    		<select id="xiaoqu" name="xiaoqu" style="width:100px;" onchange="show_jiaoxuelou()">
                                    			<option value="">--请选择--</option>
                                    			<c:forEach items="${xiaoqu}" var="XiaoQu">
                                    				<option value="${XiaoQu.xiaoquid}">${XiaoQu.mingcheng}</option>
                                    			</c:forEach>
                                    		</select>&emsp;&emsp;
                                    		<span style="font-weight:bold;">教学楼：</span>
                                    		<select id="jiaoxuelou" name="jiaoxuelou" style="width:100px;" onchange="show_jiaoshi()">
                                    		</select>&emsp;&emsp;
                                    		
                                    		<span style="font-weight:bold;">教室：</span>
                                    		<select id="shangkejiaoshi" name="shangkejiaoshi" style="width:100px;">
                                    		</select>&emsp;&emsp;
                                    		
                                    	</div>
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">上课周：</span>
                                    		<span>第</span>
                                    		<select id="kaishizhou" name="kaishizhou" style="width:50px">
                                    			<c:forEach var="i" begin="1" end="30" step="1">
                                    				<option value="${i}">${i}</option>
                                    			</c:forEach>
                                    		</select>
                                    		<span>周——至——第</span>
                                    		<select id="jieshuzhou" name="jieshuzhou" style="width:50px">
                                    			<c:forEach var="i" begin="1" end="30" step="1">
                                    				<option value="${i}">${i}</option>
                                    			</c:forEach>
                                    		</select>
                                    		<span>周</span>
                                    		&emsp;&emsp;&emsp;&emsp;
                                    		<span style="font-weight:bold;">星期：</span>
                                    		<select id="zhouci" name="zhouci" style="width:50px">
                                    			<option value="1">一</option>
                                    			<option value="2">二</option>
                                    			<option value="3">三</option>
                                    			<option value="4">四</option>
                                    			<option value="5">五</option>
                                    			<option value="6">六</option>
                                    			<option value="7">日</option>
                                    		</select>
                                    		&emsp;&emsp;&emsp;&emsp;
                                    		<span style="font-weight:bold;">单双周：</span>
                                    		<select id="danshuangzhou" name="danshuangzhou" onchange="show(this)" style="width:70px">
                                    			<option value="0">每周</option>
                                    			<option value="1">其他</option>
                                    		</select>
                                    		&emsp;&emsp;
                                    		<div id="other" style="display:none">
                                    			<span style="font-weight:bold;">其他情况说明：</span><input id="danshuangzhoushuoming" name="danshuangzhoushuoming" type="text" size=35 placeholder="如：1~5周(单),6~10周(双),11~17周"/>
                                    		</div>
                                    	</div>
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">上课节次：</span>
                                    		<span>第</span>
                                    		<select id="kaishijieci" name="kaishijieci" style="width:70px">
                                    			<c:forEach items="${jcsj }" var="jc">
                                    				<option value="${jc.id}">${jc.jieci}</option>
                                    			</c:forEach>
                                    		</select>
                                    		<span>节——至——第</span>
                                    		<select id="jieshujieci" name="jieshujieci" style="width:70px">
                                    			<c:forEach items="${jcsj }" var="jc">
                                    				<option value="${jc.id}">${jc.jieci}</option>
                                    			</c:forEach>
                                    		</select>
                                    		<span>节</span>
                                    	</div>
                                    	<span class="pull-right"><input type="submit" class="flip-link btn btn-info" value="保存" onclick="return save()"></span>
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
		var jiaoXueLouId=$("#jiaoxuelou").val();
		$.ajax({
			type: "POST",
			url: 'show_JiaoShi',
	    	data:{CODE:jiaoXueLouId},
			dataType:'json',
			cache:false,
			timeout: 5000,
			async:true, 
			success:function(data){
				var data = eval(data);
				var code = '<option disabled selected value>';
				if(data.length!=0){
					for(var i=0;i<data.length;i++){
						code += '<option value="'+data[i].jiaoshiid+'">'+data[i].jiaoshiming+'</option>';
					}
					$("#shangkejiaoshi").html(code);
				}
				else{
					alert("该教学楼下还没有教室!");
				}
				
			},
	    	error:function()
			{
				alert("登录超时!")
			}
		});
	}
	
	function show_jiaoxuelou(){
		var xiaoquid=$("#xiaoqu").val();
		$.ajax({
			type: "POST",
			url: 'show_JiaoxueLou',
	    	data:{CODE:xiaoquid},
			dataType:'json',
			cache:false,
			timeout: 5000,
			async:true, 
			success:function(data){
				var data = eval(data);
				var code = '<option disabled selected value>';
				if(data.length!=0){
					for(var i=0;i<data.length;i++){
						code += '<option value="'+data[i].jiaoXueLouId+'">'+data[i].jiaoXueLouMing+'</option>';
					}
					$("#jiaoxuelou").html(code);
				}
				else{
					alert("该校区下还没有教学楼!");
				}
				
			},
	    	error:function()
			{
				alert("登录超时!")
			}
		});
	}
	
		/*function show_jiaoshi(){
			var xiaoquid=$("#xiaoqu").val();
			$.ajax({
				type: "POST",
				url: 'show_jiaoshi',
		    	data:{CODE:xiaoquid},
				dataType:'json',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data){
					var data = eval(data);
					var code = '<option disabled selected value>';
					for(var i=0;i<data.length;i++){
						code += '<option value="'+data[i].jiaoshiid+'">'+data[i].jiaoshiming+'</option>';
					}
					$("#shangkejiaoshi").html(code);
				},
		    	error:function()
				{
					alert("登录超时!")
				}
			});
		}*/
	
		function show(obj){//选择其他时，弹出其他说明
			document.getElementById("other").style.display=(obj.value==1)?"":"none"
		}
		function save(){
			if($("#kecheng").val()==""){
				alert("请选择课程！");
				return false;
			}
			if($("#xiaoqu").val()==""){
				alert("请选择校区！");
				return false;
			}
			if($("#jiaoxuelou").val()==""||$("#jiaoxuelou").val()==null){
				alert("请选择教学楼!");
				return false;
			}
			if($("#shangkejiaoshi").val()==""||$("#shangkejiaoshi").val()==null){
				alert("请选择教室！");
				return false;
			}
			
			return true;
		}
	</script>
</body>
</html>