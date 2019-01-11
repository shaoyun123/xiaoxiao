<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>事件接收人
			</h2>
		</div>
		<div class="box-content">
			<form class="form-horizontal" action="" method="post" id="form">
				<fieldset>
				<div >
					<div class="control-group" style="display:inline-block;">
						<label class="control-label" for="neirong">消息内容：</label>
						<div class="controls">
							<input type="text" id="neirong" name="neirong" class="input-xlarge uneditable-input"
								 value="${shijian.neirong}">
						</div>
					</div>
					<div class="control-group" style="display:inline-block;">
						<label class="control-label" for="banji">班级：</label>
						<div class="controls">
							<select id="banji" name="banjiid" style="style="display: none;">
								<c:if test="${not empty banjis }">
									<c:forEach items="${banjis}" var="bjs">
										<option value="${bjs.banjiid}"
											<c:if test="${bjs.banjiid == bjid}">selected="selected"</c:if>>${bjs.banjimingcheng }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					<button type="button" class="btn btn-success" style="margin-left:10px;" onclick="sub()">查询</button>
					</div>
				</fieldset>
				<input type="hidden" name="id" id="beiwlid" value="${shijian.id }">
			</form>
			<c:choose>
				<c:when test="${not empty msg }">
					<span style="color: red; margin-left: 220px;">${msg }</span>
				</c:when>
				<c:otherwise>
					<table 
						class="table table-bordered table-striped table-condensed bootstrap-datatable">
						<thead>
							<tr style="background-color: #ffffff;">
								<th style="text-align: center">班级</th>
								<th style="text-align: center">学号</th>
								<th style="text-align: center">姓名</th>
								<th style="text-align: center">回执结果</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${xuesheng}" var="xuesheng">
								<tr>
									<td style="text-align: center">${xuesheng.banji}</td>
									<td style="text-align: center">${xuesheng.xuehao}</td>
									<td style="text-align: center">${xuesheng.xingming}</td>
									<c:if test="${shijian.huizhi==1}">
										<td style="text-align: center"><c:if test="${xuesheng.zhuangtai=='0'}">
												<span style="color: black">未答复</span>
											</c:if> <c:if test="${xuesheng.zhuangtai=='1'}">
												<span style="color: green">已完成</span>
											</c:if> <c:if test="${xuesheng.zhuangtai=='2'}">
												<span style="color: red">未完成</span>
											</c:if></td>
									</c:if>
									<c:if test="${shijian.huizhi==0}">
										<tdstyle="text-align: center">——</td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
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
function sub() {
	var id = $("#beiwlid").val();
	var banjiid= $("#banji").val();
	toContentPage('chakanjieshouren?id='+id+'&banjiid='+banjiid);
// 	$.ajax({
// 		type : "POST",//方法类型
// 		dataType : "text",//预期服务器返回的数据类型
// 		url : "subchaqinanpai",//url
// 		data : {"id":id},
// 		success : function(result) {
// 			if (result == "success") {
// 				alert("success");
// 				toContentPage('chaqin');
// 			}
// 		},
// 		error : function() {
// 			alert("异常！");
// 		}
// 	});
}
</script>
