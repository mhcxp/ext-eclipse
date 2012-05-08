<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/auth/include/check_user.jsp"/>

<!-- L10N -->
<fmt:setBundle basename="nls.main" />

<html>
<head>
<title><fmt:message key="page.head.title" /></title>
<link href="/auth/css/Admin_Style.css" type=text/css rel=stylesheet />
<link href="/auth/css/style.css" type=text/css rel=stylesheet />
<style type="text/css">
body {
	MARGIN-LEFT: 0px;
	BACKGROUND-COLOR: #ffffff
}
</style>
</head>

<body leftMargin="0" background="/auth/images/MainBg.gif" topMargin="0"
	marginheight="0" marginwidth="0">
	<table height="100%" cellSpacing="0" cellPadding="0" width="100%"
		border="0">
		<tbody>
			<tr>
				<td vAlign=top height="50%">
					<table class=topdashed cellSpacing=0 cellPadding=0 width="100%"
						border=0>
						<tbody>
							<tr>
								<td height=25>&nbsp;&nbsp;<font color=red> <%--=list.get(0).toString() --%>
								</font> 您好，欢迎进入网站后台管理系统！您的身份：<font color=red> <%--=Integer.parseInt(list.get(1).toString())==1?"超级管理员":"普通管理员"--%>
								</font> 登录次数：<%--=list.get(2).toString() --%>次 本次登录时间：<%--=list.get(3).toString() --%>
								</td>
							</tr>
						</tbody>
					</table> <BR>
						<table cellSpacing=0 cellPadding=0 width="90%" align=center
							border=0>
							<tbody>
								<tr>
									<td height=10>
										<div align=center>
											<table cellSpacing=0 cellPadding=0 width="100%" border=0>
												<tbody>

													<tr>
														<td colspan="3" width=100%></td>
													</tr>
													<tr>
														<td colspan="3">
															<table class=tablewidth cellSpacing=1 cellPadding=3
																width="100%" border=0>
																<tbody>
																	<tr class=head>
																		<td colSpan=2 height=23>服 务 器 信 息</td>
																	</tr>
																	<tr bgColor=#ffffff>
																		<td id=map width="50%">服务器解译引擎： <%
																			String publish = getServletContext().getServerInfo();
																			out.println(publish);
																		%></td>
																		<td id=map width="50%">服务器所在时间： <%
																			out.println(new java.util.Date());
																		%></td>
																	</tr>
																	<tr bgColor=#ffffff>
																		<td id=map width="50%">JDK版本： <%=System.getProperty("java.vm.version")%></td>
																		<td id=map width="50%">服务器名（IP）： <%=request.getServerName()%></td>
																	</tr>
																	<tr bgColor=#ffffff>
																		<td id=map width="50%">JAVA虚拟机名称： <%=System.getProperty("java.vm.name")%></td>
																		<td id=map width="50%">服务器端口: <%=request.getServerPort()%></td>
																	</tr>
																	<tr bgColor=#ffffff>
																		<td id=map width="50%">服务器操作系统： <%=System.getProperty("os.name") + " "
					+ System.getProperty("os.version")%></td>
																		<td id=map width="50%">网站所在磁盘物理位置： <%=System.getProperty("user.dir")%></td>
																	</tr>
																	<tr bgColor=#ffffff>
																		<td id=map width="50%">服务器时区: <%=System.getProperty("user.timezone")%></td>
																		<td id=map width="50%">当前文件: <%=request.getServletPath()%></td>
																	</tr>
																	<tr bgColor=#ffffff>
																		<td id=map width="50%">HTTP解译引擎: <%=request.getProtocol()%></td>
																		<td id=map width="50%">你的IP： <%=request.getRemoteAddr()%></td>
																	</tr>
																	<tr bgColor=#ffffff>
																		<td id=map width="50%">当前用户:<%--=list.get(0).toString() --%></td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="3"></td>
													</tr>
													<tr>
														<td colspan="3"></td>
													</tr>
												</tbody>
											</table>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>

</HTML>
