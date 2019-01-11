<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="static/lib/js/ajaxfileupload.js"></script>
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
				<i class="icon-align-justify"></i><span class="break"></span>导入用户
			</h2>
		</div>
		<div class="box-content">
			<form action="" class="form-horizontal" method="post">
				<div class="sub-title">
					<!--  <span style="font-weight: bold;">院系:</span> <select
												id="yuanxiid" name="yuanxiid">
												<c:forEach items="${yuanxis}" var="yuanxi">
													<option value="${yuanxi.yuanxiid}">${yuanxi.yuanximingcheng}</option>
												</c:forEach>
											</select> &emsp;-->
					<!-- 											<span style="font-weight: bold;">年份:</span> <select -->
					<!-- 												id="nianfen" name="nianfen" style="width: 100px;"> -->
					<%-- 												<c:forEach items="${nianfens}" var="nianfen"> --%>
					<%-- 													<option value="${nianfen.ruxuenianfenid}">${nianfen.ruxuenianfen}</option> --%>
					<%-- 												</c:forEach> --%>
					<!-- 											</select> -->
				</div>

				<!-- <span class="pull-left"><a href="addyuanxi"><input
												type="button" class="flip-link btn btn-info" value="新增院系"></a>
										</span> -->
			<div class="control-group">
					<label class="control-label">用户导入:</label>
					<div class="controls">
						<input class="input-file uniform_on" type="file" style="width:200px;"
							accept=".xlsx,.txt" ContentEditable="false" id="file" name="file" />
						<span calss="help-inline" style="color: red;">只读取txt文件</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="focusedInput"></label>
					<div class="controls">
						<input type="button" style="margin-left: 30px;"
							class="btn btn-success" value="上传" onclick="uploadExcel()" />
						<span calss="help-inline" id="hidebutton" style="display: none;margin-left:40px;"><input
							type="button"  class="btn btn-success" value="确认导入"
							onclick="importData()"></span>
					</div>
				</div>
			</form>
<!-- 			<div id="hidebutton" style="display: none"> -->
<!-- 				<span class="pull-right"><input type="submit" -->
<!-- 					class="flip-link btn btn-info" value="确认导入" onclick="importData()"></span> -->
<!-- 			</div> -->
<!-- 			<div> -->
<!-- 				<span class="pull-right"><input type="file" -->
<!-- 					accept=".xlsx,.txt" ContentEditable="false" id="file" name="file" -->
<!-- 					class="file_up" /> -->
<!-- 					<button onclick="uploadExcel()">上传</button> </span> -->
<!-- 			</div> -->
		</div>
	</div>
	<div class="card" id="hideyonghulist" style="display: none;">
		<br>
		<div class="sub-title">
			<span style="font-weight: bold;">正确处理的数据：</span>
		</div>
		<table style="width: 100%" class="table table-bordered table-striped table-condensed bootstrap-datatable">
			<thead>
				<tr style="background-color: #e0e0e0;">
					<th>院系名称</th>
					<th>角色</th>
					<th>用户名</th>
					<th>用户姓名</th>
					<th>密码</th>
					<th>邮箱</th>
				</tr>
			</thead>
			<tbody id="yonghulist">

			</tbody>
		</table>
	</div>

	<div class="card" id="hideyonghulisttrue" style="display: none;">
		<br>
		<div class="sub-title">
			<span style="font-weight: bold;">导入成功的数据：</span>
		</div>
		<table style="width: 100%" class="table table-bordered table-striped table-condensed bootstrap-datatable">
			<thead>
				<tr style="background-color: #e0e0e0;">
					<th>院系名称</th>
					<th>角色</th>
					<th>用户名</th>
					<th>用户姓名</th>
					<th>密码</th>
					<th>邮箱</th>
				</tr>
			</thead>
			<tbody id="yonghulisttrue">

			</tbody>
		</table>
	</div>
	<div class="card" id="hideyonghulisterror" style="display: none;">
		<br>
		<div class="sub-title">
			<span style="font-weight: bold;">导入失败的数据：</span>
		</div>
		<table style="width: 100%" class="table table-bordered table-striped table-condensed bootstrap-datatable">
			<thead>
				<tr style="background-color: #e0e0e0;">
					<th>院系名称</th>
					<th>角色</th>
					<th>用户名</th>
					<th>用户姓名</th>
					<th>密码</th>
					<th>邮箱</th>
				</tr>
			</thead>
			<tbody id="yonghulisterror">

			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
	function uploadExcel() {
		/*if($("#yuanxiid").val()==""){
			alert("请选择院系!");
			return false;
		}*/
		// 		if($("#nianfen").val()==""){
		// 			alert("请选择年份!");
		// 		}
		if (confirm("确认上传该文件吗?") == true) {
			$.ajaxFileUpload({
				url : "importFile", //用于文件上传的服务器端请求地址
				fileElementId : "file", //文件上传域的ID
				dataType : 'json', //返回值类型 一般设置为json
				success : function(data) { //服务器成功响应处理函数
					if (data.status == "success") {
						alert("解析成功!");
						var code = "";
						for (var i = 0; i < data.data.length; i++) {
							code += '<tr><td>' + (data.data[i])[0]
									+ '</td><td>' + (data.data[i])[1]
									+ '</td><td>' + (data.data[i])[2]
									+ '</td><td>' + (data.data[i])[3]
									+ '</td><td>' + (data.data[i])[4]
									+ '</td><td>' + (data.data[i])[5] + '</td>'
									+ '</tr>';
						}
						$("#yonghulist").html(code);
						$("#hideyonghulist").show();
						$("#hidebutton").show();
					} else if (data.status == "format") {
						alert("文件格式错误!");
					} else if (data.status == "null") {
						alert("文件内容为空!");
					} else
						alert("解析失败!");

				},
				error : function() {
					alert("fail!");
				}

			});
		} else {
			return false;
		}

	}
