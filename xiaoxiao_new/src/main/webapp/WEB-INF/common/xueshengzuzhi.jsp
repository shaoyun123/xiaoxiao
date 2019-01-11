<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Main Content -->
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>社团介绍
		</h2>
	</div>
	<div class="box-content">
		<!-- 			                         <table class="table table-bordered table-striped table-condensed bootstrap-datatable "> -->
		<!-- 										<thead> -->
		<!-- 										<tr> -->
		<!-- 											<th>社团名称：</th> -->
		<!-- 											<th>星级：</th> -->
		<!-- 											<th>社长：</th> -->
		<!-- 											<th>人数：</th> -->
		<!-- 											<th>社团介绍：</th> -->
		<!-- 											<th>创建时间</th> -->
		<!-- 											<th>详情</th> -->
		<!-- 										</tr> -->
		<!-- 										</thead> -->
		<!-- 									</table> -->

		<div class="card" style="padding: 10px;">
			<span style="font-size: 16px;">学生组织名称：</span>&emsp;${jibenxinxi.mingcheng}<br>
			<span style="font-size: 16px;">指导教师：</span>&emsp;&emsp;&emsp;${jibenxinxi.zhidaojiaoshi }<br>
			<span style="font-size: 16px;">指导人：</span>&emsp;&emsp;&emsp;${xinxi.zhidaoren}<br>
			<span style="font-size: 16px;">负责人：</span>&emsp;&emsp;&emsp;${xinxi.fuzeren }<br>
			<span style="font-size: 16px;">人数：</span>&emsp;${xinxi.renshu }人<br>
			<span style="font-size: 16px;">介绍：</span>&emsp;${jibenxinxi.jianjie }<br>
			<%-- 			<h3>星级：${jibenxinxi.xingji}星级&emsp;&emsp;社长：${xinxi.shezhang }&emsp;&emsp;人数：${xinxi.sheyuanrenshu }人</h3> --%>
			<!-- 			<h3>社团介绍：</h3> -->

			<%-- 			<font size="4px">${jibenxinxi.shetuanjieshao }</font> --%>

			<c:forEach items="${xinxi.bumen }" var="bumen">

				<span style="font-size: 16px;">部门：&emsp;&emsp;&emsp;${bumen.mingcheng }</span>
				<br>
				<span style="font-size: 16px;">部长：&emsp;&emsp;&emsp;${bumen.buzhang}</span>
				<br>
				<span style="font-size: 16px;">部门人数：&emsp;${bumen.bumenrenshu}人</span>
				<br>
				<span style="font-size: 16px;">职责：&emsp;&emsp;&emsp;${bumen.zhize}</span>
				<br>
				<!-- 					<div class="text-indent"> -->
				<!-- 						<font size="4px">&emsp;&emsp;${bumen.zhize}</font> -->
				<!-- 					</div> -->
			</c:forEach>
		</div>
	</div>
	<!-- 	<input type="button" class="btn btn-primary" value="返回上一页" -->
	<!-- 		onclick="goback()" /> -->
</div>
<script type="text/javascript">
	function goback() {
		toContentPage('shetuanjieshao_c');
		return false;

	}
</script>
