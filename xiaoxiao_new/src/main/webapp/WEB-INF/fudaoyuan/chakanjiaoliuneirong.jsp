<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>查看交流内容
			</h2>
		</div>
		<div class="box-content">
			<div style="margin-left: 10px">
				<span style="font-size: 20px">${jlnr.jiaoliumingcheng}</span> <span
					style="margin-left: 30px; color: orange;">#${jlnr.xingming}#</span>
				<span style="margin-left: 20px; color: orange;">#${jlnr.xuehao}#</span>
				<span style="margin-left: 20px; color: orange;">#${jlnr.banjimingcheng}#</span>
			</div>
			<div style="margin-left: 40px">
				<h5 style="color: blue;">${jlnr.shangchuanriqi}</h5>
			</div>
			<div style="width: 90%; margin-left: 30px">
				<h4 style="font-family: '楷体'">${jlnr.xueshengshangchuan}</h4>
			</div>
			<hr>
			<div style="width: 80%; margin-left: 30px">
				<span style="font-size: 15px;">审核</span>
			</div>
			<div style="margin-left: 40px">
				<h5 style="color: blue;">${jlnr.shenheriqi}</h5>
			</div>
			<div style="width: 90%; margin-left: 30px">
				<h4 style="font-family: '楷体'">${jlnr.fudaoyuanshenhe}</h4>
			</div>
			<div>
				<button
					onclick="toContentPage('chakanxiangqing?id=${jlnr.anpaiid}')" class="btn"
					style="margin-right: 20px; float: right;">关闭</button><br><br>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
		
	</script>
</body>
</html>