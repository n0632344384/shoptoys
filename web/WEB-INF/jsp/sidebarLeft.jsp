<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%-- setting locale --%>
<c:if test="${empty sessionScope.curShopLang}">
	<fmt:setLocale value="ru" />
	<fmt:setBundle basename="config" var="lang" />
</c:if>
<c:if test="${!empty sessionScope.curShopLang}">
	<fmt:setLocale value="${sessionScope.curShopLang}" />
	<fmt:setBundle basename="config" var="lang" />
</c:if>

<div id="sidebarLeft">
	
	<sec:authorize access="hasRole('ROLE_MANDATOR')" var="isMandator" />
	<sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
	
	<%-- main menu --%>
	<c:if test="${!isMandator and !isAdmin}">
		<div id="menu">
			<a href="/shop"><fmt:message key="menuhome" bundle="${lang}" /></a>
		</div>
	</c:if>
	
	<%-- menu mandator --%>
	<c:if test="${isMandator}">
		<div id="menuMandator">
			<a href="/shop/mandator"><fmt:message key="menuhome"
					bundle="${lang}" /></a><br> <a
				href="/shop/mandator?mandator=addProduct"><fmt:message
					key="addproduct" bundle="${lang}" /></a><br> <a
				href="/shop/mandator?mandator=updateProduct"><fmt:message
					key="updateproduct" bundle="${lang}" /></a>
		</div>
	</c:if>
	
	<%-- menu admin --%>
	<c:if test="${isAdmin}">
		<div id="menuAdmin">
			<a href="/shop/admin"><fmt:message key="menuhome"
					bundle="${lang}" /></a><br> <a
				href="/shop/admin?task=users"><fmt:message
					key="admin.menu.users" bundle="${lang}" /></a>
		</div>
	</c:if>
</div>