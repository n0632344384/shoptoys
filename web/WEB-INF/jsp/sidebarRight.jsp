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

<div id="sidebarRight">
	<div id="setLang">
		<%-- set current language interface --%>
		<form action="<c:url value="/setLang"/>" method="post">
			<input type="hidden" name="curUrl"
				value=${requestScope['javax.servlet.forward.servlet_path']} /> <input
				type="hidden" name="curUrlParam"
				value=${requestScope['javax.servlet.forward.query_string']} />
			<fmt:message key="shopLang" bundle="${lang}" />
			<br> <select size="1" name="selectedLang">
				<option value="ru"
					${((empty sessionScope.curShopLang) and (pageContext.request.locale.language eq 'ru')) 
						or sessionScope.curShopLang eq 'ru' ? 'selected' : ''}><fmt:message
						key="shopLangRu" bundle="${lang}" /></option>
				<option value="en"
					${((empty sessionScope.curShopLang) and (pageContext.request.locale.language eq 'en')) 
						or sessionScope.curShopLang eq 'en'  ? 'selected' : ''}><fmt:message
						key="shopLangEn" bundle="${lang}" /></option>
			</select><br> <input type="submit" name="setLang"
				value=<fmt:message key="update" bundle="${lang}" />>
		</form>
	</div>

	<sec:authorize access="hasRole('ROLE_USER')" var="isUser" />
	<sec:authorize access="hasRole('ROLE_MANDATOR')" var="isMandator" />
	<sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
	<c:choose>
		<c:when test="${isUser or isMandator or isAdmin}">

			<%-- print user info --%>
			<div id="userInfo">
				<fmt:message key="login" bundle="${lang}" />
				:
				<sec:authentication property="principal.username" />
				<br>
				<fmt:message key="role" bundle="${lang}" />
				:
				<%--sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" /--%>
				<c:choose>
					<c:when test="${isUser}">User</c:when>
					<c:when test="${isMandator}">MANDATOR</c:when>
					<c:when test="${isAdmin}">ADMIN</c:when>
				</c:choose>
				<br>
				<form action="<c:url value="/logout"/>" method="post">
					<input type="hidden" name="paginationCurPage" value="${param.page}">
					<input type="submit" name="logout"
						value=<fmt:message key="logout" bundle="${lang}" />>
				</form>
			</div>

			<%-- print basket if not empty --%>
			<div id="basketWrapper">
				<c:if test="${!empty sessionScope.basket}">
					<%@ include file="printBasket.jsp"%>
				</c:if>
			</div>
		</c:when>

		<c:otherwise>
			<%-- rest cases for (when user logged in) --%>
			<div id="login">
				<%-- error massage when trying to enter to the shop --%>
				<c:if test="${!empty sessionScope.error}">
					<div id="loginError">
						<c:out value="${sessionScope.error}" />
						<c:remove var="error" scope="session" />
					</div>
				</c:if>
				<br>

				<%-- form to enter users to shop --%>
				<form action="<c:url value="/login"/>" method="post">
					<input type="hidden" name="paginationCurPage" value="${param.page}">
					<fmt:message key="login" bundle="${lang}" />
					<br> <input type="text" name="j_username" /><br>
					<fmt:message key="loginpass" bundle="${lang}" />
					<br> <input type="password" name="j_password" /><br> <label><input
						type="checkbox" name="_spring_security_remember_me" /> <fmt:message
							key="rememberMe" bundle="${lang}" /></label><br> <input
						type="submit" name="entrance"
						value=<fmt:message key="entrance" bundle="${lang}" />>
				</form>

				<%-- form to register users in shop --%>
				<form action="<c:url value="/register"/>" method="get">
					<input type="submit"
						value=<fmt:message key="registration" bundle="${lang}" />>
				</form>
			</div>

			<c:if test="${filterCategory eq 'true'}">
				<%-- c:out value="${filterCategory}"></c:out--%>
				<%@ include file="printFilters.jsp"%>
			</c:if>
		</c:otherwise>
	</c:choose>
</div>