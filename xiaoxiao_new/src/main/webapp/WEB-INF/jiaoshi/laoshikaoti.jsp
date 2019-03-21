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
				<i class="icon-align-justify"></i><span class="break"></span>已选考题
			</h2>
			<!-- 			<div class="box-icon"> -->
			<!-- 				<a -->
			<%-- 					href="javascript:toContentPage('laoshixuanti?shixiid=${shixiid}')"><i --%>
			<!-- 					title="添加实践题" class="icon-plus"></i></a> -->
			<!-- 			</div> -->
		</div>
		<div class="box-content">
			<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper"
				role="grid">
				<input type="hidden" name="shixiid" id="shixiid" value=${shixiid } />
				<table
					class="table table-striped  table-bordered  bootstrap-datatable datatable dataTable"
					id="DataTables_Table_0_filter"
					aria-describedby="DataTables_Table_0_info">
					<thead>
						<tr>
							<th>考题名称</th>
							<th>容量</th>
							<th>是否审核</th>
							<th>操作</th>
							<th>修改</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${kaotis}" var="kaoti">
							<tr>
								<td class="center">${kaoti.fabumingcheng}</td>
								<td class="center">${kaoti.rongLiang}</td>
								<c:if test="${kaoti.shiFouShenHe==0}">
									<td class="center" style="color: red;">未审核</td>
								</c:if>
								<c:if test="${kaoti.shiFouShenHe==1}">
									<td class="center" style="color: green;">审核通过</td>
								</c:if>
								<c:if test="${kaoti.shiFouShenHe==2}">
									<td class="center" style="color: red;">驳回</td>
								</c:if>
								<c:if test="${kaoti.shiFouShenHe==3}">
									<td class="center" style="color: red;">发布</td>
								</c:if>
								<c:if test="${kaoti.shiFouShenHe==4}">
									<td class="center" style="color: red;">互选完成</td>
								</c:if>

								<td>
								<c:if test="${kaoti.shiFouShenHe<3}">
										<p style="color:red;">暂无</p>
									</c:if>
								<c:if test="${kaoti.shiFouShenHe==3}">
										<button
											onclick="toContentPage('laoshixuanxuesheng?id=${kaoti.kaotiid}')"
											type="button" class="btn btn-success">老师选学生</button>
									</c:if>
								<c:if test="${kaoti.shiFouShenHe==4}">
										<button onclick="toContentPage('detail?id=${kaoti.kaotiid}')"
											type="button" class="btn btn-success">详情</button>
									</c:if></td>
								<td><c:if test="${kaoti.shiFouShenHe<3}">
										<button
											onclick="toContentPage('updateshijianti?id=${kaoti.kaotiid}')"
											type="button" class="btn btn-warning">修改</button>
									</c:if></td>

								<td><c:if test="${kaoti.shiFouShenHe<4}">
										<button onclick="delshijianti(${kaoti.kaotiid})" type="button"
											class="btn btn-danger">删除</button>
									</c:if></td>
							</tr>
						</c:forEach>
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
	function delshijianti(id) {
		var shixiid = $("#shixiid").val();
		if (cfm()) {
			$.ajax({
				type : "POST",
				url : 'delshijianti',
				data : {
					"kaotiid" : id,"shixiid":shixiid
				},
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data.status == "success") {
						alert("success");
						toContentPage("laoshikaoti?shixiid="+shixiid);
					} 
					if (data.status == "limited") {
						alert("暂不能删除!");
						toContentPage("laoshikaoti?shixiid="+shixiid);
					}
					if (data.status == "fail") {
						alert("fail!");
						toContentPage("laoshikatoti?shixiid="+shixiid);
					}
					if (data.status == "error") {
						alert("系统异常!");
						toContentPage("laoshikatoti?shixiid="+shixiid);
					}
				},
				error : function() {
					alert("error");
					toContentPage("kaotilist?shixiid="+shixiid);
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