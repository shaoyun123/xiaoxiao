<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>学生组织
			</h2>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>创建ID</th>
						<th>名称</th>
						<th>类型</th>
						<th>创建人</th>
						<th>联系电话</th>
						<th>介绍</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${chuangjian}" var="chuangjian">
						<tr>
							<th scope="row">${chuangjian.chuangjianid}</th>
							<td>${chuangjian.mingcheng}</td>
							<c:choose>
								<c:when test="${chuangjian.leixing==false}">
									<td>社团</td>
								</c:when>
								<c:when test="${chuangjian.leixing==true}">
									<td>学生组织</td>
								</c:when>
							</c:choose>
							<td>${chuangjian.chuangjianrenxingming}</td>
							<td>${chuangjian.dianhua}</td>
							<td
								style="width: 20%; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${chuangjian.jieshao}</td>
							<c:choose>
								<c:when test="${chuangjian.zhuangtai==0}">
									<td>待处理</td>
								</c:when>
								<c:when test="${chuangjian.zhuangtai==1}">
									<td>辅导员已确认</td>
								</c:when>
								<c:when test="${chuangjian.zhuangtai==2}">
									<td>已通过</td>
								</c:when>
								<c:when test="${chuangjian.zhuangtai==3}">
									<td>已驳回</td>
								</c:when>
							</c:choose>
							<td>
								<%-- 													<a href="stcjDetail?id=${chuangjian.chuangjianid}"><input --%>
								<!-- 															type="button" class="btn btn-success" value="详情"></a> -->

								<button type="button" class="btn btn-success" value="查看详情"
									onclick="toContentPage('stcjDetail?id=${chuangjian.chuangjianid}')">查看详情</button>



							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul class="pagination">
					<c:if test="${page >1}">
						<li><a href="javascript:void(0);" onclick="toContentPage('stcjsq')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="javascript:void(0);" onclick="toContentPage('stcjsq?page=${page-1}')">上一页</a></li>
					</c:if>
					<li><a href="#">第${page}页</a></li>
					<c:if test="${page < pages}">
						<li><a href="javascript:void(0);" onclick="toContentPage('stcjsq?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="javascript:void(0);" onclick="toContentPage('stcjsq?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>

		</div>
	</div>