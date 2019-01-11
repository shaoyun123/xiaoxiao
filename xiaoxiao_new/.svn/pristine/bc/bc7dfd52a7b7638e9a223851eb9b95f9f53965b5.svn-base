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
				<i class="icon-align-justify"></i><span class="break"></span>事件接收人
			</h2>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>班级</th>
						<th>学号</th>
						<th>姓名</th>
						<th>回执结果</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${xuesheng}" var="xuesheng">
						<tr>
							<td>${xuesheng.banji}</td>
							<td>${xuesheng.xuehao}</td>
							<td>${xuesheng.xingming}</td>
							<c:if test="${shijian.huizhi==1}">
								<td><c:if test="${xuesheng.zhuangtai==0}">
										<span style="color: black">未答复</span>
									</c:if> <c:if test="${xuesheng.zhuangtai==1}">
										<span style="color: green">已完成</span>
									</c:if> <c:if test="${xuesheng.zhuangtai==2}">
										<span style="color: red">未完成</span>
									</c:if></td>
							</c:if>
							<c:if test="${shijian.huizhi==0}">
								<td>——</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 			<div class="pagination pagination-centered"> -->
			<!-- 				<ul> -->
			<%-- 					<c:if test="${page >1}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<!-- 							onclick="toContentPage('chaqin')">首页</a></li> -->
			<%-- 					</c:if> --%>
			<%-- 					<c:if test="${page > 1}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaqin?page=${page-1}')">上一页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
			<!-- 					</li> -->
			<%-- 					<c:if test="${page < pages}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaqin?page=${page+1}')">下一页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<c:if test="${page < pages}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaqin?page=${pages}')">尾页</a></li> --%>
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
