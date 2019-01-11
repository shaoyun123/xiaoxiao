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
				<i class="icon-align-justify"></i><span class="break"></span>添加事件
			</h2>
		</div>
		<div class="box-content">
			<form action="" class="form-horizontal" method="post" id="form"
				enctype="multipart/form-data">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="neirong">事件内容：</label>
						<div class="controls">
							<p>${neirong}</p>
						</div>
						<input type="hidden" id="neirong" name="neirong"
							value="${neirong}" />
					</div>
					<div class="control-group">
						<label class="control-label">时间：</label>
						<div class="controls">
							<p>${shijian}</p>
							<input type="hidden" id="shijian" name="shijian"
								value="${shijian}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">是否需要回执：</label>
						<div class="controls">
							<div style="display: inline-block;">
								<div style="display: inline-block;">
									<input type="radio" id="yes" name="huizhi" value="1">
								</div>
								<div style="display: inline-block;">
									<label style="color: red" for="yes">是</label>
								</div>
							</div>
							<div style="display: inline-block;">
								<div style="display: inline-block;">
									<input type="radio" id="no" name="huizhi" value="0">
								</div>
								<div style="display: inline-block;">
									<label style="color: red" for="no">否</label>
								</div>
							</div>
						</div>
					</div>

					<table id="DataTables_Table_0" style="width:300px;margin-left:60px;"
						class="table table-bordered table-striped table-condensed bootstrap-datatable">
						<thead>
							<tr>
								<th style="width: 30%">班级</th>
								<th style="width: 10%">全选 <input type="checkbox"
									class="checker" style="zoom: 150%;"
									onclick="checkall(this,'stu')" />
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${banji}" var="banji">
								<tr id="${banji.banjiid}">
									<td style="width: 30%">${banji.banjimingcheng}</td>
									<td style="width: 10%"><input type="checkbox"
										class="checker" name="stu" onclick="check(${banji.banjiid});"
										value="${banji.banjiid}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input type="hidden" id="xueshengids" name="xueshengids" /> <input
						type="hidden" id="xuesheng" name="xuesheng" value="${xuesheng}"/>
					<div id="submit" " class="" style="margin-left: 200px;">
						<button type="button" name="action" class="btn btn-primary"
							onclick="save()">保存</button>
					</div>
					<fieldset>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<script type="text/javascript">
	function checkall(e,name){
		var aaa = document.getElementsByName("stu");
		for(var i = 0;i<aaa.length;i++){
			aaa[i].checked = e.checked;
		}
	}
	var ids="";
	function  check(id){  
	    var val = id+",";  
	    ids += val;
	 }
	
		function save(){
			 var neirong = $("#neirong").val();
			 var shijian = $("#shijian").val();
			var a='';
			var aaa = document.getElementsByName("stu");
			for(var i = 0;i<aaa.length;i++){
				if(aaa[i].checked == true){
					a += aaa[i].value+",";
				}
			}
			first(ids.split(","));
			if (str1.length <= 0) {
				alert("请选择班级！")
				return false;
			}
			$("#xueshengids").val(str1);
			var radios = document.getElementsByName("huizhi");
			if(radios[0].checked==false && radios[1].checked==false){
				alert("请选择是否需要回执！")
				return false;
			}
		    if(document.getElementById("xueshengids").value ==0){
				alert("请选择班级！")
				return false;
			}
		   
		    $.ajax({
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "saveshijianforbanji",//url
				data : $('#form').serialize(),
				success : function(data) {
					var result = eval(data);
					if (result.status == "success") {
						alert("success");
						toContentPage('mybeiwanglu_fdy');
					}
					else{
						alert("fail!");
						toContentPage('mybeiwanglu_fdy');
					}
				},
				error : function() {
					alert("异常！");
					toContentPage('mybeiwanglu_fdy');
				}
			});
		}
		var str1=[];
		function first(args){
	        for(i=0;i<args.length;i++){//从此处循环args
	            if(str1.indexOf(args[i])<0){//从这里开始匹配，如果没有匹配到，那么就执行push方法
	                str1.push(args[i])//push() 方法可向数组的末尾添加一个或多个元素，并返回新的长度。
	            }
	        }
	        return str1;
	    }
</script>
