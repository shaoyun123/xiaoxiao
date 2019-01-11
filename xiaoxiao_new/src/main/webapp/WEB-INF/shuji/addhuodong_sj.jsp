<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>新增活动
		</h2>
	</div>
	<div class="box-content">

		<form action="" class="form-horizontal" method="post"
			id="form">
			<fieldset>
			<div class="control-group">
				<label class="control-label" for="mingcheng">活动名称：</label>
				<div class="controls">
					<input id="mingcheng" name="mingcheng" type="text"
						class="input-xlarge focused" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="didian">活动地点：</label>
				<div class="controls">
					<input id="didian" name="didian" type="text"
						class="input-xlarge focused" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="shuoming">活动说明：</label>
				<div class="controls">
					<input id="shuoming" name="shuoming" type="text"
						class="input-xlarge focused" />
				</div>
			</div>
			<!--                                     	<div id="MyDiv" class="white_content">弹出框 -->
			<!--                                     	<div id="kexuan" class="kexuan">可选 -->
			<!--                                     		<div> -->
			<!--                                     			<input style="margin-left: 0px;" id="xuesheng" type="button" value="我的学生" onclick="show('xueshengdiv','haoyoudiv')"/> -->
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
			<%--                                     								<div id="${XueSheng.xueshengid}${XueSheng.xueshengid}" style="cursor:pointer;"> --%>
			<%--     																	<span style="color:blue;cursor:pointer;" onclick="add('${XueSheng.banji}','${XueSheng.xuehao}','${XueSheng.xingming}','${XueSheng.xueshengid}')">添加</span> --%>
			<!--     																</div> -->
			<!--     															</td> -->
			<!--                                     						</tr> -->
			<%--                                     					</c:forEach> --%>
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
			<!--                                     	</div>已选end -->
			<!--                                     	<div id="move" class="rightbottom1" style="cursor:pointer;"> -->
			<!--                                     		<span style="font-size: 16px;" onclick="CloseDiv('MyDiv')">保存</span> -->
			<!--                                     	</div> -->
			<!--                                     	</div> -->
			<div class="control-group">
				<label class="control-label" for="riqi">活动日期：</label>
				<div class="controls">
					<input id="riqi" name="riqi" type="text" class="Wdate"
						
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="kaishishijian">开始时间：</label>
				<div class="controls">
					<input id="kaishishijian" name="kaishishijian" type="text"
						class="Wdate" 
						onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="jieshushijian">结束时间：</label>
				<div class="controls">
				 <input	id="jieshushijian" name="jieshushijian" type="text" class="Wdate"
					onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})" />
				</div>
			</div>
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
							url : "savehuodong_sj",//url
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
			//弹出隐藏层
// 			function ShowDiv(show_div){
// 				document.getElementById(show_div).style.display='block';
// 			};
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
// 			};
// 			//显示同班、社团、好友
// 			function show(show,close){
// 				document.getElementById(show).style.display='block';
// 				document.getElementById(close).style.display='none';
// 			};
// 			//添加参与人
// 			function add(banji,xuehao,xingming,xueshengid){
// 				var code = '<tr id="'+xueshengid+'"><td>'+banji+'</td><td>'+xuehao+'</td><td>'+xingming+'</td><td><span style="color:blue;cursor:pointer;" onclick="del(\''+xueshengid+'\')">删除</span></td></tr>'; 
// 				$("#beixuanren").append(code);
// 				var id = xueshengid+xueshengid;
// 				document.getElementById(id).style.display="none";
// 			}
// 			//删除已选参与人
// 			function del(xueshengid){
// 				var id = xueshengid+xueshengid;
// 				document.getElementById(id).style.display="block";
// 				var tr = document.getElementById(xueshengid);
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
//     							'<td><div id="'+data[i].xueshengid+data[i].xueshengid+'" style="cursor:pointer;">'+
//     							'<span style="color:blue;cursor:pointer;" onclick="add(\''+data[i].banji+'\',\''+data[i].xuehao+'\',\''+data[i].xingming+'\',\''+data[i].xueshengid+'\')">添加</span>'+
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
			/*var posX;
		    var posY;
		    fwuss = document.getElementById("MyDiv");
		    fwuss.onmousedown=function(e){
		        posX = event.x - fwuss.offsetLeft;//获得横坐标x
		        posY = event.y - fwuss.offsetTop;//获得纵坐标y
		        document.onmousemove = mousemove;//调用函数，只要一直按着按钮就能一直调用
		    }
		    document.onmouseup = function()
		    {
		        document.onmousemove = null;//鼠标举起，停止
		    }
		    function mousemove(ev)
		    {
		        if(ev==null) ev = window.event;//IE
		        fwuss.style.left = (ev.clientX - posX) + "px";
		        fwuss.style.top = (ev.clientY - posY) + "px";
		    }*/
			</script>
