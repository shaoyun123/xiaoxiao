<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table td {
	text-align: center;
}

.table th {
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>教室列表
			</h2>
			<div class="box-icon">
			<a href="JavaScript:void(0);"
				onclick="toContentPage('addjiaoshi')"><i title="添加教室"
				class="icon-plus"></i></a>
		</div>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>教室名称</th>
						<th>所属教学楼</th>
						<th>所属校区</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${jiaoshi}" var="jiaoshi">
						<tr id="${jiaoshi.jiaoshiid}">
							<td>${jiaoshi.jiaoshiming}</td>
							<td>${jiaoxuelou.jiaoXueLouMing}</td>
							<td>${xiaoqu.mingcheng}</td>
							<td><a href="javascript:void(0);"
								onclick="toContentPage('modifyjiaoshi?id=${jiaoshi.jiaoshiid}')"><input type="button"
									class="btn btn-warning" value="修改"></a></td>
							<td><a href="javascript:void(0);"
								onclick="deletejiaoshi(${jiaoshi.jiaoshiid})"><input type="button"
									class="btn btn-danger" value="删除"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul class="pagination">
					<c:if test="${page >1}">
						<li><a
							href="javascript:void(0)" onclick="toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou.jiaoXueLouId}')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a
							href="javascript:void(0)" onclick="toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou.jiaoXueLouId}&page=${page-1}')">上一页</a></li>
					</c:if>
					<li><a href="#">第${page}页</a></li>
					<c:if test="${page < pages}">
						<li><a
							href="javascript:void(0)" onclick="toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou.jiaoXueLouId}&page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a
							href="javascript:void(0)" onclick="toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou.jiaoXueLouId}&page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>

		</div>
	</div>
</div>



<script type="text/javascript">
	function modifyjiaoshi() {
		if (confirm("修改教室影响会很严重！确认修改吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
	function deletejiaoshi(id) {
		if (confirm("删除教室影响会很严重！确认删除吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'deletejiaoshi',
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
					toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou.jiaoXueLouId}');
				},
				error : function() {
					alert("超时!")
				}
			});
		}
	}
</script>
