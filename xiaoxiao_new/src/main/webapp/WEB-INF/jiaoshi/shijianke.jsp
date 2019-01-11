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
				<i class="icon-align-justify"></i><span class="break"></span>实践课
			</h2>
			<div class="box-icon">
				<a href="javascript:toContentPage('addshijianke')"><i
					title="添加实践课" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper"
				role="grid">

				<table
					class="table table-striped  table-bordered  bootstrap-datatable datatable dataTable"
					id="DataTables_Table_0_filter"
					aria-describedby="DataTables_Table_0_info">
					<thead>
						<tr>
							<th>实践课程</th>
							<th>小组容量</th>
							<th>查看</th>
							<th>修改</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${kechengs}" var="kecheng">
							<tr>
								<td class="center">${kecheng.keChengMingCheng}</td>
								<td class="center">${kecheng.xiaozurongliang}</td>
								<td>
									<button
										onclick="toContentPage('forward?id=${kecheng.shijiankechengid}')"
										type="button" class="btn btn-success">查看</button>
								</td>
								<td>
									<button
										onclick="toContentPage('updateshijianke?id=${kecheng.shijiankechengid}')"
										type="button" class="btn btn-warning">修改</button>
								</td>
								<td>
									<button onclick="delshijianke(${kecheng.shijiankechengid})"
										type="button" class="btn btn-danger">删除</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination pagination-centered">
					<ul>
						<c:if test="${page >1}">
							<li><a href="JavaScript:void(0);"
								onclick="toContentPage('getshijianke')">首页</a></li>
						</c:if>
						<c:if test="${page > 1}">
							<li><a href="JavaScript:void(0);"
								onclick="toContentPage('getshijianke?page=${page-1})">上一页</a></li>
						</c:if>
						<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
						</li>
						<c:if test="${page < pages}">
							<li><a href="JavaScript:void(0);"
								onclick="toContentPage('getshijianke?page=${page+1}')">下一页</a></li>
						</c:if>
						<c:if test="${page < pages}">
							<li><a href="JavaScript:void(0);"
								onclick="toContentPage('getshijianke?page=${pages}')">尾页</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function delshijianke(id) {
		if (cfm()) {
			$.ajax({
				type : "POST",
				url : 'delshijianke',
				data : {
					"CODE" : id
				},
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data.status == "success") {
						alert("success");
						toContentPage("getshijianke");
					} 
					if (data.status == "limited") {
						alert("受限!");
						toContentPage("getshijianke");
					}
					if (data.status == "fail") {
						alert("fail!");
						toContentPage("getshijianke");
					}
				},
				error : function() {
					alert("error");
					toContentPage("getshijianke");
				}
			});
		}
	}
	function cfm() {
		if (confirm("确认删除？") == true) {
			return true;
		} else {
			return false;
		}
	}
</script>