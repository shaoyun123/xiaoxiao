<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- Main Content -->
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>德育成绩
		</h2>
	</div>
	<div class="box-content">
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">

			<tr>
				<td style="text-align: center; center; width: 5%;"><strong>学年</strong></td>
				<td style="text-align: center; center; width: 5%;"><strong>学期</strong></td>
				<td style="text-align: center; center; width: 5%;"><strong>德育分方案名称</strong></td>
				<%-- 													<c:set value="${fn:length(fangAn)+2}" var="num"/> --%>
				<%-- 													<c:forEach items="${map.fangAn}" var="fangAn"> --%>
				<%-- 														<th style="text-align: center;width:${100/num}%;height:80px;"><strong>${fangAn.mingcheng }(${fangAn.manfen })(${fangAn.xuefen})</strong></th> --%>
				<%-- 													</c:forEach> --%>
				<td style="text-align: center; width: 5%;"><font
					style="size: 15px; font-weight: bold;">德育分</font></td>
				<td style="text-align: center; center; width: 5%;" colspan="4"><strong>详情</strong></td>
			</tr>

			<c:choose>
				<c:when test="${not empty xinxiList }">
					<c:forEach items="${xinxiList }" var="map">
						<tr style="text-align: center; height: 50px;">
							<td style="text-align: center;"><strong>${map.xuenian}</strong></td>
							<td style="text-align: center;"><strong>${map.xueqi}</strong></td>
							<td style="text-align: center;"><strong>${map.fanganmingcheng}</strong></td>

							<td style="text-align: center; width: 40px;"><strong>${map.deyufen}</strong>
							</td>
							<c:choose>
								<c:when test="${map.deyufenid > 0}">
									<td><a target="_blank"
										href="deYuDetail?id=${map.deyufenid}"><input type="button"
											class="btn btn-default" value="详情"></a></td>
									<td><c:if test="${map.zhuangtai==1}">
											<a href="xiuGaiDeYu?id=${map.deyufenid}"><input
												type="button" class="btn btn-default" value="修改"></a>
										</c:if></td>
								</c:when>
								<c:otherwise>
									<td colspan="4">
									<input type="button"
										class="btn btn-default" value="本学期无德育成绩" style="hidden: none;">
										</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="8"
							style="text-align: center; color: red; height: 100px;">该学生还未填写过德育成绩</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
</div>

<script type="text/javascript">
	function searchResult() {
		var xn = document.getElementById("xuenian");
		var xq = document.getElementById("xueqi");
		var xuenian = document.getElementById("xuenian").value;
		var xueqi = document.getElementById("xueqi").value;
		if (xuenian == "") {
			alert("请选择要查询的学年");
			return false;
		} else if (xueqi == "") {
			alert("请选择要查询的学期");
			return false;
		} else {
			return true;
		}
	}
</script>
