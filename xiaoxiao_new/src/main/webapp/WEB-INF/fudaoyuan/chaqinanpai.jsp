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
				<i class="icon-align-justify"></i><span class="break"></span>查寝结果
			</h2>
		</div>
		<div class="box-content">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr>
						<th>宿舍</th>
						<th>未到学生</th>
						<th>请假学生</th>
						<th>上传状态</th>
						<th>是否审核</th>
						<th>查看</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${zhanshis}" var="jieguo">
						<tr>
							<td>${jieguo.sushemingcheng }</td>
							<td><c:forEach items="${jieguo.weidaoxuesheng }"
									var="xuesheng">
									<span title="${xuesheng.dianhua }">${xuesheng.xingming }&emsp;</span>
								</c:forEach></td>

							<td><c:forEach items="${jieguo.qingjiaxuesheng }"
									var="xuesheng">
									<span title="${xuesheng.dianhua }">${xuesheng.xingming }&emsp;</span>
								</c:forEach></td>

							<td><c:if test="${jieguo.zhuangtai ==1}">
									<i class="icon-ok" style="color: green;"></i>
									<!-- 									<font color="green">√</font> -->
								</c:if> <c:if test="${jieguo.zhuangtai ==0}">
									<i class="icon-remove" style="color: red;"></i>
									<!-- 									<font color="red">X</font> -->
								</c:if></td>
							<td><c:if test="${jieguo.shifoushenhe ==1}">
									<i class="icon-ok" style="color: green;"></i>
									<!-- 									<font color="green">√</font> -->
								</c:if> <c:if test="${jieguo.shifoushenhe ==0}">
									<i class="icon-remove" style="color: red;"></i>
									<!-- 									<font color="red">X</font> -->
								</c:if></td>
							<td><c:if test="${jieguo.zhuangtai ==1}">
									<a onclick="toContentPage('chaqinjilu?id=${jieguo.jieguoid}')">
										<button class="btn btn-success">查看记录</button>
									</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaqinanpai?id=${anpaiid}')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaqinanpai?id=${anpaiid}&page=${page-1}')">上一页</a></li>
					</c:if>
					<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
					</li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaqinanpai?id=${anpaiid}&page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('chaqinanpai?id=${anpaiid}&page=${pages}')">尾页</a></li>
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