<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>学生组织创建
			</h2>
		</div>
		<div class="box-content">

			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th style="text-align:center;">创建ID</th>
						<th style="text-align:center;">名称</th>
						<th style="text-align:center;">创建人</th>
						<th style="text-align:center;">联系电话</th>
						<th style="text-align:center;">介绍</th>
						<th style="text-align:center;">状态</th>
						<th style="text-align:center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${chuangjian}" var="chuangjian">
						<tr height=65px>
							<th style="text-align:center;" scope="row">${chuangjian.chuangjianid}</th>
							<td style="text-align:center;">${chuangjian.mingcheng}</td>
							<td style="text-align:center;">${chuangjian.chuangjianrenxingming}</td>
							<td style="text-align:center;">${chuangjian.dianhua}</td>
							<td
								style="width: 20%; overflow: hidden;text-align:center;white-space: nowrap; text-overflow: ellipsis;">${chuangjian.jieshao}</td>
							<c:choose>
								<c:when test="${chuangjian.zhuangtai==0}">
									<td style="text-align:center;">待处理</td>
								</c:when>
								<c:when test="${chuangjian.zhuangtai==1}">
									<td style="text-align:center;">辅导员已确认</td>
								</c:when>
								<c:when test="${chuangjian.zhuangtai==2}">
									<td style="text-align:center;">已通过</td>
								</c:when>
								<c:when test="${chuangjian.zhuangtai==3}">
									<td style="text-align:center;">已驳回</td>
								</c:when>
							</c:choose>
							<td style="text-align:center;"><button type='button' class='btn btn-success'
									onclick="toContentPage('xszzcjDetail?id=${chuangjian.chuangjianid}')">详情</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

<!-- 			<div class="pagination pagination-centered"> -->
<!-- 				<ul> -->
<%-- 					<c:if test="${page >1}"> --%>
<!-- 						<li><a href="JavaScript:void(0);" -->
<!-- 							onclick="toContentPage('xszzcjsq')">首页</a></li> -->
<%-- 					</c:if> --%>
<%-- 					<c:if test="${page > 1}"> --%>
<!-- 						<li><a href="JavaScript:void(0);" -->
<%-- 							onclick="toContentPage('xszzcjsq?page=${page-1}')">上一页</a></li> --%>
<%-- 					</c:if> --%>
<%-- 					<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
<!-- 					</li> -->
<%-- 					<c:if test="${page < pages}"> --%>
<!-- 						<li><a href="JavaScript:void(0);" -->
<%-- 							onclick="toContentPage('xszzcjsq?page=${page+1}')">下一页</a></li> --%>
<%-- 					</c:if> --%>
<%-- 					<c:if test="${page < pages}"> --%>
<!-- 						<li><a href="JavaScript:void(0);" -->
<%-- 							onclick="toContentPage('xszzcjsq?page=${pages}')">尾页</a></li> --%>
<%-- 					</c:if> --%>
<!-- 				</ul> -->
<!-- 			</div> -->
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">
	
</script>















