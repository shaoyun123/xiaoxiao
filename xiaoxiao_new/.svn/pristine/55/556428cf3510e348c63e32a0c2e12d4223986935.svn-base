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
				<i class="icon-align-justify"></i><span class="break"></span>查寝安排
			</h2>
			<div class="box-icon">
				<a onclick="toContentPage('addchaqin')"><i title="添加" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>日期</th>
						<th>拍照要求</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>查看记录</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${anpais}" var="anpai">
						<tr>
							<td>${anpai.riqi }</td>
							<td>${anpai.paizhaoyaoqiu }</td>
							<td>${anpai.kaishishijian }</td>
							<td>${anpai.jieshushijian }</td>
							<td><a
								onclick="toContentPage('chaqinanpai?id=${anpai.anpaiid }')"><button
										class="btn btn-success">查看记录</button></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaqin')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaqin?page=${page-1}')">上一页</a></li>
					</c:if>
					<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
					</li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaqin?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaqin?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">
	
</script>