<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page import ="com.web.service.JiaoXueLouService,com.web.model.JiaoXueLou,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>添加用户</title>
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
			
			<!-- Main Content -->
			<div class="container-fluid">
                <div class="side-body">
                	<div class="row">
                        <div class="col-xs-12">
                            <div class="card" style="background-color:#ffffff;">
                                <div class="card-body">
                                
                                    <form action="saveyonghu" class="form-inline" method="post">
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">院系：</span>
                                    		<select id="yuanxiid" name="yuanxiid" class="form-control chosen-select success" style="display: none;width:10%" aria-required="true" aria-invalid="false">
                                    		<option value="">--请选择--</option>
                                    			<c:forEach items="${yuanxis}" var="yuanxi">
                                    				<option value="${yuanxi.yuanxiid}">${yuanxi.yuanximingcheng}</option>
                                    			</c:forEach>
                                    		</select>
                                    	</div>
                                    	
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">请选择身份：</span>
                                    		<select id="jueseid" name="jueseid" class="form-control chosen-select success" style="width:10%" aria-required="true" aria-invalid="false" multiple="multiple">
	                                    		<option value="">--请选择--</option>
	                                    		<option value="1">教师</option>
	                                    		<option value="2">辅导员</option>
	                                    		<option value="3">书记</option>
                                    		</select>
                                    	</div>
                                    	
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">用户名：</span>
                                    		<input id="yonghuming" name="yonghuming" type="text" size="15px"/>
                                    	</div>
                                    	
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">用户姓名：</span>
                                    		<input id="yonghuxingming" name="yonghuxingming" type="text" size="15px"/>
                                    	</div>
                                    	
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">密码：</span>
                                    		<input id="mima" name="mima" type="text" size="15px"/>
                                    	</div>
                                    	
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">邮箱：</span>
                                    		<input id="yonghuyouxiang" name="yonghuyouxiang" type="text" size="15px"/>
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
	function save(){
		if($("#yuanxiid").val()==""){
			alert("请选择院系!");
			return false;
		}
		if($("#jueseid").val()=="" ||  $("#jueseid").val() == null){
			alert("请选择身份!");
			return false;
		}
		if($("#yonghuming").val()==""){
			alert("请输入用户名!");
			return false;
		}
		
		if($("#yonghuxingming").val()==""){
			alert("请输入用户姓名!");
			return false;
			
		}
		if($("#mima").val()==""){
			alert("请输入密码!");
			return false;
			
		}
		if($("#yonghuyouxiang").val()==""){
			if(confirm("确认不输入邮箱？")==true){
				return true;
			}
			else{
				return false;
			}
		}
	}
	</script>
</body>
</html>