<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css"
	href="static/css/summernote/summernote-0.8.8.css">
<style>
.modal {
	display: none;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>修改通知
			</h2>
		</div>
		<div class="box-content">
			<div class="card-body">
				<form class="form-inline" id="forms" method="post">
					<input type="hidden" name="id" value="${tongzhi.id }">
					<div class="sub-title">
						<span style="font-weight: bold;">标题：</span>&emsp;<input id="title"
							name="title" type="text" class="form-control"
							value="${tongzhi.title }" size="30" />
					</div>
					<div style="display: none;">
						<input type="hidden" id="contents" name="content">
						<textarea rows="30000000000" cols="90000000000" hidden="hidden"
							id="contentss">${tongzhi.content }</textarea>
					</div>
					<div class="sub-title">
						<span style="font-weight: bold;">内容：</span><br>
						<div class="ibox-content no-padding" style="width: 100%;">
							<div id="content_sn" class="summernote"></div>
						</div>
					</div>
					<div class="sub-title" style="display: inline">
						<span style="font-weight: bold;">发布范围：</span>&emsp;
						<c:choose>
							<c:when test="${user.status == 'fudaoyuan'}">
								<select id="fanwei" name="fabufanwei" style="width: 150px"
									onchange="s(this.value)">
									<option value="">请选择</option>
									<option value="2"
										<c:if test="${tongzhi.fabufanwei==2 }">selected="selected"</c:if>>专业</option>
									<option value="3"
										<c:if test="${tongzhi.fabufanwei==3 }">selected="selected"</c:if>>班级</option>
								</select>
							</c:when>
							<c:when test="${user.status == 'guanliyuan'}">
								<select id="fanwei" name="fabufanwei" style="width: 150px"
									onchange="s(this.value)">
									<option value="">请选择</option>
									<option value="1"
										<c:if test="${tongzhi.fabufanwei==1 }">selected="selected"</c:if>>全校</option>
									<option value="2"
										<c:if test="${tongzhi.fabufanwei==2 }">selected="selected"</c:if>>专业</option>
									<option value="3"
										<c:if test="${tongzhi.fabufanwei==3 }">selected="selected"</c:if>>班级</option>
								</select>
							</c:when>
						</c:choose>
						<span id="zhuanye-span"
							style="display: none; position: relative; left: 250px; top: -30px;">
							<input type="hidden" id="zhuanyes" name="zhuanye"> <select
							id="zhuanye" style="width: 150px; float: left" multiple="true">
								<option value="">请选择</option>
								<c:forEach items="${zhuanyes }" var="zhuanye">
									<c:choose>
										<c:when test="${not empty zhuanYes }">
											<c:forEach items="${zhuanYes }" var="zhuanYe">
												<option value="${zhuanye.zhuanyeid}"
													<c:if test="${zhuanye.zhuanyeid == zhuanYe.zhuanyeid}">selected="selected"</c:if>>${zhuanye.zhuanyemingcheng }</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<option value="${zhuanye.zhuanyeid}">${zhuanye.zhuanyemingcheng }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select>
						</span> <span id="banji-span"
							style="display: none; position: relative; left: 250px; top: -30px;">
							<input type="hidden" id="banjis" name="banji"> <select
							id="banji" style="width: 150px; float: left" multiple="true">
								<option value="">请选择</option>
								<c:forEach items="${banjis }" var="banji">
									<c:choose>
										<c:when test="${not empty banJis }">
											<c:forEach items="${banJis }" var="banJi">
												<option value="${banji.banjiid}"
													<c:if test="${banji.banjiid == banJi.banjiid}">selected="selected"</c:if>>${banji.banjimingcheng }</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<option value="${banji.banjiid}">${banji.banjimingcheng }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select>
						</span>

					</div>
					<div class="sub-title"
						style="margin-top: 10px; position: absolute;">
						<span style="font-weight: bold;">是否置顶：</span>&emsp; <select
							id="istop" name="istop" style="width: 150px">
							<option value="">请选择</option>
							<option value="1"
								<c:if test="${tongzhi.istop==true }">selected="selected"</c:if>>是</option>
							<option value="0"
								<c:if test="${tongzhi.istop==false }">selected="selected"</c:if>>否</option>
						</select>
					</div>
					<!-- 										<div class="sub-title"> -->
					<!-- 											<span style="font-weight: bold;">发布日期：</span>&emsp;<input -->
					<!-- 												id="riqi" name="riqi" type="text" class="Wdate" -->
					<!-- 												style="height: 25px" -->
					<!-- 												onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" /> -->
					<!-- 										</div> -->

					<span class="pull-right"><input type="button"
						class="flip-link btn btn-info" value="提交" onclick="save()"></span>
				</form>

			</div>
		</div>
	</div>
</div>


<script type="text/javascript" src="static/js/summernote/summernote.js"></script>


<script type="text/javascript">
	$().ready(function() {
		$('.summernote').summernote({
			height : '620px',
			lang : 'zh-CN',
			callbacks : {
				onImageUpload : function(files, editor, $editable) {
					console.log("onImageUpload");
					sendFile(files);
				}
			}
		});
		var content = $("#contentss").val();
		$('#content_sn').summernote('code', content);
	});

	function s(e) {
		if (e == "2") {
			document.getElementById("zhuanye-span").style.display = "block";
			document.getElementById("banji-span").style.display = "none";
		} else if (e == "3") {
			document.getElementById("zhuanye-span").style.display = "none";
			document.getElementById("banji-span").style.display = "block";
		}
	}

	function save() {
		var content_sn = $("#content_sn").summernote('code');
		$("#contents").val(content_sn);

		if (($("#title").val() == "")) {
			alert("标题不能为空")
			return false;
		}
		if (($("#contents").val() == "")) {
			alert("内容不能为空")
			return false;
		}
		if (($("#fanwei").val() == "")) {
			alert("发布范围不能为空")
			return false;
		}
		$("#banjis").val($("#banji").val());
		$("#zhuanyes").val($("#zhuanye").val());
		if ($("#istop").val() != 1) {
			if (confirm("确认发布的通知不置顶？") != true) {
				return false;
			}
		}

		$.ajax({
			type : "POST",
			url : 'updateneirongtongzhi',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("修改成功！")
				} else {
					alert("修改失败！")
				}
				toContentPage('tongzhilist');
			},
			error : function() {
				alert("fail")
			}

		});
	}
</script>
