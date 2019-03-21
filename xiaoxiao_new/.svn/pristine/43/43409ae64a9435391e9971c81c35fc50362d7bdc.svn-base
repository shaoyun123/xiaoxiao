<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>实践课设置
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="forms">
			<input id="yonghuid" name="yonghuid" type="hidden" size="30"
				value="${yonghus}" /> <input id="renkelaoshiids"
				name="renkelaoshiids" type="hidden" size="30" />
			<div class="control-group">
				<label class="control-label">选择实践课：</label>
				<div class="controls">
					<select id="shijiankeid" name="shijiankeid" style="width: 100px;">
						<option value="">--请选择--</option>
						<c:forEach items="${shijiankelist}" var="shijianke">
							<option value="${shijianke.shixiid}">${shijianke.keChengMingCheng}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">选择类型：</label>
				<div class="controls">
					<select id="leixing" name="leixing" style="width: 100px;"
						onchange="show(this.id)">
						<option value="">--请选择--</option>
						<option value="0">一个老师</option>
						<option value="1">多个老师</option>
					</select>
				</div>
			</div>
			<div class="control-group" id="xuanlaoshi" style="display: none;">
				<label class="control-label">选择老师：</label>
				<div class="controls">
					<!-- 					<select id="laoshiid" name="laoshiid" style="width: 100px;" -->
					<!-- 						multiple="multiple"> -->
					<!-- 						<option value="">--请选择--</option> -->
					<%-- 						<c:forEach items="${yonghus}" var="yonghu"> --%>
					<%-- 							<option value="${yonghu.yonghuid}">${yonghu.yonghuxingming}</option> --%>
					<%-- 						</c:forEach> --%>
					<!-- 					</select> -->
					<a onclick="addlaoshi()" style="text-decoration: none;"><i
						title="添加考题" class="icon-plus"></i></a>
					<div id="add"></div>

				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否设置题目：</label>
				<div class="controls">
					<select id="isxuanti" name="isxuanti" style="width: 100px;"
						onchange="show(this.id)">
						<option value="">--请选择--</option>
						<option value="1">选题</option>
						<option value="0" id="hidded">无题</option>
					</select>
				</div>
			</div>

			<div class="control-group" id="xuantileixing" style="display: none">
				<label class="control-label">选题类型：</label>
				<div class="controls">
					<select id="xuantifangshi" name="xuantifangshi"
						style="width: 100px;" onchange="show(this.id)">
						<option value="">--请选择--</option>
						<option value="0">一题一人</option>
						<option value="1">一题多人</option>
					</select>
				</div>
			</div>

			<!-- 			<div class="control-group" id="isfenzu" style="display: none;"> -->
			<!-- 				<label class="control-label">是否分组：</label> -->
			<!-- 				<div class="controls"> -->
			<!-- 					<select id="shifoufenzu" name="shifoufenzu" style="width: 100px;" -->
			<!-- 						onchange="show(this.id)"> -->
			<!-- 						<option value="">--请选择--</option> -->
			<!-- 						<option value="1">分组</option> -->
			<!-- 						<option value="0">不分组</option> -->
			<!-- 					</select> -->
			<!-- 				</div> -->
			<!-- 			</div> -->

			<!-- 			<div class="control-group" id="fenzuleixing" style="display: none;"> -->
			<!-- 				<label class="control-label">分组类型：</label> -->
			<!-- 				<div class="controls"> -->
			<!-- 					<select id="fenzufangshi" name="fenzufangshi" style="width: 100px;"> -->
			<!-- 						<option value="">--请选择--</option> -->
			<!-- 						<option id="hidden" value="1">按题目</option> -->
			<!-- 						<option value="0">不按题目</option> -->
			<!-- 					</select> -->
			<!-- 				</div> -->
			<!-- 			</div> -->

			<div class="control-group" id="dazu" style="display: none;">
				<label class="control-label">分大组：</label>
				<div class="controls">
					<select id="fendazu" name="fendazu" style="width: 100px;"
						onchange="show(this.id)">
						<option value="">--请选择--</option>
						<option value="1">是</option>
						<option value="0">否</option>
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

			<div class="control-group" id="xiaozu" style="display: none;">
				<label class="control-label">分小组：</label>
				<div class="controls">
					<select id="fenxiaozu" name="fenxiaozu" style="width: 100px;"
						onchange="show(this.id)">
						<option value="">--请选择--</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
			</div>

			<div class="control-group" id="xiaozurongliang" style="display: none">
				<label class="control-label">小组容量：</label>
				<div class="controls">
					<input id="rongliang" name="rongliang" type="number" size="30" />
					<span class="" help-inline>请输入整数!</span>
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
		var renkelaoshiids = '';
		var id = $("#shijiankeid").val();
		//拼接老师id和数量
		var select = [];
		$("select[name='laoshi']").each(function() {
			if ($(this).val()) {
				select.push($(this).val());
			}
		});
		var input = [];
		$("input[name='shuliang']").each(function() {
			if ($(this).val()) {
				input.push($(this).val());
			}
		});
		for (var i = 0; i < select.length; i++) {
			if (select[i] == '' || input[i] == '') {
				alert("请选择老师，或者填写容量!");
				return;
			}
			renkelaoshiids += select[i] + ',' + input[i] + ';';
			document.getElementById("renkelaoshiids").value = renkelaoshiids;
		}
		if ($("#shijiankeid").val() == "") {
			alert("请选择课程!");
			return false;
		}
		if ($("#leixing").val() == "") {
			alert("请选择类型！");
			return false;
		}
		if ($("#leixing").val() == 1) {
			if (renkelaoshiids == "") {
				alert("请添加任课老师!");
				return false;
			}
		}
		if ($("#laoshiid").val() == "") {
			alert("请选择课程!");
			return false;
		}
		if ($("#isxuanti").val() == "") {
			alert("请选题题目类型！");
			return false;
		}
		if ($("#isxuanti").val() == "1") {
			if ($("#xuantifangshi").val() == "") {
				alert("请选择选题方式！");
				return false;
			}
		}
		if ($("#isxuanti").val() == "0") {
			if ($("#fenxiaozu").val() == "") {
				alert("请选择是否分小组！");
				return false;
			}
			if ($("#fenxiaozu").val() == 1) {
				if ($("#rongliang").val() == "") {
					alert("请填写小组容量！");
					return false;
				}
			}
			if ($("#fendzu").val() == "0") {
				if ($("#dazuzhangid").val() == "") {
					alert("请选择大组长！");
					return false;
				}
			}
		}
		$.ajax({
			type : "POST",
			url : 'saveshezhi',
			data : $("#forms").serialize(),
			dataType : 'json',
			success : function(data) {
				if (data.status == "success") {
					alert("设置成功！");
				} else if (data.status == "noshijianke") {
					alert("没有实践课");
				} else {
					alert("设置失败");
				}
				toContentPage("shezhi");
			},
			error : function() {
				alert("登录超时!")
			}
		});
	}

	function show(id) {
		var xuanzeleixing = document.getElementById(id).value;
		if (id == "leixing") {
			if (xuanzeleixing == 1) { // 多个老师
				$("#xuanlaoshi").show();
				$("#hidded").hide();
				$("#isxuanti").val("");

				$("#dazu").hide();
				$("#xiaozu").hide();
				$("#xuantileixing").hide();
				$("#fenzuleixing").hide();
				$("#xiaozurongliang").hide();
				$("#dazuid").hide();
			} else {
				$("#xuanlaoshi").hide();
				$("#hidded").show();
				$("#isxuanti").val("");

				$("#dazu").hide();
				$("#xiaozu").hide();
				$("#xuantileixing").hide();
				$("#fenzuleixing").hide();
				$("#xiaozurongliang").hide();
				$("#dazuid").hide();
			}
		}
		if (id == "isxuanti") {
			if (xuanzeleixing == 1) {
				$("#dazu").hide();
				$("#xiaozu").hide();
				$("#xuantileixing").show();
			}
			if (xuanzeleixing == 0) {
				$("#xuantileixing").hide();
				$("#dazu").show();
				$("#xiaozu").show();
			}
		}

		if (id == "xuantifangshi") {
			if (xuanzeleixing == "0") {
				$("#hidden").hide();
				$("#fenzuleixing").show();
			}
			if (xuanzeleixing == "1") {
				$("#fenzuleixing").show();
			}
		}

		if (id == "fenxiaozu") {
			if (xuanzeleixing == 1) {
				$("#xuantileixing").hide();
				$("#xiaozurongliang").show();
			}
			if (xuanzeleixing == 0) {
				$("#xuantileixing").hide();
				$("#xiaozurongliang").hide();
			}
		}
		if (id == "fendazu") {
			if (xuanzeleixing == 0) {
				$("#xuantileixing").hide();
				getshijianke();
				$("#dazuid").show();
			}
			if (xuanzeleixing == 1) {
				$("#xuantileixing").hide();
				$("#dazuid").hide();
			}
		}
	}
	function getshijianke() {
		var id = $("#shijiankeid").val();
		if (id == "") {
			alert("请选择实践课!");
			return false;
		}
		$
				.ajax({
					type : "POST",
					url : 'getunfenzustu',
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
									'<option value="'+data.xueshengs[i].xueshengid+'">'
											+ data.xueshengs[i].xingming
											+ '</option>');
						}
					},
					error : function() {
						alert("登录超时!")
					}
				});
	}

	var i = 0;
	function addlaoshi() {
		$
				.ajax({
					type : "POST",
					url : 'getyonghuinfo',
					dataType : 'json',
					cache : false,
					timeout : 5000,
					async : true,
					success : function(data) {
						var code = "";
						code += '</br><select name ="laoshi" id="laoshiid-'+i+'" style="width: 100px">';
						code += '<option value="">请选择</option>';
						for (var a = 0; a < data.data.length; a++) {
							code += '<option value="'+data.data[a].yonghuid+'">'
									+ data.data[a].yonghuxingming + '</option>';

						}
						code += '</select>';
						code += '<span>容量：</span><input id ="shuliangid-'+i+'" name="shuliang"></input>';
						$("#add").append(code);
						i++;
					},
					error : function() {
						alert("error");
					}
				});

	}
</script>
</body>
</html>