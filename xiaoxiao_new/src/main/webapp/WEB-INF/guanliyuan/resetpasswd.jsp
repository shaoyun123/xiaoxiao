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
				<i class="icon-align-justify"></i><span class="break"></span>重置密码
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
					<th>修改</th>
					<th>删除</th>
					<th>重置密码</th>

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

						<td>
							<button type="button"
								onclick="toContentPage('modifyxuesheng?id=${xuesheng.xueshengid}')"
								class="btn btn-warning">修改</button>

						</td>
						<td><input type="button" class="btn btn-danger" value="删除"
							onclick="return delxuesheng('${xuesheng.xueshengid}',${banji.banjiid})">
						</td>

						<td><input type="button" class="btn btn-default" value="重置密码"
							onclick="return updatepasswd('${xuesheng.xueshengid}','${xuesheng.xuehao}','${xuesheng.xuexiaoXuehao}')">
						</td>
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
			toContentPage('searchxuesheng?xuehao=' + xh);
		}
		function updatepasswd(xueshengid,xuehao,xuexiaoxuehao){
			$.ajax({
				type : "POST",
				url : 'updatepassword',
				data : {"xueshengid": xueshengid,"xuehao":xuehao,"xuexiaoxuehao":xuexiaoxuehao},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
				if (data == "success") {
					alert("密码重置成功!");
					toContentPage('searchxuesheng');
					} else {
						alert("密码重置失败!");
					}
				},
				error : function() {
					alert("fail");
				}
			});
		}
		
	</script>