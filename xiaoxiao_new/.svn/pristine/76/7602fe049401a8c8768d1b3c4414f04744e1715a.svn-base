<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>修改考题
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<input type="hidden" name="shixiid" id="shixiid"
				value="${kaoti.shixiid}" /> <input type="hidden" name="kaotiid"
				id="kaotiid" value="${kaoti.id}" />
			<div class="control-group">
				<label class="control-label">名称:</label>
				<div class="controls">
					<input id="fabumingcheng" name="fabumingcheng" type="text"
						size="30" value="${kaoti.mingcheng}" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">容量:</label>
				<div class="controls">
					<input id="rongliang" name="rongliang" type="number" size="30"
						value="${kaoti.rongliang}" /> <span class="" help-inline>请输入整数!</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否分组：</label>
				<div class="controls">
					<select id="shifoufenzu" name="shifoufenzu" style="width: 100px;"
						onchange="show(this.id)">
						<option value="">--请选择--</option>
						<option value="1">分组</option>
						<option value="0">不分组</option>
					</select>
				</div>
			</div>
			
			<div class="control-group" id="dazuid" style="display: none">
				<label class="control-label">指定大组长：</label>
				<div class="controls">
					<select id="dazuzhangid" name="dazuzhangid" style="width: 100px;">
						<option value="">--请选择--</option>
					</select>
				</div>
			</div>

			<div class="control-group" id="fenzuleixing" style="display: none;">
				<label class="control-label">分组类型：</label>
				<div class="controls">
					<select id="fenzufangshi" name="fenzufangshi" style="width: 100px;">
						<option value="">--请选择--</option>
						<option id="hidden" value="1">按题目</option>
						<option value="0">不按题目</option>
					</select>
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
		var shixiid = $("#shixiid").val();
		if ($("#fabumingcheng").val() == "") {
			alert("名称不能为空！");
			return false;
		} 
		else if ($("#rongliang").val() == "") {
			alert("容量不能为空！");
			return false;
		}
		else if($("#shifoufenzu").val()==""){
			alert("请选择是否分组!");
			return false;
		}
		else if($("#shifoufenzu").val()==1){
			if($("#fenzufangshi").val()==""){
				alert("请选择分组方式!");
				return false;
			}
		}else if(($("#shifoufenzu").val()==0)){
			if($("#dazuzhangid").val()==""){
				alert("请选择大组长!");
				return false;
			}
		}
		$.ajax({
			type : "POST",
			url : 'saveupdateshijianti',
			data : $("#forms").serialize(),
			dataType : 'json',
			success : function(data) {
				if (data.status == "success") {
					alert("修改成功！");
				}
				else if(data.status == "samename"){
					alert("重名!");
				}
				else {
					alert("添加失败");
				}
				toContentPage('laoshikaoti?shixiid='+shixiid);
			},
			error : function() {
				alert("error!")
				toContentPage('laoshikaoti?shixiid='+shixiid);
			}
		});
	}
	function show(id) {
		var xuanzeleixing = $("#"+id).val();
		if (id == "shifoufenzu") {
			if (xuanzeleixing == 1) {
// 				$("#fenzuleixing").show();
				$("#fenzuleixing").css("display","block");
			} 
			if(xuanzeleixing == 0){
				$("#fenzuleixing").hide();
				getdazuzhang();
				$("#dazuid").show();
			}
		}
	
	}
	function getdazuzhang() {
		var id = $("#shixiid").val();
		if (id == "") {
			alert("请选择实践课!");
			return false;
		}
		$
				.ajax({
					type : "POST",
					url : 'getbufenzustu',
					data : {
						"shijiankeid" : id
					},
					dataType : 'json',
					cache : false,
					timeout : 5000,
					async : true,
					success : function(data) {
						var code = "";
						for (var i = 0; i < data.xueshengs.length; i++) {
							$("#dazuzhangid").append(
									'<option value="'+data.xueshengs[i].ID+'">'
											+ data.xueshengs[i].xingming
											+ '</option>');
						}
					},
					error : function() {
						alert("登录超时!")
					}
				});
	}
</script>