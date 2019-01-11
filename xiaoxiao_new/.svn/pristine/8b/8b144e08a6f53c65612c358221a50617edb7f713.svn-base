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
				<i class="icon-align-justify"></i><span class="break"></span>校区
			</h2>
			<div class="box-icon">
				<a href="JavaScript:void(0);" onclick="toContentPage('addxiaoqu')"><i
					title="添加校区" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr style="background-color: #ffffff;">
						<th style="text-align: center;">校区名称</th>
						<th style="text-align: center;">校区状态</th>
						<th style="text-align: center;">查看</th>
						<th style="text-align: center;">修改</th>
						<th style="text-align: center;">删除</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${xiaoqu}" var="xiaoqu">
						<tr id="${xiaoqu.xiaoquid}">
							<td style="text-align: center;">${xiaoqu.mingcheng}</td>
							<%--                             				<c:set var="xiaoquzhuangtai" scope="session" value="${xiaoqu.zhuangtai }"/> --%>
							<c:if test="${xiaoqu.zhuangtai==1 }">
								<td style="text-align: center;">已启用</td>
							</c:if>
							<c:if test="${xiaoqu.zhuangtai==0 }">
								<td style="text-align: center;">已停用</td>
							</c:if>
							<td style="text-align: center;">
								<%--                             						<a href="chakanjiaoxuelou?xiaoquid=${xiaoqu.xiaoquid}" ><input type="button" class="btn btn-success" value="查看"></a> --%>
								<button type="button" class="btn btn-success" value="查看"
									onclick="toContentPage('chakanjiaoxuelou?xiaoquid=${xiaoqu.xiaoquid}')">查看</button>
							</td>
							<td style="text-align: center;">
								<%--                             						<a href="modifyxiaoqu?id=${xiaoqu.xiaoquid}" onclick="return modifyxiaoqu()"><input type="button" class="btn btn-warning" value="修改"></a> --%>
								<button type="button" class="btn btn-warning" value="修改"
									onclick="toContentPage('modifyxiaoqu?id=${xiaoqu.xiaoquid}')">修改</button>

							</td>
							<td style="text-align: center;"><a
								href="javascript:void(0);"
								onclick="deletexiaoqu(${xiaoqu.xiaoquid})"><input
									type="button" class="btn btn-danger" value="删除"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul class="pagination">
					<c:if test="${page >1}">
						<li><a href="javascript:void(0)"
							onclick="toContentPage('xiaoquliebiao')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="javascript:void(0)"
							onclick="toContentPage('xiaoquliebiao?page=${page-1}')">上一页</a></li>
					</c:if>
					<li><a href="#">第${page}页</a></li>
					<c:if test="${page < pages}">
						<li><a href="javascript:void(0)"
							onclick="toContentPage('xiaoquliebiao?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="javascript:void(0)"
							onclick="toContentPage('xiaoquliebiao?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>

		</div>
	</div>
</div>
<script type="text/javascript">
	function modifyxiaoqu() {
		if (confirm("修改校区影响会很严重！确认修改吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
	function deletexiaoqu(id) {
		if (confirm("删除校区影响会很严重！确认删除吗？") == true) {
			$.ajax({
				type : "POST",
				url : 'deletexiaoqu',
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
					} else if(data == "jiaoxuelou"){
						alert("请先删除该校区下的所有教学楼!");
					}else {
						alert("删除失败");
					}
					toContentPage('xiaoquliebiao');
				},
				error : function() {
					alert("无法删除校区，其他地方正在使用!")
				}
			});
		}
	}
</script>