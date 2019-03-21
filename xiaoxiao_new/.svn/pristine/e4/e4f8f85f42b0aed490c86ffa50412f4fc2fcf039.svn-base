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
				<i class="icon-align-justify"></i><span class="break"></span>详情
			</h2>
			<div class="box-icon">
				<a href="javascript:toContentPage('addshijianti?shixiid=${shijiankeid}')"><i
					title="添加考题"
					class="icon-plus"></i></a>
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
							<th>实习</th>
							<th>查看考题</th>
							<th>老师选题</th>
							<c:if test="${fuzerenid==yonghuid}"><th>查看所有考题</th></c:if>
							<th>分组</th>
							<th>未选考题学生</th>
							<th>查看分组</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="center">${kecheng.keChengMingCheng}</td>
							<td>
								<button
									onclick="toContentPage('laoshikaoti?shixiid=${kecheng.ID}')"
									type="button" class="btn btn-success">查看已选考题</button>
							</td>
							<td>
								<button
									onclick="toContentPage('laoshixuanti?shixiid=${kecheng.ID}')"
									type="button" class="btn btn-success">老师选考题</button>
							</td>
							<c:if test="${fuzerenid==yonghuid}"><td>
								<button
									onclick="toContentPage('kaotilist?shixiid=${kecheng.ID}')"
									type="button" class="btn btn-success">查看所有考题</button>
							</td></c:if>
							<td><button
									onclick="toContentPage('fenzu?shixiid=${kecheng.ID}')"
									type="button" class="btn btn-success">分组</button></td>
									<td><button
									onclick="toContentPage('unkaotixuesheng?shixiid=${kecheng.ID}')"
									type="button" class="btn btn-success">查看未选考题学生</button></td>
									<td>
								<button
									onclick="toContentPage('getxiaozu?id=${kecheng.ID}')"
									type="button" class="btn btn-success">查看分组</button>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 				<div class="pagination pagination-centered"> -->
				<!-- 					<ul> -->
				<%-- 						<c:if test="${page >1}"> --%>
				<!-- 							<li><a href="JavaScript:void(0);" -->
				<!-- 								onclick="toContentPage('getshijianke')">首页</a></li> -->
				<%-- 						</c:if> --%>
				<%-- 						<c:if test="${page > 1}"> --%>
				<!-- 							<li><a href="JavaScript:void(0);" -->
				<%-- 								onclick="toContentPage('getshijianke?page=${page-1})">上一页</a></li> --%>
				<%-- 						</c:if> --%>
				<%-- 						<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
				<!-- 						</li> -->
				<%-- 						<c:if test="${page < pages}"> --%>
				<!-- 							<li><a href="JavaScript:void(0);" -->
				<%-- 								onclick="toContentPage('getshijianke?page=${page+1}')">下一页</a></li> --%>
				<%-- 						</c:if> --%>
				<%-- 						<c:if test="${page < pages}"> --%>
				<!-- 							<li><a href="JavaScript:void(0);" -->
				<%-- 								onclick="toContentPage('getshijianke?page=${pages}')">尾页</a></li> --%>
				<%-- 						</c:if> --%>
				<!-- 					</ul> -->
				<!-- 				</div> -->
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