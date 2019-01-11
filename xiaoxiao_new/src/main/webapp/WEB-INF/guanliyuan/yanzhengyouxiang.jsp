<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table th {
	text-align: center;
}

.table td {
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>验证邮箱
			</h2>
		</div>
		<div class="box-content">
			<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper"
				role="grid">
				<div class="span6">
					<div class="dataTables_filter" id="DataTables_Table_0_filter">
						<label>学号：<input type="text" id="xuehao"  value="${xuehao}"
							aria-controls="DataTables_Table_0">
							<button onclick="chaxun();" type="button" class="btn btn-success"
								style="margin-left: 10px; margin-bottom: 7px;">查询</button></label>
					</div>
				</div>
			</div>
			<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr>
					<th>学生姓名</th>
					<th>班级</th>
					<th>学号</th>
					<th>用户名</th>
					<th>职务</th>
					<th>通过验证</th>

				</tr>
			</thead>
			<tbody>
<%-- 				<c:forEach items="${xueshengs}" var="xuesheng"> --%>
					<c:if test="${not empty xuesheng}">
							
						
					<tr id="${xuesheng.xueshengid}">
						<td>${xuesheng.xingming}</td>
						<td>${banji.banjimingcheng}</td>
						<td>${xuesheng.xuehao}</td>
						<td>${xuesheng.yonghuming}</td>
						<td><c:if test="${xuesheng.isbanzhang==true}">班长</c:if></td>
						<c:choose>
						<c:when test="${not empty xuesheng.checkcodefor}">
						<td><input type="button" class="btn btn-default" value="验证邮箱"
							onclick="yanzhengyouxiang('${xuesheng.xueshengid}','${xuesheng.xuehao}')">
						</td>
						</c:when>
						<c:otherwise>
						<td style = "color:green;">已通过</td>
						</c:otherwise>
						</c:choose>
					</tr>
					</c:if>
<%-- 				</c:forEach> --%>
			</tbody>
		</table>
		</div>
	</div>
	<script type="text/javascript">
		function chaxun() {
			var xh = $("#xuehao").val();
			toContentPage('searchxueshengs?xuehao=' + xh);
		}
		function yanzhengyouxiang(xueshengid,xuehao){
			$.ajax({
				type : "POST",
				url : 'yanzhengyouxiang',
				data : {"xueshengid": xueshengid},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
				if (data.status == "success") {
					alert("验证成功!");
					} 
				if(data.status == "fail"){
						alert("验证失败!");
					}
				toContentPage('searchxueshengs?xuehao='+xuehao);
				},
				error : function() {
					alert("系统错误");
				}
			});
		}
		
	</script>