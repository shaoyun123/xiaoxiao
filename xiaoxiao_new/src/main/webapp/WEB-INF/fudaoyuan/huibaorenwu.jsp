<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table th{
	text-align: center;
}
.table td{
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>任务列表
			</h2>
			<div class="box-icon">
				<a onclick="toContentPage('addhuibaorenwu')"><i title="添加"
					class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>名称</th>
						<th>要求</th>
						<th>发布日期</th>
						<th>截止日期</th>
						<th>查看与审核</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${fdap}" var="FDAP">
						<tr>
							<td>${FDAP.mingcheng}</td>
							<td>${FDAP.yaoqiu}</td>
							<td>${FDAP.kaishishijian}</td>
							<td>${FDAP.jiezhishijian}</td>
							<td><a onclick="toContentPage('chakanxiangqing?id=${FDAP.anpaiid}')"> <input
									type="button" class="btn btn-success" value="查看与审核" /></a></td>
							<td><a 
								onclick="toContentPage('updatehuibaorenwu?id=${FDAP.anpaiid}')"> <input type="button"
									class="btn btn-warning" value="修改" /></a></td>
							<td><a 
								onclick="del(${FDAP.anpaiid})"> <input type="button"
									class="btn btn-danger" value="删除" /></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('huibaorenwu')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('huibaorenwu?page=${page-1}')">上一页</a></li>
					</c:if>
					<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
					</li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('huibaorenwu?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('huibaorenwu?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">
	function del(id){
		if(confirm("删除会影响所有要做汇报的学生，确认要删除吗？")==true){
			$.ajax({
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "deletehuibaorenwu",//url
				data : {"id":id},
				success : function(data) {
// 					var result = eval(data);
					if (data.status == "success") {
						alert("success");
						toContentPage('huibaorenwu');
					}
					else{
						alert("fail!");
						toContentPage('huibaorenwu');
					}
				},
				error : function() {
					alert("异常！");
					toContentPage('huibaorenwu');
				}
			});
		}else{
			return false;
		}
	}
</script>
