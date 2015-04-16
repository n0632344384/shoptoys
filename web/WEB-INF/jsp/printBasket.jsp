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

<div id="basket">
	<table id="pagination">
		<c:if test="${!empty basket}">
			<fmt:message key="shopBasket" bundle="${lang}" />
			<tr>
				<td><fmt:message key="productId" bundle="${lang}" /></td>
				<td><fmt:message key="productName" bundle="${lang}" /></td>
				<td></td>
				<td></td>
			</tr>
		</c:if>

		<c:forEach items="${basket}" var="basket">
			<tr>
				<td><c:out value="${basket.productId}"></c:out></td>
				<td><c:out value="${basket.productName}"></c:out></td>
				<td><c:out value="${basket.productQuantity}"></c:out></td>

				<c:if test="${!empty param.page}">
					<c:set var="paginCurPage" value="&page=${param.page}"></c:set>
				</c:if>

				<td><a class="removeBasketProduct"
					href="/shop/delFromBasket?productId=<c:out value="${basket.productId}"></c:out><c:out value="${paginCurPage}"></c:out>">X</a></td>
			</tr>
		</c:forEach>
	</table>
</div>