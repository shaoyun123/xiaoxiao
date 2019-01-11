<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>处理意见
		</h2>
	</div>
	<div class="box-content">
				<div class="card" style="background-color:#ffffff;"><br>
					<div style="margin-left:10px">
						<span style="font-size:20px">${yijian.yijianmingcheng}</span>
						<c:if test="${yijian.nimingbiaoji==0}">
							<span style="margin-left:30px;color:orange;">#公开#</span>
							<span style="margin-left:20px;color:orange;">#${yijian.banjimingcheng}#</span>
							<span style="margin-left:20px;color:orange;">#${yijian.xueshengxingming}#</span>
						</c:if>
						<c:if test="${yijian.nimingbiaoji==1}"><span style="margin-left:30px;color:orange;">#匿名#</span></c:if>
						<span style="margin-left:20px;color:orange;">#${yijian.biaoqian}#</span>
					</div>
					<div style="margin-left:40px">
						<h5 style="color:blue;">${yijian.tijiaoshijian}</h5>
					</div>
					<div style="width:90%;margin-left:30px">
						<h4 style="font-family:'楷体'">${yijian.wenzineirong}</h4>
					</div>
					<div style="margin-left:50px;">
							<c:if test="${not empty yijian.tupian}">
							<c:forEach items="${yijian.tupian}" var="tupian">
								<img  height="150px" alt="" src="getPic?id=${tupian}">
							</c:forEach>
							</c:if>
					</div><br>
					<div style="width:88%;margin-left:40px">
						<form action="" method="post" id="form1">
							<textarea id="chuli" name="chuli" style="height:150px;width:100%;resize:none;"></textarea><br>
							<span class="pull-left" style="display: inline;">
                            	<input style="display: inline;" type="radio" id="bugongbu" name="isgongbu"  checked value="0"><label style="display: inline;color: red" for="bugongbu">不公布</label>
								<input style="display: inline;" type="radio" id="gongbu" name="isgongbu"  value="1"><label style="display: inline;color: red" for="gongbu">公布</label></span><br>
								<span>
								选择范围：
								<select id="fanwei" name="fanwei" style="width:100px;">
									<c:if test="${yijian.nimingbiaoji==0}">
										<option value="本班">本班</option>
									</c:if>
									<option value="本专业">本专业</option>
									<option value="本学院">本学院</option>
								</select>
								</span>&emsp;&emsp;<br>
								<input type="hidden" id="id" name="id"  value="${yijian.yijianid}" />
								<button type="button" name="action" class="btn btn-primary"  onclick="subchuli();">提交</button>
						</form>
					</div><br><br>
				</div>
			</div>
			</div>


	<script type="text/javascript">
	function subchuli(){
		if($("#chuli").val()==""){
			alert("请输入评论内容！")
			return false;
		}
		if(confirm("确认要提交吗？")==true){
			$.ajax({
				type : "POST",
				url : 'submitchuli_shuji',
				data :$("#form1").serialize(),
				dataType : 'json',
				cache : false,
				timeout : 5000,
				async : true,
				success : function(data) {
					if (data.status == "success") {
						alert("success");
						toContentPage("xueshengyijian_shuji");
					} else {
						alert("fail!");
						toContentPage("xueshengyijian_shuji");
					}
				},
				error : function() {
					alert("登录超时!");
					toContentPage("xueshengyijian_shuji");
				}
			});
		}else{
			return false;
		}
	}
	</script>
