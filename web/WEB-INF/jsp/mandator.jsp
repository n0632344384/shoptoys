<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SHOP - Mandator</title>
<link type="text/css" rel="stylesheet" href="resources/css/index.css" />
</head>
<body>
	<%-- setting locale --%>
	<c:if test="${empty sessionScope.curShopLang}">
		<fmt:setLocale value="ru" />
		<fmt:setBundle basename="config" var="lang" />
	</c:if>
	<c:if test="${!empty sessionScope.curShopLang}">
		<fmt:setLocale value="${sessionScope.curShopLang}" />
		<fmt:setBundle basename="config" var="lang" />
	</c:if>

	<div id="header">
		<%@ include file="header.jsp"%>
	</div>

	<div id="content">
		<%@ include file="sidebarLeft.jsp"%>

		<div id="center">
			<c:choose>
				<%-- if choosed add product --%>
				<c:when test="${requestScope.mandator eq 'addProduct'}">
					<%@ include file="mandatorAddProduct.jsp"%>
				</c:when>

				<%-- if choosed update product --%>
				<c:when test="${requestScope.mandator eq 'updateProduct'}">
					<%@ include file="mandatorUpdateProduct.jsp"%>
				</c:when>

				<c:otherwise>
					<%-- rest cases (not add, not update) --%>
					<div id="mandatorMessage">
						<fmt:message key="mandatorLoggedIn" bundle="${lang}" />
						<br>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<%@ include file="sidebarRight.jsp"%>
	</div>
</body>
</html>