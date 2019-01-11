<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>添加课程
			</h2>
		</div>
		<div class="box-content">
			<form action="" class="form-horizontal" method="post">
				<div id="chazhaokecheng" class="sub-title">
					<span style="font-weight: bold;">校区：</span> <select id="xiaoqu"
						name="xiaoqu" style="width: 100px;" onchange="show_jiaoxuelou()">
						<option value="">--请选择--</option>
						<c:forEach items="${xiaoqu}" var="XiaoQu">
							<option value="${XiaoQu.xiaoquid}">${XiaoQu.mingcheng}</option>
						</c:forEach>
					</select>&emsp; <span style="font-weight: bold;">教学楼：</span> <select
						id="jiaoxuelou" name="jiaoxuelou" style="width: 100px;"
						onchange="show_jiaoshi()">

					</select>&emsp; <span style="font-weight: bold;">教室：</span> <select
						id="shangkejiaoshi" name="shangkejiaoshi" style="width: 100px;">

					</select>&emsp;&emsp;
					<!--                                     		<span style="font-weight:bold;">上课学年：</span> <select id="xuenian" -->
					<!-- 												name="xuenian" style="width: 100px"> -->
					<!-- 												<option value="">请选择</option> -->
					<%-- 												<c:forEach items="${xuenians }" var="xuenian"> --%>
					<%-- 													<option value="${xuenian }">${xuenian }</option> --%>
					<%-- 												</c:forEach> --%>
					<!-- 											</select>&emsp; <span style="font-weight:bold;">上课学期：</span> <select id="xueqi" name="xueqi" -->
					<!-- 												style="width: 100px"><option value="">请选择</option> -->
					<%-- 												<c:forEach items="${xueqis }" var="xueqi"> --%>
					<%-- 													<option value="${xueqi}">${xueqi}</option> --%>
					<%-- 												</c:forEach> --%>
					<!-- 											</select>&emsp;&emsp; -->
					<span style="font-weight: bold;">星期：</span> <select id="zhouci"
						name="zhouci" style="width: 50px">
						<option value="1">一</option>
						<option value="2">二</option>
						<option value="3">三</option>
						<option value="4">四</option>
						<option value="5">五</option>
						<option value="6">六</option>
						<option value="7">日</option>
					</select>
					<!-- 											<input type="submit" value="下一步" -->
					<!-- 												onclick="return selectkecheng()" /> -->
					<div class="control-group" style="margin-top: 30px;">
						<label class="control-label" for="focusedInput"></label>
						<div class="controls">
							<button type="button" style="margin-left: 30px;"
								class="btn btn-success" value="下一步" onclick="selectkecheng()">下一步</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
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
				var code = '<option value="">请选择</option>';
				var defaultValue = '';
				if (data.length != 0) {
					for (var i = 0; i < data.length; i++) {
						// 								defaultValue=[data[0].jiaoshiid];
						code += '<option value="'+data[i].jiaoshiid+'">'
								+ data[i].jiaoshiming + '</option>';
					}
					$("#shangkejiaoshi").html(code);
				} else {
					if (jiaoXueLouId != null) {
						alert("该教学楼下还没有教室!");
						$("#shangkejiaoshi").empty();
						$("#shangkejiaoshi").val(defaultValue)
								.trigger('change');
						show_jiaoxuelou1();
					}
				}
			},
			error : function() {
				alert("登录超时!")
			}

		});
	}

	function show_jiaoxuelou1() {
		var defaultValue = '';
		var jiaoXueLouId = $("#jiaoxuelou").val();
		var xiaoquid = $("#xiaoqu").val();
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

				if (data.length != 0) {
					for (var i = 0; i < data.length; i++) {
						defaultValue = [ data[0].jiaoXueLouId ];
						code += '<option value="'+data[i].jiaoXueLouId+'">'
								+ data[i].jiaoXueLouMing + '</option>';
					}
					$("#jiaoxuelou").html(code);
				} else {
					$("#shangkejiaoshi").empty();
					$("#shangkejiaoshi").val(defaultValue).trigger('change');
					alert("该校区下还没有教学楼!");
					$("#jiaoxuelou").empty();
					$("#jiaoxuelou").val(defaultValue).trigger('change');

				}
			},
			error : function() {
				alert("登录超时!")
			}
		});
	};

	function show_jiaoxuelou() {
		var defaultValue = '';
		var jiaoXueLouId = $("#jiaoxuelou").val();
		var jiaoshiid = $("#shangkejiaoshi").val();
		var xiaoquid = $("#xiaoqu").val();
		if (jiaoXueLouId != null || jiaoshiid != null) {
			$("#shangkejiaoshi").empty();
			$("#shangkejiaoshi").val(defaultValue).trigger('change');
			$("#jiaoxuelou").empty();
			$("#jiaoxuelou").val(null).trigger("change");
		}
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
				if (data.length != 0) {
					for (var i = 0; i < data.length; i++) {
						defaultValue = [ data[0].jiaoXueLouId ];
						code += '<option value="'+data[i].jiaoXueLouId+'">'
								+ data[i].jiaoXueLouMing + '</option>';
					}
					$("#jiaoxuelou").html(code);
				} else {
					$("#shangkejiaoshi").empty();
					$("#shangkejiaoshi").val(defaultValue).trigger('change');
					alert("该校区下还没有教学楼!");
					$("#jiaoxuelou").empty();
					$("#jiaoxuelou").val(defaultValue).trigger('change');

				}
			},
			error : function() {
				alert("登录超时!")
			}
		});
	};

	function selectkecheng() {
		if ($("#xiaoqu").val() == "") {
			alert("请选择校区！")
			return false;
		}
		if ($("#jiaoxuelou").val() == "" || $("#jiaoxuelou").val() == null) {
			alert("请选择教学楼!");
			return false;
		}
		if ($("#shangkejiaoshi").val() == ""
				|| $("#shangkejiaoshi").val() == null) {
			alert("请选择教室！")
			return false;
		}
		if ($("#zhouci").val() == "") {
			alert("请选择星期！")
			return false;
		}
		toContentPage('selectkecheng?xiaoqu=' + $("#xiaoqu").val()
				+ '&jiaoxuelou=' + $("#jiaoxuelou").val() + '&shangkejiaoshi='
				+ $("#shangkejiaoshi").val() + '&zhouci=' + $("#zhouci").val());
	}
</script>
