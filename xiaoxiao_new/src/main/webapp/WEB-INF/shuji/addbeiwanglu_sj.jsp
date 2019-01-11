<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加事件
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post"
			enctype="multipart/form-data">
			<fieldset>
				<div class="control-group">
					<label class="control-label">事件内容：</label>
					<div class="controls">
						<input id="neirong" name="neirong" type="text"
							class="input-xlarge focused" size="40" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">日期：</label>
					<div class="controls">
						<input id="riqi" name="riqi" type="text" class="Wdate"
							style="height: 25px"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">时间：</label>
					<div class="controls">
						<input id="shijian" name="shijian" type="text" class="Wdate"
							style="height: 25px"
							onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" />
					</div>
				</div>
				<div class="control-group">
						<label class="control-label">事件类型：</label>
						<div class="controls">
							<!-- 						<input type="radio" id="ziji" name="ziji_xuesheng"  value="0"><label style="color: red" for="ziji">自己备忘</label> -->
							<!-- 										   <input type="radio" id="xuesheng" name="ziji_xuesheng"  value="1"><label style="color: red" for="xuesheng">发给学生</label> -->
							<!--   
						                                		<input type="radio" id="banji" name="ziji_xuesheng"  value="2"><label style="color: red" for="xuesheng">发给班级</label> -->
						
<!-- 						</div> -->
<!-- 						</div> -->
<!-- 						<div>   -->
							<div style="display: inline-block;">
								<div style="display: inline-block;">
									<input type="radio" id="ziji" name="ziji_xuesheng" value="0">
								</div>
								<div style="display: inline-block;">
									<label style="color: red" for="ziji">自己备忘</label>
								</div>
							</div>
							<div style="display: inline-block;">
								<div style="display: inline-block;">
									<input type="radio" id="xuesheng" name="ziji_xuesheng"
										value="1">
								</div>
								<div style="display: inline-block;">
									<label style="color: red" for="xuesheng">发给学生</label>
								</div>
							</div>
							<div style="display: inline-block;">
								<div style="display: inline-block;">
									<input type="radio" id="banji" name="ziji_xuesheng" value="2">
								</div>
								<div style="display: inline-block;">
									<label style="color: red" for="xuesheng">发给班级</label>
								</div>
							</div>
					</div>
					</div>
				<div id="submit" style="display: none" class="form-actions">
					<button type="button" name="action" class="btn btn-primary"
						onclick="save()">保存</button>
				</div>
				<div id="next" style="display: none" class="form-actions">
					<button type="button" name="action" class="btn btn-primary"
						onclick="save()">下一步</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>

<!-- Javascript -->
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	var radio = $('input[name="ziji_xuesheng"]');
	radio.change(function() {
		var isclient = $(this).val();
		if (isclient == '0') {
			document.getElementById("next").style.display = "none";
			document.getElementById("submit").style.display = "";
		}
		if (isclient == '1') {
			document.getElementById("submit").style.display = "none";
			document.getElementById("next").style.display = "";
		}
		if (isclient == '2') {
			document.getElementById("submit").style.display = "none";
			document.getElementById("next").style.display = "";
		}
	});

	function save() {
		var neirong = $("#neirong").val();
		 var shijian = $("#shijian").val();
		 var riqi = $("#riqi").val();
		 var leixing = $('input[name="ziji_xuesheng"]:checked').val();
		if ($("#neirong").val() == "") {
			alert("内容不能为空！");
			return false;
		} else if ($("#riqi").val() == "") {
			alert("日期不能为空！");
			return false;
		} else if ($("#shijian").val() == "") {
			return false;
		} else {
			if (leixing == "0") {
				$.ajax({
					type : "POST",//方法类型
					dataType : "json",//预期服务器返回的数据类型
					url : "savebeiwang_sj",//url
					data : {
						"neirong" : neirong,
						"riqi" : riqi,
						"shijian" : shijian
					},
					success : function(result) {
						if (result.status == "success") {
							alert("success");
							toContentPage('wodebeiwanglu_sj');
						} else {
							alert("fail!");
							toContentPage('wodebeiwanglu_sj');
						}
					},
					error : function() {
						alert("异常！");
						toContentPage('wodebeiwanglu_sj');
					}
				});
			}
			if (leixing == "1") {
				toContentPage('addshijianforxuesheng_sj?neirong=' + neirong
						+ '&riqi=' + riqi + '&shijian=' + shijian);
			}
			if (leixing == "2") {
				toContentPage('addshijianforbanji_sj?neirong=' + neirong
						+ '&riqi=' + riqi + '&shijian=' + shijian);
			}
		}
	}
</script>
