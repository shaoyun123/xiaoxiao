<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Main Content -->
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>修改节次方案
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<input type="hidden" name="id" value="${fangan.id}" >
			<div class="control-group" style="width: 60%">
				<span style="font-weight: bold">方案名称：</span>&emsp; <input
					id="mingcheng" name="mingcheng" size="30"
					value="${fangan.mingcheng}" />
			</div>
			<div id="set-time">
				<table
					class="table table-bordered table-striped table-condensed bootstrap-datatable " id="time">
					<thead>
						<tr style="background-color: #DEDEDE;">
							<th>节次</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>时间段</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${jcsj}" var="jcsj" varStatus="in">
							<input type="hidden" id="${in.index+1}-jieciid" value="${jcsj.id}" />
							<tr id="${in.index+1}" style="background-color: white">
								<td>${jcsj.jieci}</td>
								<td><input id="${in.index+1}-kaishishijian" type="text"
									class="Wdate" style="width: 70px"
									onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})"
									value="${jcsj.kaishishijian}" /></td>
								<td><input id="${in.index+1}-jieshushijian" type="text"
									class="Wdate" style="width: 70px"
									onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})"
									value="${jcsj.jieshushijian}" /></td>
								<td><select id="${in.index+1}-shijianduan"
									style="width: 80px">
										<option value="1"
											<c:if test="${jcsj.shijianduan==1}">selected="selected"</c:if>>上午</option>
										<option value="2"
											<c:if test="${jcsj.shijianduan==2}">selected="selected"</c:if>>下午</option>
										<option value="3"
											<c:if test="${jcsj.shijianduan==3}">selected="selected"</c:if>>晚上</option>
								</select></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<input type="hidden" id="jiecidata" name="jiecidata" /> 
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
			var jieciid = $("#" + i + "-jieciid").val();
			data += jieciid + "," + i + "," + kaishishijian + ","
					+ jieshushijian + "," + shijianduan + ";";
		}
		document.getElementById("jiecidata").value = data;
		
		$.ajax({
			type : "POST",
			url : 'save_xiugaijiecifangan',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("修改成功！");
				} else {
					alert("修改失败");
				}
				toContentPage('jiecishijianguanli');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>
