<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.controls {
	position: relative;
	top: 5px;
}
</style>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set value="${jiatiao }" var="jiatiao" />
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>请假详情
		</h2>
	</div>
	<div class="box-content">
		<div class="card-body">
			<form class="form-horizontal">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="disabledInput">学号:</label>
						<div class="controls">
							<span>${jiatiao.xuehao}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">姓名:</label>
						<div class="controls">
							<p>${jiatiao.xueshengxingming}</p>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">班级:</label>
						<div class="controls">
							<p>${jiatiao.banjimingcheng}</p>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">请假类别:</label>
						<div class="controls">
							<c:if test="${jiatiao.qingjialeibie==1 }">事假</c:if>
							<c:if test="${jiatiao.qingjialeibie==2 }">病假</c:if>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">请假事由:</label>
						<div class="controls">
							<p>${jiatiao.qingjiashiyou}</p>
						</div>
					</div>

					<!-- 			<div class="sub-title"> -->
					<!-- 				<label>&emsp;&emsp;上传证明：</label> -->
					<!-- 				<div id="imgAll" class="imgAll"> -->
					<!-- 					<ul id="imgul"> -->
					<%-- 						<c:choose> --%>
					<%-- 							<c:when test="${not empty jiatiao.tupian }"> --%>
					<%-- 								<c:set var="num" value="1" /> --%>
					<%-- 								<c:forEach items="${jiatiao.tupian }" var="tupian"> --%>
					<!-- 									<li -->
					<!-- 										style="width: 200px; height: 200px; list-style-type: none; border: solid 1px #ccc; margin: 8px 5px; float: left; position: relative; box-shadow: 0 0 10px #eee;"><img -->
					<!-- 										style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; display: block;" -->
					<%-- 										id="${num}" data-original="getPic?id=${tupian}" title="点击查看大图" --%>
					<%-- 										src="getPic?id=${tupian}" height="200px;" --%>
					<%-- 										onerror="javascript:this.src='${basePath }static/img/452633983262339668.jpg'"></li> --%>
					<%-- 									<c:set var="num" value="${num+1}" /> --%>
					<%-- 								</c:forEach> --%>
					<%-- 							</c:when> --%>
					<%-- 							<c:otherwise> --%>
					<!-- 								<li -->
					<!-- 									style="display: none; width: 200px; height: 200px; list-style-type: none; border: solid 1px #ccc; margin: 8px 5px; float: left; position: relative; box-shadow: 0 0 10px #eee;"> -->
					<!-- 									<img -->
					<!-- 									style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; display: block;" -->
					<!-- 									id="1" data-original="" title="点击查看大图" -->
					<%-- 									src="${basePath }static/img/452633983262339668.jpg" --%>
					<!-- 									height="200px;"> -->
					<!-- 								</li> -->
					<%-- 							</c:otherwise> --%>
					<%-- 						</c:choose> --%>
					<!-- 					</ul> -->
					<!-- 					<script src="static/js/viewer.min.js"></script> -->
					<!-- 					<script type="text/javascript"> -->
					<!--    												    </script> -->
					<!-- 				</div> -->
					<!-- 			</div> -->

					<div class="control-group">
						<label class="control-label" for="focusedInput">上传证明:</label>
						<div class="controls">
							<div id="imgAll" class="imgAll">
								<ul id="imgul">
									<c:choose>
										<%-- 												data-original="getPic?id=${tupian}" --%>
										<c:when test="${not empty jiatiao.tupian }">
											<c:set var="num" value="1" />
											<c:forEach items="${jiatiao.tupian }" var="tupian">
												<li
													style="width: 200px; height: 200px; list-style-type: none; border: solid 1px #ccc; margin: 8px 5px; float: left; position: relative; box-shadow: 0 0 10px #eee;">
													<img
													style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; display: block;"
													id="${num }" data-original="getPic?id=${tupian}"
													title="点击查看大图" src="getPic?id=${tupian}" height="200px;"
													onerror="javascript:this.src='${basePath }static/img/452633983262339668.jpg'">
												</li>
												<c:set var="num" value="${num+1}" />
											</c:forEach>
										</c:when>
										<c:otherwise>
											<li
												style="display: none; width: 200px; height: 200px; list-style-type: none; border: solid 1px #ccc; margin: 8px 5px; position: relative; box-shadow: 0 0 10px #eee;">
												<img
												style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; visibility: hidden;"
												id="1" data-original="getPic?id=452633983262339668.jpg"
												title="点击查看大图"
												src="${basePath }static/img/452633983262339668.jpg"
												height="200px;">
											</li>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">时间:</label>
						<div class="controls">
							自：<font color="red">${jiatiao.qingjiakaishishijian }</font> 至：<font
								color="red">${jiatiao.qingjiajieshushijian} </font>&emsp;共：<font
								color="red">${jiatiao.tianshu }天</font>
						</div>
					</div>

					<c:if test="${jiatiao.zhuangtai==3 }">
						<div class="control-group">
							<label class="control-label" for="focusedInput">被驳回的理由:</label>
							<div class="controls">
								<span>
									<p>${jiatiao.tongzhineirong }</p>
								</span>
							</div>
						</div>
					</c:if>


					<div class="control-group">
						<label class="control-label" for="focusedInput"></label>
						<div class="controls">
							<c:choose>
								<c:when test="${jiatiao.zhuangtai==6&&jiatiao.tianshu>7 }">
									<input type="button" style="margin-left: 30px;"
										class="btn btn-success" value="同意并上报"
										onclick="shangbao(${jiatiao.qingjiaid })">
									<input type="button" style="margin-left: 30px;"
										class="btn btn-danger" value="拒绝"
										onclick="jujue(${jiatiao.qingjiaid })">
								</c:when>
								<c:when test="${jiatiao.zhuangtai==6&&jiatiao.tianshu<=7 }">
									<input type="button" style="margin-left: 30px;"
										class="btn btn-success" value="同意"
										onclick="tongyi(${jiatiao.qingjiaid })">
									<input type="button" style="margin-left: 30px;"
										class="btn btn-danger" value="拒绝"
										onclick="jujue(${jiatiao.qingjiaid })">
								</c:when>
							</c:choose>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>
