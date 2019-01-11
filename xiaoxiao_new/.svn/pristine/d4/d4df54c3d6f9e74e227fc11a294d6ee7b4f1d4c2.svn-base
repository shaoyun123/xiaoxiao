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
	top: 0;
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
				<i class="icon-align-justify"></i><span class="break"></span>新增课程
			</h2>
		</div>
		<div class="box-content">
			<div class="card-body">
				<!-- 								savekecheng_xinjia -->
				<form action="" class="form-horizontal" method="post" id="forms">
					<input type="hidden" name="lianxukecheng" id="lianxukecheng">
					<input type="hidden" name="xuenian" value="${xuenian }"> <input
						type="hidden" name="xueqi" value="${xueqi }"> <input
						type="hidden" id="zhoushu" value="${zhounum}">
					<!-- 										<input type="hidden" name="dancikecheng" id="dancikecheng"> -->
					<div class="sub-title">
						<span style="font-weight: bold;">校区：</span> <span>${xiaoqu.mingcheng}</span>&emsp;&emsp;
						<input id="xiaoqu" name="xiaoqu" type="hidden"
							value="${xiaoqu.xiaoquid}" /> <span style="font-weight: bold;">教学楼：</span>
						<span>${jiaoxuelou.jiaoXueLouMing}</span>&emsp;&emsp; <input
							id="jiaoxuelou" name="jiaoxuelou" type="hidden"
							value="${jiaoxuelou.jiaoXueLouId}" /> <span
							style="font-weight: bold;">教室：</span> <span>${jiaoshi.jiaoshiming}</span>&emsp;&emsp;
						<input id="1-shangkejiaoshi" name="jiaoshi" type="hidden"
							value="${jiaoshi.jiaoshiid}" /> <span style="font-weight: bold;">星期：</span>
						<c:if test="${zhouci==1}">
							<span>一</span>
						</c:if>
						<c:if test="${zhouci==2}">
							<span>二</span>
						</c:if>
						<c:if test="${zhouci==3}">
							<span>三</span>
						</c:if>
						<c:if test="${zhouci==4}">
							<span>四</span>
						</c:if>
						<c:if test="${zhouci==5}">
							<span>五</span>
						</c:if>
						<c:if test="${zhouci==6}">
							<span>六</span>
						</c:if>
						<c:if test="${zhouci==7}">
							<span>日</span>
						</c:if>
						&emsp;&emsp; <input id="1-zhouci" name="zhouci" type="hidden"
							value="${zhouci}" />
					</div>

					<div id="wanshanxinxi" style="margin-left: 30px;" class="sub-title">
						<span style="font-weight: bold">请继续完善信息……</span><br> <br>
						<span>课程：</span> <select id="kecheng" name="kecheng"
							style="width: 300px">
							<option value="">--请选择--</option>
							<c:forEach items="${kecheng}" var="KeCheng">
								<option value="${KeCheng.id}">${KeCheng.kechengmingcheng}<c:if
										test="${not empty KeCheng.daima }">(${KeCheng.daima})</c:if></option>
							</c:forEach>
						</select><br> <br> <span>任课教师：</span> <select id="teacher"
							name="teacher" style="width: 300px">
							<option value="">--请选择--</option>
							<c:forEach items="${yonghu}" var="yonghu">
								<option value="${yonghu.yonghuid}">${yonghu.yonghuxingming}(${yonghu.yuanximingcheng})</option>
							</c:forEach>
						</select><br> <br> <span>添加课程对象：</span> <input id="Button1"
							type="radio" value="0" onclick="ShowDiv('MyDiv')" name="tianjia" />按学生添加
						&emsp;<input id="Button1" type="radio" value="1"
							onclick="ShowDiv('MyDiv2')" name="tianjia" /> 按班级添加
						<!-- 	<input id="canyuren" name="canyuren" type="hidden" />  -->
						<input id="shangkebanji" name="shangkebanji" type="hidden" />


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
								<div id="yixuan2" class="yixuan">
									<!-- 已选 -->
									<span style="margin-left: 110px; color: red">已选班级</span>
									<table class="table">
										<thead>
											<tr>
												<th>班级名称</th>
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
							<!-- 							<div id="move2" class="rightbottom1" style="cursor: pointer;"> -->
							<!-- 								<span style="font-size: 16px;" onclick="CloseDiv2('MyDiv2')">保存</span> -->
							<!-- 							</div> -->
						</div>

						<div id="mask" style="width: 100%; height: 100%;"></div>
						<div id="MyDiv" class="white_content modal hide fade in"
							style="display: none;" aria-hidden="false">
							<div class="modal-header">
								<button type="button" class="close btn" data-dismiss="modal">×</button>
								<h3>按学生添加</h3>
							</div>
							<div class="modal-body" style="height: 70%;">
								<!-- 弹出框 -->
								<div id="kexuan" class="kexuan">
									<!-- 可选 -->
									<div>
										<input style="margin-left: 0px;" id="xuesheng" type="button"
											value="我的学生" onclick="show('xueshengdiv','haoyoudiv')" />
										<div class="controls"
											style="margin-left: 90px; margin-top: -25px;">
											<select style="width: 90px;" id="banji2" name="banji">
												<option value="">全部</option>
												<c:forEach items="${banji}" var="BanJi">
													<option value="${BanJi.banjiid}">${BanJi.banjimingcheng}</option>
												</c:forEach>
											</select> <input type="button" value="查询"
												onclick="show_wodexuesheng()" />
										</div>
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
							<!-- 							<div id="move" class="rightbottom1" style="cursor: pointer;"> -->
							<!-- 								<span style="font-size: 16px;" onclick="CloseDiv('MyDiv')">保存</span> -->
							<!-- 							</div> -->
						</div>

						<br> <br> <span style="font-weight: bold;">课程类型：</span>
						<select id="danshuangzhou" name="danshuangzhou"
							onchange="shows(this)" style="width: 120px">
							<option value="0">请选择</option>
							<option value="1">连续周课</option>
							<option value="2">单次周课</option>
						</select> <br>
						<hr>
						<div id="disp1" style="display: none">
							<div id="disp2">
								<div id="1" class="aa">
									<hr>
									<span>上课周：</span> <span>第</span> <select id="1-kaishizhou"
										name="kaishizhou" style="width: 50px">
										<c:forEach var="i" begin="1" end="${zhounum}" step="1">
											<option value="${i}">${i}</option>
										</c:forEach>
									</select> <span>周——至——第</span> <select id="1-jieshuzhou"
										name="jieshuzhou" style="width: 50px">
										<c:forEach var="i" begin="1" end="${zhounum}" step="1">
											<option value="${i}">${i}</option>
										</c:forEach>
									</select> <span>周</span> <br> <br> <span
										style="font-weight: bold;">课程类型：</span> <select
										id="1-danshuang" name="danshuang" style="width: 120px">
										<option value="0">请选择</option>
										<option value="1">每周</option>
										<option value="2">单周</option>
										<option value="3">双周</option>
									</select> &emsp;&emsp;&emsp;<span>上课节次：</span> <span>第</span> <select
										id="1-kaishijieci" name="kaishijieci" style="width: 70px">
										<c:forEach items="${jieci}" var="jieci">
											<option value="${jieci.id}">${jieci.jieci}</option>
										</c:forEach>
									</select> <span>节——至——第</span> <select id="1-jieshujieci"
										name="jieshujieci" style="width: 70px">
										<c:forEach items="${jieci}" var="jieci">
											<option value="${jieci.id}">${jieci.jieci}</option>
										</c:forEach>
									</select> <span>节</span>&emsp;&emsp;&emsp;&emsp;&emsp; <span>&emsp;节数:&emsp;</span>
									<input style="width: 60px;" type="button"
										onclick="add_kecheng()" value="+" />

								</div>
							</div>

						</div>
						<br>
						<div id="divp1" style="display: none;">
							<div id="2-1" class="2-aa">
								<span style="font-weight: bold;">上课时间：</span> &emsp;<input
									id="2-1-riqi" type="text" class="Wdate" style="height: 25px"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />

								&emsp;&emsp;&emsp;<span>上课节次：</span> <span>第</span> <select
									id="2-1-kaishijieci" style="width: 70px">
									<c:forEach items="${jieci}" var="jieci">
										<option value="${jieci.id}">${jieci.jieci}</option>
									</c:forEach>
								</select> <span>节——至——第</span> <select id="2-1-jieshujieci"
									name="2-1-jieshujieci" style="width: 70px">
									<c:forEach items="${jieci}" var="jieci">
										<option value="${jieci.id}">${jieci.jieci}</option>
									</c:forEach>
								</select> <span>节</span> &emsp;&emsp; <span>&emsp;节数:&emsp;</span> <input
									style="width: 60px;" type="button" onclick="add_jie()"
									value="+" />
								<hr>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput"></label>
						<div class="controls">
							<button type="button" style="margin-left: 30px;"
								class="btn btn-success" value="保存" onclick="save()">保存</button>
						</div>
					</div>
					<!-- 					<span class="pull-right"><input type="submit" -->
					<!-- 						class="flip-link btn btn-info" value="保存" onclick="return save()"></span> -->
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function add_kecheng() {
		var zhounum = $("#zhoushu").val();
		var num = $("#disp2 .aa").length;
		// 			var xq = $("#xiaoqu").val();
		// 			var jxl = $("#jiaoxuelou").val();
		// 			var js = $("#1-shangkejiaoshi").val();
		var i = num + 1;
		if (i > 2) {
			alert("这位老师，您已经加了两节课了！请您谨慎加课！！");
		}
		var code = '<div id="' + i + '" class="aa" style="width:100%;"><hr>';
		$
				.ajax({
					type : "POST",
					url : 'getjiecishuju_fdy1',
					data : {

					},
					dataType : 'json',
					cache : false,
					timeout : 5000,
					async : true,
					success : function(data) {
						var data = eval(data);
						code += '<div style="width:80%;">';
						code += '<span>上课周：</span> <span>第</span> <select id="'+i+'-kaishizhou" style="width: 50px">';
						for (var a = 1; a <= zhounum; a++) {
							code += '<option value="'+a+'">' + a + '</option>';
						}
						code += '</select> <span>周——至——第</span> <select id="'+i+'-jieshuzhou" style="width: 50px">';
						for (var a = 1; a <= zhounum; a++) {
							code += '<option value="'+a+'">' + a + '</option>';
						}
						code += '</select> <span>周</span> <br><br>';
						code += '<span>课程类型：</span><select style="padding-left:10px;width:120px;" id="'+i+'-danshuang">';
						code += '<option value="0">请选择</option> <option value="1">每周</option> <option value="2">单周</option> <option value="3">双周</option>';
						code += ' </select>&emsp;&emsp;&emsp;';
						code += '<span>上课节次：</span><span>第</span><select style="padding-left:10px;width:70px;" id="'+i+'-kaishijieci">';
						for (var a = 0; a < data.jieci.length; a++) {
							code += '<option value="'+data.jieci[a].id+'">'
									+ data.jieci[a].jieci + '</option>';
						}
						code += ' </select>';
						code += '<span>节——至——第</span><select style="padding-left:10px;width:70px;" id="'+i+'-jieshujieci">';
						for (var a = 0; a < data.jieci.length; a++) {
							code += '<option value="'+data.jieci[a].id+'">'
									+ data.jieci[a].jieci + '</option>';
						}
						code += ' </select><span>节</span>&emsp;&emsp;&emsp;';
						code += '<span>星期：</span><select id="'+i+'-zhouci" style="padding-left:10px;width:70px;">';
						code += '<option value="1">一</option><option value="2">二</option><option value="3">三</option><option value="4">四</option><option value="5">五</option>';
						code += '<option value="6">六</option><option value="7">日</option></select><br><br>';
						code += '<span>校区：</span><select style="padding-left:10px;width:120px;" id="'
								+ i
								+ '-xiaoqu" onchange="show_jiaoxuelou(this)">';
						code += '<option value="0">请选择</option>';
						// 							for (var a = 0; a < data.xiaoqu.length; a++) {
						// 								if(xq == data.xiaoqu[a].xiaoquid){
						// 									code += '<option value="'+data.xiaoqu[a].xiaoquid+'" selected="selected">'
						// 									+ data.xiaoqu[a].mingcheng + '</option>';
						// 								}else{
						// 									code += '<option value="'+data.xiaoqu[a].xiaoquid+'">'
						// 									+ data.xiaoqu[a].mingcheng + '</option>';
						// 								}

						// 							}
						code += ' </select>&emsp;&emsp;&emsp;';
						code += '<span>教学楼：</span><select style="padding-left:10px;width:120px;" onchange="show_jiaoshi(this)" id="'
								+ i + '-jiaoxuelou">';
						code += '<option value="0">请选择</option>';
						code += ' </select>&emsp;&emsp;&emsp;';
						code += '<span>教室：</span><select style="padding-left:10px;width:120px;" id="'+i+'-shangkejiaoshi">';
						code += '<option value="0">请选择</option>';
						code += ' </select>';
						// 							code += '<div onclick="deletes('+i+')" style="width:15%;float:left;margin-top:10px;background-color:green;text-align:center;"> 删除 </div>';
						code += '</div></div>';
						show_xiaoqu(i);
						$("#disp2").append(code);
					},
					error : function() {
						alert("超时")
					}
				});
	}
	function add_jie() {
		var num = $("#divp1 .2-aa").length;
		var i = num + 1;
		var code = '<div id=2-"' + i + '" class="2-aa" style="width:100%;">';
		$
				.ajax({
					type : "POST",
					url : 'getjiecishuju_fdy1',
					data : {

					},
					dataType : 'json',
					cache : false,
					timeout : 5000,
					async : true,
					success : function(data) {
						var data = eval(data);

						code += '<span  style="width:80%;font-weight: bold;">校区：</span><select style="padding-left:10px;width:120px;" id="2-'
								+ i
								+ '-xiaoqu" onchange="show_jiaoxuelou(this)">';
						code += '<option value="0">请选择</option>';
						code += ' </select>&emsp;&emsp;&emsp;&emsp;';
						code += '<span style="width:80%;font-weight: bold;">教学楼：</span><select style="padding-left:10px;width:120px;" onchange="show_jiaoshi(this)" id="2-'
								+ i + '-jiaoxuelou">';
						code += '<option value="0">请选择</option>';
						code += ' </select> &emsp;&emsp;&emsp;';
						code += '<span style="width:80%;font-weight: bold;">教室：</span><select style="padding-left:10px;width:120px;"  id="2-'+i+'-shangkejiaoshi">';
						code += '<option value="0">请选择</option>';
						code += ' </select><br><br>';
						code += '<span style="font-weight: bold;">上课时间：</span> &emsp;<input id="2-'
								+ i
								+ '-riqi" type="text" class="Wdate" style="height: 25px"'
								+ 'onclick="WdatePicker({readOnly:true,dateFmt:\'yyyy-MM-dd\'})" />'
						code += '&emsp;&emsp;&emsp;';
						code += '<span>上课节次：</span> <span>第</span><select style="width: 70px;" id="2-'+i+'-kaishijieci">';
						for (var a = 0; a < data.jieci.length; a++) {
							code += '<option value="'+data.jieci[a].id+'">'
									+ data.jieci[a].jieci + '</option>';
						}
						code += ' </select>';
						code += '<span>节——至——第</span><select style="width: 70px;" id="2-'+i+'-jieshujieci">';
						for (var a = 0; a < data.jieci.length; a++) {
							code += '<option value="'+data.jieci[a].id+'">'
									+ data.jieci[a].jieci + '</option>';
						}
						code += ' </select>节</div>';
						// 							code += '<div onclick="deletes('+i+')" style="width:15%;float:left;margin-top:10px;background-color:green;text-align:center;"> 删除 </div>';
						code += '</div><hr>';

						show_xiaoqu("2-" + i);
						$("#divp1").append(code);
					},
					error : function() {
						alert("超时")
					}
				});
	}
	function deletes(id) {
		// 			var num = $("#disp2 .aa").length;
		$("div[id=" + id + "]").removeClass("aa");
		;
		$("div[id=" + id + "]").remove();
	}
	function show_xiaoqu(id) {
		$.ajax({
			type : "POST",
			url : "getxiaoqu_fdy",
			data : {

			},
			dataType : 'json',
			cache : false,
			timeout : 5000,
			success : function(data) {
				html = '<option value="">请选择</option>';
				var data = eval(data);
				if (data) {
					for (var i = 0; i < data.length; i++) {
						html += '<option value="' + data[i].xiaoquid + '">'
								+ data[i].mingcheng + '</option>';
					}
					$("#" + id + "-xiaoqu").html(html);
				}
			},
			error : function() {
				alert("超时");
			}
		});
	}
	function show_jiaoxuelou(obj) {

		var id = new Array();
		id = $(obj).attr("id").split("-xiaoqu");
		var xiaoquid = $("#" + $(obj).attr("id")).val();
		$.ajax({
			type : "POST",
			url : 'show_jiaoxuelou',
			data : {
				CODE : xiaoquid
			},
			dataType : 'json',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				var data = eval(data);
				var code = '<option value="">请选择</option>';
				var defaultValue = '';
				if (data.length != 0) {
					for (var i = 0; i < data.length; i++) {
						defaultValue = [ data[0].jiaoXueLouId ];
						code += '<option value="'+data[i].jiaoXueLouId+'">'
								+ data[i].jiaoXueLouMing + '</option>';
					}
					$("#" + id[0] + "-jiaoxuelou").html(code);
				} else {
					alert("该校区下还没有教学楼!");
					$("#" + id[0] + "-jiaoxuelou").empty();
					$("#" + id[0] + "-jiaoxuelou").val(defaultValue).trigger(
							'change');
				}

			},
			error : function() {
				alert("登录超时!")
			}
		});
	}

	function show_jiaoshi(obj) {
		var id = new Array();
		id = $(obj).attr("id").split("-jiaoxuelou");
		var jiaoXueLouId = $("#" + $(obj).attr("id")).val();
		if (jiaoXueLouId.length != 0) {
			$.ajax({
				type : "POST",
				url : 'show_jiaoshi',
				data : {
					CODE : jiaoXueLouId
				},
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					var data = eval(data);
					var code = '<option value="">请选择</option>';
					var defaultValue = '';
					if (data) {
						for (var i = 0; i < data.length; i++) {
							defaultValue = [ data[0].jiaoshiid ];
							code += '<option value="'+data[i].jiaoshiid+'">'
									+ data[i].jiaoshiming + '</option>';
						}
						$("#" + id[0] + "-shangkejiaoshi").html(code);
					} else {
						alert("该教学楼下还没有教室!");
						$("#" + id[0] + "-jiaoxuelou").empty();
						$("#" + id[0] + "-jiaoxuelou").val(defaultValue)
								.trigger('change');

					}
				},
				error : function() {
					alert("登录超时!")
				}

			});
		} else {
			code += '<option value="">--请选择--</option>';
			$("#" + id[0] + "-shangkejiaoshi").html(code);
			alert("该教学楼下还没有教室!");
		}
	}

	function shows(obj) {//选择其他时，弹出其他说明
		document.getElementById("disp1").style.display = (obj.value == 1) ? "block"
				: "none"
		document.getElementById("divp1").style.display = (obj.value == 2) ? "block"
				: "none"
	}
	function save() {
		var select2 = document.getElementsByName("tianjia"); //获取select对象
		var n2 = 0;
		var str2 = [];
		for (i = 0; i < select2.length; i++) {
			if (select2[i].checked == true) {
				str2.push(select2[i].value);
				n2++;
			}
		}
		// 			if ($("#xuenian").val() == "") {
		// 				alert("请选择上课学年！");
		// 				return false;
		// 			}
		// 			if ($("#xueqi").val() == "") {
		// 				alert("请选择上课学期！");
		// 				return false;
		// 			}
		if ($("#kecheng").val() == "") {
			alert("请选择课程！");
			return false;
		}
		if ($("#teacher").val() == "") {
			alert("请选择任课教师！");
			return false;
		}
		if ($("#shangkebanji").val() == "" || $("#shangkebanji").val() == null) {
			alert("请选择上课学生!");
			return false;
		}
		if (n2 == 0) {
			alert("请选择课程对象!");
			return false;
		}
		if ($("#danshuangzhou").val() == "0") {
			alert("请选择课程类型");
			return false;
		}
		if ($("#danshuangzhou").val() == "1") {
			if ($("#danshuang").val() == "0") {
				alert("请选择课程类型");
				return false;
			}

			var da = "";
			var num = $("#disp2 .aa").length;
			for (var a = 1; a <= num; a++) {
				// 					alert("周次："+$("#"+a+"-zhouci").val()+"教室："+$("#"+a+"-shangkejiaoshi").val()+"开始节次："+ $("#"+a+"-kaishijieci").val()+"结束节次："+$("#"+a+"-jieshujieci").val());
				var zhouci = $("#" + a + "-zhouci").val();
				var shangkejiaoshi = $("#" + a + "-shangkejiaoshi").val();
				var kaishijieci = $("#" + a + "-kaishijieci").val();
				var jieshujieci = $("#" + a + "-jieshujieci").val();
				var danshuang = $("#" + a + "-danshuang").val();
				var kaishizhou = $("#" + a + "-kaishizhou").val();
				var jieshuzhou = $("#" + a + "-jieshuzhou").val();
				da += danshuang + "," + zhouci + "," + kaishijieci + ","
						+ jieshujieci + "," + shangkejiaoshi + "," + kaishizhou
						+ "," + jieshuzhou + ";";
			}
			$("#lianxukecheng").val(da);
		}
		if ($("#danshuangzhou").val() == "2") {
			var da = "";
			var num = $("#divp1 .2-aa").length;
			for (var a = 1; a <= num; a++) {
				if (a == 1) {
					var riqi = $("#2-" + a + "-riqi").val();
					var shangkejiaoshi = $("#1-shangkejiaoshi").val();
					var kaishijieci = $("#2-" + a + "-kaishijieci").val();
					var jieshujieci = $("#2-" + a + "-jieshujieci").val();
					da += riqi + "," + kaishijieci + "," + jieshujieci + ","
							+ shangkejiaoshi + ";";
				} else {
					var riqi = $("#2-" + a + "-riqi").val();
					var shangkejiaoshi = $("#2-" + a + "-shangkejiaoshi").val();
					var kaishijieci = $("#2-" + a + "-kaishijieci").val();
					var jieshujieci = $("#2-" + a + "-jieshujieci").val();
					da += riqi + "," + kaishijieci + "," + jieshujieci + ","
							+ shangkejiaoshi + ";";
				}
			}
			$("#lianxukecheng").val(da);
			console.log($("#lianxukecheng").val());
		}

		$.ajax({
			type : "POST",
			url : 'savekecheng_xinjia',
			data : $('#forms').serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("添加成功！");
					toContentPage('xueshengkecheng');
				} else if(data == "shijianyichu"){
					alert('选课时间超出学期范围！！');
					toContentPage('addkecheng_fdy');
				}else {
					alert("添加失败");
					toContentPage('xueshengkecheng');
				}
				
			},
			error : function() {
				alert("超时!")
			}
		});

	}

	// 		$('#kecheng')
	// 				.change(
	// 						function() {
	// 							var kechengid = $('#kecheng option:selected').val();//获取当前选中的值
	// 							$
	// 									.ajax({
	// 										type : "POST",
	// 										url : 'getteacher',
	// 										data : {
	// 											CODE : kechengid
	// 										},
	// 										dataType : 'json',
	// 										cache : false,
	// 										timeout : 5000,
	// 										async : true,
	// 										success : function(data) {
	// 											var data = eval(data);
	// 											var code = '<option value="">--请选择--</option>';
	// 											if (data.length != 0
	// 													|| data != null) {
	// 												for (var i = 0; i < data.length; i++) {
	// 													code += '<option value='+data[i].yonghuid+'>'
	// 															+ data[i].yonghuxingming
	// 															+ '</option>';
	// 												}
	// 												$('#teacher').html(code);
	// 											} else {
	// 												alert("请为该课程添加任课教师!");
	// 											}

	// 										},
	// 										error : function() {
	// 											alert("fail")
	// 										}

	// 									});
	// 						})
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
			document.getElementById(show_div).style.display = 'block';
			document.getElementById("MyDiv2").style.display = 'none';
			document.getElementById("mask").style.display = 'block';
		}
		if (show_div == "MyDiv2") {
			document.getElementById(show_div).style.display = 'block';
			document.getElementById("MyDiv").style.display = 'none';
			document.getElementById("mask").style.display = 'block';
		}
	};
	//关闭弹出层，保存数据
	function CloseDiv(close_div) {
		var a = document.getElementById("beixuanren")
				.getElementsByTagName("tr")
		var s = "";
		for (var i = 0; i < a.length; i++) {//将添加的参与人的id拼接成s，逗号隔开
			s += a[i].id + ',';
		}
		document.getElementById("shangkebanji").value = s;
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
		document.getElementById("shangkebanji").value = s;
		document.getElementById(close_div).style.display = 'none';
		document.getElementById("mask").style.display = 'none';

	};
	//显示同班、社团、好友
	function show(show, close) {
		console.log(show == "xueshengdiv");
		document.getElementById(show).style.display = 'block';
		document.getElementById(close).style.display = 'none';
		if (show == "xueshengdiv") {
			document.getElementById("yixuanren").style.display = 'block';
			document.getElementById("yixuanrenbanji").style.display = 'none';
		} else {
			document.getElementById("yixuanren").style.display = 'none';
			document.getElementById("yixuanrenbanji").style.display = 'block';
		}
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
									alert("该学生已经添加过了！！！");
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
