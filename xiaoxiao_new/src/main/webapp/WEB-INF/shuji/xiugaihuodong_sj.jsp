<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>修改活动
		</h2>
	</div>
	<div class="box-content">
		<form action="" class="form-horizontal" method="post" id="form">
			<fieldset>
			<div class="control-group">
				<label class="control-label" for="mingcheng">活动名称：</label>
				<div class="controls">
					<input id="mingcheng" name="mingcheng" type="text"
					class="input-xlarge focused" value="${huodong.huodongmingcheng}"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="shuoming">活动说明：</label>
				<div class="controls">
					<input id="shuoming" name="shuoming" type="text"
						class="input-xlarge focused" value="${huodong.shuoming}" />
				</div>
			</div>
			<!--                                     	<div class="sub-title"> -->
			<!--                                     		<span style="font-weight:bold;">参与人：</span>&emsp;<input id="Button1" type="button" value="编辑" onclick="ShowDiv('MyDiv')" /> -->
			<%--                                     		<input id="canyuren" name="canyuren" type="hidden" value="${canyuren}"/> --%>
			<!--                                     	</div> -->
			<!--                                     	<div id="MyDiv" class="white_content">弹出框 -->
			<!--                                     	<div id="kexuan" class="kexuan">可选 -->
			<!--                                     		<div> -->
			<!--                                     			<input style="margin-left: 0px;" id="xuesheng" type="button" value="我的学生" onclick="show('xueshengdiv','haoyoudiv')"/> -->
			<!--                                     			<input style="float:right;margin-right: 0px;" id="haoyou" type="button" value="其他好友" onclick="show('haoyoudiv','xueshengdiv')"/> -->
			<!--                                     		</div> -->
			<!--                                     		<div id="xueshengdiv" class="kexuanren"> -->
			<!--                                     			<div style="margin-left: 90px;"> -->
			<!--                                     			<select style="width:90px;" id="banji" name="banji"> -->
			<!--                                     				<option value="">全部</option> -->
			<%--                                     				<c:forEach items="${banji}" var="BanJi"> --%>
			<%--                                     					<option value="${BanJi.banjiid}">${BanJi.banjimingcheng}</option> --%>
			<%--                                     				</c:forEach> --%>
			<!--                                     			</select> -->
			<!--                                     			<input type="button" value="查询" onclick="show_wodexuesheng()"/> -->
			<!--                                     			</div> -->
			<!--                                     			<table class="table"> -->
			<!--                                     				<thead> -->
			<!--                                     					<tr> -->
			<!--                                     						<th>班级</th> -->
			<!--                                     						<th>学号</th> -->
			<!--                                     						<th>姓名</th> -->
			<!--                                     						<th>操作</th> -->
			<!--                                     					</tr> -->
			<!--                                     				</thead> -->
			<!--                                     				<tbody id="wodexuesheng"> -->
			<%--                                     					<c:forEach items="${xuesheng}" var="XueSheng"> --%>
			<!--                                     						<tr> -->
			<%--                                     							<td>${XueSheng.banji}</td> --%>
			<%--                                     							<td>${XueSheng.xuehao}</td> --%>
			<%--                                     							<td>${XueSheng.xingming}</td> --%>
			<!--                                     							<td> -->
			<%--                                     								<div id="${XueSheng.xueshengid}" style="cursor:pointer;"> --%>
			<%--     																	<span style="color:blue;cursor:pointer;" onclick="add('${XueSheng.banji}','${XueSheng.xueshengid}','${XueSheng.xuehao}','${XueSheng.xingming}')">添加</span> --%>
			<!--     																</div> -->
			<!--     															</td> -->
			<!--                                     						</tr> -->
			<%--                                     					</c:forEach> --%>
			<!--                                     				</tbody> -->
			<!--                                     			</table> -->
			<!--                                     		</div> -->
			<!--                                     		<div id="haoyoudiv" class="kexuanren"> -->
			<!--                                     			<table class="table"> -->
			<!--                                     				<thead> -->
			<!--                                     					<tr> -->
			<!--                                     						<th>学号</th> -->
			<!--                                     						<th>姓名</th> -->
			<!--                                     						<th>操作</th> -->
			<!--                                     					</tr> -->
			<!--                                     				</thead> -->
			<!--                                     				<tbody> -->
			<!--                                     					<tr> -->
			<!--                                     						<th>2</th> -->
			<!--                                     						<td>小明</td> -->
			<!--                                     						<td>添加</td> -->
			<!--                                     					</tr> -->
			<!--                                     				</tbody> -->
			<!--                                     			</table> -->
			<!--                                     		</div> -->
			<!--                                     	</div>可选end -->
			<!--                                     	<div id="yixuan" class="yixuan">已选 -->
			<!--                                     		<span style="margin-left:110px;color:red">已选参与人</span> -->
			<!--                                     		<div id="yixuanren" class="yixuanren"> -->
			<!--                                     		<table class="table"> -->
			<!--                                     			<thead> -->
			<!--                                     				<tr> -->
			<!--                                     					<th>班级</th> -->
			<!--                                     					<th>学号</th> -->
			<!--                                     					<th>姓名</th> -->
			<!--                                     					<th>操作</th> -->
			<!--                                     				</tr> -->
			<!--                                     			</thead> -->
			<!--                                     			<tbody id="beixuanren"> -->
			<!--                                     			</tbody> -->
			<!--                                     		</table> -->
			<!--                                     		</div> -->
