<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.controls {
	position: relative;
	top: 5px;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>介绍
			</h2>
		</div>
		<div class="box-content">
<!-- 			<div class="card-body"> -->
				<form class="form-horizontal">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="xszzmingcheng">学生组织名称:</label>
							<div class="controls">
								<p>${chuangjian.mingcheng}</p>
								<!-- 							<input style="border:none;" class="input-xlarge uneditable-input" id="xszzmingcheng" -->
								<%-- 								name="xszzmingcheng" type="text" value="${chuangjian.mingcheng}"> --%>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="chuangjianren">创建人:</label>
							<div class="controls">
								<p>${chuangjian.chuangjianrenxingming }</p>
								<!-- 							<input style="border:none;" class="input-xlarge uneditable-input" id="chuangjianren" -->
								<!-- 								name="chuangjianren" type="text" -->
								<%-- 								value="${chuangjian.chuangjianrenxingming }"> --%>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="jieshao">介绍:</label>
							<div class="controls">
								<p>${chuangjian.jieshao }</p>
								<!-- 							<input style="border:none;" class="input-xlarge uneditable-input" id="jieshao" -->
								<%-- 								name="jieshao" type="text" value="${chuangjian.jieshao }"> --%>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="chuangjianliyou">创建理由:</label>
							<div class="controls">
								<p>${chuangjian.chuangjianliyou }</p>
								<!-- 							<input style="border:none;" class="input-xlarge uneditable-input" id="chuangjianliyou" -->
								<!-- 								name="chuangjianliyou" type="text" -->
								<%-- 								value="${chuangjian.chuangjianliyou }"> --%>
							</div>
						</div>
						<div class="form-actions">
							<button type="button" class="btn btn-primary"
								onclick="tongyi(${chuangjian.chuangjianid })">同意</button>
							<button type="button" class="btn"
								onclick="jujue(${chuangjian.chuangjianid})">拒绝</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">
function tongyi(id) {
	$.ajax({
		type: "POST",
		url: 'xxzzsqty_fdy',
    	data:{CODE:id},
		dataType:'text',
		cache:false,
		timeout: 5000,
		async:true, 
		success:function(data)
		{
			if(data=="success"){
				alert("成功！")
				toContentPage('xszzcjsq');
			}
			else{
				alert("fail");
				toContentPage('xszzcjsq');
			}
		},
		error:function(e)
		{
			alert('异常');
			toContentPage('xszzcjsq');
		}
		
	});
}
function jujue(id) {
	$.ajax({
		type: "POST",
		url: 'xszzsqjj_fdy',
    	data:{CODE:id},
		dataType:'text',
		cache:false,
		timeout: 5000,
		async:true, 
		success:function(data)
		{
			if(data=="success"){
				alert("成功！")
				toContentPage('xszzcjsq');
			}
			else{
				alert("fail");
				toContentPage('xszzcjsq');
			}
		},
		error:function()
		{
			alert("超时!");
			toContentPage('xszzcjsq');
		}
	});
}
// function goback(){
// 	toContentPage('xszzcjsq');
// 	return false;
// }
</script>
