<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Main Content -->
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>学期列表
		</h2>
		<div class="box-icon">
			<a href="JavaScript:void(0);" onclick="toContentPage('addxueqi')"><i
				title="添加学期" class="icon-plus"></i></a>
		</div>
	</div>
	<div class="box-content">
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr style="background-color: #ffffff">
					<th style="width: 20%; text-align: center;">学年</th>
					<th style="width: 20%; text-align: center;">学期名称</th>
					<th style="width: 20%; text-align: center;">开始日期</th>
					<th style="width: 20%; text-align: center;">结束日期</th>
					<th style="width: 10%; text-align: center;">修改</th>
					<!--                                     			<th style="width:10%;text-align: center;">删除</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${xueqi}" var="xueqi">
					<tr style="background-color: white">
						<td style="text-align: center;">${xueqi.xuenian}</td>
						<td style="text-align: center;"><c:choose>
								<c:when test="${ not empty xueqi.mingcheng }">
                                    						${xueqi.mingcheng }
                                    					</c:when>
								<c:otherwise>
                                    						第
	                                    					<c:if test="${xueqi.xueqi==1}">一</c:if>
									<c:if test="${xueqi.xueqi==2}">二</c:if>
									<c:if test="${xueqi.xueqi==3}">三</c:if>
                                    						学期
                                    					</c:otherwise>
							</c:choose></td>
						<td style="text-align: center;">${xueqi.kaishiriqi}</td>
						<td style="text-align: center;">${xueqi.jieshuriqi}</td>
						<td style="text-align: center;">
							<button type="button" class="btn btn-warning" value="修改"
								onclick="toContentPage('xiugaixueqi?id=${xueqi.xueqiid}')">修改</button>

							<%--                                     					<a href="xiugaixueqi?id=${xueqi.xueqiid}"><input type="button" class="btn btn-warning" value="修改"/></a> --%>
						</td>
						<!--                                     				<td style="text-align: center;"> -->
						<%--                                     					<a href="delxueqi?id=${xueqi.xueqiid}" onclick="return del()"><input type="button" class="btn btn-default" value="删除"/></a> --%>
						<!--                                     				</td> -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">
	function del() {
		if (confirm("删除可能会有严重的影响，确认删除吗？") == false) {
			return false;
		}
		return true;
	}
</script>