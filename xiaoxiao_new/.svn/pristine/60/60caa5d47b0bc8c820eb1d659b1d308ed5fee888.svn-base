<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>修改事件
			</h2>
		</div>
		<div class="box-content">
			<form action="" class="form-horizontal" method="post" id="form"
				enctype="multipart/form-data">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="neirong">事件内容：</label>
						<div class="controls">
							<input id="neirong" name="neirong" type="text"
								class="input-xlarge focused" value="${beiwanglu.neirong}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="riqi">事件日期：</label>
						<div class="controls">
							<input id="riqi" name="riqi" type="text" class="Wdate"
								onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="${date}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="shijian">事件时间：</label>
						<div class="controls">
							<input id="shijian" name="shijian" type="text" class="Wdate"
								onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" value="${time}"/>
						</div>
					</div>
					<input type="hidden" id="qufen" name="qufen" value="${qufen}"/>
					<input type="hidden" id="id" name="id" value="${beiwanglu.id}"/>
					<div id="submit"  class="" style="margin-left: 200px;">
						<button type="button" class="btn btn-primary"
							 onclick="save()">保存</button>
					</div>
					<fieldset>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function save(){
	if($("#neirong").val()==""){
		alert("请填写内容！");
	}
	if($("#riqi").val()==""){
		alert("请选择日期！");
	}
	if($("#shijian").val()==""){
		alert("请选择时间！");
	}
	$.ajax({
		type : "POST",//方法类型
		dataType : "json",//预期服务器返回的数据类型
		url : "saveupdatebeiwanglu_fdy",//url
		data : $('#form').serialize(),
		success : function(result) {
			if (result.status == "success") {
				alert("success");
				toContentPage('mybeiwanglu_fdy');
			}
			else{
				alert("fail!");
				toContentPage('mybeiwanglu_fdy');
			}
		},
		error : function() {
			alert("异常！");
			toContentPage('mybeiwanglu_fdy');
		}
	});
	
}
</script>
