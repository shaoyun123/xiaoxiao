<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>修改事件
			</h2>
		</div>
		<div class="box-content">
			<form action="" class="form-horizontal" method="post" id="form"
				enctype="multipart/form-data">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="neirong">事件内容：</label>
						<div class="controls">
							<input id="neirong" name="neirong" type="text"
								class="input-xlarge focused" value="${beiwanglu.neirong}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="neirong">日期：</label>
						<div class="controls">
							<input id="riqi" name="riqi" type="text"
								onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
								class="Wdate" value="${date}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="neirong">时间：</label>
						<div class="controls">
							<input id="shijian" name="shijian" type="text"
								onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm'})"
								class="Wdate" value="${time}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">是否需要回执：</label> <input type="hidden"
							id="jieshourens" value="${beiwanglu.jieshouren }">
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

					<table id="DataTables_Table_0"
						class="table table-bordered table-striped table-condensed bootstrap-datatable"
						style="width: 100%">
						<thead>
							<tr style="background-color: #DEDEDE">
								<th style="width: 30%">班级</th>
								<th style="width: 30%">学号</th>
								<th style="width: 30%">姓名</th>
								<th style="width: 10%">全选 <input type="checkbox"
									style="zoom: 150%;" onclick="checkall(this,'stu')" />
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${xuesheng}" var="xuesheng">
								<c:set var="i" value="0" />
								<c:forEach items="${xuesheng2}" var="xuesheng2">
									<c:if test="${xuesheng.xueshengid==xuesheng2.xueshengid}">
										<c:set var="i" value="1" />
									</c:if>
								</c:forEach>
								<tr id="${xuesheng.xueshengid}">
									<td style="width: 30%">${xuesheng.banji}</td>
									<td style="width: 30%">${xuesheng.xuehao}</td>
									<td style="width: 30%">${xuesheng.xingming}</td>
									<td style="width: 10%"><input type="checkbox"
										<c:if test="${i==1}">checked="checked"</c:if> name="stu"
										onclick="check(${xuesheng.xueshengid});"
										value="${xuesheng.xueshengid}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input type="hidden" id="xueshengids" name="xueshengids" /> <input
						type="hidden" id="qufen" name="qufen" value="${qufen}" /> <input
						type="hidden" id="id" name="id" value="${beiwanglu.id}" />
					<div id="submit" class="" style="text-align: center;">
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
<script type="text/javascript"
	src="static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var ids="";
function  check(id){  
    var val = id+",";  
    ids += val;
 }

function checkall(e,name){
	var aaa = document.getElementsByName(name);
	for(var i = 0;i<aaa.length;i++){
		aaa[i].checked = e.checked;
	}
}

function save(){
	if($("#neirong").val()==""){
		alert("请填写内容！");
		return false;
	}
	if($("#riqi").val()==""){
		alert("请选择日期！");
		return false;
	}
	if($("#shijian").val()==""){
		alert("请选择时间！");
		return false;
	}
	var radios = document.getElementsByName("huizhi");
	if(radios[0].checked==false && radios[1].checked==false){
		alert("请选择是否需要回执！")
		return false;
	}
//		obj = document.getElementsByName("stu");
//	    check_val = [];
//	    for(k in obj){
//	        if(obj[k].checked)
//	            check_val.push(obj[k].value);
//	    }
    
  	 first((ids).split(","));
  	if (str1.length <= 0) {
		alert("请选择学生！")
		return false;
	}
    document.getElementById("xueshengids").value = str1;
    if($("#xueshengids").val() == ""){
		alert("请选择学生！")
		return false;
	}
    $.ajax({
		type : "POST",//方法类型
		dataType : "json",//预期服务器返回的数据类型
		url : "saveupdateshijianforxuesheng",//url
		data : $('#form').serialize(),
		success : function(result) {
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
var jieshourens = $("#jieshourens").val();
var str1=[];
var jsr=[];
function first(args){
	jsr = jieshourens.split(",");
	for(i=0;i<args.length;i++){
		var o=-1;
		for(var j=0;j<jsr.length;j++){
			if(args[i]==jsr[j]){
				o=1;
			}
		}
		if(o==-1){
			str1.push(args[i]);
		}
	}
	for(i=0;i<jsr.length;i++){
		var o=-1;
		for(var j=0;j<args.length;j++){
			if(args[j]==jsr[i]){
				o=1;
			}
		}
		if(o==-1){
			str1.push(jsr[i]);
		}
	}
    return str1;
}
</script>
