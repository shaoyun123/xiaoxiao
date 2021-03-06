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
				<i class="icon-align-justify"></i><span class="break"></span>请假待处理
			</h2>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>申请人学号</th>
						<th>申请人姓名</th>
						<th>申请人班级</th>
						<th>请假类别</th>
						<th>状态</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>请假天数</th>
						<th>申请时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${jiatiao}" var="qingjiatiao">
						<tr>
							<td>${qingjiatiao.xuehao}</td>
							<td>${qingjiatiao.xueshengxingming}</td>
							<td>${qingjiatiao.banjimingcheng}</td>
							<c:choose>
								<c:when test="${qingjiatiao.qingjialeibie==1}">
									<td>事假</td>
								</c:when>
								<c:when test="${qingjiatiao.qingjialeibie==2}">
									<td>病假</td>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${qingjiatiao.zhuangtai==1}">
									<td>待处理</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai==2}">
									<td>已通过</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai==3}">
									<td>已驳回</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai==4}">
									<td>待销假</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai==5}">
									<td>已销假</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai==6}">
									<td>已上报</td>
								</c:when>
								<c:when test="${qingjiatiao.zhuangtai==7}">
									<td>已上报</td>
								</c:when>
							</c:choose>
							<td>${qingjiatiao.qingjiakaishishijian}</td>
							<td>${qingjiatiao.qingjiajieshushijian}</td>
							<td>${qingjiatiao.tianshu}</td>
							<td>${qingjiatiao.shenqingshijian}</td>
							<td>
								<button
									onclick="toContentPage('jiaTiaoDetail?id=${qingjiatiao.qingjiaid}')"
									type="button" class="btn btn-success">详情</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('qingjiadaichuli_fdy')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('qingjiadaichuli_fdy?page=${page-1}')">上一页</a></li>
					</c:if>
					<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
					</li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('qingjiadaichuli_fdy?page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('qingjiadaichuli_fdy?page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->