<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>社团创建详情
		</h2>
	</div>
	<div class="box-content">
		<c:set value="${chuangjian }" var="chuangjian" />
		<div class="card">
			<div class="card-header">
				<div class="card-title">
					<div class="title">
						<strong>类别： <c:if test="${chuangjian.leixing==false }">社团</c:if>
							<c:if test="${chuangjian.leixing==true }">学生组织</c:if>
							&emsp;名称：${chuangjian.mingcheng}
							&emsp;创建人:${chuangjian.chuangjianrenxingming }
							&emsp;指导教师:${chuangjian.zhidaojiaoshi }
						</strong>
					</div>
				</div>
			</div>
			<div class="card-body">
				<div class="text-indent">
					<h3>介绍：${chuangjian.jieshao }</h3>
<!-- 					<div class="text-indent" style="width: 80%"> -->
<%-- 						<font size="4px">${chuangjian.jieshao }</font> --%>
<!-- 					</div> -->
				</div>
				<div class="text-indent">
					<h3>创建理由：${chuangjian.chuangjianliyou }</h3>
<!-- 					<div class="text-indent" style="width: 80%"> -->
<%-- 						<font size="4px">${chuangjian.chuangjianliyou }</font> --%>
<!-- 					</div> -->
				</div>
			</div>
			<c:if
				test="${chuangjian.zhuangtai==1  or (chuangjian.leixing==false and chuangjian.zhuangtai==0)}">
				<input type="button" style="margin-left: 30px;"
					class="flip-link btn btn-info" value="同意"
					onclick="tongyi(${chuangjian.chuangjianid })">
				<input type="button" style="margin-left: 30px;"
					class="flip-link btn btn-info" value="拒绝"
					onclick="jujue(${chuangjian.chuangjianid })">
			</c:if>
		</div>
		<!-- 		<button type="button" name="back" onclick="goback()" -->
		<!-- 			class="btn btn-default">返回上一页</button> -->
	</div>
</div>
<script type="text/javascript">
	function tongyi(id) {
		$.ajax({
			type: "POST",
			url: 'sqty_gly',
	    	data:{CODE:id},
			dataType:'text',
			cache:false,
			timeout: 5000,
			async:true, 
			success:function(data)
			{
				if(data=="success"){
					alert("成功！")
				}
				else{
					alert("fail")
				}
				toContentPage('stcjsq');
			},
			error:function(e)
			{
				alert(e)
			}
			
		});
	}
	function jujue(id) {
		$.ajax({
			type: "POST",
			url: 'sqjj_gly',
	    	data:{CODE:id},
			dataType:'text',
			cache:false,
			timeout: 5000,
			async:true, 
			success:function(data)
			{
				if(data=="success"){
					alert("成功！")
				}
				else{
					alert("fail")
				}
				toContentPage('stcjsq');
			},
			error:function()
			{
				alert("超时!")
			}
		});
	}
	function goback(){
		window.location.href="stcjsq";
		return false;
	}
</script>