<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>编辑学生信息
			</h2>
		</div>
		<div class="box-content">
			<form class="form-horizontal" method="post" id="form"
				enctype="multipart/form-data">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="xuenian">学号:</label>
						<div class="controls">
							<input class="input-xlarge uneditable-input" id="xuehao"
								name="xuehao" type="text" value="${xuesheng.xuehao}"> <span
								class="pull-right">
								<button class="btn"
									onclick="updatepassword('${xuesheng.xueshengid}','${xuesheng.xuehao}','${xuesheng.xuexiaoXuehao}')">重置密码</button>
							</span>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="banjiid">班级：</label>
						<div class="controls">
							<select id="banjiid" name="banjiid">
								<c:forEach items="${banjis}" var="banji">
									<option value="${banji.banjiid}"
										<c:if test="${banji.banjiid==banji2.banjiid }">selected="selected"</c:if>>${banji.banjimingcheng}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="xueshengxingming">学生姓名：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="xueshengxingming"
								name="xueshengxingming" type="text" value="${xuesheng.xingming}">
						</div>
					</div>
					<input type="hidden" id="id" name="id"
						value="${xuesheng.xueshengid}">
					<div style="text-align: center;">
						<button type="button" class="btn btn-primary"
							onclick="savemodifystudent('${xuesheng.xueshengid}')">保存</button>
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
	var banjiid = $("#banjiid").val();
	function updatepassword(xueshengid, xuehao, xuexiaoxuehao) {
		$.ajax({
			type : "POST",
			url : 'updatePassWord',
			data : {
				"xueshengid" : xueshengid,
				"xuehao" : xuehao,
				"xuexiaoxuehao" : xuexiaoxuehao
			},
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("密码重置成功!")
					toContentPage('banji?id=' + banjiid);
				} else {
					alert("密码重置失败!");
					toContentPage('banji?id=' + banjiid);
				}
			},
			error : function() {
				alert("fail");
				toContentPage('banji?id=' + banjiid);
			}
		});
	}
	function savemodifystudent(id) {
		if ($("#xueshengxingming").val() == "") {
			alert("学生姓名不能为空！");
			return false;
		} else {
			$.ajax({
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "savemodifystudent",//url
				data : $('#form').serialize(),
				success : function(result) {
					if (result.status == "success") {
						alert("success");
						toContentPage('banji?id=' + banjiid);
					} else {
						alert("fail!");
						toContentPage('banji?id=' + banjiid);
					}
				},
				error : function() {
					alert("异常！");
					toContentPage('banji?id=' + banjiid);
				}
			});
		}

	}
</script>
