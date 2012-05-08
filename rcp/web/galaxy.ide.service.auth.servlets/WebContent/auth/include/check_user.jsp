<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty sessionScope.username}">
	<c:redirect url="/auth/index.jsp"/>
</c:if>

