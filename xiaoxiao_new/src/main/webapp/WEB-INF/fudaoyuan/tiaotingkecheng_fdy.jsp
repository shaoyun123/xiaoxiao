<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>调停课程
			</h2>
		</div>
		<div class="box-content">
			<form action="" method="post" class="form-horizontal" id="forms">
				<input type="hidden" name="id" value="${kecheng.id}">
				<input type="hidden" name="shangkeriqi"
					value="${kecheng.shangkeriqi}"> <input type="hidden"
					id="kaishiriqi" value="${kaishiriqi}"> <input type="hidden"
					id="jieshuriqi" value="${jieshuriqi}">
				<div class="control-group">
						<span style="font-weight: bold;">课程：${kecheng.kechengmingcheng} </span> 
						<span style="margin-left: 30px;">${kecheng.xiaoquming}&emsp;${kecheng.jiaoshiming} </span> 
						<span style="margin-left: 20px;"> 
							<c:choose>
								<c:when test="${kecheng.leixing==3 }"> ${kecheng.kaifangyuanxi}</c:when>
								<c:when test="${kecheng.leixing==2 }">
									<c:if test="${kecheng.keDaiBiao=='1' }">${kecheng.kaishizhou}~${kecheng.jieshuzhou} 单</c:if>
									<c:if test="${kecheng.keDaiBiao=='2' }">${kecheng.kaishizhou}~${kecheng.jieshuzhou} 双</c:if>
									<c:if test="${kecheng.keDaiBiao=='0' }">${kecheng.kaishizhou}~${kecheng.jieshuzhou} </c:if>
								</c:when>
								<c:otherwise>
	                         		${kecheng.kaishizhou}~${kecheng.jieshuzhou}
	                         	</c:otherwise>
							</c:choose> 周
						</span> 
						<span style="margin-left: 20px;">周 <c:if
								test="${kecheng.zhouci==1}">一</c:if> <c:if
								test="${kecheng.zhouci==2}">二</c:if> <c:if
								test="${kecheng.zhouci==3}">三</c:if> <c:if
								test="${kecheng.zhouci==4}">四</c:if> <c:if
								test="${kecheng.zhouci==5}">五</c:if> <c:if
								test="${kecheng.zhouci==6}">六</c:if> <c:if
								test="${kecheng.zhouci==7}">日</c:if>
						</span> <span style="margin-left: 20px;">${kecheng.kaishijieci}~${kecheng.jieshujieci}节</span>
						<span style="margin-left: 20px;">${kecheng.renkejiaoshi}老师</span>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="checkbox inline"> <input type="radio"
							id="tingke" name="tiaoting" value="0"
							onclick="close1_open2('wanshanxinxi','tiaoting')" /> 停课
						</label> <label class="checkbox inline"> <input type="radio"
							id="tiaoke" name="tiaoting" value="1"
							onclick="open1_open2('wanshanxinxi','tiaoting')" /> 调课
						</label> <label class="checkbox inline"> <input type="radio"
							id="jiake" name="tiaoting" value="2"
							onclick="open1_close2('wanshanxinxi','tiaoting')" /> 加课
						</label>


						<!-- 					<input type="radio" id="tingke" name="tiaoting" value="0" -->
						<!-- 						onclick="close1_open2('wanshanxinxi','tiaoting')"><label -->
						<!-- 						style="color: red" for="tingke">停课</label> -->
						<!-- 					 <input type="radio" -->
						<!-- 						id="tiaoke" name="tiaoting" value="1" -->
						<!-- 						onclick="open1_open2('wanshanxinxi','tiaoting')"><label -->
						<!-- 						style="color: red" for="tiaoke">调课</label>  -->
						<!-- 					<input type="radio" -->
						<!-- 						id="jiake" name="tiaoting" value="2" -->
						<!-- 						onclick="open1_close2('wanshanxinxi','tiaoting')"><label -->
						<!-- 						style="color: red" for="jiake">加课</label> -->
					</div>
				</div>
				<div class="control-group" id="tiaoting" style="display: none;">
					<label class="control-label" for="tiaotingriqi">选择停课日期：</label>
					<div class="controls">
						<select id="tiaotingriqi" name="tiaotingriqi">
							<option value="">--请选择--</option>
							<c:forEach items="${riqi}" var="riqi">
								<option value="${riqi}">${riqi}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="control-group" id="wanshanxinxi" style="display: none;">
					<label class="control-label" for="newriqi">调加课时间：</label>
					<div class="controls">
						<input id="newriqi" name="newriqi" type="date" /> <span
							style="font-size: 13px; font-weight: bold;">（**调加课时间不得超出教学周范围，如有需要请修改教学周）</span>
						<!-- 					  	<select id="tiaotingriqi" name="tiaotingriqi"> -->
						<!-- 							<option value="">--请选择--</option> -->
						<%-- 							<c:forEach items="${riqi}" var="riqi"> --%>
						<%-- 								<option value="${riqi}">${riqi}</option> --%>
						<%-- 							</c:forEach> --%>
						<!-- 					  	</select> -->
					</div>
					<div class="controls" style="padding-top: 12px;">
						<span style="font-size: 13px;">校区：</span> <select id="xiaoqu"
							name="xiaoqu" style="width: 100px;" onchange="show_jiaoshi()">
							<option value="">--请选择--</option>
							<c:forEach items="${xiaoqu}" var="XiaoQu">
								<option value="${XiaoQu.xiaoquid}"
									<c:if test="${XiaoQu.xiaoquid==kecheng.xiaoqu}">selected="selected"</c:if>>${XiaoQu.mingcheng}</option>
							</c:forEach>
						</select>&emsp; <span style="font-size: 13px;">教室：</span> <select
							id="shangkejiaoshi" name="shangkejiaoshi" style="width: 100px;">
							<option value="">--请选择--</option>
							<c:forEach items="${jiaoshi}" var="jiaoshi">
								<option value="${jiaoshi.jiaoshiid}"
									<c:if test="${jiaoshi.jiaoshiid==kecheng.shangkejiaoshi}">selected="selected"</c:if>>${jiaoshi.jiaoshiming}</option>
							</c:forEach>
						</select>
					</div>
					<div class="controls" style="padding-top: 12px;">
						<span style="font-size: 13px;">节次：</span> <select id="kaishijieci"
							name="kaishijieci" style="width: 100px">
							<c:forEach items="${jieci}" var="jieci">
								<option value="${jieci.id}"
									<c:if test="${kecheng.kaishijieci==jieci.jieci}">selected="selected"</c:if>>${jieci.jieci}</option>
							</c:forEach>
						</select> <span style="font-size: 13px;">&emsp;~&emsp;</span> <select
							id="jieshujieci" name="jieshujieci" style="width: 100px;">
							<c:forEach items="${jieci}" var="jieci">
								<option value="${jieci.id}"
									<c:if test="${kecheng.jieshujieci==jieci.jieci}">selected="selected"</c:if>>${jieci.jieci}</option>
							</c:forEach>
						</select> <span style="font-size: 13px;">节</span>
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
</div>

