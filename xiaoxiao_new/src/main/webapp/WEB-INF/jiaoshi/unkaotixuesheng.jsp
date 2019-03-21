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
				<i class="icon-align-justify"></i><span class="break"></span>未选题学生
			</h2>
<%-- 			<c:if test = "${not empty xueshengs}"> --%>
<!-- 			<div class="box-icon"> -->
<%-- 				<a href="javascript:toContentPage('addxiaozu?id=${kechengid}')"><i --%>
<!-- 					title="添加小组" class="icon-plus"></i></a> -->
<!-- 			</div> -->
<%-- 			</c:if> --%>
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
							<th>班级</th>
							<th>学号</th>
							<th>姓名</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${xueshengs}" var="unfenzu">
							<tr>
								<td class="center">${unfenzu.banjimingcheng}</td>
								<td class="center">${unfenzu.xuehao}</td>
								<td class="center">${unfenzu.xingming}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination pagination-centered">
			<ul>
				<c:if test="${page >1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('unkaotixuesheng?shixiid=${kechengid}')">首页</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('unkaotixuesheng?shixiid=${kechengid}&page=${page-1}')">上一页</a></li>
				</c:if>
				<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
				</li>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('unkaotixuesheng?shixiid=${kechengid}&page=${page+1}')">下一页</a></li>
				</c:if>
				<c:if test="${page < pages}">
					<li><a href="JavaScript:void(0);"
						onclick="toContentPage('unkaotixuesheng?shixiid=${kechengid}&page=${pages}')">尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
</script>