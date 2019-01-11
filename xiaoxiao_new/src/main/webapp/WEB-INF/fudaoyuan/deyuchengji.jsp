<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>德育成绩
			</h2>
		</div>
		<div class="box-content">
			<div class="row-fluid">
				<input type="hidden" name="fanganid" id="fanganid"
					value="${pingFenFangAn.fanganid}">
				<div style="width: 100%;" class="span3">
					<span style="color: red; margin-left: 100px;">德育方案名：${pingFenFangAn.fanganmingcheng}</span>
					<br> <br>
					<div class="dataTables_length">
						<span style="margin-left: 100px;">学年：</span> <select id="xuenian"
							name="xuenian" style="width: 120px;">
							<c:forEach items="${xueNianList }" var="xueNians">
								<option value="${xueNians }"
									<c:if test="${xuenian==xueNians}">selected="selected"</c:if>>${xueNians }</option>
							</c:forEach>
						</select>
						<!-- 					</div> -->
						<!-- 					<div class="dataTables_length"> -->
						<span style="margin-left: 50px;">学期：</span> <select id="xueqi"
							name="xueqi" style="width: 100px;">
							<option value=""
								<c:if test="${xueqi==''}">selected="selected"</c:if>>--请选择--</option>
							<option value=1
								<c:if test="${xueqi=='1'}">selected="selected"</c:if>>1</option>
							<option value=2
								<c:if test="${xueqi=='2'}">selected="selected"</c:if>>2</option>
							<option value=3
								<c:if test="${xueqi=='3'}">selected="selected"</c:if>>3</option>
						</select>
						<!-- 					</div> -->
						<!-- 					<div class="dataTables_length"> -->
						<span style="margin-left: 50px;">班级：</span> <select id="banji"
							name="banji" style="width: 100px;">
							<option value=""
								<c:if test="${banjiid==''}">selected="selected"</c:if>>--请选择--</option>
							<c:forEach items="${banjis}" var="banji">
								<option value="${banji.banjiid }"
									<c:if test="${banji.banjiid == banjiid}">selected="selected"</c:if>>${banji.banjimingcheng }</option>
							</c:forEach>
						</select>
						<!-- 					<input type="submit" style="margin-left: 50px" value="查询"> -->
						<button type="button" style="margin-left: 30px;margin-top:-10px;"
							class="btn btn-success" value="查询" onclick="searchResult()">查询</button>
						<button type="button" style="margin-left: 30px;margin-top:-10px;"
							class="btn btn-success" value="导出" onclick="exports()">导出</button>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${not empty xueShengDeYus}">
					<table class="table table-bordered table-striped table-condensed bootstrap-datatable">
						<tr>
							<th style="text-align: center;"><font
								style="color: red; size: 15px; font-weight: bold;">学号</font></th>
							<th style="text-align: center;"><font
								style="color: red; size: 15px; font-weight: bold;">姓名</font></th>
							<th style="text-align: center;"><font
								style="color: red; size: 15px; font-weight: bold;">加权总分</font></th>
							<th style="text-align: center;"><strong>详情</strong></th>
						</tr>
						<c:forEach items="${xueShengDeYus}" var="deyu">
							<tr style="text-align: center; height: 40px;">
								<td style="text-align: center;"><strong>${deyu.xuehao}</strong></td>
								<td style="text-align: center;"><strong>${deyu.xingming}</strong></td>
								<c:choose>
									<c:when test="${not empty deyu.deyufen and (deyu.leixing == 3)}">
										<td style="text-align: center;"><strong>${deyu.deyufen}</strong></td>
									</c:when>
									<c:otherwise>
										<td style="text-align: center;"><strong>0.00</strong></td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${deyu.deyufenid>0  and (deyu.leixing == 3)}">
										<td style="text-align: center;"><a
											target="_blank" href="javascript:void(0)"> 
												<button class="btn btn-default" value="详情" onclick="toContentPage('deYuDetail_fdy?id=${deyu.deyufenid}')">详情</button></a></td>
<%-- 																											<c:if test="${zhuangtai == 1 }"> --%>
<%-- 																												<td style="text-align: center;width:5%;"><a target="_blank" href="xiugaideyu_fdy?id=${deyu.deyufenid}"><input --%>
<!-- 																													type="button" value="修改"></a></td> -->
<%-- 																											</c:if> --%>
									</c:when>
									<c:otherwise>
										<td colspan="4" style="text-align: center;"><span>本学期无德育成绩</span></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<span style="margin-left:200px;color:red;margin-top:230px;">在当前学年学期下，该班无德育成绩</span>
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
		toContentPage('deyuchengji_fdy?xuenian=' + xuenian + '&xueqi=' + xueqi
				+ '&banji=' + banji + '&fanganid=' + $("#fanganid").val());
	}
	
	function exports(){
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
		window.location.href= 'exportdeyuchengji_fdy?xuenian=' + xuenian + '&xueqi=' + xueqi
				+ '&banji=' + banji;
<%-- 				window.location.href= '<%=request.getContextPath() %>/static/exportdeyu.jsp'; --%>
	}
</script>