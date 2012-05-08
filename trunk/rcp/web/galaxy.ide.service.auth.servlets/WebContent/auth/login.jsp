<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page errorPage="/auth/errorPage.jsp"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- L10N -->
<fmt:setBundle basename="nls.login" />
<c:set var="username" value="name" scope="page" />
<c:set var="password" value="pass" scope="page" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />
<!-- Include YUI utilities and Ext: -->
<script type="text/javascript" src="/auth/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/auth/ext/ext-all.js"></script>
<script type="text/javascript" src="/auth/js/util/md5.js"></script>
<script type="text/javascript" src="/auth/js/login.js" charset="gb2312"></script>
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="/auth/css/login.css" />
<title><fmt:message key="login.head.title" /></title>
</head>

<body bgcolor="#000077" text="#000000" leftmargin="0" topmargin="0"
	marginwidth="0" marginheight="0">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		height="100%">
		<tr>
			<td background="/auth/images/bg.jpg" valign="middle" align="center">
				<table width="360" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td align="center"><object
								classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
								codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0"
								width="330" height="190" id=ShockwaveFlash1>
								<param name=movie value="/auth/images/admin_m.swf">
									<param name=quality value=high>
										<param name="wmode" value="transparent">
											<embed src="/auth/images/admin_m.swf" quality=high
												pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash"
												type="application/x-shockwave-flash" width="330"
												height="190" wmode="transparent"> </embed>
							</object></td>
					</tr>
				</table>
				<table width="280" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="30">&nbsp;</td>
					</tr>
					<tr>
						<td bgcolor="#4047A4" height="1"></td>
					</tr>
				</table>
				<table width="280" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td bgcolor="#4047A4" width="1"></td>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="36">&nbsp;</td>
								</tr>
							</table>
							<form action="/jspHttpServlet" name=form1 id=form1 method=post
								onSubmit="return checkform()" autocomplete="off">
								<table width="160" border="0" cellspacing="0" cellpadding="0"
									align="center">

									<tr>
										<td colspan="3" class="news" height="5"><input
											type=hidden name="service" value="login" /><b id="message"
											style="color: red; font-size: 15px"> &nbsp; ${requestScope.message}</b></td>
									</tr>

									<tr>
										<td width="5" class="nwes" height="36"></td>
										<td width="56" class="font1" height="36"><font
											color="#CFD1E8"><fmt:message
													key="login.panel.username" /></font></td>
										<td><input type="text" name="name" id="name" size="13"
											class=input
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
											onkeyup="value=value.replace(/[\W]/g,'')" maxlength="10" /></td>
									</tr>
									<tr>
										<td class="nwes" height="36">&nbsp;</td>
										<td class="font1" height="36"><font color="#CFD1E8"><fmt:message
													key="login.panel.password" /></font></td>
										<td><input type="password" name="pass_display"
											id="pass_display" value="" size="13" class=input
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
											onkeyup="value=value.replace(/[\W]/g,'')" maxlength="10" />
											<input type="hidden" name="pass" id="pass" value="" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="center">&nbsp;</td>
										<td><input type="image" border="0" name="imageField"
											src="/auth/images/bt_login.gif" width="70" height="16" /></td>
									</tr>

								</table>
							</form>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="36">&nbsp;</td>
								</tr>
							</table>
						</td>
						<td bgcolor="#4047A4" width="1"></td>
					</tr>
				</table>
				<table width="280" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td bgcolor="#4047A4" height="1"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
