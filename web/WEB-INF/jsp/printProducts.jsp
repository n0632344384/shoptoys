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

<%-- print products information --%>
<c:forEach items="${products}" var="product">
	<div class="products">
		<div class="productLeft">
			<div class="productImage">
				<c:set var="curImageUrl" scope="session"
					value="resources/img/products/categories/${product.category_id}/${product.image}.jpg" />
				<img src="<c:url value='${curImageUrl}'/>" width="100" height="100" />
			</div>

			<div class="productPrice">
				<b><c:out value="${product.price}"></c:out></b>
			</div>

			<%-- add to basket products just for logged in users --%>
			<sec:authorize access="hasRole('ROLE_USER')" var="isUser" />
			<sec:authorize access="hasRole('ROLE_MANDATOR')" var="isMandator" />
			<%--sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" /--%>
			<c:if test="${isUser or isMandator}">
				<form class="formAddToBasket" action="<c:url value="/addToBasket"/>">
					<input type="hidden" name="productId" value="${product.id}">
					<input type="hidden" name="productName" value="${product.name}">
					<select size="1" name="productQuantity">
						<option value="0"><fmt:message key="choose"
								bundle="${lang}" /></option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select> <input type="submit"
						value=<fmt:message key="add" bundle="${lang}" />>
				</form>
			</c:if>
		</div>
		<%-- class="productLeft" --%>

		<div class="productName">
			<c:out value="${product.name}"></c:out>
		</div>

		<div class="productDescription">
			<c:out value="${product.description}"></c:out>
		</div>
	</div>
	<%-- class="products" --%>
</c:forEach>
<%-- items="${products}" var="product" --%>

<%@ include file="printProductsPagination.jsp"%>
<br>
<br>