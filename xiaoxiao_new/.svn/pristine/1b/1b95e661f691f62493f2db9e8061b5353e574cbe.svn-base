<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.controls {
	position: relative;
	top: 5px;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>修改课程
			</h2>
		</div>
		<div class="box-content">
			<form action="" class="form-horizontal"
				method="post" id="forms">
				<input type="hidden" name="id" value="${kecheng.id}" /> <input
					type="hidden" id="leixing" name="leixing"
					value="${kecheng.leixing }">
				<div class="control-group">
					<label class="control-label">课程：</label>
					<div class="controls">
						${kecheng.kechengmingcheng}&emsp;&emsp; <span>上课周： <c:choose>
								<c:when test="${kecheng.leixing==3 }">
									<c:forEach items="${maps }" var="maps" varStatus="in">
										<c:if test="${in.index+1==1 }">${maps.zhoushu }周</c:if>
									</c:forEach>
								</c:when>
								<c:when test="${kecheng.leixing==2 }">
									<c:if test="${maps.ds=='1'}">${kecheng.kaishizhou}~${kecheng.jieshuzhou }单周</c:if>
									<c:if test="${maps.ds=='2'}">${kecheng.kaishizhou}~${kecheng.jieshuzhou }双周</c:if>
								</c:when>
								<c:otherwise>
														${kecheng.kaishizhou}~${kecheng.jieshuzhou }周
													</c:otherwise>
							</c:choose> &emsp;&emsp;
						</span>
					</div>
				</div>
				<c:choose>
					<c:when test="${kecheng.leixing==3 }">
						<c:forEach items="${maps }" varStatus="in" var="maps">
							<c:if test="${in.index+1==1 }">
								<div class="control-group">
									<label class="control-label">校区：</label>
									<div class="controls">
										${maps.xiaoquming}&emsp;&emsp; <span
											style="font-weight: bold;">教学楼：${maps.jiaoxuelouming}&emsp;&emsp;</span>
										<span style="font-weight: bold;">教室：${maps.jiaoshiming}</span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">星期：</label>
									<div class="controls">
										<c:if test="${maps.zhouci==1}">一</c:if>
										<c:if test="${maps.zhouci==2}">二</c:if>
										<c:if test="${maps.zhouci==3}">三</c:if>
										<c:if test="${maps.zhouci==4}">四</c:if>
										<c:if test="${maps.zhouci==5}">五</c:if>
										<c:if test="${maps.zhouci==6}">六</c:if>
										<c:if test="${maps.zhouci==7}">日</c:if>
										&emsp;&emsp; <span style="font-weight: bold;">节次：${maps.kaishijieci}~${maps.jieshujieci }</span>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="control-group">
							<label class="control-label">校区：</label>
							<div class="controls">
								${maps.xiaoquming}&emsp;&emsp; <span style="font-weight: bold;">教学楼：${maps.jiaoxuelouming}&emsp;&emsp;</span>
								<span style="font-weight: bold;">教室：${maps.jiaoshiming}</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">星期： </label>
							<div class="controls">
								<c:if test="${maps.zhouci==1}">一</c:if>
								<c:if test="${maps.zhouci==2}">二</c:if>
								<c:if test="${maps.zhouci==3}">三</c:if>
								<c:if test="${maps.zhouci==4}">四</c:if>
								<c:if test="${maps.zhouci==5}">五</c:if>
								<c:if test="${maps.zhouci==6}">六</c:if>
								<c:if test="${maps.zhouci==7}">日</c:if>
								&emsp;&emsp; <span style="font-weight: bold;">节次：${maps.kaishijieci}~${maps.jieshujieci }</span>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<!-- 				<div class="control-group"> -->
				<!-- 					<label class="control-label">上课班级： </label> -->
				<!-- 					<div class="controls"> -->
				<%-- 						<c:forEach items="${yixuanbanjiming}" var="ming">${ming }&emsp;&emsp;</c:forEach> --%>
				<!-- 					</div> -->
				<!-- 				</div> -->
				<div class="control-group" id="zhanshi">
					<label class="control-label" for="focusedInput"></label>
					<div class="controls">
						<button type="button" style="margin-left: 30px;"
							class="btn btn-success" value="修改" onclick="xiugai()">修改</button>
					</div>
				</div>
				<div id="xiugai" style="display: none;">
					<c:choose>
						<c:when test="${kecheng.leixing==3 }">
							<div class="control-group" style="color: red;">
								<div class="controls">修改课程信息</div>
							</div>
							<c:forEach items="${maps }" var="maps" varStatus="in">
								<div id="num" class="aa">
									<input type="hidden" id="yuanriqi-${in.index+1 }"
										value="${maps.riqi }/${maps.kaishijieci}/${maps.jieshujieci}/${maps.jiaoshiid}">
									<div class="control-group">
										<label class="control-label">上课日期：</label>
										<div class="controls">
											<input id="riqi-${in.index+1 }" type="text" class="Wdate"
												style="height: 25px" value="${maps.riqi }"
												onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />

											&emsp;&emsp; <span style="font-weight: bold;">上课节次：</span> <span>第</span>
											<select id="kaishijieci-${in.index+1 }" name="kaishijieci"
												style="width: 70px">
												<c:forEach items="${jieci}" var="jieci">
													<option value="${jieci.id}"
														<c:if test="${maps.kaishijieci==jieci.jieci}">selected="selected"</c:if>>${jieci.jieci}</option>
												</c:forEach>
											</select> <span>节——至——第</span> <select id="jieshujieci-${in.index+1 }"
												name="jieshujieci" style="width: 70px">
												<c:forEach items="${jieci}" var="jieci">
													<option value="${jieci.id}"
														<c:if test="${maps.jieshujieci==jieci.jieci}">selected="selected"</c:if>>${jieci.jieci}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">校区：</label>
										<div class="controls">
											<select id="xiaoqu-${in.index+1 }" name="xiaoqu"
												style="width: 100px;"
												onchange="show_jiaoxuelous(${in.index+1 })">
												<option value="">--请选择--</option>
												<c:forEach items="${xiaoqu}" var="XiaoQu">
													<option value="${XiaoQu.xiaoquid}"
														<c:if test="${XiaoQu.xiaoquid==maps.xiaoquid}">selected="selected"</c:if>>${XiaoQu.mingcheng}</option>
												</c:forEach>
											</select>&emsp;&emsp; <span style="font-weight: bold;">教学楼：</span> <select
												id="jiaoxuelou-${in.index+1 }" name="jiaoxuelou"
												style="width: 100px;"
												onchange="show_jiaoshis(${in.index+1 })">
												<c:forEach items="${jiaoxuelou}" var="jiaoxuelou">
													<option value="${jiaoxuelou.jiaoXueLouId}"
														<c:if test="${jiaoxuelou.jiaoXueLouId==maps.jiaoxuelouid}">selected="selected"</c:if>>${jiaoxuelou.jiaoXueLouMing}</option>
												</c:forEach>

											</select>&emsp;&emsp; <span style="font-weight: bold;">教室：</span> <select
												id="shangkejiaoshi-${in.index+1 }" name="shangkejiaoshi"
												style="width: 100px;">
												<c:forEach items="${jiaoshi}" var="jiaoshi">
													<option value="${jiaoshi.jiaoshiid}"
														<c:if test="${jiaoshi.jiaoshiid==maps.jiaoshiid}">selected="selected"</c:if>>${jiaoshi.jiaoshiming}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</c:forEach>
							<input type="hidden" name="yuanriqi" id="yuanriqi">
							<input type="hidden" name="cixinxi" id="cixinxi">
						</c:when>
						<c:otherwise>
							<div class="control-group" style="color: red;">
								<div class="controls">修改课程信息</div>
							</div>
							<input type="hidden" name="maps"
								value="${maps.ds}/${maps.zhouci}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.jiaoshiid}/${kecheng.kaishizhou}/${kecheng.jieshuzhou}">
							<div class="control-group">
								<label class="control-label">上课周：</label>
								<div class="controls">
									<span>第</span> <select id="kaishizhou" name="kaishizhou"
										style="width: 50px">
										<c:forEach var="i" begin="1" end="${zhounum}" step="1">
											<option value="${i}"
												<c:if test="${kecheng.kaishizhou==i}">selected="selected"</c:if>>${i}</option>
										</c:forEach>
									</select> <span>周——至——第</span> <select id="jieshuzhou" name="jieshuzhou"
										style="width: 50px">
										<c:forEach var="i" begin="1" end="${zhounum}" step="1">
											<option value="${i}"
												<c:if test="${kecheng.jieshuzhou==i}">selected="selected"</c:if>>${i}</option>
										</c:forEach>
									</select> <span>周</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">校区：</label>
								<div class="controls">
									<select id="xiaoqu" name="xiaoqu" style="width: 100px;"
										onchange="show_jiaoxuelou()">
										<option value="">--请选择--</option>
										<c:forEach items="${xiaoqu}" var="XiaoQu">
											<option value="${XiaoQu.xiaoquid}"
												<c:if test="${XiaoQu.xiaoquid==maps.xiaoquid}">selected="selected"</c:if>>${XiaoQu.mingcheng}</option>
										</c:forEach>
									</select>&emsp;&emsp; <span style="font-weight: bold;">教学楼：</span> <select
										id="jiaoxuelou" name="jiaoxuelou" style="width: 100px;"
										onchange="show_jiaoshi()">
										<c:forEach items="${jiaoxuelou}" var="jiaoxuelou">
											<option value="${jiaoxuelou.jiaoXueLouId}"
												<c:if test="${jiaoxuelou.jiaoXueLouId==maps.jiaoxuelouid}">selected="selected"</c:if>>${jiaoxuelou.jiaoXueLouMing}</option>
										</c:forEach>

									</select>&emsp;&emsp; <span style="font-weight: bold;">教室：</span> <select
										id="shangkejiaoshi" name="shangkejiaoshi"
										style="width: 100px;">
										<c:forEach items="${jiaoshi}" var="jiaoshi">
											<option value="${jiaoshi.jiaoshiid}"
												<c:if test="${jiaoshi.jiaoshiid==maps.jiaoshiid}">selected="selected"</c:if>>${jiaoshi.jiaoshiming}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">星期：</label>
								<div class="controls">
									<select id="zhouci" name="zhouci" style="width: 50px">
										<option value="1"
											<c:if test="${maps.zhouci==1}">selected="selected"</c:if>>一</option>
										<option value="2"
											<c:if test="${maps.zhouci==2}">selected="selected"</c:if>>二</option>
										<option value="3"
											<c:if test="${maps.zhouci==3}">selected="selected"</c:if>>三</option>
										<option value="4"
											<c:if test="${maps.zhouci==4}">selected="selected"</c:if>>四</option>
										<option value="5"
											<c:if test="${maps.zhouci==5}">selected="selected"</c:if>>五</option>
										<option value="6"
											<c:if test="${maps.zhouci==6}">selected="selected"</c:if>>六</option>
										<option value="7"
											<c:if test="${maps.zhouci==7}">selected="selected"</c:if>>日</option>
									</select> &emsp;&emsp;<span style="font-weight: bold;">上课节次：</span> <span>第</span>
									<select id="kaishijieci" name="kaishijieci" style="width: 70px">
										<c:forEach items="${jieci}" var="jieci">
											<option value="${jieci.id}"
												<c:if test="${maps.kaishijieci==jieci.jieci}">selected="selected"</c:if>>${jieci.jieci}</option>
										</c:forEach>
									</select> <span>节——至——第</span> <select id="jieshujieci"
										name="jieshujieci" style="width: 70px">
										<c:forEach items="${jieci}" var="jieci">
											<option value="${jieci.id}"
												<c:if test="${maps.jieshujieci==jieci.jieci}">selected="selected"</c:if>>${jieci.jieci}</option>
										</c:forEach>
									</select> <span>节</span> &emsp;&emsp; <span style="font-weight: bold;">单双周：</span>
									<select id="ds" name="ds" style="width: 70px">
										<option value="0"
											<c:if test="${maps.ds=='0' }">selected="selected"</c:if>>每周</option>
										<option value="1"
											<c:if test="${maps.ds=='1' }">selected="selected"</c:if>>单周</option>
										<option value="2"
											<c:if test="${maps.ds=='2' }">selected="selected"</c:if>>双周</option>
									</select>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="control-group">
						<label class="control-label" for="focusedInput"></label>
						<div class="controls">
							<button type="button" style="margin-left: 30px;"
								class="btn btn-success" value="保存" onclick="save()">保存</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function xiugai(){
		document.getElementById("xiugai").style.display = 'block';
		document.getElementById("zhanshi").style.display = 'none';
	}
	function show_jiaoshi() {
		var jiaoXueLouId = $("#jiaoxuelou").val();
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
				var code = '<option disabled selected value>';
				if (data.length != 0) {
					for (var i = 0; i < data.length; i++) {
						code += '<option value="'+data[i].jiaoshiid+'">'
								+ data[i].jiaoshiming + '</option>';
					}
					$("#shangkejiaoshi").html(code);
				} else {
					code += '<option value="">--请选择--</option>';
					$("#shangkejiaoshi").html(code);
					alert("该校区下还没有教室!");
				}
			},
			error : function() {
				alert("登录超时!")
			}
		});
	}

	function show_jiaoxuelou() {
		var xiaoquid = $('#xiaoqu').val();
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
				var code = '<option disabled selected value>';
				if (data.length != 0) {
					for (var i = 0; i < data.length; i++) {
						code += '<option value="'+data[i].jiaoXueLouId+'">'
								+ data[i].jiaoXueLouMing + '</option>';
						$("#jiaoxuelou").html(code);
					}
				} else {
					code += '<option value="">--请选择--</option>';
					$("#jiaoxuelou").html(code);
					alert("该校区下还没有教学楼!");
				}

			},
			error : function() {
				alert("登录超时!")
			}
		});
	}
	function show_jiaoshis(id) {
		var jiaoXueLouId = $("#jiaoxuelou-"+id).val();
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
				var code = '<option disabled selected value>';
				if (data.length != 0) {
					for (var i = 0; i < data.length; i++) {
						code += '<option value="'+data[i].jiaoshiid+'">'
								+ data[i].jiaoshiming + '</option>';
					}
					$("#shangkejiaoshi-"+id).html(code);
				} else {
					code += '<option value="">--请选择--</option>';
					$("#shangkejiaoshi-"+id).html(code);
					alert("该校区下还没有教室!");
				}
			},
			error : function() {
				alert("登录超时!")
			}
		});
	}

	function show_jiaoxuelous(id) {
		var xiaoquid = $('#xiaoqu-'+id).val();
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
				var code = '<option disabled selected value>';
				if (data.length != 0) {
					for (var i = 0; i < data.length; i++) {
						code += '<option value="'+data[i].jiaoXueLouId+'">'
								+ data[i].jiaoXueLouMing + '</option>';
						$("#jiaoxuelou-"+id).html(code);
					}
				} else {
					code += '<option value="">--请选择--</option>';
					$("#jiaoxuelou-"+id).html(code);
					alert("该校区下还没有教学楼!");
				}

			},
			error : function() {
				alert("登录超时!")
			}
		});
	}

	function show(obj) {//选择其他时，弹出其他说明
		document.getElementById("other").style.display = (obj.value == 1) ? ""
				: "none"
	}
	function save() {
		if($("#leixing").val()!="3"){
			if ($("#xiaoqu").val() == "") {
				alert("请选择校区！");
				return false;
			}
			if ($("#jiaoxuelou").val() == "" || $("#jiaoxuelou").val() == null) {
				alert("请选择教学楼!");
				return false;
			}
			if ($("#shangkejiaoshi").val() == ""
					|| $("#shangkejiaoshi").val() == null) {
				alert("请选择教室！");
				return false;
			}
		}
		if($("#leixing").val()=="3"){
			var num = $("#num ").size();
			var yuanriqi="";
			var cixinxi="";
			for(var a=1;a<=num;a++){
				
			yuanriqi += $("#yuanriqi-"+a).val()+",";
				
			var xiaoqu = $("#xiaoqu-"+a).val();
			var jiaoxuelou = $("#jiaoxuelou-"+a).val();
			var shangkejiaoshi = $("#shangkejiaoshi-"+a).val();
			var kaishijieci = $("#kaishijieci-"+a).val();
			var jieshujieci = $("#jieshujieci-"+a).val();
			var riqi = $("#riqi-"+a).val();
			if(xiaoqu=="" || xiaoqu==null){
				alert("请选择校区！");
				return false;
			}
			if(jiaoxuelou=="" || jiaoxuelou==null){
				alert("请选择教学楼!");
				return false;
			}
			if(shangkejiaoshi=="" || shangkejiaoshi==null){
				alert("请选择教室！");
				return false;
			}
			
			cixinxi += riqi+","+kaishijieci+","+jieshujieci+","+shangkejiaoshi+";";
			
			}
			$("#yuanriqi").val(yuanriqi);
			$("#cixinxi").val(cixinxi);
		}
		$.ajax({
			type : "POST",
			url : 'saveupdatekecheng_jiaoshi',
			data : $("#forms").serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("修改成功！");
				} else {
					alert("修改失败");
				}
				toContentPage('wodekecheng_jiaoshi');
			},
			error : function() {
				alert("登录超时!")
			}
		});
	}
	</script>