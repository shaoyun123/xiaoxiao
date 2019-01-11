<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加考评
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
		<input id="kechengid" name="kechengid"  type="hidden" value=${kechengid } />
			<div class="control-group">
				<label class="control-label">课程名称：</label>
				<div class="controls">
					<input id="kechengmingcheng" name="kechengmingcheng" disabled= "disabled" type="text" value=${kechengmingcheng } />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">简报名称：</label>
				<div class="controls">
					<input id="jianbaomingcheng" name="jianbaomingcheng" type="text" />
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">开始时间：</label>
				<div class="controls">
					<input id="kaishishijian" name="kaishishijian" type="text" class="Wdate"
						style="height: 25px"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" />&emsp;
				</div>
			</div>
				<div class="control-group">
				<label class="control-label">结束时间：</label>
				<div class="controls">
					<input id="jieshushijian" name="jieshushijian" type="text" class="Wdate"
						style="height: 25px"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" />&emsp;
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">简报状态：</label>
				<div class="controls">
					<select id="zhuangtai" name="zhuangtai" style="width: 100px;">
						<option value="">--请选择--</option>
						<option value="0">未开始</option>
						<option value="1">开始</option>
						<option value="2">完成</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">说明：</label>
				<div class="controls">
				<input id="shuoming" name="shuoming"  type="text" />
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
	function save() {
		var kechengid = ${kechengid};
		if ($("#jianbaomingcheng").val() == "") {
			alert("简报名称不能为空!");
			return false;
		}
		else if ($("#kaishishijian").val() == "") {
			alert("开始时间不能为空!");
			return false;
		} else if ($("#jieshushijian").val() == "") {
			alert("结束时间不能为空！");
			return false;
		} else if ($("#zhuangtai").val() == "") {
			alert("状态不能为空！");
			return false;
		} 
// 		else if ($("#shuoming").val() == "") {
// 			alert("简报说明不能为空！");
// 			return false;
// 		}
		else {
			$.ajax({
				type : "POST",
				url : 'savejianbao',
				data : $("#forms").serialize(),
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data.status == "success") {
						alert("添加成功！");
					} 
					if(data.status == "samename")
					{
						alert("重名！");
					}
					if(data.status == "fail")
					{
						alert("添加失败！");
					}
					toContentPage('getwendang?kechengid='+kechengid);
				},
				error : function() {
					alert("系统异常!");
					toContentPage('getwendang?kechengid='+kechengid);
				}
			});
		}
		
	}
</script>