<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>查寝安排
			</h2>
		</div>
		<div class="box-content">
			<form class="form-horizontal" action="" method="post" id="form">
				<fieldset>
					<div id="pinlv" class="control-group">
						<label class="control-label" for="pl">重复：</label>
						<div class="controls">
							<select id="pl" name="pl" style="width: 150px"
								onchange="s(this.value)">
								<option value="0">单次</option>
								<option value="1">每天</option>
								<option value="2">每周</option>
								<option value="3">每月</option>
							</select>
						</div>
					</div>
					<div class="control-group" id="selectoncetime">
						<label class="control-label" for="oncetime">日期：</label>
						<div class="controls">
							<input type="text" id="oncetime" class="Wdate"
								onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"
								name="oncetime" min="${now }" max="${end }" value="${now }">
						</div>
					</div>
					<div class="control-group" id="selecteveryday"
						style="display: none;">
						<label class="control-label" for="starttime">开始日期：</label>
						<div class="controls">
							<span id="everyday-span"> <input type="text"
								id="starttime" class="Wdate" style="height: 25px"
								onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"
								name="starttime" min="${now }" max="${end }" value="${now }">
							</span>
						</div>
					</div>

					<div class="control-group" id="selecteverydayend"
						style="display: none;">
						<label class="control-label" for="endtime">结束日期：</label>
						<div class="controls">
							<span id="everyday-span2"> <input type="text"
								id="endtime" class="Wdate" style="height: 25px"
								onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"
								name="endtime">
							</span>
						</div>
					</div>
					<div class="control-group" id="selectweek" style="display: none;">
						<label class="control-label" for="week">周次：</label>
						<div class="controls">
							<span id="week-span"> <select id="week" name="week"
								style="width: 150px;">
									<option value="1">周一</option>
									<option value="2">周二</option>
									<option value="3">周三</option>
									<option value="4">周四</option>
									<option value="5">周五</option>
									<option value="6">周六</option>
									<option value="7">周日</option>
							</select>
							</span>
						</div>
					</div>
					<div class="control-group" id="selectsingleday"
						style="display: none;">
						<label class="control-label" for="day">开始日期：</label>
						<div class="controls">
							<span id="day-span"> <select id="day" name="day"
								style="width: 150px;">
									<c:forEach var="day" begin="1" end="31" step="1">
										<option value="${day}">${day}日</option>
									</c:forEach>
							</select>
							</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="kaishishijian">提交开始时间：</label>
						<div class="controls">
							<input type="text" id="kaishishijian" class="Wdate"
								style="height: 25px"
								onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm',minDate:'%H:%m'})"
								name="kaishishijian" value="20:00">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="jiezhishijian">提交截止时间：</label>
						<div class="controls">
							<input type="text" id="jiezhishijian" class="Wdate"
								style="height: 25px"
								onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm',minDate:'%H:%m'})"
								name="jiezhishijian" value="22:00">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="yaoqiu">要求：</label>
						<div class="controls">
							<input type="text" id="yaoqiu" name="yaoqiu"
								style="width: 400px;">
						</div>
					</div>

					<div class="" style="margin-left: 200px;">
						<button type="button" class="btn btn-primary" onclick="sub()">提交</button>
					</div>
				</fieldset>
			</form>
		</div>

		<!-- 			<div class="pagination pagination-centered"> -->
		<!-- 				<ul> -->
		<%-- 					<c:if test="${page >1}"> --%>
		<!-- 						<li><a href="JavaScript:void(0);" -->
		<!-- 							onclick="toContentPage('chaqin')">首页</a></li> -->
		<%-- 					</c:if> --%>
		<%-- 					<c:if test="${page > 1}"> --%>
		<!-- 						<li><a href="JavaScript:void(0);" -->
		<%-- 							onclick="toContentPage('chaqin?page=${page-1}')">上一页</a></li> --%>
		<%-- 					</c:if> --%>
		<%-- 					<li class="active"><a href="JavaScript:void(0);">第${page}页</a> --%>
		<!-- 					</li> -->
		<%-- 					<c:if test="${page < pages}"> --%>
		<!-- 						<li><a href="JavaScript:void(0);" -->
		<%-- 							onclick="toContentPage('chaqin?page=${page+1}')">下一页</a></li> --%>
		<%-- 					</c:if> --%>
		<%-- 					<c:if test="${page < pages}"> --%>
		<!-- 						<li><a href="JavaScript:void(0);" -->
		<%-- 							onclick="toContentPage('chaqin?page=${pages}')">尾页</a></li> --%>
		<%-- 					</c:if> --%>
		<!-- 				</ul> -->
		<!-- 			</div> -->
	</div>
</div>
<!--/span-->
</div>
<!--/row-->
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function sub() {
		$.ajax({
			type : "POST",//方法类型
			dataType : "text",//预期服务器返回的数据类型
			url : "subchaqinanpai",//url
			data : $('#form').serialize(),
			success : function(result) {
				if (result == "success") {
					alert("添加成功！");
				}else{
					alert("添加失败");
				}
				toContentPage('chaqin');
			},
			error : function() {
				alert("异常！");
			}
		});
	}

	function s(e) {
		if (e == "1") {
			document.getElementById("selectweek").style.display = "none";
			document.getElementById("selectsingleday").style.display = "none";
			document.getElementById("selectoncetime").style.display = "none";
			document.getElementById("selecteveryday").style.display = "";
			document.getElementById("selecteverydayend").style.display = "";
		} else if (e == "2") {
			document.getElementById("selectweek").style.display = "";
			document.getElementById("selectsingleday").style.display = "none";
			document.getElementById("selectoncetime").style.display = "none";
			document.getElementById("selecteveryday").style.display = "none";
			document.getElementById("selecteverydayend").style.display = "none";
		} else if (e == "3") {
			document.getElementById("selectweek").style.display = "none";
			document.getElementById("selectsingleday").style.display = "";
			document.getElementById("selectoncetime").style.display = "none";
			document.getElementById("selecteveryday").style.display = "none";
			document.getElementById("selecteverydayend").style.display = "none";
		} else {
			document.getElementById("selectweek").style.display = "none";
			document.getElementById("selectsingleday").style.display = "none";
			document.getElementById("selectoncetime").style.display = "";
			document.getElementById("selecteveryday").style.display = "none";
			document.getElementById("selecteverydayend").style.display = "none";
		}
	}
</script>
