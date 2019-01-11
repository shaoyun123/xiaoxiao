<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>编辑大组
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<input type="hidden" name="id" value="${dazu.id}" /> 
			<div class="control-group">
				<label class="control-label">大组名称：</label>
				<div class="controls">
					<input id="zuMingCheng" name="zuMingCheng" type="text" size="30"
						value="${dazu.zuMingCheng}" />
				</div>
			</div>
		
			<div class="control-group">
				<label class="control-label">容量：</label>
				<div class="controls">
					<input id="rongliang" name="rongliang" type="number" size="30"
						value="${dazu.rongLiang}" />
						<span
						class="" help-inline>请输入整数!</span>
				</div>
			</div>
			<div class="control-group"> 
				<label class="control-label">选择大组长：</label>
				<div class="controls">
					<select id="dazuzhangid" name="dazuzhangid" style="width: 100px;">
						<option value="">--请选择--</option>
						<c:forEach items="${xueshengs}" var="xuesheng">
							<option value="${xuesheng.xueshengid}">${xuesheng.xingming}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">已加入：</label>
				<div class="controls">
					<input id="yijiaru" name="yijiaru" type="text" size="30" disabled="disabled"
						value="${dazu.yiJiaRu}" />
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
		if ($("#mingcheng").val() == "") {
			alert("请填写大组名称！");
			return false;
		} else if ($("#rongliang").val() == "") {
			alert("请填写容量！");
			return false;
		}else if ($("#dazuzhangid").val() == "") {
			alert("请选择大组长！");
			return false;
		}
		$.ajax({
			type : "POST",
			url : 'saveupdatedazu',
			data : $("#forms").serialize(),
			dataType : 'json',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data.status == "success") {
					alert("修改成功！");
				} else {
					alert("修改失败");
				}
				toContentPage("getdazu?id="+data.kechengid);
			},
			error : function() {
				alert("登录超时!")
			}
		});
	}
</script>
</body>
</html>