<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<script src="./js/jquery-1.7.2.js"></script>
	<title>点击下载应用</title>
	<style type="text/css">
	*{margin:0; padding:0;}
	a{text-decoration: none;}
	img{max-width: 100%; height: auto;}
	.weixin-tip{display: none; position: fixed; left:0; top:0; bottom:0; background: rgba(0,0,0,0.8); filter:alpha(opacity=80);  height: 100%; width: 100%; z-index: 100;}
	.weixin-tip p{text-align: center; margin-top: 10%; padding:0 5%;}

	.cont{ display: none;overflow: hidden; position: fixed; left:0; top:0;  background:#F6F6F6; height: 100%; width: 100%; z-index: 100;}
	.cont .left,.cont .right{  float: left;width: 50%;box-sizing: border-box;padding:10%;}
	.cont a{  display: block; text-align: center;border:1px solid #0000ff;padding:2%;}
	.cont .img1,.cont .img2{padding:5% 15%;}
	.cont  a:hover{border-color: red;color:red;}
	</style>
</head>
<body>
	<div class="weixin-tip">
		<p>
			<img src="./app/live_weixin.png" alt="微信打开"/>
		</p>
	</div>
	<div class="cont">
		<div id="android" style="display: none;">
			<div>
				<img src="./app//3.jpg" alt=""/>
			</div>
			<div class="img1">
				<a href="<%=basePath%>downApp">立即下载</a>
			</div>
		</div>
		<div id="ios" style="display: none;">
			<div>
				<img src="./app/4.jpg" alt=""/>
			</div>
			<div class="img2">
				<a href="https://itunes.apple.com/cn/app/id1370716194?mt=8">前往苹果商店下载</a>
				<%-- <a href="###">前往苹果商店下载</a> --%>
			</div>
		</div>
	</div>

	<script type="text/javascript">
        $(window).on("load",function(){
	      
        })
        
		var browser={
			versions:function(){
				var u = navigator.userAgent, 
				app = navigator.appVersion;
				return {
					trident: u.indexOf('Trident') > -1, //IE内核
					presto: u.indexOf('Presto') > -1, //opera内核
					webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
					gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
					mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
					ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
					android: u.indexOf('Android') > -1 || u.indexOf('Adr') > -1, //android终端
					iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
					iPad: u.indexOf('iPad') > -1, //是否iPad
					webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
					weixin: u.indexOf('MicroMessenger') > -1, //是否微信 
					qq: u.match(/\sQQ/i) == " qq" //是否QQ
				};
			}(),
			language:(navigator.browserLanguage || navigator.language).toLowerCase()
		}
		function is_weixin() {
		    var ua = navigator.userAgent.toLowerCase();
		    if (ua.match(/MicroMessenger/i) == "micromessenger") {
		        return true;
		    } else {
		        return false;
		    }
		}
		function is_QQ() {
		   var ua = navigator.userAgent.toLowerCase();
			if (ua.match(/QQ/i) == "qq") {
				return true;
			}else{
				return false;
			}
		}
		$(function () {
			var winHeight = $(window).height();
			var isWeixin = is_weixin();
			var is_qq = is_QQ();
			if(isWeixin || is_qq){
				$(".weixin-tip").css("height",winHeight);
	            $(".weixin-tip").show();
	            return;
			}else{
				$(".cont").show();
			}
		    if (browser.versions.ios || browser.versions.iPhone || browser.versions.iPad) {
		        $('#ios').show();
		    }else 
		    if (browser.versions.android) {
		        $('#android').show();
		    } else {
		    	 $('#ios').addClass('right');
		    	 $('#android').addClass('left');
		    	 $('#ios').show();
				$('#android').show();
		    }
		});
	</script>
</body>
</html>
