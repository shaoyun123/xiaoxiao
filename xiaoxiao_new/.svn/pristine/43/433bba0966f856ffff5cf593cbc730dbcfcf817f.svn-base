<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css"
	href=" static/lib/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/animate.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/bootstrap-switch.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/checkbox3.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/select2.min.css">
<!-- CSS App -->
<link rel="stylesheet" type="text/css" href=" static/css/style.css">
<link rel="stylesheet" type="text/css"
	href=" static/css/themes/flat-blue.css">
<link href='static/css/fullcalendar.min.css' rel='stylesheet' />
<link href='static/css/fullcalendar.print.min.css' rel='stylesheet'
	media='print' />
<script src='static/js/moment.min.js' type="text/javascript"></script>
<script src='static/js/jquery-1.10.2.min.js' type="text/javascript"></script>
<!-- <script src='static/js/fullcalendar.min.js'  type="text/javascript"></script> -->
<script src='static/js/fullcalendar.js' type="text/javascript"></script>
<script src='static/js/locale-all.js' type="text/javascript"></script>

<script type="text/javascript">
var path = '<%=request.getContextPath()%>';
	var thisDate = new Date();

	function fanhuichongzai() {

		//$('#calendarHtml').html('');
		$('#calendarHtml').load(
				path + '/static/richeng/wodericheng_jiaoshi.jsp?t='
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


<div class="box span12">
	<div class="navbar-inner">
		<!-- 			<h2><i class="icon-align-justify"></i><span class="break"></span>我的日程</h2>				 -->

		<div class="nav-no-collapse header-nav">
			<ul class="nav pull-left" style="display: inline;">
					<h2 style="display: inline;">
						<i class="icon-align-justify" style="margin-top: 10px;"></i>
					</h2>
					<li class="dropdown" style="display: inline; margin-left: 30px;"><a
				class="btn account dropdown-toggle"
					data-toggle="dropdown" href="#">
						日程管理
				</a>
					<ul class="dropdown-menu">
						<li class="dropdown-menu-title"></li>
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('addhuodong_jiaoshi')"><i
								class="icon-plus" style="margin-left: 2px;"></i> 新增活动</a></li>
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('newhuodong_jiaoshi')"><i
								class="icon-comment" style="margin-left: 2px;"></i> 待确认活动</a></li>
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('myhuodong_jiaoshi')"><i
								class="icon-align-justify" style="margin-left: 2px;"></i> 我的活动</a></li>
					</ul></li>
				<!-- end: User Dropdown -->
			</ul>
		</div>

	</div>



	<div class="box-content">
		<form id="riqi" name="form" onsubmit="return search()"
			action="chaxunricheng_fdy" method="post" class="form-horizontal"
			style="margin-bottom: 10px" role="form" data-toggle="validation"
			novalidate="novalidate">
			<div style="width: 100%; ">

				<div id='calendarHtml'></div>
				<!-- <div id='calendar'></div> -->
			</div>
		</form>
	</div>
</div>
<div>
	<!-- Javascript Libs -->
	<!-- 			<script type="text/javascript" src="static/lib/js/jquery-1.10.2.min.js"></script> -->
	<!-- 			<script type="text/javascript" src="static/lib/js/bootstrap.min.js"></script> -->
	<!-- 			<script type="text/javascript" src="static/lib/js/Chart.min.js"></script> -->
	<!-- 			<script type="text/javascript" -->
	<!-- 				src="static/lib/js/bootstrap-switch.min.js"></script> -->
	<!-- 			<script type="text/javascript" -->
	<!-- 				src="static/lib/js/jquery.matchHeight-min.js"></script> -->
	<!-- 			<script type="text/javascript" -->
	<!-- 				src="static/lib/js/jquery.dataTables.min.js"></script> -->
	<!-- 			<script type="text/javascript" -->
	<!-- 				src="static/lib/js/dataTables.bootstrap.min.js"></script> -->
	<!-- 			<script type="text/javascript" -->
	<!-- 				src="static/lib/js/select2.full.min.js"></script> -->
	<!-- 			<script type="text/javascript" src="static/lib/js/ace/ace.js"></script> -->
	<!-- 			<script type="text/javascript" src="static/lib/js/ace/mode-html.js"></script> -->
	<!-- 			<script type="text/javascript" -->
	<!-- 				src="static/lib/js/ace/theme-github.js"></script> -->
	<!-- Javascript -->
	<!-- 			<script type="text/javascript" src="static/js/app.js"></script> -->
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
</div>
</div>
</div>
</body>
</html>