<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>修改发布考评
			</h2>
		</div>
		<div class="box-content">
			<form method="post" id="form" class="form-horizontal">
				<!-- 				<span>方案名称：</span><input type="text" name="fanganmingcheng" value="" id="fanganmingcheng"> -->
				<div class="control-group">
					<label class="control-label">方案名称： </label>
					<div class="controls">
						<input type="text" value="" name="fanganmingcheng"
							id="fanganmingcheng" />
					</div>
				</div>
				<c:forEach items="${tianxietiaomu}" var="tiaomu">
					<div id="${tiaomu.pingfenid }" class="control-group">
						<label class="control-label" style="width: 160px;">${tiaomu.mingcheng}(${tiaomu.manfen })</label>
						<div class="controls">
							<input type="button" style="" class="btn btn-deafult"
								onclick="add(${tiaomu.pingfenid })" value="添加" />
						</div>
					</div>
				</c:forEach>
				<input type="hidden" id="fanganid" name="fanganid"
					value="${tianxietiaomu[0].fanganid}">
				<!-- 					<input type="button"  class="btn btn-deafult" onclick="sub()" value="提交" /> -->
				<div class="control-group">
					<label class="control-label" for="focusedInput"></label>
					<div class="controls">
						<button type="button" style="margin-left: 30px;"
							class="btn btn-success" value="提交" onclick="sub()">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
			function add(id)
			{
				var j = 0;
				for(var i = 1;document.getElementById(id+"-"+i)!=null;i++){
					j++;
				}
				j=j+1;
				var num = ("#"+id);
				var a = '';
				a += '<div id="'+id+'-'+j+'-div" class="controls">名称：<input style="width:120px;" type="text" id="'+id+'-'+j+'-mingcheng" name="'+id+'-'+j+'-mingcheng"></input> ';
				a += '&emsp;项类型：<select style="width:120px;" id="'+id+'-'+j+'-s" name="'+id+'-'+j+'-s"><option value="1">得分项</option><option value="0">得分点</option></select>';
				a += '&emsp;填写方式：<select style="width:120px;" id="'+id+'-'+j+'-c" name="'+id+'-'+j+'-c"><option value="1">填写分数</option><option value="2">填写名称</option><option value="3">选择是否</option></select>';
				a += '<br>&emsp;满分：<input style="width:120px;" id="'+id+'-'+j+'-manfen" name="'+id+'-'+j+'-manfen" type="number" max="100" min="0" >';
				a += '<br>&emsp;单项分(填写方式为填写名称时规定，其它方式不需填写)：<input style="width:120px;" id="'+id+'-'+j+'-dxf" name="'+id+'-'+j+'-dxf" type="number" max="100" min="0" >';
				a += '&emsp;<input type="button" onclick="cfm(\''+id+'-'+j+'\')" value="确认"/></div><br>';
				$(num).append(a);
			}
			
			function cfm(string){
				var name = $("#"+string+"-mingcheng").val();
				var xiangleixing = $("#"+string+"-s").val();
				if(xiangleixing=='1'){
					$("#"+string+"-s").val(1);
				}else{
					$("#"+string+"-s").val(0);
				}
				var tianxiefangshi = $("#"+string+"-c").val();
				var manfen = $("#"+string+"-manfen").val();
				$("#"+string+"-manfen").val(manfen);
				var danxiangfen = $("#"+string+"-dxf").val();
				$("#"+string+"-dxf").val(danxiangfen)
				var s = string.split("-");
				var num = ("#"+s[0])
				var code="";
				var aa = string.split("-");
				if(xiangleixing==0){
					if(aa.length==2){
						if(tianxiefangshi==3){
							code = '<br><div style="" id = "'+string+'" class="controls"><span style="font-size:20px;width:40%;padding-left:0em;text-align:left;">'+name+'('+manfen+')</span><span  style="margin-left:30px;" >是</span><input style="margin-left:10px;" type="radio" value="'+manfen+'"><span style="margin-left:10px;">否</span><input style="margin-left:10px;" type="radio" value="0"></div>';
						}
						else if(tianxiefangshi==2){
							code = '<br><div style="" id = "'+string+'" class="controls"><span style="font-size:20px;width:40%;padding-left:0em;text-align:left;">'+name+'('+manfen+')</span><input style="margin-left:30px;" type="text"><span style="margin-left:10px;"> 名称：</span></div>';
						}
						else if(tianxiefangshi==1){
							code = '<br><div style=" id = "'+string+'" class="controls"><span style="font-size:20px;width:40%;padding-left:0em;text-align:left;">'+name+'('+manfen+')</span><input style="margin-left:30px;"  type="number" max="'+manfen+'" min="0"/><span style="margin-left:10px;">得分:</span></div>'
						}
					}else{
						if(tianxiefangshi==3){
							code = '<br><div style="" id = "'+string+'" class="controls"><span style="font-size:15px;width:40%;padding-left:0em;text-align:left;">'+name+'('+manfen+')</span><span style="margin-left:30px;">是</span><input style="margin-left:10px;" type="radio" value="'+manfen+'"><span style="margin-left:10px;">否</span><input style="margin-left:10px;" type="radio" value="0"></div>';
						}
						else if(tianxiefangshi==2){
							code = '<br><div style="" id = "'+string+'" class="controls"><span style="font-size:15px;width:40%;padding-left:0em;text-align:left;">'+name+'('+manfen+')</span><input style="margin-left:30px;" type="text"><span style="margin-left:10px;">名称：</span></div>';
						}
						else if(tianxiefangshi==1){
							code = '<br><div style="" id = "'+string+'" class="controls"><span style="font-size:15px;width:40%;padding-left:0em;text-align:left;">'+name+'('+manfen+')</span><input style="margin-left:30px;" type="number" max="'+manfen+'" min="0"/><span style="margin-left:10px;">得分:</span></div>'
						}
					}
				}else{
					code = '<br><div style="" id = "'+string+'" class="controls"><span style="font-size:20px;width:40%;padding-left:0em;text-align:left;">'+name+'('+manfen+')</span><input style="margin-left:30px;" type="button" class="btn btn-deafult" onclick="add(\''+string+'\')" value="添加"/></div>';
				}
				$(num).append(code)
				document.getElementById(string+"-div").style.display="none";
			}
			
			function sub(){
				var fanganmingcheng = $("#fanganmingcheng").val();
				if(fanganmingcheng==null || ''==fanganmingcheng){
					alert("填写方案名称");
					return false;
				}
				$.ajax({
		                type: "POST",//方法类型
		                dataType: "text",//预期服务器返回的数据类型
		                url: "sub-new-deyu" ,//url
		                data: $('#form').serialize(),
		                success: function (result) {
		                    if (result=="success") {
		                        alert("修改成功");
		                    }
							toContentPage('fabukaoping');
		                },
		                error : function() {
		                    alert("异常！");
		                }
		            });
			}
			</script>