<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>修改学生
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal"
			method="post" id="forms">
			<input type="hidden" name="id" value="${xuesheng.xueshengid}" />
			<div class="control-group">
				<label class="control-label">所属院系：</label>
				<div class="controls">
					<select id="yuanxiid" name="yuanxiid">
						<option value="">--请选择--</option>
						<c:forEach items="${yuanxis}" var="yuanXi">
							<option value="${yuanXi.yuanxiid}"
								<c:if test="${yuanXi.yuanxiid == yuanxi.yuanxiid}">selected="selected"</c:if>>${yuanXi.yuanximingcheng}</option>

						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">入学年份：</label>
				<div class="controls">
					<select id="ruxuenianfen" name="ruxuenianfen">
						<c:forEach items="${nianfens}" var="nianFen">
							<option value="${nianFen.ruxuenianfenid}"
								<c:if test="${nianFen.ruxuenianfenid == nianfen.ruxuenianfenid}">selected="selected"</c:if>>${nianFen.ruxuenianfen}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">班级：</label>
				<div class="controls">
					<select id="banjiid" name="banjiid">
						<option value="">--请选择--</option>
						<c:forEach items="${banjis}" var="banJi">
							<option value="${banJi.banjiid}"
								<c:if test="${banJi.banjiid == banji.banjiid}">selected="selected"</c:if>>${banJi.banjimingcheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">校区：</label>
				<div class="controls">
					<select id="xiaoquid" name="xiaoquid">
						<option value="">--请选择--</option>
						<c:forEach items="${xiaoqus}" var="xiaoqu">
							<option value="${xiaoqu.xiaoquid}">${xiaoqu.mingcheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">宿舍楼：</label>
				<div class="controls">
					<select id="sushelouid" name="sushelouid">

					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">宿舍：</label>
				<div class="controls">
					<select id="susheid" name="susheid">

					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">姓名：</label>
				<div class="controls">
					<input id="xingming" name="xingming" type="text"
						value="${xuesheng.xingming}" />

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
		if ($("#banjiid").val() == '') {
			alert("请选择班级!");
			return false;
		}
		if ($("#susheid").val() == null || $("#susheid").val() == '') {
			alert("请选择宿舍!")
			return false;
		}
		if ($("#xingming").val() == '') {
			alert("请输入姓名!");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savemodifyxuesheng',
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
					toContentPage('chakanxuesheng?banjiid='+datas[1]);
				} else {
					alert("修改失败");
					toContentPage('chakanxuesheng?banjiid=${banji.banjiid}');
				}
			},
			error : function() {
				alert("超时!")
			}
		});
	}
	$('#xiaoquid')
			.change(
					function() {
						var xiaoquid = $('#xiaoquid option:selected').val();//获取当前选中的值
						$
								.ajax({
									type : "POST",
									url : 'getsushelou',
									data : {
										CODE : xiaoquid
									},
									dataType : 'json',
									cache : false,
									timeout : 5000,
									async : true,
									success : function(data) {
										var data = eval(data);
										if (data.length > 0) {
											var code = '<option disabled selected value></option>';
											for (var i = 0; i < data.length; i++) {
												code += '<option value='+data[i].suSheLouId+'>'
														+ data[i].mingCheng
														+ '</option>';
											}
											$('#sushelouid').html(code);
										}
									},
									error : function() {
										alert("程序出错了!");
									}
								});
					})

	$('#sushelouid')
			.change(
					function() {
						var sushelouid = $('#sushelouid option:selected').val();//获取当前选中的值
						$
								.ajax({
									type : "POST",
									url : 'getsushe',
									data : {
										CODE : sushelouid
									},
									dataType : 'json',
									cache : false,
									timeout : 5000,
									async : true,
									success : function(data) {
										var data = eval(data);
										if (data.length > 0) {
											var code = '<option disabled selected value></option>';
											for (var i = 0; i < data.length; i++) {
												code += '<option value='+data[i].suSheId+'>'
														+ data[i].menPaiHao
														+ '</option>';
											}
											$('#susheid').html(code);
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
						// 		var zhuanyeid=$('#zhuanyeid option:selected').val();//获取当前选中的值
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
										var data = eval(data);
										var defaultValue = '';
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
