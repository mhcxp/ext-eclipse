<%@ page contentType="text/html;charset=utf-8"
	import="java.util.*,galaxy.ide.auth.role.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="users" value="${sessionScope.users}" />
<c:set var="start" value="${requestScope.start} }" />
{rows:[
<c:forEach var="user" items="${users}" varStatus="status">
	<c:if test="${!status.index}">
		  		{"uid":"${user.ID}",
		  			"name":"${user.name}",
		  			"info":"${user.userInfo}",
		  			"roles":"${status.index}",
		  			"op":"${start}"}
		  		<c:if test="${!status.last}">
		  		,
		  		</c:if>
	</c:if>
</c:forEach>
]}
