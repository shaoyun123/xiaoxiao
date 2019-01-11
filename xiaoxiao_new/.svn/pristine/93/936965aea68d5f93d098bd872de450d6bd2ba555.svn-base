<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.control-group{
width:　100%;
    margin-left: auto; 
    margin-right: auto;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>添加任务
			</h2>
		</div>
		<div class="box-content">
			<form class="form-horizontal" action="" method="post" id="form"
				enctype="multipart/form-data">
				<fieldset>
					<div id="pinlv" class="control-group">
						<label class="control-label" for="banji">班级：</label>
						<div class="controls">

							<select id="banji" name="banji" style="width: 150px">
								<c:forEach items="${banji}" var="BanJi">
									<option value="${BanJi.banjiid}">${BanJi.banjimingcheng}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				<div class="control-group">
						<label class="control-label" for="mingcheng">任务名称：</label>
						<div class="controls">
							<input id="mingcheng" type="text" name="mingcheng"
								class="input-xlarge focused"></input>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="yaoqiu">任务要求：</label>
						<div class="controls">
							<input type="text" id="yaoqiu" name="yaoqiu"
								class="input-xlarge focused" />
						</div>
					</div>
					<input id="banjis" name="banjis" type="hidden" />
					<div class="control-group">
						<label class="control-label" for="jiezhiriqi">截止日期：</label>
						<div class="controls">
							<input type="text" id="jiezhirichi" class="Wdate"
								style="height: 25px"
								onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
								name="jiezhiriqi">
						</div>
					</div>

					<div style="margin-left: 200px;">
						<button name="publish" type="button" class="btn btn-primary"
							onclick="sub()">提交</button>
					</div>
				</fieldset>
			</form>
		</div>


	</div>
</div>
<!--/span-->
</div>
<!--/row-->
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function sub() {
		if ($("#mingcheng").val() == "") {
			alert("请输入任务名称")
			return false;
		}
		if ($("#yaoqiu").val() == "") {
			alert("请输入任务要求")
			return false;
		}
		var select = document.getElementById("banji"); //获取select对象
		var n = 0;
		var str = [];
		for (i = 0; i < select.length; i++) {
			if (select.options[i].selected) {
				str.push(select[i].value);
				n++;
			}
		}
		if (n == 0) {
			alert("请选择班级")
			return false;
		}
		if ($("#jiezhiriqi").val() == "") {
			alert("请选择截止日期")
			return false;
		}
		document.getElementById("banjis").value = str + ",";
		$.ajax({
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "pubhuibaorenwu",//url
			data : $('#form').serialize(),
			success : function(data) {
				if (data.status == "success") {
					alert("success");
					toContentPage('huibaorenwu');
				} else {
					alert("fail!");
					toContentPage('huibaorenwu');
				}
			},
			error : function() {
				alert("异常！");
				toContentPage('huibaorenwu');
			}
		});
	}
</script>
