<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/common/global.jsp"%>
<head>
<title>主页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<input style="display:none;" id="ss" type="text" value="${user.status}">
	<div class="navbar" id="naberDiv">
		
	</div>
	<div class="container-fluid-full">
		<div class="row-fluid">
			<!-- start: Main Menu -->
			<div id="sidebar-left" class="span2">
				
			</div>
			<!-- end: Main Menu -->
			<!-- start: Content -->
			<div id="content" class="span10">
				
			</div>
			<!-- end: Content -->
		</div><!--/fluid-row-->
		<footer id="footerDiv" style="text-align: center;">
		</footer>
	</div><!--/.fluid-container-->
</body>
<script type="text/javascript">
	$(document).ready(function() {
		var status = $("#ss").val();
		if(status == 'xuesheng'){
			
		}else{
			$('#content').load('index.jsp');
		}
		$('#naberDiv').load('navber.jsp');
		$('#sidebar-left').load('menu.jsp');
		$('#footerDiv').load('footer.jsp');
		
	});

</script>
</html>
