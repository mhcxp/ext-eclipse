<%@ page language="java"
	import="java.util.*,galaxy.ide.service.auth.adapters.*"
	contentType="text/html;charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/auth";
	String dir = "";
%>