<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Main Content -->
			<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-align-justify"></i><span class="break"></span>创建学生组织</h2>
		</div>
					<div class="box-content" style="height: 1000px;">
							<form action="" method="post">
								<div
									style="line-height:46px;width: 40%;height: 46px; border-top: 1px solid #000000;border-right: 1px solid #000000;border-left: 1px solid #000000; text-align: center; float: left">
									<font size=5px style="margin-left: -300px;">姓名：${user.xingming}</font>
								</div>
								<div
									style="line-height:46px;width: 40%;height: 46px; border-top: 1px solid #000000;border-right: 1px solid #000000; text-align: center; float: left;">
									<font size=5px>联系电话：</font><input name="dianhua" id="dianhua"
										type="tel" style="width: 150px;"/>
									
								</div>
								<div
									style="line-height:36px;width: 40%; border: 1px solid #000000;  float: left;">
									<font size=5px>&emsp;学生组织名称：</font><input name="mingcheng" id="mingcheng"
										type="text" style="width: 40%;height:30px;margin-bottom:5px;"/>
									
								</div>
								<div
									style="line-height:46px;width: 40%;height: 45px; border-top: 1px solid #000000;border-bottom: 1px solid #000000;border-right: 1px solid #000000; text-align: center; float: left;">
									<font size=5px>&emsp;指导教师：</font>
									<select id="jiaoshi" name="jiaoshi" style="width: 40%;height:30px;margin-bottom:5px;">
                                    	<c:forEach items="${jiaoshis}" var="jiaoshi">
                                    		<option value="${jiaoshi.yonghuid}">${jiaoshi.yonghuxingming}</option>
                                    	</c:forEach>
                                    </select>
								</div>
								<div
									style="width: 80%; border-left: 1px solid #000000;border-right: 1px solid #000000; float: left;">
									<font size=5px>&emsp;学生组织介绍：</font>
									<br>
									<textarea  name="jieshao" id="jieshao"  class="form-control" style="width:80%;height:300px;margin-left: 50px;resize:none;"></textarea>
									<br>
								</div>
								<div
									style="width: 80%;border: 1px solid #000000; float: left;">
									<font size=5px>&emsp;创建理由：</font>
									<br>
									<textarea  name="liyou" id="liyou"  class="form-control" style="width:80%;height:300px;margin-left: 50px;resize:none;"></textarea>
									<br>
								</div>
								<div style="width:80%">
									<a onclick="severCheck()"
							 		 class="flip-link btn btn-info pull-right" id="to-recover"><span>提交</span></a>
								</div>
							</form>
						</div>

					</div>
				
		
			<script type="text/javascript">
			function severCheck(){
				if(check()){
					var phone = $("#dianhua").val();
					var name = $("#mingcheng").val();
					var jieshao = $("#jieshao").val();
					var liyou = $("#liyou").val();
					var jiaoshi = $("#jiaoshi").val();
					var code = phone+",zytech,"+name+",zytech,"+jieshao+",zytech,"+liyou+",zytech,"+jiaoshi;
					$.ajax({
						type: "POST",
						url: 'subcjxszz',
				    	data:{CODE:code},
						dataType:'text',
						cache:false,
						timeout: 5000,
						async:true, 
						success:function(data)
						{
							if(data=="mingcheng"){
								alert("已有同名学生组织！")
								$("#mingcheng").focus();
							}
							else if(data=="success"){
								alert("提交成功！")
								window.location.href="wodeshetuan";
							}
							else{
								alert("提交失败！")
							}
								
								
						},
						error:function()
						{
							alert("超时!")
						}
						
					});
				}
			}
			
			
			function check() {
			if ($("#dianhua").val() == "") {
				alert("请填写联系电话")
				$("#dianhua").focus();
				return false;
			}
			
			var phone = document.getElementById('dianhua').value;
			if(!(/^1[34578]\d{9}$/.test(phone))){ 
		        alert("电话号码有误，请重填");  
		        $("#dianhua").focus();
		        return false; 
		    } 
			if($("#mingcheng").val()=="") {
				alert("请填写学生组织名称")
				$("#mingcheng").focus();
				return false;
			}
			if($("#jiaoshi").val()=="") {
				alert("请选择指导教师")
				$("#jiaoshi").focus();
				return false;
			}
			if ($("#jieshao").val() == "") {
				alert("请填写学生组织介绍")
				$("#jieshao").focus();
				return false;
			}
			if ($("#liyou").val() == "") {
				alert("请填写创建理由")
				$("#liyou").focus();
				return false;
			}
			return true;
		}
		</script>
	