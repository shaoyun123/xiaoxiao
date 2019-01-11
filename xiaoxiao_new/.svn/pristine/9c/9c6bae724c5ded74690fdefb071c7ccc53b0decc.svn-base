<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href='static/css/fullcalendar.min.css' rel='stylesheet' />
<link href='static/css/fullcalendar.print.min.css' rel='stylesheet'
	media='print' />
<script src='static/js/moment.min.js' type="text/javascript"></script>
<script src='static/js/jquery-1.10.2.min.js' type="text/javascript"></script>
<script src='static/js/fullcalendar.js' type="text/javascript"></script>
<script src='static/js/locale-all.js' type="text/javascript"></script>

<script type="text/javascript">
var path = '<%=request.getContextPath()%>';
	var thisDate = new Date();

	function fanhuichongzai() {
		//$('#calendarHtml').html('');
		$('#calendarHtml').load(
				path + '/static/richeng/wodericheng.jsp?t='
						+ new Date().getTime(), function(e) {
				});
	}

	$(document).ready(function() {
		$.ajaxSetup({
			cache : false
		});
		fanhuichongzai();

	});
</script>


<!-- Main Content -->
<div class="row-fluid">
	<div class="box span12">
		<div class="navbar-inner">

			<div class="nav-no-collapse header-nav">
				<ul class="nav pull-left" style="display: inline;">
					<h2 style="display: inline;">
						<i class="icon-align-justify" style="margin-top: 10px;"></i>
					</h2>
					<li class="dropdown" style="display: inline; margin-left: 30px;"><a
						class="btn account dropdown-toggle" data-toggle="dropdown"
						href="#"> 日程管理 </a>
						<ul class="dropdown-menu" style="position: absolute;">
							<li class="dropdown-menu-title"></li>
							<li><a href="JavaScript:void(0);"
								onclick="toContentPage('addhuodong')"><i class="icon-plus"
									style="margin-left: 2px;"></i> 新增活动</a></li>
							<li><a href="JavaScript:void(0);"
								onclick="toContentPage('newhuodong')"><i
									class="icon-comment" style="margin-left: 2px;"></i> 待确认活动</a></li>
							<li><a href="JavaScript:void(0);"
								onclick="toContentPage('myhuodong')"><i
									class="icon-align-justify" style="margin-left: 2px;"></i> 我的活动</a></li>
						</ul></li>
					<!-- end: User Dropdown -->
				</ul>
			</div>

		</div>
		<div class="box-content">
			<form id="riqi" name="form" onsubmit="return search()"
				action="chaxunricheng" method="post" class="form-horizontal"
				style="margin-bottom: 10px" role="form" data-toggle="validation"
				novalidate="novalidate">
				<div style="width: 100%; margin-left: 20px;">

					<div id='calendarHtml'></div>
					<!-- <div id='calendar'></div> -->
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	//根据年、月、日查询日程
	function search() {
		var year = document.getElementById("year");
		var month = document.getElementById("month");
		var day = document.getElementById("day");
		var yy = year.value;
		var mm = month.value;
		var dd = day.value;
		if (yy == "") {
			alert("请选择要查询的年");
			return false;
		} else if (mm == "") {
			alert("请选择要查询的月");
			return false;
		} else if (dd == "") {
			alert("请选择要查询的日");
			return false;
		} else {
			return true;
			document.getElementById("year").value = yy;
			document.getElementById("month").value = mm;
			document.getElementById("day").value = dd;
		}
	}
</script>
<script type="text/javascript">
	function val(num) {
		var i = (num + "").length;
		while (i++ < 2)
			num = "0" + num;
		return num;
	}

	function delhuodong() {
		if (confirm("确认拒绝吗？") == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
