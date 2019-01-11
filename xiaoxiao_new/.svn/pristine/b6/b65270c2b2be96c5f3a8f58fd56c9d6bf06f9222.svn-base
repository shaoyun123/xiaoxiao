<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>审核汇报
			</h2>
		</div>
		<div class="box-content">
			<form class="form-horizontal">
				<fieldset>
					<div style="margin-left: 10px">
						<span style="font-size: 20px">${jlnr.jiaoliumingcheng}</span> <span
							style="margin-left: 30px; color: orange;">#${jlnr.xingming}#</span>
						<span style="margin-left: 20px; color: orange;">#${jlnr.xuehao}#</span>
						<span style="margin-left: 20px; color: orange;">#${jlnr.banjimingcheng}#</span>
					</div>
					<div style="margin-left: 40px">
						<h5 style="color: blue;">${jlnr.shangchuanriqi}</h5>
					</div>
					<div style="width: 80%; margin-left: 30px">
						<h4 style="font-family: '楷体'">${jlnr.xueshengshangchuan}</h4>
					</div>
					<br>
					<textarea id="shenhe" name="shenhe"
						style="height: 200px; width: 100%; resize: none;"></textarea>
					<br>
					<div class="form-actions">
						<button type="button"
							onclick="subshenhe('${jlnr.jiaoliuid}','${jlnr.anpaiid}')"
							class="btn btn-primary">提交</button>
					</div>
				</fieldset>
			</form>
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
	function subshenhe(jiaoliuid, anpaiid) {
		if ($("#shenhe").val() == "") {
			alert("请输入审核内容！")
			return false;
		}
		if (confirm("提交后将无法撤回与修改，确认提交吗？") == true) {
			$.ajax({
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "submitshenhe",//url
				data : {
					"id" : jiaoliuid
				},
				success : function(data) {
					var result = eval(data);
					if (result.status == "success") {
						alert("success");
						toContentPage('chakanxiangqing?id=' + anpaiid);
					} else {
						alert("fail!");
						toContentPage('chakanxiangqing?id=' + anpaiid);
					}
				},
				error : function() {
					alert("异常！");
					toContentPage('chakanxiangqing?id=' + anpaiid);
				}
			});
		} else {
			return false;
		}
	}
</script>