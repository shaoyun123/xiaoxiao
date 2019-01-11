<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>意见动态</h2>
		</div>
					<div class="box-content">
				<form action="chaxunyijian" id="chaxunyijian" name="chaxunyijian" method="post">
					<div style="width:100%;">
						<span style="margin-left:20px;color:red;font-weight:bold">标签</span>
						<select id="biaoqian" name="biaoqian" style="width:15%;">
							<option value="" <c:if test="${biaoqian==''}">selected="selected"</c:if>>全部</option>
							<option value="生活" <c:if test="${biaoqian=='生活'}">selected="selected"</c:if>>生活</option>
							<option value="学习" <c:if test="${biaoqian=='学习'}">selected="selected"</c:if>>学习</option>
							<option value="社团" <c:if test="${biaoqian=='社团'}">selected="selected"</c:if>>社团</option>
						</select>
						<span style="margin-left:20px;color:red;font-weight:bold">发表人范围</span>
						<select id="fanwei" name="fanwei" style="width:15%;">
							<option value="" <c:if test="${fanwei==''}">selected="selected"</c:if>>全部</option>
							<option value="本学院" <c:if test="${fanwei=='本学院'}">selected="selected"</c:if>>本学院</option>
							<option value="本专业" <c:if test="${fanwei=='本专业'}">selected="selected"</c:if>>本专业</option>
							<option value="本班" <c:if test="${fanwei=='本班'}">selected="selected"</c:if>>本班</option>
						</select>
						<span style="margin-left:20px;color:red;font-weight:bold">匿名状态</span>
						<select id="niming" name="niming" style="width:15%;">
							<option value="" <c:if test="${niming==''}">selected="selected"</c:if>>全部</option>
							<option value="0" <c:if test="${niming=='0'}">selected="selected"</c:if>>公开</option>
							<option value="1" <c:if test="${niming=='1'}">selected="selected"</c:if>>匿名</option>
						</select>
						
						<input type="submit" style="margin-left: 50px;width:5%"value="查询">
					</div>
				</form>
				
				<div class="card">
				<table class="table" id="DataTables_Table_0" style="width: 100%;">
				<thead><tr><th></th></tr></thead>
				<tbody>
					<c:forEach items="${yijian}" var="YiJian" varStatus="i">
					<tr style="background-color:#dfffdf;"><td>
						<div style="margin-left:20px;width:80%">
							<span style="font-size:20px;">${YiJian.yijianmingcheng}</span>
							<span style="margin-left:20px;font-size:10px;color:orange;">#${YiJian.biaoqian}#</span>
							<span style="margin-left:20px;font-size:10px;color:orange;">
								<c:if test="${YiJian.nimingbiaoji=='1'}">#匿名#</c:if>
								<c:if test="${YiJian.nimingbiaoji=='0'}">#公开#</c:if>
							</span>
							<span style="margin-left:20px;font-size:10px;color:orange;">
								<c:if test="${YiJian.jieshourenleixing==1}">#辅导员&emsp;收#</c:if>
								<c:if test="${YiJian.jieshourenleixing==2}">#书记&emsp;收#</c:if>
								<c:if test="${YiJian.jieshourenleixing==3}">#学生处管理员&emsp;收#</c:if>
							</span>
							<span style="margin-left:20px;font-size:10px;color:orange;">#可见人：${YiJian.kejianrenfanwei}#</span>
							<c:if test="${YiJian.nimingbiaoji=='0'}">
								<span style="float:right;margin-right:20px;font-size:15px;color:blue">${YiJian.xueshengxingming}</span>
								<span style="float:right;margin-right:20px;font-size:15px;color:blue">${YiJian.banjimingcheng}</span>
							</c:if>
						</div>
						<div style="margin-left:50px;color:blue"><h5>${YiJian.tijiaoshijian}</h5></div>
						<div style="margin-left:30px;width:80%;"><h4 style="font-family:'楷体';">${YiJian.wenzineirong}</h4></div>
						<div style="margin-left:50px;">
							<c:if test="${not empty YiJian.tupian}">
							<c:forEach items="${YiJian.tupian}" var="tupian">
								<img  height="150px" alt="" src="getPic?id=${tupian}">
							</c:forEach>
							</c:if>
						</div><br>
						<div style="margin-left:50px;">
							<a onclick="pinglun(${YiJian.yijianid})"><span id="${YiJian.yijianid}pinglunliang" style="margin-left:10px;" title="评论" class="fa fa-comment-o">&emsp;${YiJian.pinglunliang}</span></a>
							<a onclick="dianzan(${YiJian.yijianid})"><span id="${YiJian.yijianid}" style="margin-left:30px;cursor:pointer;<c:if test="${YiJian.isdianzan==1}">color:red;</c:if>" title="点赞" class="glyphicon glyphicon-thumbs-up">&emsp;${YiJian.dianzanliang}</span></a>
						</div><br>
						
						<div style="margin-left:50px;width:80%">
							<form action="subpinglun?id=${YiJian.yijianid}" class="form-inline" method="post">
								<input id="pinglunneirong${YiJian.yijianid}" name="pinglunneirong" size="100" type="text" placeholder="评论"/>&emsp;
								<input type="submit" value="发送" onclick="return subpinglun(${YiJian.yijianid})">
							</form>
						</div><br>
						<div id="${YiJian.yijianid}2" style="display:none;margin-left:50px;width:80%">
							<div id="${YiJian.yijianid}pinglun" style="width:80%;background-color:#f0fff0;"><br>
								<c:forEach items="${YiJian.yijianhuifus}" var="yijianhuifu" varStatus="status">
								<div id="${yijianhuifu.huifuid}">
									<c:if test="${yijianhuifu.jiaoshiid!=null}">
										<span style="font-weight:bold">&emsp;${yijianhuifu.jiaoshixingming}</span>
										<a onclick="huifu(${i.index},${status.index})"><span style="float:right;margin-right:20px;" title="回复" class="fa fa-comment-o"></span></a>
									</c:if>
									<c:if test="${yijianhuifu.xueshengid!=null}">
										<c:choose>
										<c:when test="${yijianhuifu.xueshengid==userid}">
											<span style="font-weight:bold">&emsp;我</span>
											<a onclick="delhuifu(${yijianhuifu.huifuid},${YiJian.yijianid})"><span style="float:right;margin-right:20px;" title="删除" class="fa fa-trash-o"></span></a>
										</c:when>
										<c:otherwise>
											<span style="font-weight:bold">&emsp;${yijianhuifu.banjimingcheng}-${yijianhuifu.xueshengxingming}</span>
											<a onclick="huifu(${i.index},${status.index})"><span style="float:right;margin-right:20px;" title="回复" class="fa fa-comment-o"></span></a>
										</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${yijianhuifu.beihuifujiaoshiid!=null}">&emsp;回复&emsp;<span style="font-weight:bold">${yijianhuifu.beihuifujiaoshixingming}</span></c:if>
									<c:if test="${yijianhuifu.beihuifuxueshengid!=null}">&emsp;回复&emsp;
										<c:choose>
											<c:when test="${yijianhuifu.beihuifuxueshengid==userid}">
												<span style="font-weight:bold">我</span>
											</c:when>
											<c:otherwise>
												<span style="font-weight:bold">${yijianhuifu.beihuifubanjimingcheng}-${yijianhuifu.beihuifuxueshengxingming}</span>
											</c:otherwise>
										</c:choose>
									</c:if>
									<span>:&emsp;${yijianhuifu.huifuneirong}</span><br>
									<span style="font-size:10px">&emsp;&emsp;${yijianhuifu.shijian}</span><br><br>
									<c:if test="${yijianhuifu.jiaoshiid!=null}">
										<div id="${i.index}-${status.index}" style="margin-left:30px;display:none;">
											<form action="subhuifujiaoshi?id=${YiJian.yijianid}_${yijianhuifu.jiaoshiid}" class="form-inline" method="post">
												<input id="huifuneirong${i.index}${status.index}" name="huifuneirong" size="80" type="text" placeholder="回复&emsp;${yijianhuifu.jiaoshixingming}"/>&emsp;
												<input type="submit" value="发送" onclick="return subhuifu(${i.index},${status.index})"><br><br>
											</form>
										</div>
									</c:if>
									<c:if test="${yijianhuifu.xueshengid!=null}">
										<div id="${i.index}-${status.index}" style="margin-left:30px;display:none;">
											<form action="subhuifuxuesheng?id=${YiJian.yijianid}_${yijianhuifu.xueshengid}" class="form-inline" method="post">
												<input id="huifuneirong${i.index}${status.index}" name="huifuneirong" size="80" type="text" placeholder="回复&emsp;${yijianhuifu.xueshengxingming}"/>&emsp;
												<input type="submit" value="发送" onclick="return subhuifu(${i.index},${status.index})"><br><br>
											</form>
										</div>
									</c:if>
								</div>
								</c:forEach>
							</div>
						</div>
						</td></tr>
					</c:forEach>
					</tbody>
					</table>
					<div class="pagination pagination-centered">
						<ul class="pagination">
							<c:if test="${page >1}">
								<li><a href="chaxunyijian?biaoqian=${biaoqian}&niming=${niming}&fanwei=${fanwei}&page=1">首页</a></li>
							</c:if>
							<c:if test="${page > 1}">
								<li><a href="chaxunyijian?biaoqian=${biaoqian}&niming=${niming}&fanwei=${fanwei}&page=${page-1}">上一页</a></li>
							</c:if>
							<li><a href="#">第${page}页</a></li>
							<c:if test="${page < pages}">
								<li><a href="chaxunyijian?biaoqian=${biaoqian}&niming=${niming}&fanwei=${fanwei}&page=${page+1}">下一页</a></li>
							</c:if>
							<c:if test="${page < pages}">
								<li><a href="chaxunyijian?biaoqian=${biaoqian}&niming=${niming}&fanwei=${fanwei}&page=${pages}">尾页</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			</div>


	<script type="text/javascript">
	function dianzan(id){
		var code=id;
		$.ajax({
			type:"POST",
			url:"dianzan",
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
					alert("fail")
				}
			},
			error:function()
			{
				alert("登录超时!")
			}
		});
	}
	
	function pinglun(id){
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
			return true;
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
	function subhuifu(id1,id2){
		if($("#huifuneirong"+id1+id2).val()==""){
			alert("请输入回复内容……")
			return false;
		}else{
			return true;
		}
	}
	
	function delhuifu(id1,id2){
		var code=id1;
		if(confirm("确定要删除吗？")==true){
			$.ajax({
				type:"POST",
				url:"delhuifu",
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
					}
					if(datas[0]=="null"){
						alert("该评论不存在！")
					}
					if(datas[0]=="false"){
						alert("删除失败！")
					}
				},
				error:function()
				{
					alert("登录超时！")
				}
			});
		}
	}
	</script>
		