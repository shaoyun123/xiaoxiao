<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>新增节次方案
			</h2>
		</div>
		<div class="box-content">
			<form action="" class="form-horizontal" method="post" id="forms">
				<div class="sub-title" style="width: 60%">
					<span style="font-weight: bold">方案名称：</span>&emsp; <input
						id="mingcheng" name="mingcheng" size="30" /> <input
						style="margin-right: 30px; width: 50px; font-weight: bold"
						class="pull-right" type="button" onclick="del()" value="—" /> <input
						style="width: 50px; font-weight: bold" class="pull-right"
						type="button" onclick="add()" value="+" /> <span
						style="font-weight: bold; margin-right: 20px;" class="pull-right">节数:</span>
				</div>
				<div id="set-time">
					<table id="time" class="table" style="width: 60%">
						<thead>
							<tr style="background-color: #DEDEDE;">
								<th>节次</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>时间段</th>
							</tr>
						</thead>
						<tbody>
							<tr id="1" style="background-color: white">
								<td>1</td>
								<td><input id="1-kaishishijian" type="text" class="Wdate"
									style="width: 70px"
									onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" /></td>
								<td><input id="1-jieshushijian" type="text" class="Wdate"
									style="width: 70px"
									onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" /></td>
								<td><select id="1-shijianduan" style="width: 80px">
										<option selected value="">--请选择--</option>
										<option value="1">上午</option>
										<option value="2">下午</option>
										<option value="3">晚上</option>
								</select></td>
							</tr>
						</tbody>
					</table>
				</div>
				<input type="hidden" id="jiecidata" name="jiecidata" /> 
<!-- 				<span -->
<!-- 					class="pull-right"><input type="submit" -->
<!-- 					class="flip-link btn btn-info" value="保存" onclick="return save()"></span> -->
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
</div>
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function add() {
		var num = $("#time tbody tr").length;
		var i = num + 1;
		var code = '<tr id="'+i+'"style="background-color:white">'
				+ '<td>'
				+ i
				+ '</td>'
				+ '<td><input id="'
				+ i
				+ '-kaishishijian" type="text" class="Wdate" style="width:70px" onclick="WdatePicker({readOnly:true,dateFmt:\'HH:mm\'})"/></td>'
				+ '<td><input id="'
				+ i
				+ '-jieshushijian" type="text" class="Wdate" style="width:70px" onclick="WdatePicker({readOnly:true,dateFmt:\'HH:mm\'})"/></td>'
				+ '<td><select id="'+i+'-shijianduan" style="width:80px">'
				+ '<option selected value="">--请选择--</option>'
				+ '<option value="1">上午</option>'
				+ '<option value="2">下午</option>'
				+ '<option value="3">晚上</option></select></td></tr>';
		$("#time").append(code);
	}
	function del() {
		var num = $("#time tbody tr").length;
		$("tr[id=" + num + "]").remove();
	}
	function save() {
		var num = $("#time tbody tr").length;
		var data = "";
		if ($("#mingcheng").val() == "") {
			alert("请填写方案名称！")
			return false;
		}
		for (var i = 1; i <= num; i++) {
			if ($("#" + i + "-kaishishijian").val() == "") {
				alert("error：第" + i + "节的'开始时间'")
				return false;
			}
			var kaishishijian = $("#" + i + "-kaishishijian").val();
			if ($("#" + i + "-jieshushijian").val() == "") {
				alert("error：第" + i + "节的'结束时间'")
				return false;
			}
			var jieshushijian = $("#" + i + "-jieshushijian").val();
			if ($("#" + i + "-shijianduan").val() == "") {
				alert("error：第" + i + "节的'时间段'")
				return false;
			}
			var shijianduan = $("#" + i + "-shijianduan").val();
			data += i + "," + kaishishijian + "," + jieshushijian + ","
					+ shijianduan + ";";
		}
		document.getElementById("jiecidata").value = data;
		
		$.ajax({
			type : "POST",
			url : 'save_addjiecifangan',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("添加成功！");
				} else {
					alert("添加失败");
				}
				toContentPage('jiecishijianguanli');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>