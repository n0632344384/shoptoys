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

<%-- pagination for printed products --%>
<div id="productsPagination">
	<%-- displaying page numbers. The when condition does not display a link for the current page --%>
	<c:set var="filterCategoryParam"
		value="&filterCategory=${filterCategory}" />
	<table id="pagination">
		<tr>
			<%-- displaying previous link except first page --%>
			<c:if test="${currentPage != 1}">
				<td><a class="pagePagin"
					href="<c:url value="/printProducts"/>?page=${currentPage - 1}${!empty filterCategory ? filterCategoryParam : ''}">&lt;</a></td>
			</c:if>

			<c:forEach begin="1" end="${numOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td><a class="pagePagin"
							href="<c:url value="/printProducts"/>?page=${i}${!empty filterCategory ? filterCategoryParam : ''}">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<%-- displaying next link except last page --%>
			<c:if test="${currentPage lt numOfPages}">
				<td><a class="pagePagin"
					href="<c:url value="/printProducts"/>?page=${currentPage + 1}${!empty filterCategory ? filterCategoryParam : ''}">&gt;</a></td>
			</c:if>
		</tr>
	</table>
</div>