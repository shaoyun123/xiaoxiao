<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

				<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>新增意见</h2>
		</div>
					<div class="box-content">
                                <div class="card-body">
                               		 <form action="saveorsubmityijian" class="form-inline" method="post" enctype="multipart/form-data" >
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">意见名称：</span><input id="mingcheng" name="mingcheng" class="form-control" size="40"></input>
                                    	</div>
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">意见内容：</span><br><textarea id="neirong" name="neirong" class="form-control" style="width:1000px;height:200px"></textarea>
                                    	</div>
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">上传图片:</span><br><input id="tumingcheng" name="tumingcheng" type="file" class="form-control" multiple="multiple" style="border:none" accept="image/*"/>
                                    	</div>
                                    	<div class="sub-title">
                                    		<span style="font-weight:bold;">接收人：</span>
                                    		<select id="jieshouren" name="jieshouren" style="width:150px">
                                    			<option value="1">辅导员</option>
                                    			<option value="2">书记</option>
                                    			<option value="3">学生处管理员</option>
                                    		</select>&emsp;
                                    		<span style="font-weight:bold;">标签：</span>
                                    		<select id="biaoqian" name="biaoqian" style="width:100px">
                                    			<option value="生活">生活类</option>
                                    			<option value="学习">学习类</option>
                                    			<option value="社团">社团类</option>
                                    		</select>&emsp;
                                    	</div>
                                    	<span class="pull-right">
                                    		<input type="radio" id="niming" name="isniming"  value="1"><label style="color: red" for="niming">匿名</label>
											<input type="radio" id="gongkai" name="isniming"  checked value="0"><label style="color: red" for="gongkai">公开</label>
											&emsp;&emsp;
											<input type="submit" name="action" class="flip-link btn btn-info" value="保存" onclick="return save()">
											<input type="submit" name="action" class="flip-link btn btn-info" value="提交" onclick="return sub()">
                                    	</span>
                                    </form>
                                </div>
                            </div>
                        </div>                
	<script type="text/javascript">
		function save(){
			if(confirm("是否要保存到草稿箱？")==true){
				return true;
			}else{
				return false;
			}
		}
		function sub(){
			if($("#mingcheng").val()==""){
				alert("请编辑意见名称！")
				return false;
			}
			if($("#neirong").val()==""){
				alert("请编辑意见内容！");
				return false;
			}
			var obj = document.getElementById("tumingcheng");    
			var length = obj.files.length;    
			for(var i=0;i<length;i++)
			{         
				var temp = obj.files[i].name;          
				var postfix = temp.substring(temp.lastIndexOf(".")+1);
				if(postfix!=""){
					if(!(postfix == "jpg"||postfix == "pdf"||postfix == "png"||postfix == "JPG"||postfix == "PDF"||postfix == "PNG"))
					{   
				      	alert('文件类型不正确，请选择.jpg或.pdf或.png文件！');   
				      	$("#tumingcheng").value=""; 
				      	$("#tumingcheng").focus(); 
				      	return false;   
				    }
				}
			}
			if(confirm("提交后将无法撤回！确认提交吗？")==true){
				return true;
			}else{
				return false;
			}
		}
		
	</script>
