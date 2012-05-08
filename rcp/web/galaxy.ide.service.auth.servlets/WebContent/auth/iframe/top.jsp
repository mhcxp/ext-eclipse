<!DOCTYPE html PUBLIC "-//W3C//Dtd Xhtml 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/auth/include/check_user.jsp"/>

<!-- L10N -->
<fmt:setBundle basename="nls.top" />


<html>
<head>
<style type=text/css>
body {
	SCROLLBAR-FACE-COLOR: #73a2d6;
	SCROLLBAR-HIGHLIGHT-COLOR: #73a2d6;
	SCROLLBAR-SHADOW-COLOR: #73a2d6;
	SCROLLBAR-3DLIGHT-COLOR: #73a2d6;
	SCROLLBAR-ARROW-COLOR: #ffffff;
	SCROLLBAR-trACK-COLOR: #aabfec;
	SCROLLBAR-DARKSHADOW-COLOR: #73a2d6
}

.logo {
	font-WEIGHT: bold;
	font-SIZE: 36px;
	COLOR: #73a2d6;
	font-FAMILY: Georgia, "Times New Roman", Times, serif
}

.font_text {
	font-SIZE: 12px;
	COLOR: #ffffff;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

.font_nav_active {
	font-WEIGHT: bold;
	font-SIZE: 14px;
	BACKGROUND-IMAGE: url(images/topnav_active.jpg);
	COLOR: #ffffff;
	LINE-HEIGHT: 20px;
	HEIGHT: 31px;
	TEXT-DECORATION: none
}

.font_nav_unactive {
	BORDER-RIGHT: 0px solid;
	BORDER-TOP: #adbbc7 1px solid;
	font-WEIGHT: bold;
	font-SIZE: 14px;
	BORDER-LEFT: 0px solid;
	CURSOR: hand;
	COLOR: #436e9d;
	LINE-HEIGHT: 20px;
	BORDER-BOTTOM: #5085c5 1px solid;
	HEIGHT: 27px;
	BACKGROUND-COLOR: #f6f8f9;
	TEXT-DECORATION: none
}

.br_nav {
	BORDER-RIGHT: 0px solid;
	BORDER-TOP: #adbbc7 1px solid;
	font-SIZE: 14px;
	BORDER-LEFT: 0px solid;
	CURSOR: hand;
	COLOR: #436e9d;
	LINE-HEIGHT: 20px;
	BORDER-BOTTOM: #5085c5 1px solid;
	HEIGHT: 27px;
	BACKGROUND-COLOR: #f6f8f9;
	TEXT-DECORATION: none
}

A.white:link {
	font-WEIGHT: bold;
	font-SIZE: 13px;
	COLOR: #ffffff;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

A.white:active {
	font-WEIGHT: bold;
	font-SIZE: 13px;
	COLOR: #ffffff;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

A.white:visited {
	font-WEIGHT: bold;
	font-SIZE: 13px;
	COLOR: #ffffff;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

A.white:hover {
	font-WEIGHT: bold;
	font-SIZE: 13px;
	COLOR: #ffffff;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

.button {
	BORDER-RIGHT: #436e9d 1px double;
	BORDER-TOP: #436e9d 1px double;
	font-SIZE: 12px;
	BACKGROUND-IMAGE: url(images/bg.gif);
	BORDER-LEFT: #436e9d 1px double;
	CURSOR: hand;
	COLOR: #333333;
	LINE-HEIGHT: 16px;
	BORDER-BOTTOM: #436e9d 1px double;
	TEXT-DECORATION: none
}

A:link {
	font-SIZE: 12px;
	COLOR: #666666;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

A:active {
	font-SIZE: 12px;
	COLOR: #666666;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

A:visited {
	font-SIZE: 12px;
	COLOR: #666666;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

A:hover {
	font-SIZE: 12px;
	COLOR: #f29422;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

A.blue:link {
	font-SIZE: 12px;
	COLOR: #ffffff;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

A.blue:active {
	font-SIZE: 12px;
	COLOR: #73a2d6;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

A.blue:visited {
	font-SIZE: 12px;
	COLOR: #ffffff;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: none
}

A.blue:hover {
	font-SIZE: 12px;
	COLOR: #73a2d6;
	LINE-HEIGHT: 20px;
	TEXT-DECORATION: underline
}
</style>
<script language=javascript>		
function out(src){
	if(confirm("确定要退出吗？"))	{
		return true;	
	}
	return false;
}
</script>
<script language=JavaScript src="/auth/js/Common.js"></script>
<script language=JavaScript src="/auth/js/SetFocus.js"></script>
<meta content="MShtml 6.00.2900.3243" name=GENERATOR>
</head>
<c:if test="${empty sessionScope.nickname}">
	<jsp:forward page="/auth/login.jsp" />
</c:if>

<body leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
	<table cellSpacing=0 cellPadding=0 width="100%" bgColor=#3a6592
		border=0>
		<tbody>
			<tr>
				<td height=25>
					<table cellSpacing=0 cellPadding=0 width="100%" border=0>
						<tbody>
							<tr>
								<td><img src="/auth/images/Title.gif"></td>
								<td>
									<table height="100%" width="100%" border=0>
										<tbody>
											<tr>
												<td class=font_text width="40%"><script
														language=JavaScript src="/auth/js/date.js"
														charset="gb2312"></script></td>
												<td class=font_text style="DISPLAY: none" align=right>
													<font color=#ffffff></font>
												</td>
												<td style="DISPLAY: none" width="40%"></td>
												<td class=font_text align=right>[<A class=blue
													onclick="return out(this)"
													href="/jspHttpServlet?service=logout" target=_top>注销</A>]
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	<table class=HeaderStyle height=30 cellSpacing=0 cellPadding=0
		width="100%" border=0>
		<tbody>
			<tr>
				<td width=14 background=/auth/images/topnav_bg.jpg>&nbsp;</td>
				<td vAlign=bottom background=/auth/images/topnav_bg.jpg>
					<table class=font_nav_active id=tdTT0 cellSpacing=0 cellPadding=0
						width=150 border=0>
						<tbody>
							<tr>
								<td>
									<div align=center>当前用户：${sessionScope.username}</div>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	<table cellSpacing=0 cellPadding=0 width="100%">
		<tbody>
			<tr>
				<td bgColor=#73a3d4 height=6></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
