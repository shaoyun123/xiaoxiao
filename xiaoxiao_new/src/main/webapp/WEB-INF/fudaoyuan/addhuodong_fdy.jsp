<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.white_content {
	display: none;
	position: absolute;
	top: 70px;
	left: 430px;
	width: 56%;
	height: 70%;
	border: 16px solid lightblue;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.white_content2 {
	display: none;
	position: absolute;
	top: 70px;
	left: 430px;
	width: 56%;
	height: 70%;
	border: 16px solid lightblue;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.kexuan {
	position: absolute;
	top:;
	left:;
	width: 48%;
	height: 95%;
	border: 4px solid gray;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.yixuan {
	position: absolute;
	top: 0;
	left: 50%;
	width: 48%;
	height: 95%;
	border: 4px solid gray;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.yixuan2 {
	position: absolute;
	top: auto;
	left: 50%;
	width: 48%;
	height: 95%;
	border: 4px solid gray;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.rightbottom1 {
	position: absolute;
	bottom: 0;
	right: 0;
	height: 5%;
}

.rightbottom2 {
	position: absolute;
	bottom: 0;
	right: 40px;
	height: 5%;
}

.kexuanren {
	position: absolute;
	top: 27px;
	display: none;
	width: 100%;
}

.yixuanren {
	position: absolute;
	top: 27px;
	display: block;
	width: 100%;
}

#mask {
	width: 100%;
	height: 100%;
	opacity: 0.7; /*半透明*/
	filter: alpha(opacity = 70); /*IE6半透明*/
	background-color: black;
	/*background-color: red;*/
	position: fixed;
	top: 0;
	left: 0;
	display: none;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>新增活动
			</h2>
		</div>
		<div class="box-content">
			<!-- Main Content -->
			<div class="card-body">
				<form action="" class="form-horizontal" method="post" id="forms">
					<fieldset>
						<input type="hidden" name="qufen" id="qufen" value="${qufen }">
						<div class="control-group">
							<label class="control-label" for="mingcheng">活动名称：</label>
							<div class="controls">
								<input id="mingcheng" name="mingcheng" type="text"
									class="input-xlarge focused" size="30" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="didian"> 活动地点：</label>
							<div class="controls">
								<input id="didian" name="didian" type="text"
									class="input-xlarge focused" size="30" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="beizhu">活动说明：</label>
							<div class="controls">
								<input id="beizhu" name="shuoming" type="text"
									class="input-xlarge focused" size="30" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">参与人：</label>
							<div class="controls">
								<label class="radio inline"> <input type="Button"
									id="Button1" name="btn1" value="按学生添加"
									onclick="ShowDiv('MyDiv')" />
								</label> <label class="radio inline"> <input type="Button"
									id="Button2" name="btn2" value="按班级添加"
									onclick="ShowDiv('MyDiv2')" />
								</label>
								<!-- 								<input id="Button1" type="Button" name="btn1" value="按学生添加" -->
								<!-- 								onclick="ShowDiv('MyDiv')" /> &emsp; -->
								<!-- 								<input id="Button2" -->
								<!-- 								type="Button" name="btn2" value="按班级添加" -->
								<!-- 								onclick="ShowDiv('MyDiv2')" />  -->
								<input id="canyuren" name="canyuren" type="hidden" /> <input
									id="huodongbanji" name="huodongbanji" type="hidden" /> <input
									type="hidden" name="leixing" id="leixing"> <input
									type="hidden" name="leixing2" id="leixing2">
							</div>
						</div>


						<div id="mask" style="width: 100%; height: 100%;"></div>
						<div id="MyDiv2" class="white_content modal hide fade in">
							<div class="modal-header">
								<button type="button" class="close btn" data-dismiss="modal">×</button>
								<h3>按班级添加</h3>
							</div>
							<div class="modal-body" style="height: 70%;">

								<!-- 弹出框 班级-->
								<div id="kexuan2" class="kexuan">
									<!-- 可选 -->
									<div>
										<input style="margin-left: 0px;" id="banji" type="button"
											value="我的班级" onclick="show('banjidiv')" />
									</div>
									<div id="banjidiv" class="kexuanren">
										<table class="table">
											<thead>
												<tr>
													<th>班级名称</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody id="wodexueshengbanji">
												<c:forEach items="${banji}" var="BanJi">
													<tr>
														<td>${BanJi.banjimingcheng}</td>
														<td>
															<div id="${BanJi.banjiid}${BanJi.banjiid}"
																style="cursor: pointer;">
																<span style="color: blue; cursor: pointer;"
																	onclick="addbanji('${BanJi.banjimingcheng}','${BanJi.banjiid}')">添加</span>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<!-- 可选end -->
								<div id="yixuan2" class="yixuan2">
									<!-- 已选 -->
									<span style="margin-left: 110px; color: red">已选班级</span>
									<table class="table">
										<thead>
											<tr>
												<th>班级名称</th>
												<!--                                     					<th>学号</th> -->
												<!--                                     					<th>姓名</th> -->
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="beixuanren2">
										</tbody>
									</table>
								</div>
							</div>
							<!-- 已选end -->
							<div id="move2" class="modal-footer"
								style="cursor: pointer; text-align: center;">
								<span style="font-size: 16px;" onclick="CloseDiv2('MyDiv2')">保存</span>
							</div>
						</div>

						<div id="mask" style="width: 100%; height: 100%;"></div>
						
						
						<div id="MyDiv" class="white_content modal hide fade in" style="display: none;" aria-hidden="false">
							<div class="modal-header">
								<button type="button" class="close btn" data-dismiss="modal">×</button>
								<h3>按学生添加</h3>
							</div>
							<div class="modal-body" style="height: 70%;">
								<!-- 弹出框 -->
								<div id="kexuan" class="kexuan" style="top: 0;">
									<!-- 可选 -->
									<div>
										<input style="margin-left: 0px;" id="xuesheng" type="button"
											value="我的学生" onclick="show('xueshengdiv','banjidiv2')" />

										<div class="controls"
											style="margin-left: 90px; margin-top: -25px;">
											<select style="width: 150px;" id="banji2" name="banji">
												<option value="">全部</option>
												<c:forEach items="${banji}" var="BanJi">
													<option value="${BanJi.banjiid}">${BanJi.banjimingcheng}</option>
												</c:forEach>
											</select> <input type="button" value="查询"
												onclick="show_wodexuesheng()" />
										</div>
										<!-- 										<input -->
										<!-- 										style="margin-left: 0px;" id="banjis" type="button" -->
										<!-- 										value="我的班级" onclick="show('banjidiv2','xueshengdiv')" /> -->
									</div>
									<div id="xueshengdiv" class="kexuanren">

										<table class="table">
											<thead>
												<tr>
													<th>班级</th>
													<th>学号</th>
													<th>姓名</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody id="wodexuesheng">
												<c:forEach items="${xuesheng}" var="XueSheng">
													<tr>
														<td>${XueSheng.banji}</td>
														<td>${XueSheng.xuehao}</td>
														<td>${XueSheng.xingming}</td>
														<td>
															<div id="${XueSheng.xueshengid}${XueSheng.xueshengid}"
																style="cursor: pointer;">
																<span style="color: blue; cursor: pointer;"
																	onclick="add('${XueSheng.banji}','${XueSheng.xuehao}','${XueSheng.xingming}','${XueSheng.xueshengid}')">添加</span>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>

									<div id="banjidiv2" class="kexuanren">
										<table class="table">
											<thead>
												<tr>
													<th>班级名称</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody id="wodexueshengbanji">
												<c:forEach items="${banji}" var="BanJi">
													<tr>
														<td>${BanJi.banjimingcheng}</td>
														<td>
															<div id="${BanJi.banjiid}${BanJi.banjiid}"
																style="cursor: pointer;">
																<span style="color: blue; cursor: pointer;"
																	onclick="add('${BanJi.banjimingcheng}','','','${BanJi.banjiid}')">添加</span>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>

								</div>
								<!-- 可选end -->
								<div id="yixuan" class="yixuan">
									<!-- 已选 -->
									<span style="margin-left: 110px; color: red">已选参与人</span>
									<div id="yixuanren" class="yixuanren">
										<table class="table">
											<thead>
												<tr>
													<th>班级</th>
													<th>学号</th>
													<th>姓名</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody id="beixuanren">
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<!-- 已选end -->
							<div id="move" class="modal-footer"
								style="cursor: pointer; text-align: center;">
								<span style="font-size: 16px;" onclick="CloseDiv('MyDiv')">保存</span>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="riqi">活动日期：</label>
							<div class="controls">
								<!-- 							<input -->
								<!-- 								id="riqi" name="riqi" type="text" class="input-xlarge datepicker" -->
								<!-- 								value="" /> -->
								<input id="riqi" name="riqi" type="text" class="Wdate"
									style="height: 25px"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="kaishishijian">开始时间：</label>
							<div class="controls">
								<input id="kaishishijian" name="kaishishijian" type="text"
									class="Wdate" style="height: 25px"
									onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="jieshushijian">结束时间：</label>
							<div class="controls">
								<input id="jieshushijian" name="jieshushijian" type="text"
									class="Wdate" style="height: 25px"
									onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="jieshushijian">预提醒时间：</label>
							<div class="controls">
								<label class="checkbox inline"> <input type="checkbox"
									id="check1" onclick="check()">
								<div id="hidetime" style="display: none" style="width:120px;">
									<select id="booktime" name="booktime">
										<option value="2">提前2小时</option>
										<option value="4">提前4小时</option>
										<option value="6">提前6小时</option>
										<option value="8">提前8小时</option>
										<option value="24">提前一天</option>
									</select>
								</div>
								</label>
								<input type="hidden" name="ischecked" id="ischecked">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="focusedInput"></label>
							<div class="controls">
								<button type="button" style="margin-left: 30px;"
									class="btn btn-success" value="提交" onclick="save()">提交</button>
							</div>
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
</div>
<!-- Main Content End-->
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function save() {
		if ($("#mingcheng").val() == "") {
			alert("请填写活动名称！");
			return false;
		}
		if ($("#didian").val() == "") {
			alert("请填写活动地点！");
			return false;
		}
		if ($("#leixing2").val() == "" && $("#leixing").val() == "") {
			alert("请选择参与人！！");
			return false;
		}
		if ($("#leixing2").val() == "按班级添加") {
			if ($("#huodongbanji").val() == "") {
				alert("请选择班级!");
				return false;
			}

		}
		if ($("#leixing").val() == "按学生添加") {
			if ($("#canyuren").val() == "") {
				alert("请选择学生！!");
				return false;
			}

		}
		if ($("#riqi").val() == "") {
			alert("请选择活动日期！");
			return false;
		}
		if ($("#kaishishijian").val() == "") {
			alert("请选择活动开始时间！");
			return false;
		}
		if ($("#jieshushijian").val() == "") {
			alert("请选择活动结束时间！");
			return false;
		}

		$.ajax({
			type : "POST",
			url : 'savehuodong_fdy',
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
				toContentPage('myhuodong_fdy');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
</script>
<script type="text/javascript">
	$(function() {
		$(".close").click(function() {
			document.getElementById("mask").style.display = 'none';
			document.getElementById("MyDiv2").style.display = 'none';
			document.getElementById("MyDiv").style.display = 'none';
		})
	})
	//弹出隐藏层
	function ShowDiv(show_div) {
		if (show_div == "MyDiv") {
			// 			document.getElementById(show_div).style.display = 'block';
			// 			document.getElementById("MyDiv2").style.display = 'none';

			document.getElementById("mask").style.display = 'block';
			document.getElementById("MyDiv").style.display = 'block';
		}
		if (show_div == "MyDiv2") {
			// 			document.getElementById(show_div).style.display = 'block';
			// 			document.getElementById("MyDiv").style.display = 'none';

			document.getElementById("mask").style.display = 'block';
			document.getElementById("MyDiv2").style.display = 'block';
		}

	};
	//关闭弹出层，保存数据
	function CloseDiv(close_div) {
		var a = document.getElementById("beixuanren")
				.getElementsByTagName("tr")
		var s = "";

		for (var i = 0; i < a.length; i++) {//将添加的参与人的id拼接成s，逗号隔开
			s += a[i].id + ',1;';
		}
		document.getElementById("canyuren").value = s;
		document.getElementById("leixing").value = "按学生添加";
		document.getElementById("leixing2").value = "";
		document.getElementById(close_div).style.display = 'none';
		document.getElementById("mask").style.display = 'none';

	};

	function CloseDiv2(close_div) {

		console.log(document.getElementById("yixuan").style.display == 'none'); // 隐藏
		var s = "";
		var b = document.getElementById("beixuanren2").getElementsByTagName(
				"tr");

		for (var i = 0; i < b.length; i++) {//将添加的参与人的id拼接成s，逗号隔开
			s += b[i].id + ',';
		}
		console.log(s);
		document.getElementById("huodongbanji").value = s;
		document.getElementById("leixing").value = "";
		document.getElementById("leixing2").value = "按班级添加";
		document.getElementById(close_div).style.display = 'none';
		document.getElementById("mask").style.display = 'none';

	};
	//显示同班、社团、好友
	function show(show, close) {
		console.log(show == "xueshengdiv");
		document.getElementById(show).style.display = 'block';
		document.getElementById(close).style.display = 'none';
	};
	//添加参与人
	function add(banji, xuehao, xingming, xueshengid) {
		var code = '';
		var id = '';
		if (xuehao == "" || null == xuehao) {
			$
					.ajax({
						type : "POST",
						url : 'show_wodexuesheng',
						data : {
							CODE : xueshengid
						},
						dataType : 'json',
						cache : false,
						timeout : 5000,
						async : false,
						success : function(data) {
							var data = eval(data);
							for (var i = 0; i < data.length; i++) {
								var b = document.getElementById("beixuanren")
										.getElementsByTagName("tr");
								var a = 0;
								for (var j = 0; j < b.length; j++) {
									if (data[i].xueshengid == b[j].id) {
										a = 1;
									}
								}
								if (a == 0) {
									code += '<tr id="'+data[i].xueshengid+'"><td>'
											+ data[i].banji
											+ '</td><td>'
											+ data[i].xuehao
											+ '</td><td>'
											+ data[i].xingming
											+ '</td><td><span style="color:blue;cursor:pointer;" onclick="del('
											+ xueshengid
											+ ',\''
											+ data[i].xueshengid
											+ '\')">删除</span></td></tr>';
								} else {
									// 													alert("该学生已经添加过了！！！");
								}
							}
						},
						error : function() {
							alert("登录超时!")
						}

					});
			id = xueshengid + xueshengid;

		} else {
			var b = document.getElementById("beixuanren").getElementsByTagName(
					"tr");
			var a = 0;
			for (var j = 0; j < b.length; j++) {
				if (xueshengid == b[j].id) {
					a = 1;
				}
			}
			if (a == 0) {
				code += '<tr id="'+xueshengid+'"><td>'
						+ banji
						+ '</td><td>'
						+ xuehao
						+ '</td><td>'
						+ xingming
						+ '</td><td><span style="color:blue;cursor:pointer;" onclick="del(0,\''
						+ xueshengid + '\')">删除</span></td></tr>';
			} else {
				alert("该学生已经添加过了！！！");
			}
			id = xueshengid + xueshengid;
		}
		$("#beixuanren").append(code);
		console.log(id);
		document.getElementById(id).style.display = "none";

	}
	//删除已选参与人
	function del(banjiid, xueshengid) {
		var id = '';
		if (banjiid == 0) {
			id = xueshengid + '' + xueshengid;
		} else {
			id = banjiid + '' + banjiid;
		}
		document.getElementById(id).style.display = "block";
		var tr = document.getElementById(xueshengid);
		tr.parentNode.removeChild(tr);
	}
	function show_wodexuesheng() {
		document.getElementById("xueshengdiv").style.display = 'block';
		document.getElementById("banjidiv2").style.display = 'none';
		var banjiid = document.getElementById("banji2").value;
		$
				.ajax({
					type : "POST",
					url : 'show_wodexuesheng',
					data : {
						CODE : banjiid
					},
					dataType : 'json',
					cache : false,
					timeout : 5000,
					async : true,
					success : function(data) {
						var data = eval(data);
						var code = "";
						for (var i = 0; i < data.length; i++) {
							code += '<tr><td>'
									+ data[i].banji
									+ '</td><td>'
									+ data[i].xuehao
									+ '</td><td>'
									+ data[i].xingming
									+ '</td>'
									+ '<td><div id="'+data[i].xueshengid+data[i].xueshengid+'" style="cursor:pointer;">'
									+ '<span style="color:blue;cursor:pointer;" onclick="add(\''
									+ data[i].banji + '\',\'' + data[i].xuehao
									+ '\',\'' + data[i].xingming + '\',\''
									+ data[i].xueshengid + '\')">添加</span>'
									+ '</div></td></tr>';
						}
						$("#wodexuesheng").html(code);
					},
					error : function() {
						alert("登录超时!")
					}

				});
	}
</script>
<script type="text/javascript">
// 	var posX;
// 	var posY;
// 	fwuss = document.getElementById("MyDiv");
// 	fwuss.onmousedown = function(e) {
// 		posX = event.x - fwuss.offsetLeft;//获得横坐标x
// 		posY = event.y - fwuss.offsetTop;//获得纵坐标y
// 		document.onmousemove = mousemove;//调用函数，只要一直按着按钮就能一直调用
// 	}
// 	document.onmouseup = function() {
// 		document.onmousemove = null;//鼠标举起，停止
// 	}
// 	function mousemove(ev) {
// 		if (ev == null)
// 			ev = window.event;//IE
// 		fwuss.style.left = (ev.clientX - posX) + "px";
// 		fwuss.style.top = (ev.clientY - posY) + "px";
// 	}
</script>
<script type="text/javascript">
	function addbanji(banjimingcheng, banjiid) {
		var code = "";
		code += '<tr id="'+banjiid+'"><td>'
				+ banjimingcheng
				+ '</td><td><span style="color:blue;cursor:pointer;" onclick="del2(\''
				+ banjiid + '\')">删除</span></td></tr>';
		$("#beixuanren2").append(code);
		id = banjiid + '' + banjiid;
		console.log(id);
		document.getElementById(id).style.display = "none";
	}
	function del2(banjiid, banjimingcheng) {
		var id = banjiid + '' + banjiid;
		document.getElementById(id).style.display = "block";
		var tr = document.getElementById(banjiid);
		tr.parentNode.removeChild(tr);
	}
</script>
<script type="text/javascript">
	function check() {

		var isChecked = document.getElementById("check1").value;
		if (isChecked == "on") {
			$("#hidetime").show();
			document.getElementById("ischecked").value = "1";

		} else {
			$("#hidetime").hide();
			document.getElementById("ischecked").value = "0";
		}
	}
</script>
