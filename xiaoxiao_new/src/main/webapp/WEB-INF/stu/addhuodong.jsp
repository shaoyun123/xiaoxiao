<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.white_content {
	display: none;
	position: absolute;
	top: 10%;
	left: 40%;
	width: 50%;
	height: 70%;
	border: 16px solid lightblue;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.kexuan {
	position: absolute;
	top: 0;
	left:;
	width: 50%;
	height: 95%;
	border: 6px solid gray;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.yixuan {
	position: absolute;
	top: 0;
	left: 50%;
	width: 50%;
	height: 95%;
	border: 6px solid gray;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.rightbottom1 {
	position: absolute;
	bottom: 0;
	right: 0;
}

.rightbottom2 {
	position: absolute;
	bottom: 0;
	right: 40px;
}

.kexuanren {
	position: absolute;
	top: 27px;
	display: none;
}

.yixuanren {
	position: absolute;
	top: 27px;
	display: block;
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

<!-- Main Content -->
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加活动
		</h2>
	</div>
	<div class="box-content">
		<form action="savehuodong" class="form-inline" method="post">
			<input type="hidden" name="qufen" value="${qufen }">
			<div class="sub-title">
				<span style="font-weight: bold;">活动名称：</span>&emsp;<input
					id="mingcheng" name="mingcheng" type="text" class="form-control"
					size="30" />
			</div>
			<div class="sub-title">
				<span style="font-weight: bold;">活动地点：</span>&emsp;<input
					id="didian" name="didian" type="text" class="form-control"
					size="30" />
			</div>
			<div class="sub-title">
				<span style="font-weight: bold;">活动说明：</span>&emsp;<input
					id="beizhu" name="beizhu" type="text" class="form-control"
					size="30" />
			</div>
			<div class="sub-title">
				<span style="font-weight: bold;">参与人：</span>&emsp;<input
					id="Button1" type="button" value="编辑" onclick="ShowDiv('MyDiv')" />
				<input id="canyuren" name="canyuren" type="hidden" />
			</div>


			<div id="mask" style="width: 100%; height: 100%;"></div>
			<div id="MyDiv" class="white_content modal hide fade in">
				<!-- 弹出框 -->
				<div id="kexuan" class="kexuan" style="height: 85%;">
					<!-- 可选 -->
					&emsp;&emsp;&emsp;&emsp;&emsp;<input id="tongban" type="button"
						value="同班同学" onclick="show('tongbandiv','shetuandiv','haoyoudiv')" />
					<input id="shetuan" type="button" value="社团同学"
						onclick="show('shetuandiv','tongbandiv','haoyoudiv')" />
					<div id="tongbandiv" class="kexuanren" style="width: 100%;">
						<table class="table">
							<thead>
								<tr>
									<th style="width: 40%">学号</th>
									<th style="width: 40%">姓名</th>
									<th style="width: 20%">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${xuesheng}" var="XueSheng">
									<tr>
										<td>${XueSheng.xuehao}</td>
										<td>${XueSheng.xingming}</td>
										<td><div
												id="${XueSheng.xueshengid}${XueSheng.xueshengid}">
												<span style="color: blue; cursor: pointer;"
													onclick="add('${XueSheng.xuehao}','${XueSheng.xingming}','${XueSheng.xueshengid}')">添加</span>
											</div></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div id="shetuandiv" class="kexuanren" style="width: 100%;">
						<table class="table">
							<thead>
								<tr>
									<th style="width: 20%">姓名</th>
									<th style="width: 60%">社团</th>
									<th style="width: 20%">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${sheyuan}" var="SheYuan">
									<tr>
										<td>${SheYuan.xingming}</td>
										<td>${SheYuan.shetuanmingcheng}</td>
										<td><div id="${SheYuan.xueshengid}${SheYuan.xueshengid}">
												<span style="color: blue; cursor: pointer;"
													onclick="add('${SheYuan.xuehao}','${SheYuan.xingming}','${SheYuan.xueshengid}')">添加</span>
											</div></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 可选end -->
				<div id="yixuan" class="yixuan" style="height: 85%;">
					<!-- 已选 -->
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span
						style="color: red">已选参与人</span>
					<div id="yixuanren" class="yixuanren" style="width: 100%;">
						<table class="table">
							<thead>
								<tr>
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
				<!-- 已选end -->
				<!--                                     	<div id="move" class="rightbottom1" style="cursor:pointer;"> -->
				<!--                                     		<span style="font-size: 16px;" onclick="CloseDiv('MyDiv')">保存</span> -->
				<!--                                     	</div> -->


				<!-- 已选end -->
				<div id="move" class="modal-footer"
					style="cursor: pointer; text-align: center; position: absolute; bottom: 0; width: 100%;">
					<span style="font-size: 16px;" onclick="CloseDiv('MyDiv')">保存</span>
				</div>


			</div>
			<div class="sub-title">
				<span style="font-weight: bold;">活动日期：</span>&emsp;<input id="riqi"
					name="riqi" type="text" class="Wdate" style="height: 25px"
					onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
			</div>
			<div class="sub-title">
				<span style="font-weight: bold;">开始时间：</span>&emsp;<input
					id="kaishishijian" name="kaishishijian" type="text" class="Wdate"
					style="height: 25px"
					onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" />
			</div>
			<div class="sub-title">
				<span style="font-weight: bold;">结束时间：</span>&emsp;<input
					id="jieshushijian" name="jieshushijian" type="text" class="Wdate"
					style="height: 25px"
					onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" />
			</div>
			<span class="pull-right"><input type="submit"
				class="flip-link btn btn-info" value="提交" onclick="return save()"></span>
		</form>
	</div>
</div>

<!-- Javascript Libs -->
<script type="text/javascript" src="static/lib/js/jquery.min.js"></script>
<script type="text/javascript" src="static/lib/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/lib/js/Chart.min.js"></script>
<script type="text/javascript"
	src="static/lib/js/bootstrap-switch.min.js"></script>
<script type="text/javascript"
	src="static/lib/js/jquery.matchHeight-min.js"></script>
<script type="text/javascript"
	src="static/lib/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="static/lib/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="static/lib/js/select2.full.min.js"></script>
<script type="text/javascript" src="static/lib/js/ace/ace.js"></script>
<script type="text/javascript" src="static/lib/js/ace/mode-html.js"></script>
<script type="text/javascript" src="static/lib/js/ace/theme-github.js"></script>
<!-- Javascript -->
<script type="text/javascript" src="static/js/app.js"></script>
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function save() {
		if ($("#mingcheng").val() == "") {
			alert("请填写活动名称！");
			return false;
		} else if ($("#didian").val() == "") {
			alert("请填写活动地点！");
			return false;
		} else if ($("#riqi").val() == "") {
			alert("请选择活动日期！");
			return false;
		} else if ($("#kaishishijian").val() == "") {
			alert("请选择活动开始时间！");
			return false;
		} else if ($("#jieshushijian").val() == "") {
			alert("请选择活动结束时间！");
			return false;
		} else {
			return true;
		}
	}
</script>
<script type="text/javascript">
	//弹出隐藏层
	function ShowDiv(show_div) {
		document.getElementById(show_div).style.display = 'block';
		document.getElementById("mask").style.display = 'block';
	};
	//关闭弹出层，保存数据
	function CloseDiv(close_div) {
		var a = document.getElementById("beixuanren")
				.getElementsByTagName("tr")
		var s = "";
		for (var i = 0; i < a.length; i++) {//将添加的参与人的id拼接成s，逗号隔开
			s += a[i].id + ',0;';
		}
		//alert(s);
		document.getElementById("canyuren").value = s;
		document.getElementById(close_div).style.display = 'none';
		document.getElementById("mask").style.display = 'none';
	};
	//显示同班、社团、好友
	function show(show, close1, close2) {
		document.getElementById(show).style.display = 'block';
		document.getElementById(close1).style.display = 'none';
		document.getElementById(close2).style.display = 'none';
	};
	//添加参与人
	function add(xuehao, xingming, xueshengid) {
		var code = '<tr id="'+xueshengid+'"><td>'
				+ xuehao
				+ '</td><td>'
				+ xingming
				+ '</td><td><span style="cursor:pointer;color:blue;" onclick="del(\''
				+ xueshengid + '\')">删除</span></td></tr>';
		$("#beixuanren").append(code);
		var id = xueshengid + xueshengid;
		document.getElementById(id).style.display = "none";
	}
	//删除已选参与人
	function del(xueshengid) {
		var id = xueshengid + xueshengid;
		document.getElementById(id).style.display = "block";
		var tr = document.getElementById(xueshengid);
		tr.parentNode.removeChild(tr);
	}
</script>
<!-- <!-- 			<script type="text/javascript"> -->
<!-- // 			var posX; -->
<!-- // 		    var posY; -->
<!-- // 		    fwuss = document.getElementById("MyDiv"); -->
<!-- // 		    fwuss.onmousedown=function(e){ -->
<!-- // 		        posX = event.x - fwuss.offsetLeft;//获得横坐标x -->
<!-- // 		        posY = event.y - fwuss.offsetTop;//获得纵坐标y -->
<!-- // 		        document.onmousemove = mousemove;//调用函数，只要一直按着按钮就能一直调用 -->
<!-- // 		    } -->
<!-- // 		    document.onmouseup = function() -->
<!-- // 		    { -->
<!-- // 		        document.onmousemove = null;//鼠标举起，停止 -->
<!-- // 		    } -->
<!-- // 		    function mousemove(ev) -->
<!-- // 		    { -->
<!-- // 		        if(ev==null) ev = window.event;//IE -->
<!-- // 		        fwuss.style.left = (ev.clientX - posX) + "px"; -->
<!-- // 		        fwuss.style.top = (ev.clientY - posY) + "px"; -->
<!-- // 		    } -->
<!-- <!-- 			</script> -->