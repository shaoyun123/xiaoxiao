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
<!-- Main Content -->
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>查看节次方案
		</h2>
	</div>
	<div class="box-content">


		<div class="sub-title" style="width: 60%">
			<span style="font-weight: bold">方案名称：</span>&emsp; <span>${fangan.mingcheng}</span>
			<%--                                     		<a style="margin-right:10px;" class="pull-right" href="xiugaijiecifangan?id=${fangan.id}"> --%>
			<!--                                     			<input type="button" value="修改" style="width:100px;"/> -->
			<!--                                     		</a> -->
			<%--               		<button style="float: right;" type="button" class="btn btn-warning" value="修改" onclick="toContentPage('xiugaijiecifangan?id=${fangan.id}')">修改</button>                            					 --%>

		</div>
		<div id="set-time">
			<table
				class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<thead>
					<tr style="background-color: #DEDEDE;">
						<th>节次</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>时间段</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${jcsj}" var="jcsj">
						<tr id="${jcsj.jieci}" style="background-color: white">
							<td>${jcsj.jieci}</td>
							<td>${jcsj.kaishishijian}</td>
							<td>${jcsj.jieshushijian}</td>
							<td><c:if test="${jcsj.shijianduan==1}">上午</c:if> <c:if
									test="${jcsj.shijianduan==2}">下午</c:if> <c:if
									test="${jcsj.shijianduan==3}">晚上</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
