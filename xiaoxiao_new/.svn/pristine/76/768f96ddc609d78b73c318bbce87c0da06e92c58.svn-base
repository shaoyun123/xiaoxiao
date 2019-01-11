<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>德育详情
			</h2>
		</div>
		<div class="box-content">
			<table class="table table-bordered table-striped table-condensed bootstrap-datatable ">
				<tr>
					<th style="font-size: 25px; width: 40%; text-align: center;"><font
						color=red>学号：${xuehao}</font></th>
					<th style="font-size: 25px; width: 40%; text-align: center;"><font
						color=red>姓名：${xueshengxingming}</font></th>
				</tr>
				<c:set value="${fenshu}" var="fenshu" />
				<c:set value="0" var="num" />
				<c:forEach items="${tianxietiaomu}" var="tiaomu">

					<c:set value="${tiaomu.childList}" var="ChildList" />
					<tr>
						<th
							style="font-size: 20px; padding-left: 0em; width: 30%; text-align: left;">${tiaomu.mingcheng}(${tiaomu.manfen})</th>
						<td></td>
					</tr>
					<c:forEach items="${ChildList}" var="childlist">
						<c:set value="${childlist.childList}" var="List" />
						<tr>
							<td
								style="font-size: 15px; width: 30%; padding-left: 2em; text-align: left;">${childlist.mingcheng}(${childlist.manfen})</td>
							<c:choose>
								<c:when test="${childlist.xiangleixing == 0 }">
									<td
										style="font-size: 15px; width: 30%; height: 30px; text-align: center;"><strong>${fenshu[num]}</strong></td>
									<c:set value="${num+1 }" var="num" />
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>

						</tr>
						<c:forEach items="${List}" var="list">
							<tr>
								<td
									style="font-size: 12px; padding-left: 4em; text-align: left;">${list.mingcheng}(${list.manfen})</td>
								<c:choose>
									<c:when test="${list.xiangleixing == 0 }">
										<td
											style="font-size: 15px; width: 30%; height: 30px; text-align: center;"><strong>${fenshu[num]}</strong></td>
										<c:set value="${num+1 }" var="num" />
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>

							</tr>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
