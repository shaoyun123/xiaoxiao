<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.table th {
	text-align: center;
}

.table td {
	text-align: center;
}
</style>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>宿舍列表
		</h2>
		<div class="box-icon">
			<a href="JavaScript:void(0);"
				onclick="toContentPage('addxueshengsushe')"><i title="添加宿舍"
				class="icon-plus"></i></a>
		</div>
	</div>
	<div class="box-content">
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr>
					<th>所属校区</th>
					<th>所属宿舍楼</th>
					<th>门牌号</th>
					<th>修改</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${xueshengsushe}" var="xueshengsushe">
					<tr id="${xueshengsushe.suSheId}">
						<td>${xiaoqu.mingcheng}</td>
						<td>${sushelou.mingCheng}</td>
						<td>${xueshengsushe.menPaiHao}</td>

						<td>
							<%--                             						<a href="modifyxueshengsushe?id=${xueshengsushe.suSheId}" ><input type="button" class="btn btn-warning" value="修改" onclick="return modifyxueshengsushe()"></a> --%>
							<button type="button"
								onclick="toContentPage('modifyxueshengsushe?id=${xueshengsushe.suSheId}')"
								class="btn btn-warning">修改</button>

						</td>

						<td><a href="javascript:void(0);"><input
								type="button" class="btn btn-danger" value="删除"
								onclick="deletexueshengsushe(${xueshengsushe.suSheId})"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination pagination-centered">
			<ul class="pagination">
				<c:if test="${page >1}">
					<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chakansushe?sushelouid=${sushelou.suSheLouId}')">首页</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li><a
						href="JavaScript:void(0);"
							onclick="toContentPage('chakansushe?sushelouid=${sushelou.suSheLouId}&page=${page-1}')">上一页</a></li>
				</c:if>
				<li><a href="#">第${page}页</a></li>
				<c:if test="${page < pages}">
					<li><a
						href="JavaScript:void(0);"
							onclick="toContentPage('chakansushe?sushelouid=${sushelou.suSheLouId}&page=${page+1}')">下一页</a></li>
				</c:if>
				<c:if test="${page < pages}">
					<li><a
						href="JavaScript:void(0);"
							onclick="toContentPage('chakansushe?sushelouid=${sushelou.suSheLouId}&page=${pages}')">尾页</a></li>
				</c:if>
			</ul>
		</div>

	</div>
</div>
<script type="text/javascript">
	function modifyxueshengsushe() {
		if (confirm("修改宿舍影响会很严重，确认修改！")) {
			return true;
		} else {
			return false;
		}
	}
	function deletexueshengsushe(id) {
		if (confirm("删除宿舍影响会很严重！确认删除吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'deletexueshengsushe',
				data : {
					id : id,
				},
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data == "success") {
						alert("删除成功！");
					}else {
						alert("删除失败");
					}
					toContentPage('chakansushe?sushelouid=${sushelou.suSheLouId}');
				},
				error : function() {
					alert("该宿舍被其他内容引用，无法删除!")
				}
			});
		}
	}
</script>
