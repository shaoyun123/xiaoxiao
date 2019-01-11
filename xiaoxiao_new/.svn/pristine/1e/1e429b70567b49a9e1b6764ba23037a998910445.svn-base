<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>主页</title>
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
							<li>意见箱</li>
							<li class="active"><a href="wodeyijian">我的意见</a></li>
							<li class="active"><a href="yijiancaogaoxiang">草稿箱</a></li>
							<li class="active">编辑意见</li>
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
                                    <div class="title">编辑意见</div>
                                    </div>
                                </div>
                                <div class="card-body">
                               		 <form action="saveorsubmitupdateyijian?id=${yijian.yijianid}" class="form-inline" method="post" enctype="multipart/form-data" >
                                    	<div class="sub-title">
                                    		<span>意见名称：</span><input id="mingcheng" name="mingcheng" class="form-control" size="40" value="${yijian.yijianmingcheng}"></input>
                                    	</div>
                                    	<div class="sub-title">
                                    		<span>意见内容：</span><br><textarea id="neirong" name="neirong" class="form-control" style="width:1000px;height:200px">${yijian.wenzineirong}</textarea>
                                    	</div>
                                    	<div class="sub-title">
                                    		<span>上传图片：</span><br><input id="tumingcheng" name="tumingcheng" type="file" class="form-control" multiple="multiple" accept="image/*" value="${yijian.tumingcheng}"/>
                                    	</div>
                                    	<div class="sub-title">
                                    		<span>接收人：</span>
                                    		<select id="jieshouren" name="jieshouren" style="width:150px">
                                    			<option value="1" <c:if test="${yijian.jieshourenleixing==1}">selected="selected"</c:if>>辅导员</option>
                                    			<option value="2" <c:if test="${yijian.jieshourenleixing==2}">selected="selected"</c:if>>书记</option>
                                    			<option value="3" <c:if test="${yijian.jieshourenleixing==3}">selected="selected"</c:if>>学生处管理员</option>
                                    		</select>&emsp;
                                    		<%-- <span>可见人：</span>
                                    		<select id="kejianren" name="kejianren" style="width:100px">
                                    			<option value="不可见"<c:if test="${yijian.kejianrenfanwei=='不可见'}">selected="selected"</c:if>>不可见</option>
                                    			<option value="本班"<c:if test="${yijian.kejianrenfanwei=='本班'}">selected="selected"</c:if>>本班</option>
                                    			<option value="本专业"<c:if test="${yijian.kejianrenfanwei=='本专业'}">selected="selected"</c:if>>本专业</option>
                                    			<option value="本学院"<c:if test="${yijian.kejianrenfanwei=='本学院'}">selected="selected"</c:if>>本学院</option>
                                    		</select>&emsp; --%>
                                    		<span>标签：</span>
                                    		<select id="biaoqian" name="biaoqian" style="width:100px">
                                    			<option value="生活"<c:if test="${yijian.biaoqian=='生活'}">selected="selected"</c:if>>生活类</option>
                                    			<option value="学习"<c:if test="${yijian.biaoqian=='学习'}">selected="selected"</c:if>>学习类</option>
                                    			<option value="社团"<c:if test="${yijian.biaoqian=='社团'}">selected="selected"</c:if>>社团类</option>
                                    		</select>&emsp;
                                    	</div>
                                    	<span class="pull-right">
                                    		<input type="radio" id="niming" name="isniming"  <c:if test="${yijian.nimingbiaoji==1}">checked </c:if>value="1"><label style="color: red" for="nimeng">匿名</label>
											<input type="radio" id="gongkai" name="isniming"  <c:if test="${yijian.nimingbiaoji==0}">checked </c:if> value="0"><label style="color: red" for="gongkai">公开</label>
											&emsp;&emsp;
											<input type="submit" name="action" class="flip-link btn btn-info" value="保存" onclick="return save()">
											<input type="submit" name="action" class="flip-link btn btn-info" value="提交" onclick="return sub()">
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
		</div>
	</div>
	</div>
	<script type="text/javascript">
		function save(){
			if(confirm("是否要保存到草稿箱？")==true){
				return true;
			}else{
				return false;
			}
		}
		function sub(){
			if($("#neirong").val()==""){
				alert("请编辑意见内容！");
				return false;
			}else{
				var obj = document.getElementById("tumingcheng");    
				var length = obj.files.length;    
				for(var i=0;i<length;i++)
				{         
					var temp = obj.files[i].name;          
					var postfix = temp.substring(temp.lastIndexOf(".")+1);
					if(postfix!=""){
						if(!(postfix == "jpg"||postfix == "pdf"||postfix == "png"||postfix == "JPG"||postfix == "PDF"||postfix == "PNG"))
						{   
				      		alert('文件类型不正确，请选择.jpg或.pdf或.png文件！');   
				      		$("#tumingcheng").value=""; 
				      		$("#tumingcheng").focus(); 
				      		return false;   
				        }
				     }
				}
				if(confirm("提交后将无法撤回！确认提交吗？")==true){
					return true;
				}else{
					return false;
				}
			}
		}
	</script>
</body>
</html>