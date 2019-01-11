<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page
	import="com.web.service.JiaoXueLouService,com.web.model.JiaoXueLou,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加班级
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
							<option value="${yuanxi.yuanxiid}"
								<c:if test="${yuanxi.yuanxiid == yuanxi1.yuanxiid}">selected="selected"</c:if>>${yuanxi.yuanximingcheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">所属专业：</label>
				<div class="controls">
					<select id="zhuanyeid" name="zhuanyeid">
						<option value="">--请选择--</option>
						<c:forEach items="${zhuanyes}" var="zhuanye">
							<option value="${zhuanye.zhuanyeid}">${zhuanye.zhuanyemingcheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">入学年份：</label>
				<div class="controls">
					<select id="ruxuenianfenid" name="ruxuenianfenid">
						<option value="">--请选择--</option>
						<c:forEach items="${nianfens}" var="nianfen">
							<option value="${nianfen.ruxuenianfenid}"
								<c:if test="${nianfen.ruxuenianfenid == ruxuenianfenid}">selected="selected"</c:if>>${nianfen.ruxuenianfen}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学年制：</label>
				<div class="controls">
					<select id="leixing" name="leixing">
						<option value="">--请选择--</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">班级前缀：</label>
				<div class="controls">
					<input id="qianzhui" name="qianzhui" type="text" size="30px" /> <span
						class="help-inline">示例:自控1701S前缀为自控!</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">班级后缀：</label>
				<div class="controls">
					<input id="houzhui" name="houzhui" type="text" size="30px" /> <span
						class="help-inline"> 示例:自控1701S后缀为S!</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">插入数量：</label>
				<div class="controls">
					<input id="shuliang" name="shuliang" type="text" size="30px" /> <span
						class="help-inline">插入后班级的总数量必须小于100!</span>
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
		if ($("#zhuanyeid").val() == "") {
			alert("请选择专业!");
			return false;
		}
		if ($("#leixing").val() == "") {
			alert("请选择学制!");
			return false;
		}
		if ($("#qianzhui").val() == "") {
			alert("请输入前缀!");
			return false;
		}
		if ($("#shuliang").val() == "") {
			alert("请输入数量!");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savebanji',
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
					toContentPage('chakanbanji?yuanxiid=' + datas[1])
				} else if (datas[0] == "zhengshu") {
					alert('请输入1到100之间的整数!');
					toContentPage('addbanji');
				} else {
					alert("添加失败");
					toContentPage('chakanbanji?yuanxiid=${yuanxi1.yuanxiid}');
				}

			},
			error : function() {
				alert("超时!")
			}
		});
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
												// 						defaultValue=[data.data[0].id];
												code += '<option value='+data.data[i].zhuanyeid+'>'
														+ data.data[i].zhuanyemingcheng
														+ '</option>';
											}
											$('#zhuanyeid').html(code);
										}
										if (data.status == "none") {
											alert("该学科下还没有专业");
											$("#zhuanyeid").empty();
											$("#zhuanyeid").val(defaultValue)
													.trigger('change');
										}
									},
									error : function() {
										alert("请选择专业!");
									}
								});
					})
</script>