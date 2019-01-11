<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>意见动态
		</h2>
	</div>
	<div class="box-content">
		<div class="card-body">
			<form action="" id="chaxunyijian_fdy" name="chaxunyijian_fdy"
				method="post">
				<div style="width: 100%;">
					<span style="margin-left: 20px; color: red; font-weight: bold">标签:</span>
					<select id="biaoqian" name="biaoqian" style="width: 15%;">
						<option value=""
							<c:if test="${biaoqian==''}">selected="selected"</c:if>>全部</option>
						<option value="生活"
							<c:if test="${biaoqian=='生活'}">selected="selected"</c:if>>生活</option>
						<option value="学习"
							<c:if test="${biaoqian=='学习'}">selected="selected"</c:if>>学习</option>
						<option value="社团"
							<c:if test="${biaoqian=='社团'}">selected="selected"</c:if>>社团</option>
					</select> <span style="margin-left: 20px; color: red; font-weight: bold">班级:</span>
					<select id="banji" name="banji" style="width: 15%;">
						<option value=""
							<c:if test="${chaxunbanjiid==''}">selected="selected"</c:if>>全部</option>
						<c:forEach items="${banji}" var="BanJi">
							<option value="${BanJi.banjiid}"
								<c:if test="${BanJi.banjiid==chaxunbanjiid}">selected="selected"</c:if>>${BanJi.banjimingcheng}</option>
						</c:forEach>
					</select> <span style="margin-left: 20px; color: red; font-weight: bold">匿名状态:</span>
					<select id="niming" name="niming" style="width: 15%;">
						<option value=""
							<c:if test="${niming==''}">selected="selected"</c:if>>全部</option>
						<option value="0"
							<c:if test="${niming=='0'}">selected="selected"</c:if>>公开</option>
						<option value="1"
							<c:if test="${niming=='1'}">selected="selected"</c:if>>匿名</option>
					</select>
					<button type="button" style="margin-left: 50px; width: 5%"
						class="btn btn-success" onclick="query()">查询</button>
				</div>
			</form>

			<div class="card">
				<table class="table" id="DataTables_Table_0" style="width: 100%;">
					<thead>
						<tr>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${yijian}" var="YiJian" varStatus="i">
							<tr style="background-color: #ffffff;">
								<td>
									<div style="margin-left: 20px; width: 80%">
										<span style="font-size: 20px;">${YiJian.yijianmingcheng}</span>
										<span
											style="margin-left: 20px; font-size: 10px; color: orange;">#${YiJian.biaoqian}#</span>
										<span
											style="margin-left: 20px; font-size: 10px; color: orange;">
											<c:if test="${YiJian.nimingbiaoji=='1'}">#匿名#</c:if> <c:if
												test="${YiJian.nimingbiaoji=='0'}">#公开#</c:if>
										</span> <span
											style="margin-left: 20px; font-size: 10px; color: orange;">
											<c:if test="${YiJian.jieshourenleixing==1}">#辅导员&emsp;收#</c:if>
											<c:if test="${YiJian.jieshourenleixing==2}">#书记&emsp;收#</c:if>
											<c:if test="${YiJian.jieshourenleixing==3}">#学生处管理员&emsp;收#</c:if>
										</span> <span
											style="margin-left: 20px; font-size: 10px; color: orange;">#可见人：${YiJian.kejianrenfanwei}#</span>
										<c:if test="${YiJian.nimingbiaoji=='0'}">
											<span
												style="float: right; margin-right: 20px; font-size: 15px; color: blue">${YiJian.xueshengxingming}</span>
											<span
												style="float: right; margin-right: 20px; font-size: 15px; color: blue">${YiJian.banjimingcheng}</span>
										</c:if>
									</div>
									<div style="margin-left: 50px; color: blue">
										<h5>${YiJian.tijiaoshijian}</h5>
									</div>
									<div style="margin-left: 30px; width: 80%;">
										<h4 style="font-family: '宋体';">${YiJian.wenzineirong}</h4>
									</div>
									<div style="margin-left: 50px;">
										<c:if test="${not empty YiJian.tupian}">
											<c:forEach items="${YiJian.tupian}" var="tupian">
												<img height="150px" alt="" src="getPic?id=${tupian}">
											</c:forEach>
										</c:if>
									</div> <br>
									<div style="margin-left: 50px;">
										<a style="text-decoration: none;"
											onclick="pinglun_fdy(${YiJian.yijianid})"><span
											id="${YiJian.yijianid}pinglunliang"
											style="margin-left: 10px;" title="评论"
											class="icon-comment-alt">&emsp;${YiJian.pinglunliang}</span></a>
										<a style="text-decoration: none;"
											onclick="dianzan_fdy(${YiJian.yijianid})"><span
											id="${YiJian.yijianid}"
											style="margin-left:30px;cursor:pointer;<c:if test="${YiJian.isdianzan==1}">color:red;</c:if>"
											title="点赞" class="icon-thumbs-up">&emsp;${YiJian.dianzanliang}</span></a>
									</div> <br>

									<div style="margin-left: 50px; width: 80%;">
										<form action="" id="subpinglun" class="form-inline"
											method="post">
											<input type="hidden" name="id" value="${YiJian.yijianid}">
											<input id="pinglunneirong${YiJian.yijianid}"
												name="pinglunneirong" size="100"
												type="text" />&emsp;
											<button type="button"
												onclick="subpinglun(${YiJian.yijianid})">发送</button>
										</form>
									</div> <br>
									<div id="${YiJian.yijianid}2"
										style="display: none; margin-left: 50px; width: 80%">
										<div id="${YiJian.yijianid}pinglun"
											style="width: 80%; background-color: #ffffff;">
											<br>
											<c:forEach items="${YiJian.yijianhuifus}" var="yijianhuifu"
												varStatus="status">
												<div id="${yijianhuifu.huifuid}">
													<c:if test="${yijianhuifu.xueshengid!=null}">
														<span style="font-weight: bold">&emsp;${yijianhuifu.banjimingcheng}-${yijianhuifu.xueshengxingming}</span>
														<a style="text-decoration: none;"
															onclick="huifu(${i.index},${status.index})"><span
															style="float: right; margin-right: 20px;"
															title="回复${yijianhui.xueshengxingming}"
															class="icon-comment-alt"></span></a>
													</c:if>
													<c:if test="${yijianhuifu.jiaoshiid!=null}">
														<c:choose>
															<c:when test="${yijianhuifu.jiaoshiid==userid}">
																<span style="font-weight: bold">&emsp;我</span>
																<a style="text-decoration: none;"
																	onclick="delhuifu_fdy(${yijianhuifu.huifuid},${YiJian.yijianid})"><span
																	style="float: right; margin-right: 20px;" title="删除"
																	class="icon-trash"></span></a>
															</c:when>
															<c:otherwise>
																<span style="font-weight: bold">&emsp;${yijianhuifu.jiaoshixingming}</span>
																<a style="text-decoration: none;"
																	onclick="huifu(${i.index},${status.index})"><span
																	style="float: right; margin-right: 20px;"
																	title="回复${yijianhuifu.jiaoshixingming }"
																	class="icon-comment-alt"></span></a>
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${yijianhuifu.beihuifuxueshengid!=null}">&emsp;回复&emsp;<span
															style="font-weight: bold">${yijianhuifu.beihuifubanjimingcheng}-${yijianhuifu.beihuifuxueshengxingming}</span>
													</c:if>
													<c:if test="${yijianhuifu.beihuifujiaoshiid!=null}">&emsp;回复&emsp;
										<c:choose>
															<c:when test="${yijianhuifu.beihuifujiaoshiid==userid}">
																<span style="font-weight: bold">我</span>
															</c:when>
															<c:otherwise>
																<span style="font-weight: bold">${yijianhuifu.beihuifujiaoshixingming}</span>
															</c:otherwise>
														</c:choose>
													</c:if>
													<span>:&emsp;${yijianhuifu.huifuneirong}</span><br> <span
														style="font-size: 10px">&emsp;&emsp;${yijianhuifu.shijian}</span><br>
													<br>
													<c:if test="${yijianhuifu.jiaoshiid!=null}">
														<div id="${i.index}-${status.index}"
															style="margin-left: 30px; display: none;">
															<form id="form2" action="" class="form-inline"
																method="post">
																<input type="hidden" name="id"
																	id="8${i.index}${status.index}"
																	value="${YiJian.yijianid}_${yijianhuifu.jiaoshiid}">
																<input id="huifuneirong${i.index}${status.index}"
																	name="huifuneirong" size="80" type="text" />&emsp;
																<%-- 																	placeholder="回复&emsp;${yijianhuifu.jiaoshixingming}" />&emsp; --%>
																<button type="button"
																	onclick="return subhuifu2(${i.index},${status.index})">发送</button>
																<br> <br>
															</form>
														</div>
													</c:if>
													<c:if test="${yijianhuifu.xueshengid!=null}">
														<div id="${i.index}-${status.index}"
															style="margin-left: 30px; display: none;">
															<form id="form1" action="" class="form-inline"
																method="post">
																<input type="hidden" name="id"
																	id="6${i.index}${status.index}"
																	value="${YiJian.yijianid}_${yijianhuifu.xueshengid}">
																<input id="huifuneirong${i.index}${status.index}"
																	name="huifuneirong" size="80" type="text" />
																<%-- 																	placeholder="回复&emsp;${yijianhuifu.xueshengxingming}" />&emsp; --%>
																<button type="button" class="btn"
																	onclick="return subhuifu1(${i.index},${status.index})">发送</button>
																<br> <br>
															</form>
														</div>
													</c:if>
												</div>
											</c:forEach>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 					<div class="pagination pagination-centered"> -->
				<!-- 						<ul class="pagination"> -->
				<%-- 							<c:if test="${page >1}"> --%>
				<%-- 								<li><a href="chaxunyijian_fdy?biaoqian=${biaoqian}&banji=${chaxunbanjiid}&niming=${niming}&page=1">首页</a></li> --%>
				<%-- 							</c:if> --%>
				<%-- 							<c:if test="${page > 1}"> --%>
				<%-- 								<li><a href="chaxunyijian_fdy?biaoqian=${biaoqian}&banji=${chaxunbanjiid}&niming=${niming}&page=${page-1}">上一页</a></li> --%>
				<%-- 							</c:if> --%>
				<%-- 							<li><a href="#">第${page}页</a></li> --%>
				<%-- 							<c:if test="${page < pages}"> --%>
				<%-- 								<li><a href="chaxunyijian_fdy?biaoqian=${biaoqian}&banji=${chaxunbanjiid}&niming=${niming}&page=${page+1}">下一页</a></li> --%>
				<%-- 							</c:if> --%>
				<%-- 							<c:if test="${page < pages}"> --%>
				<%-- 								<li><a href="chaxunyijian_fdy?biaoqian=${biaoqian}&banji=${chaxunbanjiid}&niming=${niming}&page=${pages}">尾页</a></li> --%>
				<%-- 							</c:if> --%>
				<!-- 						</ul> -->
				<!-- 					</div> -->
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function query(){
		var biaoqian = $("#biaoqian").val();
		var banji = $("#banji").val();
		var niming = $("#niming").val();
		console.log(banji);
		console.log(biaoqian);
// 		console.log(chulibiaoji);
		console.log(niming);
		toContentPage("yijiandongtai_fdy?biaoqian="+biaoqian+"&banji="+banji+"&niming="+niming);
	}
	
	function dianzan_fdy(id){
		var code=id;
		$.ajax({
			type:"POST",
			url:"dianzan_shuji",
			data:{CODE:code},
			dataType:'text',
			cache:false,
			timeout: 5000,
			async:true, 
			success:function(data){
				var datas = data.split(",");
				if(datas[0]=="add"){
					document.getElementById(id).innerHTML="&emsp;"+datas[1];
					document.getElementById(id).style.color="red";
				}else if(datas[0]=="del"){
					document.getElementById(id).innerHTML="&emsp;"+datas[1];
					document.getElementById(id).style.color="black";
				}else{
					alert("fail");
				}
			},
			error:function()
			{
				alert("登录超时!")
			}
		});
	}
	
	function pinglun_fdy(id){
		if(document.getElementById(id+"2").style.display=="none"){
			document.getElementById(id+"2").style.display="block";
		}else{
			document.getElementById(id+"2").style.display="none";
		}
	}
	
	function subpinglun(id){
		if($("#pinglunneirong"+id).val()==""){
			alert("请输入评论内容……")
			return false;
		}else{
			$.ajax({
				type : "POST",
				url : 'subpinglun_fdy',
				data : $('#subpinglun').serialize(),
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data.status == "success") {
						alert("评论成功！");
					} else {
						alert("评论失败");
					}
					toContentPage('yijiandongtai_fdy');
				},
				error : function() {
					alert("超时!");
				}
			});
		}
	}
	function huifu(id1,id2){
		var id = id1+"-"+id2;
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}else{
			document.getElementById(id).style.display="none";
		}
	}
	function subhuifu1(id1,id2){
		if($("#huifuneirong"+id1+id2).val()==""){
			alert("请输入回复内容……")
			return false;
		}else{
			var id = $("#6"+id1+id2).val();
			$.ajax({
				type : "POST",
				url : 'subhuifuxuesheng_fdy',
				data : {
					id : id,
					huifuneirong : $("#huifuneirong"+id1+id2).val(),
				},
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data.status == "success") {
						alert("回复成功！");
					} else {
						alert("回复失败");
					}
					toContentPage('yijiandongtai_fdy');
				},
				error : function() {
					alert("超时!")
				}
			});
		}
	}
	function subhuifu2(id1,id2){
		if($("#huifuneirong"+id1+id2).val()==""){
			alert("请输入回复内容……")
			return false;
		}
		var id = $("#8"+id1+id2).val();
		$.ajax({
			type : "POST",
			url : 'subhuifujiaoshi_fdy',
			data : {
				id : id,
				huifuneirong : $("#huifuneirong"+id1+id2).val(),
			},
			dataType : 'json',
			cache : false,
			timeout : 5000,
			async : true,
			success : function(data) {
				if (data.status == "success") {
					alert("回复成功！");
				} else {
					alert("回复失败");
				}
				toContentPage('yijiandongtai_fdy');
			},
			error : function() {
				alert("超时!")
			}
		});
	}
	
	function delhuifu_fdy(id1,id2){
		var code=id1;
		if(confirm("确定要删除吗？")==true){
			$.ajax({
				type:"POST",
				url:"delhuifu_fdy",
				data:{CODE:code},
				dataType:'text',
				cache:false,
				timeout: 5000,
				async:true, 
				success:function(data){
					var datas = data.split(",");
					if(datas[0]=="true"){
						$("#"+id1).remove();
						document.getElementById(id2+"pinglunliang").innerHTML="&emsp;"+datas[1];
						alert("删除成功!");
					}
					if(datas[0]=="null"){
						alert("该评论不存在！")
					}
					if(datas[0]=="false"){
						alert("删除失败！");
					}
				},
				error:function()
				{
					alert("登录超时！");
				}
			});
		}
	}
	</script>

