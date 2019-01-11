<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar-inner">
	<div class="container-fluid">
		<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</a>
		<a id="main-menu-toggle" class="hidden-phone open"><i class="icon-reorder"></i></a>		
		<div class="row-fluid">
		<a class="brand span2" href="${path }"><span>校园助手</span></a>
		</div>		
		<!-- start: Header Menu -->
		<div class="nav-no-collapse header-nav">
			<ul class="nav pull-right">
				<li class="dropdown">
					<a class="btn account dropdown-toggle" data-toggle="dropdown" href="#">
						<div class="avatar"><img src="<%=request.getContextPath()%>/static/css/new/img/avatar.jpg" alt="Avatar" /></div>
						<div class="user">
							<c:choose>
								<c:when test="${user.status == 'xuesheng'}">
									<c:set var="statusName" value="学生"></c:set>
								</c:when>
								<c:when test="${user.status == 'jiaoshi'}">
									<c:set var="statusName" value="教师"></c:set>
								</c:when>
								<c:when test="${user.status == 'fudaoyuan'}">
									<c:set var="statusName" value="辅导员"></c:set>
								</c:when>
								<c:when test="${user.status == 'shuji'}">
									<c:set var="statusName" value="书记"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="statusName" value="管理员"></c:set>
								</c:otherwise>
							</c:choose>
							<span class="hello">欢迎 ${statusName }</span>
							<span class="name">
							<c:choose>
								<c:when test="${user.status == 'xuesheng'}">
									${user.xingming}
								</c:when>
								<c:otherwise>
									${user.yonghuxingming}
								</c:otherwise>
							</c:choose>
							</span>
						</div>
					</a>
					<ul class="dropdown-menu" style="z-index: 2000;">
						<li class="dropdown-menu-title">
							
						</li>
						<li><a href="JavaScript:void(0);" onclick="toContentPage('info');" ><i class="icon-user"></i> 个人信息</a></li>
						<li><a href="logout"><i class="icon-off"></i> 退出</a></li>
					</ul>
				</li>
				<!-- end: User Dropdown -->
			</ul>
		</div>
		<!-- end: Header Menu -->
		
	</div>
</div>
<script>

function browser() {
	var b = !!(window.opera && window.opera.version);
	var e = a("MozBoxSizing");
	var c = Object.prototype.toString.call(window.HTMLElement).indexOf("Constructor") > 0;
	var d = !c && a("WebkitTransform");
	function a(f) {
		return f in document.documentElement.style
	}

	if (b) {
		return false
	} else {
		if (c || d) {
			return true
		} else {
			return false
		}
	}
}
jQuery(document).ready(function(a) {
	a("input, textarea").placeholder()
});
jQuery(document).ready(function(a) {
	a("textarea").autosize()
});
jQuery(document).ready(function(a) {
	a(".discussions").find(".delete").click(function() {
		a(this).parent().fadeTo("slow", 0, function() {
			a(this).slideUp("slow", function() {
				a(this).remove()
			})
		})
	})
});
jQuery(document).ready(function(a) {
	if (a(".messagesList").width()) {
		if (jQuery.browser.version.substring(0, 2) == "8.") {
			a("ul.messagesList li:nth-child(2n+1)").addClass("odd")
		}
	}
});
function retina() {
	retinaMode = (window.devicePixelRatio > 1);
	return retinaMode
}

jQuery(document).ready(function(a) {
	if (a(".boxchart")) {
		if (retina()) {
			a(".boxchart").sparkline("html", {
				type : "bar",
				height : "60",
				barWidth : "8",
				barSpacing : "2",
				barColor : "#ffffff",
				negBarColor : "#eeeeee"
			});
			a(".boxchart").css("zoom", 0.5)
		} else {
			a(".boxchart").sparkline("html", {
				type : "bar",
				height : "30",
				barWidth : "4",
				barSpacing : "1",
				barColor : "#ffffff",
				negBarColor : "#eeeeee"
			})
		}
	}
});
jQuery(document).ready(function(a) {
	a(".todo-actions > a").click(function() {
		if (a(this).find("i").attr("class") == "icon-check-empty") {
			a(this).find("i").removeClass("icon-check-empty").addClass("icon-check");
			a(this).parent().parent().find("span").css({
				opacity : 0.25
			});
			a(this).parent().parent().find(".desc").css("text-decoration", "line-through")
		} else {
			a(this).find("i").removeClass("icon-check").addClass("icon-check-empty");
			a(this).parent().parent().find("span").css({
				opacity : 1
			});
			a(this).parent().parent().find(".desc").css("text-decoration", "none")
		}
		return false
	});
	a(function() {
		a(".todo-list").sortable();
		a(".todo-list").disableSelection()
	})
});
(function(i, s, o, g, r, a, m) {
	i['GoogleAnalyticsObject'] = r;
	i[r] = i[r] ||
	function() {
		(i[r].q = i[r].q || []).push(arguments)
	}, i[r].l = 1 * new Date();
	a = s.createElement(o), m = s.getElementsByTagName(o)[0];
	a.async = 1;
	a.src = g;
	m.parentNode.insertBefore(a, m)
})(window, document, 'script', '', 'ga');
ga('create', 'UA-9510961-22', 'clabs.co');
ga('send', 'pageview'); 
</script>