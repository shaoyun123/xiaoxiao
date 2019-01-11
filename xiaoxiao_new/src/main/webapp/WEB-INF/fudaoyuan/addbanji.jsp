<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css"
	href="static/lib/css/select2.min.css">
<script type="text/javascript" src="static/lib/js/select2.full.min.js"></script>
<script type="text/javascript" src="static/lib/js/ace/ace.js"></script>
<script type="text/javascript" src="static/lib/js/ace/mode-html.js"></script>
<script type="text/javascript" src="static/lib/js/ace/theme-github.js"></script>
<script type="text/javascript" src="static/js/app.js"></script>
<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="icon-align-justify"></i><span class="break"></span>添加班级
			</h2>
		</div>
		<div class="box-content">
			<div class="control-group">
				<label clss="control-label" for="banji"></label>
				<div class="controls">
                     		<select id="banji" name="banji" multiple="multiple" class="">
                     			<c:forEach items="${banjis}" var="BanJi">
                     				<option value="${BanJi.banjiid}">${BanJi.banjimingcheng}</option>
                     			</c:forEach>
                     		</select> 
				</div>
				<button class="btn btn-success" style="margin-left: 20px;margin-top:20px;"
					onclick="addbanji()">保存</button>
			</div>
		</div>
	</div>
</div>
						<script type="text/javascript"
							src="static/lib/js/select2.full.min.js"></script>
						<script type="text/javascript" src="static/lib/js/ace/ace.js"></script>
						<script type="text/javascript"
							src="static/lib/js/ace/mode-html.js"></script>
						<script type="text/javascript"
							src="static/lib/js/ace/theme-github.js"></script>					
						<script type="text/javascript" src="static/js/app.js"></script>						
<script type="text/javascript">
						function addbanji(){
							if(cfm()){
								var banjiids = $("select").val();
								banjiids = banjiids+",";
								$.ajax({
									type: "POST",
									url: 'subaddbanji',
							    	data:{CODE:banjiids},
									dataType:'text',
									cache:false,
									timeout: 5000,
									async:true, 
									success:function(data)
									{
										if(data=="success")
											{
											alert("成功");
											toContentPage("bjgl");
											}
										else
											{
											alert("fail");
											toContentPage("bjgl");
											}
									},
									error:function()
									{
										alert("fail");
										toContentPage("bjgl");
									}
									
								});
							}
						}
					},
					error : function() {
						alert("fail");
						toContentPage("bjgl");
					}

				});
			}
		}

		function cfm() {
			if (confirm("确认保存？") == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>