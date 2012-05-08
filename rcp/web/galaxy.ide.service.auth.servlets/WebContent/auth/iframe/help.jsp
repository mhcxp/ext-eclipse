<!DOCTYPE html PUBLIC "-//W3C//Dtd Xhtml 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/auth/include/check_user.jsp"/>

<!-- L10N -->
<fmt:setBundle basename="nls.help" />


<html>
<head>
<meta http-equiv=Content-Language content=zh-cn />
<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
<link href="/auth/css/Admin_Style.css" type=text/css rel=stylesheet />
<script language=JavaScript src="/auth/js/SetFocus.js"></script>
<meta content="MShtml 6.00.2900.3243" name=GENERATOR />
</head>
<%
	String username = (String) session.getAttribute("username");
	if (username == null) {
		response.sendRedirect("/auth/login.jsp");
	} else {
%>
<body leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
	<table style="BORDER-COLLAPSE: collapse" cellSpacing=1 width="100%"
		bgColor=#ecf3fb border=0>
		<tbody>
			<tr>
				<td class=sortbutton noWrap align=right width="32%"></td>
			</tr>
		</tbody>
	</table>
</body>
<%
	}
%>
</html>
