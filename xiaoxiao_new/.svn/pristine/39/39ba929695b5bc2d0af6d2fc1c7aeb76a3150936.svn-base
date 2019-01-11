<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>社团详情</h2>
		</div>
					<div class="box-content">
					<c:set value="${shetuanjibenxinxi}" var="jibenxinxi" />
					<c:set value="${shetuanxinxi }" var= "xinxi"/>
					
					<div class="card" style="padding: 10px;">
			<span style="font-size: 16px;">社团名称：</span>&emsp;${jibenxinxi.mingcheng}<br>
			<span style="font-size: 16px;">指导教师：</span>&emsp;${jibenxinxi.zhidaojiaoshi }<br>
			<span style="font-size: 16px;">星级：</span>&emsp;&emsp;&emsp;${jibenxinxi.xingji}星级<br>
			<span style="font-size: 16px;">社长：</span>&emsp;&emsp;&emsp;${xinxi.shezhang }<br>
			<span style="font-size: 16px;">人数：</span>&emsp;&emsp;&emsp;${xinxi.sheyuanrenshu }人<br>
			<span style="font-size: 16px;">社团介绍：</span>&emsp;${jibenxinxi.shetuanjieshao }<br>
			<c:forEach items="${xinxi.bumen }" var="bumen">				
					<span style="font-size: 16px;">部门：&emsp;&emsp;&emsp;${bumen.mingcheng }</span><br>								
					<span style="font-size: 16px;">部长：&emsp;&emsp;&emsp;${bumen.buzhang}</span><br>
					<span style="font-size: 16px;">部门人数：&emsp;${bumen.bumenrenshu}人</span><br>
					<span style="font-size: 16px;">职责：&emsp;&emsp;&emsp;</span><br>
					<div class="text-indent">
						<font size="4px">&emsp;&emsp;${bumen.zhize}</font>
					</div>				
			</c:forEach>
		</div>
		</div>			
					
					
					
					
<!-- 					<div class="card"> -->
<!-- 						<div class="card-header"> -->
<!-- 							<div class="card-title"> -->
<%-- 								<div class="title">社团名称：${jibenxinxi.mingcheng}&emsp;&emsp;指导教师：${jibenxinxi.zhidaojiaoshi }</div> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="card-body"> -->
<!-- 							<div class="text-indent"> -->
<%-- 								<h3>星级：${jibenxinxi.xingji}星级&emsp;&emsp;社长：${xinxi.shezhang }&emsp;&emsp;人数：${xinxi.sheyuanrenshu }人</h3> --%>
<!-- 								<h3>社团介绍：</h3> -->
<!-- 								<div class="text-indent" style="width:80%"> -->
<%-- 									<font size="4px">${jibenxinxi.shetuanjieshao }</font> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<%-- 							<c:forEach items="${xinxi.bumen }" var="bumen"> --%>
<%-- 								<div class="sub-title"><h3>部门:${bumen.mingcheng }</h3></div> --%>
<!-- 									<div class="text-indent"> -->
<%-- 										<h3>部长：${bumen.buzhang}</h3> --%>
<%-- 										<h3>部门人数:${bumen.bumenrenshu}人</h3> --%>
<!-- 										<h3>职责：</h3> -->
<!-- 										<div class="text-indent"> -->
<%-- 											<font size="4px">&emsp;&emsp;${bumen.zhize}</font> --%>
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 							</c:forEach> --%>
<!-- 						</div> -->
<!-- 					</div> -->
					
					
					
					
<!-- 					<button type="button" name="back" onclick="goback()" class="btn btn-default">返回上一页</button> -->
				<input type="button" class="btn btn-default" value="返回上一页"
		onclick="goback()" />
			</div>
			
	<script type="text/javascript">
	function goback(){
		self.location.href=document.referrer;
		return false;
	}
	</script>
