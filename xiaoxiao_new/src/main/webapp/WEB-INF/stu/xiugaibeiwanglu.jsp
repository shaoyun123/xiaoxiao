<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>修改备忘录</h2>
		</div>
					<div class="box-content">
                                	<form action="saveupdatebeiwanglu?id=${beiwanglu.id}&qufen=${qufen}" class="form-inline" method="post">
                                		<div class="sub-title">
                                			<span>内容：</span>&emsp;<input id="neirong" name="neirong" type="text" class="form-control" size="30" value="${beiwanglu.neirong}"/>
                                		</div>
<!--                                 		<div class="sub-title"> -->
<%--                                 			<span>地点：</span>&emsp;<input id="didian" name="didian" type="text" class="form-control" size="30" value="${beiwanglu.didian}"/> --%>
<!--                                 		</div> -->
                                		<div class="sub-title">
                                			<span>时间：</span>&emsp;<input id="riqi" name="riqi" type="text" class="Wdate" style="height:25px" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="${date}"/>&emsp;<input id="shijian" name="shijian" type="text" class="Wdate" style="height:25px" onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" value="${time}"/>
                                		</div>
                                		<span class="pull-right"><input type="submit" class="flip-link btn btn-info" value="保存" onclick="return save()"></span>
                                	</form>
                                </div>
                            </div>
                        

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
			<script type="text/javascript" src="static/js/My97DatePicker/WdatePicker.js"></script>
		
	<script type="text/javascript">
	function save(){
		if($("#neirong").val()==""){
			alert("请填写内容！");
			return false;
		}else if($("#didian").val()==""){
			alert("请填写地点！");
			return false;
		}else if($("#riqi").val()==""){
			alert("请选择日期！");
			return false;
		}else if($("#shijian").val()==""){
			alert("请选择时间！");
			return false;
		}else{
			return true;
		}
	}
	</script>
