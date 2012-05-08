<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${param.language=='zh'}">
	<fmt:setLocale value="zh" scope="session" />
</c:if>

<c:set var="LOGIN_JSP" value="/auth/login.jsp" />
<c:set var="MAIN_JSP" value="/auth/iframe/main.jsp" />
${sessionScope.username}
<c:choose>
	<c:when test="${empty sessionScope.username}">
		<jsp:forward page="${LOGIN_JSP}" />
	</c:when>
	<c:otherwise>
		<jsp:forward page="${MAIN_JSP}" />
	</c:otherwise>
</c:choose>

