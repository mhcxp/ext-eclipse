<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--主页 -->
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="nls.main" />

<html>
<head>
<title><fmt:message key="page.head.title" /></title>
</head>
<%
	String username = (String) session.getAttribute("username");
	if (username == null) {
		response.sendRedirect(basePath + "/login.jsp");
	} else {
		String dir = "";
%>
<frameset border=0 frameSpacing=0 rows=65,*,33 frameBorder=0>
<frame name=FrameTop src="<%=basePath%><%=dir%>/iframe/top.jsp"
	frameBorder=no noResize scrolling=no> <frameset border=0
	name=FrameMain frameSpacing=0 frameBorder=0 cols=165,*> <frame
	name=LeftFrame marginWidth=0 marginHeight=0
	src="<%=basePath%><%=dir%>/iframe/left.jsp" frameBorder=no scrolling=no>
<frameset border=0 frameSpacing=0 rows=*,26 frameBorder=0> <frame
	name=MainFrame marginWidth=0 marginHeight=0
	src="<%=basePath%><%=dir%>/iframe/main.jsp" frameBorder=no noResize
	scrolling=yes> <frame name=BottomFrame marginWidth=0
	marginHeight=0 src="<%=basePath%><%=dir%>/iframe/help.jsp"
	frameBorder=no scrolling=no> </frameset> </frameset> <frame name=FrameBottom
	marginWidth=0 marginHeight=0
	src="<%=basePath%><%=dir%>/iframe/foot.jsp" frameBorder=no noResize
	scrolling=no> </frameset>
<%
	}
%>
</html>