<!-- 	</div> -->
	<!-- 已选end -->
	<!--                                     	<div id="move" class="rightbottom1" style="cursor:pointer;"> -->
	<!--                                     		<span style="font-size: 16px;" onclick="CloseDiv('MyDiv')">确认</span> -->
	<!--                                     	</div> -->
	<!--                                     	</div> -->
	<div class="control-group">
		<label class="control-label" for="riqi">活动日期：</label>
		<div class="controls">
			<input id="riqi" name="riqi" type="text" class="Wdate"
				onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
				value="${huodong.riqi}" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="kaishishijian">开始时间：</label>
		<div class="controls">
			<input id="kaishishijian" name="kaishishijian" type="text"
				class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})"
				value="${huodong.kaishishijian}" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="jieshushijian">结束时间：</label>
		<div class="controls">
			<input id="jieshushijian" name="jieshushijian" type="text"
				class="Wdate" 
				onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})"
				value="${huodong.jieshushijian}" />
		</div>
	</div>
	<input type="hidden" id="id" name="id" value="${huodong.huodongid}" />
	<input type="hidden" id="qufen" name="qufen" value="${qufen}" />
	<div class="form-actions">
		<button type="button" class="btn btn-primary" onclick="save()">保存</button>
	</div>
	</fieldset>
	</form>
</div>
</div>
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
				function save(){
					if($("#mingcheng").val()==""){
						alert("请填写活动名称！");
						return false;
					}else if($("#didian").val()==""){
						alert("请填写活动地点！");
						return false;
					}else if($("#riqi").val()==""){
						alert("请选择活动日期！");
						return false;
					}else if($("#kaishishijian").val()==""){
						alert("请选择活动开始时间！");
						return false;
					}else if($("#jieshushijian").val()==""){
						alert("请选择活动结束时间！");
						return false;
					}else{
						$.ajax({
							type : "POST",//方法类型
							dataType : "json",//预期服务器返回的数据类型
							url : "saveupdatehuodong_sj",//url
							data : $('#form').serialize(),
							success : function(data) {
								var result = eval(data);
								if (result.status == "success") {
									alert("success");
								}
								else{
									alert("fail!");
								}
								toContentPage('myhuodong_sj');
							},
							error : function() {
								alert("异常！");
								toContentPage('myhuodong_sj');
							}
						});
					}
				}
			</script>
<script type="text/javascript">
			//弹出隐藏层,把hidden类型input的value打印到弹出层的已选参与人
			/*function ShowDiv(show_div){
				document.getElementById(show_div).style.display='block';
					var canyuren = document.getElementById("canyuren").value;
					var canyurens = canyuren.split(";");
					var xuesheng = new Array();
					for(var i=0;i<canyurens.length-1;i++){
						xuesheng[i] = canyurens[i].substring(0,canyurens[i].length-2);
						var xueshengs = new Array();
						xueshengs = xuesheng[i].split(",");
						var code = '<tr id="'+xuesheng[i]+'"><td>'+xueshengs[0]+'</td><td>'+xueshengs[2]+'</td><td>'+xueshengs[3]+'</td><td><span style="color:blue;cursor:pointer;" onclick="del(\''+xueshengs[0]+'\',\''+xueshengs[1]+'\',\''+xueshengs[2]+'\',\''+xueshengs[3]+'\')">删除</span></td></tr>';
						$("#beixuanren").append(code);
						document.getElementById(xueshengs[1]).style.display="none";
					}
					document.getElementById("canyuren").value="";//将hidden类型input置空，防止重复打印
			};*/
