<%@ page contentType="text/html; charset=UTF-8"%>
<%
	response.setContentType("application/vnd.ms-excel;charset=GBK");
%>
<%@ page
	import="java.util.Date,java.text.SimpleDateFormat,java.lang.String"%>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String name = "" + sdf.format(new Date()) + ".xls";
	response.setHeader("Content-disposition", "attachment; filename=" + name);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title></title>
</head>
<body>
	<table border="1">
		<tr>
			<th colspan="${num+4 }">${mingchengs }</th>
		</tr>
		<tr>
			<th rowspan="3" align="left nowrap">班级</th>
			<th rowspan="3" align="center nowrap">学号</th>
			<th rowspan="3" align="center nowrap">姓名</th>
			<c:forEach items="${fangAns }" var="fangAns" varStatus="go">
				<c:set value="${fn:length(fangAns.childList) }" var="i"></c:set>
				<c:set value="0" var="m"></c:set>
				<c:forEach items="${fangAns.childList }" var="childList">
					<c:if test="${not empty childList.childList }">
						<c:set value="${fn:length(childList.childList)-1 }" var="k"></c:set>
						<c:set value="${ m+k }" var="m"></c:set>
						<c:remove var="k" />
					</c:if>
				</c:forEach>
				<th colspan="${i+m }">${fangAns.mingcheng }(${fangAns.xuefen }学分)
					满分${fangAns.manfen }分</th>
				<c:remove var="i" />
				<th rowspan="3" align="left nowrap" >总计</th>
			</c:forEach>
				<th rowspan="3" align="left nowrap" style="color:red;">总成绩</th>
		</tr>
		<tr>
			<c:forEach items="${fangAns }" var="fangAns" varStatus="go">
				<c:forEach items="${fangAns.childList }" var="childList">
					<c:choose>
						<c:when test="${not empty childList.childList }">
							<c:set value="${fn:length(childList.childList) }" var="j"></c:set>
							<th colspan="${j }">${childList.mingcheng }（满分${childList.manfen }分）</th>
							<c:remove var="j" />
						</c:when>
						<c:otherwise>
							<th rowspan="2">${childList.mingcheng }（满分${childList.manfen }分）</th>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:forEach>
		</tr>

		<tr>
			<c:forEach items="${fangAns }" var="fangAns" varStatus="go">
				<c:forEach items="${fangAns.childList }" var="childList">
					<c:choose>
						<c:when test="${not empty childList.childList }">
							<c:forEach items="${ childList.childList }" var="child">
								<th>${child.mingcheng }</th>
							</c:forEach>
						</c:when>
					</c:choose>
				</c:forEach>
			</c:forEach>
		</tr>
		<c:forEach items="${xueShengDeYus}" var="item" varStatus="status">
			<tr>
				<td style="word-break: break-all" align="center">${mingcheng}</td>
				<td style="word-break: break-all" align="center">${item.xuehao}</td>
				<td style="word-break: break-all" align="center">${item.xingming}</td>
				<c:choose>
					<c:when test="${not empty item.fenshu }">
						<c:forEach items="${item.fenshu}" var="fenshu">
							<td style="word-break: break-all" align="center">${fenshu}</td>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach end="${num }" begin="1" step="1">
							<td style="word-break: break-all" align="center">0</td>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty item.deyufen }">
						<td style="text-align: center;color:red;" ><strong>${item.deyufen}</strong></td>
					</c:when>
					<c:otherwise>
						<td style="text-align: center;color:red;" ><strong>0.00</strong></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