<!--/span-->


<script type="text/javascript">
		function shangbao(id) {
			$.ajax({
				type: "POST",
				url: 'qjsb_sj',
		    	data:{CODE:id},
				dataType:'text',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data)
				{
					if(data=="success"){
						alert("成功！");
						toContentPage("qingjiadaichuli_sj");
					}
					else{
						alert("fail");
						toContentPage("qingjiadaichuli_sj");
					}
				},
				error:function()
				{
					alert("超时!");
					toContentPage("qingjiadaichuli_sj");
				}
				
			});
		}
		function tongyi(id) {
			$.ajax({
				type: "POST",
				url: 'qjtg_sj',
		    	data:{CODE:id},
				dataType:'text',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data)
				{
					if(data=="success"){
						alert("成功！");
						toContentPage("qingjiadaichuli_sj");
					}
					else{
						alert("fail");
						toContentPage("qingjiadaichuli_sj");
					}
				},
				error:function()
				{
					alert("超时!");
					toContentPage("qingjiadaichuli_sj");
				}
				
			});
		}
		function jujue(id) {
			var liyou = prompt("请输入拒绝理由：","")
			$.ajax({
				type: "POST",
				url: 'qjjj_sj',
		    	data:{"CODE":id,"liyou":liyou},
				dataType:'text',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data)
				{
					if(data=="success"){
						alert("成功！")
						window.location.href="qingjiadaichuli_sj";
					}
					else{
						alert("fail");
						toContentPage("qingjiadaichuli_sj");
					}
				},
				error:function()
				{
					alert("超时!");
					toContentPage("qingjiadaichuli_sj");
				}
				
			});
		}
		function goback(){
			self.location.href=document.referrer;
			return false;
		}
	</script>