// 			//关闭弹出层，保存数据
// 			function CloseDiv(close_div){
// 				var a=document.getElementById("beixuanren").getElementsByTagName("tr")
// 				var s="";
// 				for (var i=0;i<a.length;i++) {//将添加的参与人的id拼接成s，逗号隔开
// 					s+=a[i].id+',1;';
// 				}
// 				//alert(s);
// 				document.getElementById("canyuren").value=s;
// 				document.getElementById(close_div).style.display='none';
// 				$("#beixuanren").html("");//清空弹出层的已选参与人
// 			};
// 			//显示学生、好友
// 			function show(show,close){
// 				document.getElementById(show).style.display='block';
// 				document.getElementById(close).style.display='none';
// 			};
// 			//添加参与人
// 			function add(banji,xueshengid,xuehao,xingming){
// 				var id = banji+","+xueshengid+","+xuehao+","+xingming;
// 				var code = '<tr id="'+id+'"><td>'+banji+'</td><td>'+xuehao+'</td><td>'+xingming+'</td><td><span style="color:blue;cursor:pointer;" onclick="del(\''+banji+'\',\''+xueshengid+'\',\''+xuehao+'\',\''+xingming+'\')">删除</span></td></tr>'; 
// 				$("#beixuanren").append(code);
// 				document.getElementById(xueshengid).style.display="none";
// 			}
// 			//删除已选参与人
// 			function del(banji,xueshengid,xuehao,xingming){
// 				var id = banji+","+xueshengid+","+xuehao+","+xingming;
// 				document.getElementById(xueshengid).style.display="block";
// 				var tr = document.getElementById(id);
// 				tr.parentNode.removeChild(tr);
// 			}
// 			function show_wodexuesheng(){
// 				var banjiid = document.getElementById("banji").value;
// 				$.ajax({
// 					type: "POST",
// 					url: 'show_wodexuesheng',
// 			    	data:{CODE:banjiid},
// 					dataType:'json',
// 					cache:false,
// 					timeout: 5000,
// 					async:true, 
// 					success:function(data)
// 					{
// 						var data=eval(data);
// 						var code = "";
// 						for(var i=0;i<data.length;i++){
// 							code+='<tr><td>'+data[i].banji+'</td><td>'+data[i].xuehao+'</td><td>'+data[i].xingming+'</td>'+
//     							'<td><div id="'+data[i].xueshengid+'" style="cursor:pointer;">'+
//     							'<span style="color:blue;cursor:pointer;" onclick="add(\''+data[i].banji+'\',\''+data[i].xueshengid+'\',\''+data[i].xuehao+'\',\''+data[i].xingming+'\')">添加</span>'+
//     							'</div></td></tr>';
// 						}
// 						$("#wodexuesheng").html(code);
// 					},
// 					error:function()
// 					{
// 						alert("登录超时!")
// 					}
					
// 				});
// 			}
</script>
<script type="text/javascript">
// 			var posX;
// 		    var posY;
// 		    fwuss = document.getElementById("MyDiv");
// 		    fwuss.onmousedown=function(e){
// 		        posX = event.x - fwuss.offsetLeft;//获得横坐标x
// 		        posY = event.y - fwuss.offsetTop;//获得纵坐标y
// 		        document.onmousemove = mousemove;//调用函数，只要一直按着按钮就能一直调用
// 		    }
// 		    document.onmouseup = function()
// 		    {
// 		        document.onmousemove = null;//鼠标举起，停止
// 		    }
// 		    function mousemove(ev)
// 		    {
// 		        if(ev==null) ev = window.event;//IE
// 		        fwuss.style.left = (ev.clientX - posX) + "px";
// 		        fwuss.style.top = (ev.clientY - posY) + "px";
// 		    }
</script>
