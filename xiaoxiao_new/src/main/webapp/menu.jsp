<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="nav-collapse sidebar-nav">
	<ul class="nav nav-tabs nav-stacked main-menu">
		<li><a href="JavaScript:void(0);" onclick="toContentPage('index.jsp')" ><i class="icon-star"></i><span class="hidden-tablet"> 首页</span></a></li>
		<c:forEach items="${menu}" var="UserMenu">
			<c:if
				test="${UserMenu.fatherid == -1 and empty UserMenu.action }">
				<li>
					<a class="dropmenu" href="JavaScript:void(0);"><i class="icon-folder-close-alt"></i><span class="hidden-tablet"> ${UserMenu.menuname}</span> <span class="label">${fn:length(UserMenu.childMenus) }</span></a>
					<ul>
						<c:forEach items="${UserMenu.childMenus}" var="childMenus">
							<c:if test="${not empty childMenus.action}">
								<li><a class="submenu" onclick="toContentPage('${childMenus.action}')" href="JavaScript:void(0);"><i class="icon-hdd"></i><span class="hidden-tablet"> ${childMenus.menuname} </span></a></li>
							</c:if>
						</c:forEach>
					</ul>
				</li>	
			</c:if>
		</c:forEach>
		<!-- <li><a href="ui.html"><i class="icon-eye-open"></i><span class="hidden-tablet"> UI Features</span></a></li>
		<li><a href="widgets.html"><i class="icon-dashboard"></i><span class="hidden-tablet"> Widgets</span></a></li>
		<li>
			<a class="dropmenu" href="#"><i class="icon-folder-close-alt"></i><span class="hidden-tablet"> Example Pages</span> <span class="label">3</span></a>
			<ul>
				<li><a class="submenu" href="infrastructure.html"><i class="icon-hdd"></i><span class="hidden-tablet"> Infrastructure</span></a></li>
				<li><a class="submenu" href="messages.html"><i class="icon-envelope"></i><span class="hidden-tablet"> Messages</span></a></li>
				<li><a class="submenu" href="tasks.html"><i class="icon-tasks"></i><span class="hidden-tablet"> Tasks</span></a></li>
			</ul>	
		</li>
		<li><a href="form.html"><i class="icon-edit"></i><span class="hidden-tablet"> Forms</span></a></li>
		<li><a href="chart.html"><i class="icon-list-alt"></i><span class="hidden-tablet"> Charts</span></a></li>
		<li><a href="typography.html"><i class="icon-font"></i><span class="hidden-tablet"> Typography</span></a></li>
		<li><a href="gallery.html"><i class="icon-picture"></i><span class="hidden-tablet"> Gallery</span></a></li>
		<li><a href="table.html"><i class="icon-align-justify"></i><span class="hidden-tablet"> Tables</span></a></li>
		<li><a href="calendar.html"><i class="icon-calendar"></i><span class="hidden-tablet"> Calendar</span></a></li>
		<li><a href="file-manager.html"><i class="icon-folder-open"></i><span class="hidden-tablet"> File Manager</span></a></li>
		<li><a href="icon.html"><i class="icon-star"></i><span class="hidden-tablet"> Icons</span></a></li>
		<li><a href="login.html"><i class="icon-lock"></i><span class="hidden-tablet"> Login Page</span></a></li>  -->
	</ul>
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
function toContentPage(url){
	$('#content').load(url);
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
	a("ul.main-menu li a").each(function() {
		if (a(a(this))[0].href == String(window.location)) {
			a(this).parent().addClass("active")
		}
	});
	a("ul.main-menu li ul li a").each(function() {
		if (a(a(this))[0].href == String(window.location)) {
			a(this).parent().addClass("active");
			a(this).parent().parent().show()
		}
	});
	a(".dropmenu").click(function(b) {
		b.preventDefault();
		a(this).parent().find("ul").slideToggle()
	})
});
jQuery(document).ready(function(b) {
	var a = true;
	b("#main-menu-toggle").click(function() {
		if (b(this).hasClass("open")) {
			b(this).removeClass("open").addClass("close");
			var f = b("#content").attr("class");
			var e = parseInt(f.replace(/^\D+/g, ""));
			var c = e + 2;
			var d = "span" + c;
			b("#content").addClass("full");
			b(".brand").addClass("noBg");
			b("#sidebar-left").hide()
		} else {
			b(this).removeClass("close").addClass("open");
			var f = b("#content").attr("class");
			var e = parseInt(f.replace(/^\D+/g, ""));
			var c = e - 2;
			var d = "span" + c;
			b("#content").removeClass("full");
			b(".brand").removeClass("noBg");
			b("#sidebar-left").show()
		}
	})
});
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