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
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>发布通知
		</h2>

	</div>
	<div class="box-content">
		<div class="card-body">
			<form class="form-inline" id="forms" method="post">
				<div class="sub-title">
					<span style="font-weight: bold;">标题：</span>&emsp;<input id="title"
						name="title" type="text" class="form-control" size="30" />
				</div>
				<input type="hidden" id="contents" name="content" />
				<div class="sub-title">
					<span style="font-weight: bold;">内容：</span><br>

					<div class="ibox-content no-padding" style="width: 100%;">
						<div id="content_sn" class="summernote"></div>
					</div>
				</div>
				<div class="sub-title" style="display: inline; margin-top: 10px;">
					<span style="font-weight: bold;">发布范围：</span>&emsp;
					<c:if test="${user.status == 'fudaoyuan'}">
						<select id="fanwei" name="fabufanwei" style="width: 150px"
							onchange="s(this.value)">
							<option value="">请选择</option>
							<option value="2">专业</option>
							<option value="3">班级</option>
						</select>
					</c:if>
					<c:if test="${user.status == 'guanliyuan'}">
						<select id="fanwei" name="fabufanwei" style="width: 150px"
							onchange="s(this.value)">
							<option value="">请选择</option>
							<option value="1">全校</option>
							<option value="2">专业</option>
							<option value="3">班级</option>
						</select>
					</c:if>
					<span id="zhuanye-span"
						style="display: none; position: relative; left: 250px; top: -30px;">
						<input type="hidden" id="zhuanyes" name="zhuanye"> <select
						id="zhuanye" style="width: 150px; float: left" multiple="true">
							<option value="">请选择</option>
							<c:forEach items="${zhuanyes }" var="zhuanye">
								<option value="${zhuanye.zhuanyeid}">${zhuanye.zhuanyemingcheng }</option>
							</c:forEach>
					</select>
					</span> <span id="banji-span"
						style="display: none; position: relative; left: 250px; top: -30px;">
						<input type="hidden" id="banjis" name="banji"> <select
						id="banji" style="width: 150px; float: left" multiple="true">
							<option value="">请选择</option>
							<c:forEach items="${banjis }" var="banji">
								<option value="${banji.banjiid}">${banji.banjimingcheng }</option>
							</c:forEach>
					</select>
					</span>
				</div>
				<div class="sub-title" style="margin-top: 10px; position: absolute;">
					<span style="font-weight: bold;">是否置顶：</span>&emsp; <select
						id="istop" name="istop" style="width: 150px">
						<option value="">请选择</option>
						<option value="1">是</option>
						<option value="0">否</option>
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



<script type="text/javascript" src="static/js/summernote/summernote.js"></script>
<script type="text/javascript"
	src="static/js/summernote/summernote-zh-CN.min.js"></script>
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$('.summernote').summernote({
			height : '420px',
			lang : 'zh-CN',
			callbacks : {
				onImageUpload : function(files, editor, $editable) {
					sendFile(files);
				}
			}
		});
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
		if ($("#fanwei").val() == "2") {
			if ($("#zhuanye").val() == "" || $("#zhuanye").val() == null) {
				alert("请选择具体的专业！")
				return false;
			}
		}
		if ($("#fanwei").val() == "3") {
			if ($("#banji").val() == "" || $("#banji").val() == null) {
				alert("请选择具体的班级！")
				return false;
			}
		}
		$("#banjis").val($("#banji").val());
		$("#zhuanyes").val($("#zhuanye").val());
		if ($("#istop").val() != 1) {
			if (confirm("确认发布的通知不置顶？") != true) {
				return false;
			}
		}
		// 		
		$.ajax({
			type : "POST",
			url : 'saveneirongtongzhi',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("发布成功！")
				} else {
					alert("发布失败！")
				}
				toContentPage('tongzhilist');
			},
			error : function() {
				alert("fail")
			}

		});
	}
</script>

