<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>编辑教室
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post"
			id="forms">
			<input type="hidden" name="id" value="${jiaoshi.jiaoshiid}">
			<div class="control-group">
				<label class="control-label">可选校区：</label>
				<div class="controls">
					<select id="xiaoquid" name="xiaoquid">
						<option value="">--请选择--</option>
						<c:forEach items="${xiaoqu}" var="xiaoqu">
							<option value="${xiaoqu.xiaoquid}"
								<c:if test="${xiaoqu.xiaoquid == xiaoqu5.xiaoquid }">selected="selected"</c:if>>${xiaoqu.mingcheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">可选教学楼:</label>
				<div class="controls">
					<select id="jiaoxuelouid" name="jiaoxuelouid">
						<option value="">--请选择--</option>
						<c:forEach items="${jiaoxuelou}" var="jiaoxuelou">
							<option value="${jiaoxuelou.jiaoXueLouId}"
								<c:if test="${jiaoxuelou1.jiaoXueLouId == jiaoxuelou.jiaoXueLouId}">selected="selected"</c:if>>${jiaoxuelou.jiaoXueLouMing}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">教室名称:</label>
				<div class="controls">
					<input id="jiaoshimingcheng" name="jiaoshimingcheng" type="text"
						size="10px" value="${jiaoshi.jiaoshiming}" />
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
		if ($("#jiaoshimingcheng").val() == "") {
			alert("请输入教室名称！");
			return false;
		}

		if ($("#xiaoquid").val() == "") {

			alert("请选择校区!");
			return false;
		}
		if ($("#jiaoxuelouid").val() == "") {

			alert("请选择教学楼!");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'savemodifyjiaoshi',
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
					toContentPage('chakanjiaoshi?jiaoxuelouid='+datas[1]);
				}else if(datas[0] == "qiyong"){
					alert('该校区已停用！请先启用该校区！');
					toContentPage('xiaoquliebiao');
				}else if(datas[0] == "chongming"){
					alert('该教室已经存在!');
					toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou1.jiaoXueLouId}');
				} else {
					alert("修改失败");
					toContentPage('chakanjiaoshi?jiaoxuelouid=${jiaoxuelou1.jiaoXueLouId}');
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
									url : 'getJiaoXueLou',
									data : {
										CODE : xiaoquid
									},
									dataType : 'json',
									cache : false,
									timeout : 5000,
									async : true,
									success : function(data) {
										var code = '<option value="">--请选择--</option>';
										if (data.length != 0) {
											for (var i = 0; i < data.length; i++) {
												code += '<option value='+data[i].jiaoXueLouId+'>'
														+ data[i].jiaoXueLouMing
														+ '</option>';
											}
											$('#jiaoxuelouid').html(code);
										} else {
											alert("请先在该校区下添加教学楼!");
											$("#jiaoxuelouid").empty();
											$("#jiaoxuelouid")
													.val(defaultValue).trigger(
															'change');
										}

									},
									error : function() {
										alert("请选择校区!");
									}

								});
					})
</script>