</script>
<script type="text/javascript">
	function importData() {
		//var yuanxiid=$("#yuanxiid").val();
		// 			var ruxuenianfenid=$("#nianfen").val();
		if (confirm("确认导入该文件吗?") == true) {
			$
					.ajaxFileUpload({
						url : "importYongHu", //用于文件上传的服务器端请求地址
						fileElementId : "file", //文件上传域的ID
						// 			           data:{
						// yuanxiid:yuanxiid,
						// 			        	   ruxuenianfenid:ruxuenianfenid
						// 			        	   },
						dataType : 'json', //返回值类型 一般设置为json
						success : function(data) { //服务器成功响应处理函数
							var totalnumber = data.yonghulisttrue.length
									+ data.yonghulisterror.length;
							alert('总共导入' + totalnumber + '条数据!');
							if (data.yonghulisttrue.length != 0
									&& data.yonghulisttrue != null) {
								alert("导入成功" + data.yonghulisttrue.length
										+ "条数据!");
								var code = "";
								for (var i = 0; i < data.yonghulisttrue.length; i++) {
									code += '<tr><td>'
											+ data.yonghulisttrue[i].yuanximingcheng
											+ '</td><td>'
											+ data.yonghulisttrue[i].jueseid
											+ '</td><td>'
											+ data.yonghulisttrue[i].yonghuming
											+ '</td><td>'
											+ data.yonghulisttrue[i].yonghuxingming
											+ '</td><td>'
											+ data.yonghulisttrue[i].mimamd5
											+ '</td><td>'
											+ data.yonghulisttrue[i].yonghuyouxiang
											+ '</td>' + '</tr>';
								}
								$("#yonghulisttrue").html(code);
								$("#hideyonghulist").hide();
								$("#hideyonghulisttrue").show();
								$("#hidebutton").hide();
							}

							if (data.xueshenglisterror.length != 0
									&& data.xueshenglisterror != null) {
								alert("导入失败" + data.xueshenglisterror.length
										+ "条数据!");
								var code = "";
								for (var i = 0; i < data.xueshenglisterror.length; i++) {
									code += '<tr><td>'
											+ data.yonghulisterror[i].yuanximingcheng
											+ '</td><td>'
											+ data.yonghulisterror[i].jueseid
											+ '</td><td>'
											+ data.yonghulisterror[i].yonghuming
											+ '</td><td>'
											+ data.yonghulisterror[i].yonghuxingming
											+ '</td><td>'
											+ data.yonghulisterror[i].mimamd5
											+ '</td><td>'
											+ data.yonghulisterror[i].yonghuyouxiang
											+ '</td>' + '</tr>';
								}
								$("#yonghulisterror").html(code);
								$("#hideyonghulist").hide();
								$("#hideyonghulisterror").show();
								$("#hidebutton").hide();
							}
						},
						error : function() {
							alert("fail!");
						}

					});
		} else {
			return false;
		}

	}
</script>
<script type="text/javascript">
	$(function() {
		$("#DataTables_Table_0")
				.dataTable(
						{
							//lengthMenu: [5, 10, 20, 30],//这里也可以设置分页，但是不能设置具体内容，只能是一维或二维数组的方式，所以推荐下面language里面的写法。
							paging : true,//分页
							ordering : false,//是否启用排序
							searching : true,//搜索
							language : {
								lengthMenu : '显示&emsp;<select style="width:50px;height:30px;">'
										+ '<option value="10">10</option>'
										+ '<option value="20">20</option>'
										+ '<option value="30">30</option>'
										+ '<option value="40">40</option>'
										+ '<option value="50">50</option>'
										+ '</select>&emsp;条记录',//左上角的分页大小显示。
								search : '<span>搜索：</span>',//右上角的搜索文本，可以写html标签
								paginate : {//分页的样式内容。
									previous : "上一页",
									next : "下一页",
									first : "第一页",
									last : "最后"
								},

								zeroRecords : "没有内容",//table tbody内容为空时，tbody的内容。
								//下面三者构成了总体的左下角的内容。
								info : "总共_PAGES_ 页，显示第_START_ 到第 _END_条 ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
								infoEmpty : "0条记录",//筛选为空时左下角的显示。
								infoFiltered : ""//筛选之后的左下角筛选提示，
							},
							paging : true,
							pagingType : "full_numbers",//分页样式的类型

						});
		$("#table_local_filter input[type=search]").css({
			width : "auto"
		});//右上角的默认搜索文本框，不写这个就超出去了。
	});
</script>
