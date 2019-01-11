<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page
	import="com.web.service.JiaoXueLouService,com.web.model.JiaoXueLou,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加学生
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<div class="control-group">
				<label class="control-label">所属院系：</label>
				<div class="controls">
					<select id="yuanxiid" name="yuanxiid">
						<option value="">--请选择--</option>
						<c:forEach items="${yuanxis}" var="yuanxi">
							<option value="${yuanxi.yuanxiid}">${yuanxi.yuanximingcheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<!--                                     	<div class="sub-title"> -->
			<!--                                     		<span style="font-weight:bold;">专业：</span> -->
			<!--                                     		<select id="zhuanyeid" name="zhuanyeid" class="form-control chosen-select success" style="display: none;width:10%" aria-required="true" aria-invalid="false"> -->
			<!--                                     		<option value="">--请选择--</option> -->
			<%--                                     			<c:forEach items="${zhuanyes}" var="zhuanye"> --%>
			<%--                                     				<option value="${zhuanye.zhuanyeid}">${zhuanye.zhuanyemingcheng}</option> --%>
			<%--                                     			</c:forEach> --%>
			<!--                                     		</select> -->
			<!--                                     	</div> -->
			<div class="control-group">
				<label class="control-label">入学年份：</label>
				<div class="controls">
					<select id="ruxuenianfen" name="ruxuenianfen">
						<c:forEach items="${nianfens}" var="nianfen">
							<option value="${nianfen.ruxuenianfenid}">${nianfen.ruxuenianfen}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">班级：</label>
				<div class="controls">
					<select id="banjiid" name="banjiid">
<!-- 						<option value="">--请选择--</option> -->
<%-- 						<c:forEach items="${banjis}" var="banji"> --%>
<%-- 							<option value="${banji.banjiid}">${banji.banjimingcheng}</option> --%>
<%-- 						</c:forEach> --%>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学生姓名：</label>
				<div class="controls">
					<input id="xingming" name="xingming" type="text" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学号：</label>
				<div class="controls">
					<input id="xuehao" name="xuehao" type="text" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">电话：</label>
				<div class="controls">
					<input id="dianhua" name="dianhua" type="text" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">邮箱：</label>
				<div class="controls">
					<input id="youxiang" name="youxiang" type="text"/>
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
		if ($("#yuanxiid").val() == "") {
			alert("请选择院系!");
			return false;
		}
		if ($("#banjiid").val() == "") {
			alert("请选择班级!");
			return false;
		}
		if ($("#xuehao").val() == "") {
			alert("请输入学号!");
			return false;
		}
		if ($("#xueshengxingming").val() == "") {
			alert("请输入学生姓名!");
			return false;
		}
		var a = 0;
		if ($("#youxiang").val() == "") {
			if (confirm("确认不输入邮箱？") == true) {
				a = 0
			}else{
				a = 1;
			}
		}
		if(a == 0){
			$.ajax({
				type : "POST",
				url : 'savexuesheng',
				data : $('#forms').serialize(),
				dataType : 'text',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					var datas = [];
					datas = data.split("_");
					if (datas[0] == "success") {
						alert("添加成功！");
						toContentPage('chakanxuesheng?banjiid='+datas[1]);
					} else if(datas[0] == "gxsuccess"){
						alert('已有该学生，更新学生信息成功!');
						toContentPage('chakanxuesheng?banjiid='+datas[1]);
					}else if(datas[0] == "gxfail"){
						alert('已有该学生，更新学生信息失败!');
						toContentPage('chakanxuesheng?banjiid=${banji.banjiid}');
					}else {
						alert("添加失败");
						toContentPage('chakanxuesheng?banjiid=${banji.banjiid}');
					}
				},
				error : function() {
					alert("超时!")
				}
			});
		}
	}
	$('#yuanxiid')
			.change(
					function() {
						var yuanxiid = $('#yuanxiid option:selected').val();//获取当前选中的值
						$
								.ajax({
									type : "POST",
									url : 'selectzhuanye',
									data : {
										CODE : yuanxiid
									},
									dataType : 'json',
									cache : false,
									timeout : 5000,
									async : true,
									success : function(data) {
										var defaultValue = '';
										if (data.status == "success") {
											var code = '<option disabled selected value></option>';
											for (var i = 0; i < data.data.length; i++) {
												code += '<option value='+data.data[i].zhuanyeid+'>'
														+ data.data[i].zhuanyemingcheng
														+ '</option>';
											}
											$('#zhuanyeid').html(code);
										}
										if (data.status == "none") {
											$("#zhuanyeid").empty();
											$("#zhuanyeid").val(defaultValue)
													.trigger('change');
											alert("请先为该院系添加专业!");
										}
									},
									error : function() {
										alert("程序出错了!");
									}

								});
					})

	$('#yuanxiid,#ruxuenianfen')
			.change(
					function() {
						var ruxuenianfen = $('#ruxuenianfen option:selected')
								.val();
						var yuanxiid = $('#yuanxiid option:selected').val();
						var code = ruxuenianfen + "," + yuanxiid;
						$
								.ajax({
									type : "POST",
									url : 'getBanJi',
									data : {
										CODE : code
									},
									dataType : 'json',
									cache : false,
									timeout : 5000,
									async : true,
									success : function(data) {
										if (data.length != 0) {
											var code = '<option disabled selected value></option>';
											for (var i = 0; i < data.length; i++) {
												code += '<option value='+data[i].banjiid+'>'
														+ data[i].banjimingcheng
														+ '</option>';
											}
											$('#banjiid').html(code);
										} else {
											alert("请先添加班级!")
											$("#banjiid").empty();
											$("#banjiid").val(defaultValue)
													.trigger('change');
										}
									},
									error : function() {
										alert("获取班级失败!");
									}

								});
					})
</script>