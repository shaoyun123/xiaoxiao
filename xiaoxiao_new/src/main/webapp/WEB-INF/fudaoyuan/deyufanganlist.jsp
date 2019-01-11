<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>德育成绩列表
			</h2>
		</div>
		<div class="box-content">
			<c:choose>
				<c:when test="${not empty list}">
					<table class="table table-bordered table-striped table-condensed bootstrap-datatable"
						style="margin-left: 30px; margin-top: 20px;">
						<tr>
							<th style="text-align: center;"><font
								style="color: red; size: 15px; font-weight: bold;">发布人名称</font></th>
							<th style="text-align: center;"><font
								style="color: red; size: 15px; font-weight: bold;">德育方案名称</font></th>
							<th style="text-align: center; "><strong>详情</strong></th>
						</tr>
						<c:forEach items="${list}" var="deyu">
							<tr style="text-align: center; height: 40px;">
								<td style="text-align: center;"><strong>${deyu.jiaoshixingming}</strong></td>
								<td style="text-align: center;"><strong>${deyu.fanganmingcheng}</strong></td>
								<td style="text-align: center;">
										<button class="btn btn-success" value="详情" onclick="toContentPage('deyuchengji_fdy?fanganid=${deyu.fanganid}')">详情</button></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<span>该学校还未使用德育成绩方案</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<script type="text/javascript">
	function searchResult() {
		var xuenian = document.getElementById("xuenian").value;
		var xueqi = document.getElementById("xueqi").value;
		var banji = document.getElementById("banji").value;
		if (xuenian == "") {
			alert("请选择要查询的学年");
			return false;
		}
		if (xueqi == "") {
			alert("请选择要查询的学期");
			return false;
		}
		if (banji == "") {
			alert("请选择要查询的班级");
			return false;
		}
		return true;
	}
</script>
