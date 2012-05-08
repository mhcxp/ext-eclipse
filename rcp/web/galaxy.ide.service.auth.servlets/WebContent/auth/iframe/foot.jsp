<!DOCTYPE html PUBLIC "-//W3C//Dtd Xhtml 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/auth/include/check_user.jsp"/>

<!-- L10N -->
<fmt:setBundle basename="nls.foot" />


<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
<style>
td {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}
</style>

<meta content="MShtml 6.00.2900.3243" name=GENERATOR>
</head>
<%
	String username = (String) session.getAttribute("username");
	if (username == null) {
		response.sendRedirect("/auth/login.jsp");
	} else {
%>
<body leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
	<table cellSpacing=0 cellPadding=0 width="100%" border=0>
		<tbody>
			<tr>
				<td bgColor=#73a3d4 height=6></td>
			</tr>
		</tbody>
	</table>
	<table height=28 cellSpacing=0 cellPadding=0 width="100%"
		bgColor=#3a6592 border=0>
		<tbody>
			<tr>
				<td>
					<table height="100%" cellSpacing=2 cellPadding=0 width="100%"
						border=0>
						<tbody>
							<tr>
								<td align=right width=109>
								</td>
								<td align=right width=628></td>
								<td
									style="FONT-WEIGHT: bolder; FILTER: shadow(Color =     #000000, direction =     180)"
									align=right width=250></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	<script language=javascript>
		var screen = false;
		i = 0;
		width = 0;
		function ChangeLeftFrameStatu() {
			if (screen == false) {
				parent.FrameMain.cols = '0,*';
				screen = true;
				self.st.innerhtml = "√ 打开左栏"
			} else if (screen == true) {
				parent.FrameMain.cols = '165,*';
				screen = false;
				self.st.innerhtml = "<font color=red>×</font> 关闭左栏"
			}
		}
	</script>
	<!-- 此处下面加站长统计代码 -->
</body>
<%
	}
%>
</html>
