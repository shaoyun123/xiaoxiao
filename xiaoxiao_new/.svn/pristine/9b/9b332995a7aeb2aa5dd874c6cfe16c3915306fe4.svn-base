<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>我的事件
			</h2>
		</div>
		<div class="box-content">
			<form action="" class="form-horizontal" method="post" id="form">
				<div class="control-group">
					<label class="control-label">事件内容：</label>
					<div class="controls">
						<p>${neirong}</p>
						<input type="hidden" id="neirong" name="neirong"
							value="${neirong}" />
						<%--                                     		<span style="font-weight:bold;">地点：</span><span>${didian}</span>&emsp;&emsp; --%>
						<%--                                     		<input type="hidden" id="didian" name="didian" value="${didian}"/> --%>
					</div>
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
				<table id="DataTables_Table_0"
					class="table table-bordered table-striped table-condensed bootstrap-datatable">
					<thead>
						<tr>
							<th style="text-align: center;">班级</th>
							<th style="text-align: center;">学号</th>
							<th style="text-align: center;">姓名</th>
							<th style="text-align: center;">全选 <input type="checkbox"
								style="zoom: 150%;" onclick="checkall(this,'stu')" />
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${xuesheng}" var="xuesheng">
							<tr id="${xuesheng.xueshengid}">
								<td style="text-align: center;">${xuesheng.banji}</td>
								<td style="text-align: center;">${xuesheng.xuehao}</td>
								<td style="text-align: center;">${xuesheng.xingming}</td>
								<td style="text-align: center;"><input type="checkbox"
									name="stu" value="${xuesheng.xueshengid}"
									onclick="check(${xuesheng.xueshengid});" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type='hidden' id="xueshengids" name="xueshengids" />
				<div id="submit" class="form-actions">
					<button type="button" name="action"
						class="btn btn-primary"  onclick="save()">保存</button>
				</div>
			</form>
		</div>
	</div>
</div>
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
		var radios = document.getElementsByName("huizhi");
		if(radios[0].checked==false && radios[1].checked==false){
			alert("请选择是否需要回执！")
			return false;
		}
		obj = document.getElementsByName("stu");
	    check_val = [];
	    for(k in obj){
	        if(obj[k].checked)
	            check_val.push(obj[k].value);
	    }
	  	 first((ids).split(","));
	    document.getElementById("xueshengids").value = str1;
	    $.ajax({
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "saveshijianforxuesheng_sj",//url
			data : $('#form').serialize(),
			success : function(result) {
				if (result.status == "success") {
					alert("success");
					toContentPage('wodebeiwanglu_sj');
				}
				else{
					alert("fail!");
					toContentPage('wodebeiwanglu_sj');
				}
			},
			error : function() {
				alert("异常！");
				toContentPage('wodebeiwanglu_sj');
			}
		});
	}
	var str1=[];
	function first(args){
        for(i=0;i<args.length;i++){ 
            if(str1.indexOf(args[i])<0){ 
                str1.push(args[i]) 
            }
        }
        return str1;
    }
</script>
