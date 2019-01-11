<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.white_content {
	display: none;
	position: absolute;
	top: 10%;
	left: 23%;
	width: 56%;
	height: 60%;
	border: 16px solid lightblue;
	background-color: lightblue;
	z-index: 1002;
	overflow: auto;
}

.kexuan {
	position: absolute;
	top:;
	left:;
	width: 50%;
	height: 95%;
	border: 6px solid gray;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.yixuan {
	position: absolute;
	top: 0;
	left: 50%;
	width: 50%;
	height: 95%;
	border: 6px solid gray;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.rightbottom1 {
	position: absolute;
	bottom: 0;
	right: 0;
}

.rightbottom2 {
	position: absolute;
	bottom: 0;
	right: 40px;
}

.kexuanren {
	position: absolute;
	top: 27px;
	display: none;
}

.yixuanren {
	position: absolute;
	top: 27px;
	display: block;
}

#mask {
	width: 100%;
	height: 100%;
	opacity: 0.7; /*半透明*/
	filter: alpha(opacity = 70); /*IE6半透明*/
	background-color: black;
	/*background-color: red;*/
	position: fixed;
	top: 0;
	left: 0;
	display: none;
}
</style>
<style>
.table th{
	text-align: center;
}
.table td{
	text-align: center;
}
</style>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>班级成员
			</h2>
			<div class="box-icon">
				<a href="JavaScript: void(0);" onclick="toContentPage('addstudent')"><i title="添加" class="icon-plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<div class="container-fluid">
				<div class="side-body">
					<div class="row">
						<div class="col-xs-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title" style="font-weight: bold;margin-left:20px;">
										<div class="title">班级名称：${banji2.banjimingcheng}</div>
									</div>
								</div><br>
								<div class="card-body">
									<form action="" class="form-horizontal" method="post"
										enctype="multipart/form-data">
										<div class="sub-title">
											<span style="font-weight: bold;margin-left:20px;">班长信息：</span><input
												id="Button1" type="button" value="编辑"
												class="btn btn-warning" onclick="ShowDiv('MyDiv')" /> <input
												id="canyuren" name="canyuren" type="hidden" />
										</div>

										<div id="mask" style="width: 100%; height: 100%;"></div>

										<div id="MyDiv" class="white_content modal hide fade in">
											<!-- 弹出框 -->
											<div id="kexuan" class="kexuan" style="height: 85%;">
												<!-- 可选 -->
												<div>
													<input style="margin-left: 0px;" id="xuesheng"
														type="button" class="btn" value="我的学生"
														onclick="show('xueshengdiv','haoyoudiv')" />
												</div>
												<div id="xueshengdiv" class="kexuanren" style="width: 100%;">
													<div style="margin-left: 90px;">
														<select style="width: 90px;" id="banji" name="banji">
															<option value="">全部</option>
															<c:forEach items="${banjis}" var="banJi">
																<option value="${banJi.banjiid}">${banJi.banjimingcheng}</option>
															</c:forEach>
														</select> <input type="button" class="btn btn-success" value="查询"
															onclick="show_wodexuesheng()" />
													</div>
													<table class="table">
														<thead>
															<tr>
																<th>班级</th>
																<th>学号</th>
																<th>姓名</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody id="wodexuesheng">
															<c:forEach items="${xuesheng}" var="XueSheng">
																<tr>
																	<td>${XueSheng.banji}</td>
																	<td>${XueSheng.xuehao}</td>
																	<td>${XueSheng.xingming}</td>
																	<td>
																		<div id="${XueSheng.xueshengid}${XueSheng.xueshengid}"
																			style="cursor: pointer;">
																			<span style="color: blue; cursor: pointer;"
																				onclick="add('${XueSheng.banji}','${XueSheng.xuehao}','${XueSheng.xingming}','${XueSheng.xueshengid}')">添加</span>
																		</div>
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>

											</div>
											<!-- 可选end -->
											<div id="yixuan" class="yixuan" style="height: 85%;">
												<!-- 已选 -->
												<span style="margin-left: 110px; color: red">已选班长</span>
												<div id="yixuanren" class="yixuanren" style="width: 100%;">
													<table class="table">
														<thead>
															<tr>
																<th>班级</th>
																<th>学号</th>
																<th>姓名</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody id="beixuanren">
														</tbody>
													</table>
												</div>
											</div>
											<!-- 											已选end -->
											<!-- 											<div id="move" class="rightbottom1" style="cursor: pointer;"> -->
											<!-- 												<span class="pull-right"><button type="button" -->
											<!-- 														class="btn btn-info" onclick="CloseDiv('MyDiv')">保存</button>> -->



											<!-- 											</div> -->
											<div id="move" class="modal-footer"
												style="cursor: pointer; text-align: center; bottom: 3px; position: absolute; width: 96%;">
												<span style="font-size: 16px;" onclick="CloseDiv('MyDiv')">保存</span>
											</div>
										</div>
									</form>
								</div>
								<div>
									<table
										class="table table-striped  table-bordered  bootstrap-datatable datatable dataTable"
										id="DataTables_Table_0_filter"
										aria-describedby="DataTables_Table_0_info">
										<thead>
											<tr>
												<th>学号</th>
												<th>姓名</th>
												<th>电话</th>
												<th>邮箱</th>
												<th>宿舍楼</th>
												<th>宿舍</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${xueshengs}" var="xuesheng">
												<tr>
													<td>${xuesheng.xuesheng.xuehao}</td>
													<td>${xuesheng.xuesheng.xingming}</td>
													<td>${xuesheng.xuesheng.dianhua}</td>
													<td>${xuesheng.xuesheng.youxiang}</td>
													<td>${xuesheng.sushelou.mingCheng}</td>
													<td>${xuesheng.xueshengsushe.menPaiHao}</td>

													<td>
														<button
															onclick="toContentPage('modifystudent?id=${xuesheng.xuesheng.xueshengid}')"
															type="button" class="btn btn-warning">编辑学生信息</button>
													</td>

													<td><input type="button" class="btn btn-danger"
														value="删除"
														onclick="delstudent('${xuesheng.xuesheng.xueshengid}','${xuesheng.xuesheng.banjiid }')">
													</td>

												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${page >1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('banji?id=${banji2.banjiid }')">首页</a></li>
					</c:if>
					<c:if test="${page > 1}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('banji?id=${banji2.banjiid }&page=${page-1}')">上一页</a></li>
					</c:if>
					<li class="active"><a href="JavaScript:void(0);">第${page}页</a>
					</li>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('banji?id=${banji2.banjiid }&page=${page+1}')">下一页</a></li>
					</c:if>
					<c:if test="${page < pages}">
						<li><a href="JavaScript:void(0);"
							onclick="toContentPage('banji?id=${banji2.banjiid }&page=${pages}')">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">
			//弹出隐藏层
			function ShowDiv(show_div){
				document.getElementById(show_div).style.display='block';
				document.getElementById("mask").style.display = 'block';
			}
			//关闭弹出层，保存数据
			function CloseDiv(close_div){
				var a=document.getElementById("beixuanren").getElementsByTagName("tr")
				var s="";
				for (var i=0;i<a.length;i++) {//将添加的参与人的id拼接成s，逗号隔开
					s+=a[i].id+",";
				}
// 				alert(s);
				document.getElementById("canyuren").value=s;
				document.getElementById(close_div).style.display='none';
				document.getElementById("mask").style.display = 'none';
				$.ajax({
					type : "POST",
					url : 'setBanZhang',
					data : {canyuren : s},
					dataType : 'json',
					cache : false,
					timeout : 5000,
					async : true,
					success : function(data) {
					if (data.status == "success") {
						alert("设置成功!")
						toContentPage('banji?id='+${banji2.banjiid});
						} else {
							alert("设置失败!");
							toContentPage('banji?id='+${banji2.banjiid});
						}
					},
					error : function() {
						alert("fail");
						toContentPage('banji?id='+${banji2.banjiid});
					}
				});
			}
			//显示同班、社团、好友
			function show(show,close){
				document.getElementById(show).style.display='block';
				document.getElementById(close).style.display='none';
			};
			//添加参与人
			function add(banji,xuehao,xingming,xueshengid){
				var code = '<tr id="'+xueshengid+'"><td>'+banji+'</td><td>'+xuehao+'</td><td>'+xingming+'</td><td><span style="color:blue;cursor:pointer;" onclick="del(\''+xueshengid+'\')">删除</span></td></tr>'; 
				$("#beixuanren").append(code);
				var id = xueshengid+xueshengid;
				document.getElementById(id).style.display="none";
			}
			//删除已选参与人
			function del(xueshengid){
				var id = xueshengid+xueshengid;
				document.getElementById(id).style.display="block";
				var tr = document.getElementById(xueshengid);
				tr.parentNode.removeChild(tr);
			}
			function show_wodexuesheng(){
				var banjiid = document.getElementById("banji").value;
				$.ajax({
					type: "POST",
					url: 'show_MyStudents',
			    	data:{CODE:banjiid},
					dataType:'json',
					cache:false,
					timeout: 5000,
					async:true, 
					success:function(data)
					{
						var data=eval(data);
						var code = "";
						for(var i=0;i<data.length;i++){
							code+='<tr><td>'+data[i].banji+'</td><td>'+data[i].xuehao+'</td><td>'+data[i].xingming+'</td>'+
    							'<td><div id="'+data[i].xueshengid+data[i].xueshengid+'" style="cursor:pointer;">'+
    							'<span style="color:blue;cursor:pointer;" onclick="add(\''+data[i].banji+'\',\''+data[i].xuehao+'\',\''+data[i].xingming+'\',\''+data[i].xueshengid+'\')">添加</span>'+
    							'</div></td></tr>';
						}
						$("#wodexuesheng").html(code);
					},
					error:function()
					{
						alert("登录超时!")
					}
					
				});
			}
		</script>

<script type="text/javascript">
		function modifystudent(){
			if(confirm("修改学生信息影响会很严重！确认修改吗？")==true){
				return true;
			}else{
				return false;
			}
		}
		function save(){
			if($("#canyuren").val()==""){
				alert('请先选择班长！');
				return false;
			}else{
				return true;
			}
		}
		
		function delstudent(xueshengid,banjiid){
			if(confirm("删除学生信息影响会很严重！确认删除吗？")==true){
				$.ajax({
					type: "POST",
					url: 'delStudent',
			    	data:{CODE:xueshengid},
					dataType:'text',
					cache:false,
					timeout: 5000,
					async:true,
					success:function(data)
					{
						if(data=="success"){
							alert("删除成功!");
							toContentPage('banji?id='+banjiid);
						}
						if(data=="fail"){
							alert("删除失败!");
							toContentPage('banji?id='+banjiid);
						}
					},
					error:function()
					{
						alert("fail!");
						toContentPage('banji?id='+banjiid);
					}
				});
			}else{
				return false;
			}
		}
	</script>
