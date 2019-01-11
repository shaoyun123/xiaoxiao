<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table th {
	text-align: center;
}

.table td {
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>查寝校对
			</h2>
		</div>
		<div class="box-content">
			<div>
				<div style="float: center; display: inline;">
					<c:choose>
						<c:when test="${not empty chaqinjieguo.zhaopian}">
							<img style="width: 30%" class="img-responsive"
								src="getcqPic?id=${chaqinjieguo.zhaopian}&anpaiid=${chaqinjieguo.anpaiid}"
								onerror="javascript:this.src='${basePath}static/img/836343800200187442.jpg'" />
						</c:when>
						<c:otherwise>
							<img style="width: 30%;" class="img-responsive"
								src="${basePath}static/img/836343800200187442.jpg" />
						</c:otherwise>
					</c:choose>
				</div>
				<div style="float: right; display: inline; margin-right: 340px;">
					<strong>请勾选图中学生：</strong>
					<form method="post" name="form" id="form">
						<c:forEach items="${xueshengs }" var="x">
							<div style="margin-top: 50px;">
								<input type="checkbox" name="yidao" id="yidao"
									<c:if test="${x.banjimingcheng=='yidao' }"> checked="checked"</c:if>
									value="${x.xueshengid }"><span>${x.xingming}</span>
							</div>
						</c:forEach>
						<input type="hidden" name="id" id="id"
							value="${chaqinjieguo.jieguoid }"> <input type="button"
							class="btn btn-success" value="确定" onclick="sub()"
							style="margin-top: 100px;">
						<!-- 						<input type="button" -->
						<!-- 						class="btn btn-success" value="返回查寝安排" onclick="sup()" -->
						<!-- 						style="margin-top: 100px; margin-left: 80px;"> -->
					</form>
				</div>
			</div>
			<!-- 			<div class="pagination pagination-centered"> -->
			<!-- 				<ul> -->
			<%-- 					<c:if test="${page >1}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaqinjilu?id=${jieguo.jieguoid}')">首页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<c:if test="${page > 1}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaqinjilu?id=${jieguo.jieguoid}&page=${page-1}')">上一页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
			<!-- 					</li> -->
			<%-- 					<c:if test="${page < pages}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaqinjilu?id=${jieguo.jieguoid}&page=${page+1}')">下一页</a></li> --%>
			<%-- 					</c:if> --%>
			<%-- 					<c:if test="${page < pages}"> --%>
			<!-- 						<li><a href="JavaScript:void(0);" -->
			<%-- 							onclick="toContentPage('chaqinjilu?id=${jieguo.jieguoid}&page=${pages}')">尾页</a></li> --%>
			<%-- 					</c:if> --%>
			<!-- 				</ul> -->
			<!-- 			</div> -->
		</div>
	</div>
</div>
<script type="text/javascript">
	function sub() {
		obj = document.getElementsByName("yidao");
		check_val = [];
		for (k in obj) {
			if (obj[k].checked)
				check_val.push(obj[k].value);
		}
		if (check_val.length <= 0) {
			alert("请选择图片中的学生！！");
			return false;
		}

		$.ajax({
			type : "POST",//方法类型
			dataType : "text",//预期服务器返回的数据类型
			url : "subchaqinjl",//url
			data : $('#form').serialize(),
			success : function(result) {
				if (result == "success") {
					alert("操作成功!!");
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}
</script>
