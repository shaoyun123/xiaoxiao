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
				<i class="icon-align-justify"></i><span class="break"></span>导入宿舍
			</h2>
		</div>
		<div class="box-content">
			<form action="" class="form-horizontal" method="post">
				<div class="control-group">
					<label class="control-label">校区:</label>
					<div class="controls">
						<select id="xiaoquid" name="xiaoquid">
							<c:forEach items="${xiaoqus}" var="xiaoqu">
								<option value="${xiaoqu.xiaoquid}">${xiaoqu.mingcheng}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<!-- 				<span class="pull-left"><a href="addxiaoqu"><input -->
				<!-- 						type="button" class="flip-link btn btn-info" value="新增校区"></a> -->
				<!-- 				</span> -->

				<div class="control-group">
					<label class="control-label">宿舍导入:</label>
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
<!-- 								<div id="hidebutton" style="display: none"> -->
<!-- 									<span class="pull-right"><input type="submit" -->
<!-- 										class="flip-link btn btn-info" value="确认导入" onclick="importData()"></span> -->
<!-- 								</div> -->
<!-- 								<div> -->
<!-- 									<span class="pull-right"><input type="file" -->
<!-- 										accept=".xlsx,.txt" ContentEditable="false" id="file" name="file" -->
<!-- 										class="file_up" /> -->
<!-- 										<button onclick="uploadExcel()">上传</button> </span> -->
<!-- 								</div> -->
		</div>
	</div>
	<div class="box-content" id="hidexueshenglist"
		style="display: none;">
		<br>
		<div class="sub-title">
			<span style="font-weight: bold;">正确处理的数据：</span>
		</div>
		<table style="width: 100%"
			class="table table-bordered table-striped table-condensed bootstrap-datatable">
			<thead>
				<tr style="background-color: #e0e0e0;">
					<th>学号</th>
					<th>宿舍楼</th>
					<th>宿舍</th>
				</tr>
			</thead>
			<tbody id="xueshenglist">

			</tbody>
		</table>
	</div>

	<div class="card" id="hidexueshenglisttrue"
		style="display: none;">
		<br>
		<div class="sub-title">
			<span style="font-weight: bold;">导入成功的数据：</span>
		</div>
		<table style="width: 100%"
			class="table table-bordered table-striped table-condensed bootstrap-datatable">
			<thead>
				<tr style="background-color: #e0e0e0;">
					<th>学号</th>
					<th>宿舍楼</th>
					<th>宿舍</th>
				</tr>
			</thead>
			<tbody id="xueshenglisttrue">

			</tbody>
		</table>
	</div>
	<div class="card" id="hidexueshenglisterror"
		style="display: none;">
		<br>
		<div class="sub-title">
			<span style="font-weight: bold;">导入失败的数据：</span>
		</div>
		<table style="width: 100%" 
			class="table table-bordered table-striped table-condensed bootstrap-datatable">
			<thead>
				<tr style="background-color: #e0e0e0;">
					<th>学号</th>
					<th>宿舍楼</th>
					<th>宿舍</th>
				</tr>
			</thead>
			<tbody id="xueshenglisterror">

			</tbody>
		</table>
	</div>
</div>


<script type="text/javascript">
	function uploadExcel() {
		if ($("#xiaoquid").val() == "") {
			alert("请选择校区!");
			return false;
		}
		if ($("#file").val() == "" || $("#file").val() ==  null) {
			alert("请选择文件!");
			return false;
		}

		$.ajaxFileUpload({
			url : "importFile", //用于文件上传的服务器端请求地址
			fileElementId : "file", //文件上传域的ID
			dataType : 'json', //返回值类型 一般设置为json
			success : function(data) { //服务器成功响应处理函数
				if (data.status == "success") {
					alert("解析成功!");
					var code = "";
					for (var i = 0; i < data.data.length; i++) {
						code += '<tr><td>' + (data.data[i])[0] + '</td><td>'
								+ (data.data[i])[1] + '</td><td>'
								+ (data.data[i])[2] + '</td>' + '</tr>';
					}
					$("#xueshenglist").html(code);
					$("#hidexueshenglist").show();
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
	}
</script>
<script type="text/javascript">
	function importData() {
		var xiaoquid = $("#xiaoquid").val();
		$.ajaxFileUpload({
			url : "importSuShe", //用于文件上传的服务器端请求地址
			fileElementId : "file", //文件上传域的ID
			data : {
				xiaoquid : xiaoquid,
			},
			dataType : 'json', //返回值类型 一般设置为json
			success : function(data) { //服务器成功响应处理函数
				var totalnumber = data.xueshenglisttrue.length
						+ data.xueshenglisterror.length;
				alert('总共导入' + totalnumber + '条数据!');
				if (data.xueshenglisttrue.length != 0
						&& data.xueshenglisttrue != null) {
					alert("导入成功" + data.xueshenglisttrue.length + "条数据!");
					var code = "";
					for (var i = 0; i < data.xueshenglisttrue.length; i++) {
						code += '<tr><td>' + data.xueshenglisttrue[i].xuehao
								+ '</td><td>'
								+ data.xueshenglisttrue[i].sushelouming
								+ '</td><td>'
								+ data.xueshenglisttrue[i].menpaihao + '</td>'
								+ '</tr>';
					}
					$("#xueshenglisttrue").html(code);
					$("#hidexueshenglist").hide();
					$("#hidexueshenglisttrue").show();
					$("#hidebutton").hide();
				}

				if (data.xueshenglisterror.length != 0
						&& data.xueshenglisterror != null) {
					alert("导入失败" + data.xueshenglisterror.length + "条数据!");
					var code = "";
					for (var i = 0; i < data.xueshenglisterror.length; i++) {
						code += '<tr><td>' + data.xueshenglisterror[i].xuehao
								+ '</td><td>'
								+ data.xueshenglisterror[i].sushelouming
								+ '</td><td>'
								+ data.xueshenglisterror[i].menpaihao + '</td>'
								+ '</tr>';
					}
					$("#xueshenglisterror").html(code);
					$("#hidexueshenglist").hide();
					$("#hidexueshenglisterror").show();
					$("#hidebutton").hide();
				}
			},
			error : function() {
				alert("fail!");
			}

		});
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
