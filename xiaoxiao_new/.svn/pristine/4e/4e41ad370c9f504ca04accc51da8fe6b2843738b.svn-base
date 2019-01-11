<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page
	import="com.web.service.JiaoXueLouService,com.web.model.JiaoXueLou,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>修改班级
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" id="forms"
			method="post">
			<input type="hidden" name="id" value="${banji.banjiid}" />
			<div class="control-group">
				<label class="control-label">所属专业：</label>
				<div class="controls">
					<select id="daima" name="zhuanyeid">
						<option value="">--请选择--</option>
						<c:forEach items="${zhuanyes}" var="zhuanYe">
							<option value="${zhuanYe.zhuanyeid}"
								<c:if test="${zhuanYe.zhuanyeid == zhuanye.zhuanyeid}">selected="selected"</c:if>>${zhuanYe.zhuanyemingcheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">入学年份：</label>
				<div class="controls">
					<select id="ruxuenianfen" name="ruxuenianfen">
						<option value="">--请选择--</option>
						<c:forEach items="${nianfens}" var="nianfen">
							<option value="${nianfen.ruxuenianfenid}"
								<c:if test="${nianfen.ruxuenianfenid == banji.ruxuenianfenid}">selected="selected"</c:if>>${nianfen.ruxuenianfen}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学年制：</label>
				<div class="controls">
					<select id="leixing" name="leixing">
						<option value="">--请选择--</option>
						<option value="2" <c:if test="${banji.leixing==2 }">selected="selected"</c:if>>2</option>
						<option value="3" <c:if test="${banji.leixing==3 }">selected="selected"</c:if>>3</option>
						<option value="4" <c:if test="${banji.leixing==4 }">selected="selected"</c:if>>4</option>
						<option value="5" <c:if test="${banji.leixing==5 }">selected="selected"</c:if>>5</option>
					</select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">班级名称：</label>
				<div class="controls">
					<input id="banjimingcheng" name="banjimingcheng" type="text"
						size="30px" value="${banji.banjimingcheng}" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="focusedInput"></label>
				<div class="controls">
					<button type="button" style="margin-left: 30px;"
						class="btn btn-success" value="保存" onclick="save()">保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	function save() {
		if ($("#ruxuenianfen").val() == "") {
			alert("请选择入学年份!");
			return false;
		}
		if ($("#banjimingcheng").val() == "") {
			alert("请输入班级名称!");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savemodifybanji',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				var datas = [];
				datas = data.split("_");
				if (datas[0] == "success") {
					alert("修改成功！");
					toContentPage('chaxunbanji?zhuanyeid='+datas[1]+'&ruxuenianfenid='+datas[2]);
				}else if(datas[0] == "chongming"){
					alert('与其他班级名称相同!');
					toContentPage('chaxunbanji?ruxuenianfenid=${banji.ruxuenianfenid}&zhuanyeid=${zhuanye.zhuanyeid}');
				} else {
					alert("修改失败");
					toContentPage('chaxunbanji?ruxuenianfenid=${banji.ruxuenianfenid}&zhuanyeid=${zhuanye.zhuanyeid}');
				}
			},
			error : function() {
				alert("超时!");
			}
		});
	}
</script>
