<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>我的事件
		</h2>
		<div class="box-icon">
			<a href="JavaScript:void(0);"
				onclick="toContentPage('addbeiwang_sj')"><i title="添加"
				class="icon-plus"></i></a>
		</div>
	</div>

	<div class="box-content">
		<table
			class="table table-bordered table-striped table-condensed bootstrap-datatable ">
			<thead>
				<tr>
					<th style="text-align: center;">事件内容</th>
					<th style="text-align: center;">类型</th>
					<th style="text-align: center;">时间</th>
					<th style="text-align: center;">地点</th>
					<th style="text-align: center;">接收人</th>
					<th style="text-align: center;">回执</th>
					<th style="text-align: center;">修改</th>
					<th style="text-align: center;">删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${beiwl}" var="BeiWL">
					<tr>
						<td style="text-align: center;">${BeiWL.neirong}</td>
						<td style="text-align: center;"><c:if
								test="${BeiWL.leixing==0}">自己备忘</c:if> <c:if
								test="${BeiWL.leixing==2}">发给学生</c:if> <c:if
								test="${BeiWL.leixing==1}">发给班级</c:if></td>
						<td style="text-align: center;">${BeiWL.shijian}</td>
						<td style="text-align: center;">${BeiWL.didian}</td>
						<td style="text-align: center;"><c:if
								test="${BeiWL.leixing==0}">——</c:if> <c:if
								test="${BeiWL.leixing==1}">
								<c:choose>
									<c:when test="${BeiWL.huizhi==0}">
															——
														</c:when>
									<c:otherwise>
										<%-- 															<c:choose> --%>
										<%-- 																<c:when test="${ BeiWL.zhuangtai==1 }"> --%>
										<%-- 																	<a href="chakanjieshouren_sj?id=${BeiWL.id}"><input type="button" value="查看"/></a> --%>
										<button type="button"
											onclick="toContentPage('chakanjieshouren_sj?id=${BeiWL.id}')"
											class="btn btn-success">查看</button>
										<%-- 																</c:when> --%>
										<%-- 																<c:otherwise> --%>
										<!-- 																	—— -->
										<%-- 																</c:otherwise> --%>
										<%-- 															</c:choose> --%>
									</c:otherwise>
								</c:choose>
							</c:if> <c:if test="${BeiWL.leixing==2}">
								<c:choose>
									<c:when test="${BeiWL.huizhi==0}">
															——
														</c:when>
									<c:otherwise>
										<%-- 														 	<c:choose> --%>
										<%-- 																<c:when test="${BeiWL.zhuangtai==1 }"> --%>
										<%-- 																	<a href="chakanjieshouren_sj?id=${BeiWL.id}"><input type="button" value="查看"/></a> --%>
										<button type="button"
											onclick="toContentPage('chakanjieshouren_sj?id=${BeiWL.id}')"
											class="btn btn-success">查看</button>



										<%-- 																</c:when> --%>
										<%-- 																<c:otherwise> --%>
										<!-- 																	—— -->
										<%-- 																</c:otherwise> --%>
										<%-- 															</c:choose> --%>
									</c:otherwise>
								</c:choose>
							</c:if></td>
						<td style="text-align: center;"><c:if
								test="${BeiWL.leixing==0}">——</c:if> <c:if
								test="${BeiWL.leixing==1}">
								<c:if test="${BeiWL.huizhi==0}">否</c:if>
								<c:if test="${BeiWL.huizhi==1}">是</c:if>
							</c:if> <c:if test="${BeiWL.leixing==2}">
								<c:if test="${BeiWL.huizhi==0}">否</c:if>
								<c:if test="${BeiWL.huizhi==1}">是</c:if>
							</c:if></td>
						<td style="text-align: center;">
							<button type="button"
								onclick="toContentPage('xiugaibeiwanglu_sj?id=${BeiWL.id}&qufen=${qufen}')"
								class="btn btn-warning">修改</button> <%-- 												<a href="xiugaibeiwanglu_sj?id=${BeiWL.id}&qufen=${qufen}"><input type="button" value="修改"></a> --%>
						</td>
						<td style="text-align: center;">
							<button type="button"
								onclick="delbeiwl_sj('${BeiWL.id}','${qufen}')"
								class="btn btn-danger">删除</button> <%-- 												<a href="delbeiwl_sj?id=${BeiWL.id}&qufen=${qufen}" onclick="return delbeiwl()"><input type="button" value="删除"></a> --%>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">
	function delbeiwl_sj(id, qufen) {
		if (confirm("确认删除吗?") == true) {
			$.ajax({
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "delbeiwl_sj",//url
				data : {
					"id" : id,
					"qufen" : qufen
				},
				success : function(result) {
					if (result.status == "success") {
						alert("success");
					} else {
						alert("fail!");
					}
					toContentPage('wodebeiwanglu_sj');
				},
				error : function() {
					alert("异常！");
					toContentPage('wodebeiwanglu_sj');
				}
			});
		} else {
			return false;
		}
	}
</script>



