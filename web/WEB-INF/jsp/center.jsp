<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- setting locale --%>
<c:if test="${empty sessionScope.curShopLang}">
	<fmt:setLocale value="ru" />
	<fmt:setBundle basename="config" var="lang" />
</c:if>
<c:if test="${!empty sessionScope.curShopLang}">
	<fmt:setLocale value="${sessionScope.curShopLang}" />
	<fmt:setBundle basename="config" var="lang" />
</c:if>

<div id="center">
	<c:choose>
		<%-- check if user tried to open not existed page, 
		or accidentally fell to not existed page  --%>
		<c:when test="${!empty sessionScope.error404}">
			<h1>${sessionScope.error404}</h1>
			<c:remove var="error404" scope="session" />
		</c:when>

		<c:otherwise>
			<%@ include file="printProducts.jsp"%>
		</c:otherwise>
	</c:choose>
</div>