<script type="text/javascript">
	function show_jiaoshi() {
		var xiaoquid = $("#xiaoqu").val();
		$.ajax({
			type : "POST",
			url : 'show_jiaoshibyxiaoquid',
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
				var defaultValue = '';
				for (var i = 0; i < data.length; i++) {
					defaultValue = data[0].jiaoshiming;
					code += '<option value="'+data[i].jiaoshiid+'">'
							+ data[i].jiaoshiming + '</option>';
				}
				$("#shangkejiaoshi").html(code);
				$("#shangkejiaoshi").val(defaultValue).trigger('change');
			},
			error : function() {
				alert("登录超时!")
			}
		});
	}
	function open1_close2(id1, id2) {
		document.getElementById(id1).style.display = "block";
		document.getElementById(id2).style.display = "none";
	}
	function open1_open2(id1, id2) {
		document.getElementById(id1).style.display = "block";
		document.getElementById(id2).style.display = "block";
	}
	function close1_open2(id1, id2) {
		document.getElementById(id1).style.display = "none";
		document.getElementById(id2).style.display = "block";
	}

	function save() {
		var radios = document.getElementsByName("tiaoting");
		if (radios[0].checked == false && radios[1].checked == false
				&& radios[2].checked == false) {
			alert("请选择调停课类型！")
			return false;
		}
		if (radios[0].checked == true) {
			if ($("#tiaotingriqi").val() == "") {
				alert("请选择停课日期！")
				return false;
			}
		}
		if (radios[1].checked == true) {
			var kaishiriqi = $("#kaishiriqi").val();
			var jieshuriqi = $("#jieshuriqi").val();
			if ($("#tiaotingriqi").val() == "") {
				alert("请选择需要调课的日期！")
				return false;
			}
			if ($("#newriqi").val() == "") {
				alert("请选择调课后的上课时间！")
				return false;
			}
			if ($("#newriqi").val() < kaishiriqi
					|| $("#newriqi").val() > jieshuriqi) {
				alert("调课后的上课时间应在学期范围内！！");
				return false;
			}
			if ($("#xiaoqu").val() == "") {
				alert("请选择校区！")
				return false;
			}
			if ($("#shangkejiaoshi").val() == "") {
				alert("请选择教室！")
				return false;
			}
		}
		if (radios[2].checked == true) {
			var kaishiriqi = $("#kaishiriqi").val();
			var jieshuriqi = $("#jieshuriqi").val();
			if ($("#newriqi").val() == "") {
				alert("请选择加课后的上课时间！")
				return false;
			}
			if ($("#newriqi").val() < kaishiriqi
					|| $("#newriqi").val() > jieshuriqi) {
				alert("加课后的上课时间应在学期范围内！！");
				return false;
			}
			if ($("#xiaoqu").val() == "") {
				alert("请选择校区！")
				return false;
			}
			if ($("#shangkejiaoshi").val() == "") {
				alert("请选择教室！")
				return false;
			}
		}

		$.ajax({
			type : "POST",
			url : 'savetiaotingkecheng_fdy',
			data : $("#forms").serialize(),
			dataType : 'text',
			cache : false,
			timeout : 5000000,
			async : true,
			success : function(data) {
				if (data == "success") {
					alert("调课成功！");
				} else {
					alert("调课失败");
				}
				toContentPage('xueshengkecheng_liebiao');
			},
			error : function() {
				alert("登录超时!")
			}
		});
	}
</script>
