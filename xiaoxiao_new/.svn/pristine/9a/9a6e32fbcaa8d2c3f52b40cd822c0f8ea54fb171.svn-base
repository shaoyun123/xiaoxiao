<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>查看通知</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Fonts -->
<link href='static/lib/css/google1.css' rel='stylesheet' type='text/css'>
<link href='static/lib/css/google2.css' rel='stylesheet' type='text/css'>
<!-- CSS Libs -->
<link rel="stylesheet" type="text/css"
	href=" static/lib/css/bootstrap.min.css">
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
<link rel="stylesheet" type="text/css"
	href=" static/css/summernote/summernote-0.8.8.css">
<!-- CSS App -->
<link rel="stylesheet" type="text/css" href=" static/css/style.css">
<link rel="stylesheet" type="text/css"
	href=" static/css/themes/flat-blue.css">
<link rel="stylesheet" type="text/css" href=" static/css/gg-bootdo.css">
<link rel="stylesheet" type="text/css" href=" static/css/cropper.css">
</head>

<body class="flat-blue">
<!-- 	<div class="app-container"> -->
<!-- 		<div class="row content-container"> -->
			<!-- Main Content -->
			<div class="container-fluid" >
				<div class="side-body">
					<div class="row">
						<div class="col-xs-12">
							<div class="card" style="background-color: #ffffff;">
								<div class="container">
									<div class="row">
										<div
											class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
											<div class="post-heading">
												<h1 style="">${tongzhi.title}</h1>
												<span class="meta"><a href="#" style="margin-left:500px;">${tongzhi.faburiqi}</a>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div style="display:none;">
								<textarea rows="3000000" cols="9000000" hidden="hidden" id="contentss">${tongzhi.content }</textarea>
								</div>
								<article>
									<div class="container" >
										<div class="col-sm-11">
											<div id="content" class="summernote" style="display:block;"></div>
										</div>

									</div>
								</article>
							</div>
						</div>
					</div>
				</div>
			</div>
<!-- 		</div> -->

		<!-- Main Content End-->

		<footer class="app-footer">
			<div class="wrapper">
				<span class="pull-right"> <a href="#"><i
						class="fa fa-long-arrow-up"></i></a>
				</span>
			</div>
		</footer>
		<div>
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
			<script type="text/javascript"
				src="static/lib/js/select2.full.min.js"></script>
			<script type="text/javascript" src="static/lib/js/ace/ace.js"></script>
			<script type="text/javascript" src="static/lib/js/ace/mode-html.js"></script>
			<script type="text/javascript"
				src="static/lib/js/ace/theme-github.js"></script>
			<!-- Javascript -->
			<script type="text/javascript" src="static/js/app.js"></script>
			<script type="text/javascript" src="static/js/gg-bootdo.js"></script>
			<script type="text/javascript" src="static/js/cropper.min.js"></script>
			<script type="text/javascript"
				src="static/js/summernote/summernote.js"></script>
			<script type="text/javascript"
				src="static/js/summernote/summernote-zh-CN.min.js"></script>
			<script type="text/javascript"
				src="static/js/My97DatePicker/WdatePicker.js"></script>
			<script type="text/javascript">
				$().ready(function() {
					$('.summernote').summernote({
						lang : 'zh-CN'
					});
					var content = $("#contentss").val();
					$('#content').summernote('code', content);
					$('.summernote').summernote('destroy');
				});
			</script>
		</div>
<!-- 	</div> -->
</body>
</html>