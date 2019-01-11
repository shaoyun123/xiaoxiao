<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>修改考评
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<input id="kechengid" name="kechengid" type="hidden"
				value=${kechengkaoping.shiJianKeChengId } /> <input id="kaopingid"
				name="kaopingid" type="hidden" value=${kechengkaoping.id } />
			<div class="control-group">
				<label class="control-label">考评名称：</label>
				<div class="controls">
					<input id="kaopingmingcheng" name="kaopingmingcheng"
						 type="text"
						value=${kechengkaoping.kaoPingMingCheng } />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">分数占比：</label>
				<div class="controls">
					<input id="fenshuzhanbi" name="fenshuzhanbi" type="text"
						value=${kechengkaoping.fenShuZhanBi } />
						<span
						class="" help-inline>请输入浮点数，格式为0.0!</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">状态：</label>
				<div class="controls">
					<select id="zhuangtai" name="zhuangtai" style="width: 100px;">
						<option value="">--请选择--</option>
						<option value="0"
							<c:if test="${kechengkaoping.zhuangTai==0}">selected="selected"</c:if>>--未开始--</option>
						<option value="1"
							<c:if test="${kechengkaoping.zhuangTai==1}">selected="selected"</c:if>>--就绪--</option>
						<option value="4"
							<c:if test="${kechengkaoping.zhuangTai==4}">selected="selected"</c:if>>--完成--</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">开始时间：</label>
				<div class="controls">
					<input id="riqi" name="riqi" type="text" class="Wdate"
						style="height: 25px"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})"
						value=${kechengkaoping.kaiShiShiJian }>&emsp;
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
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var kechengid = $("#kechengid").val();
	function save() {
		if ($("#fenshuzhanbi").val() == "") {
			alert("课程不能为空！");
			return false;
		} else if ($("#riqi").val() == "") {
			alert("开始时间不能为空！");
			return false;
		} else if ($("#zhuangtai").val() == "") {
			alert("状态不能为空！");
			return false;
		} else{
			$.ajax({
				type : "POST",
				url : 'saveupdatekaoping',
				data : $("#forms").serialize(),
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data.status == "success") {
						alert("修改成功！");
					} else if (data.status == "samename") {
						alert("重名!");
					} else {
						alert("修改失败！");
					}
					toContentPage('getkaoping?id='+ kechengid);
				},
				error : function() {
					alert("登录超时!")
				}
			});
		}
	}
</script>