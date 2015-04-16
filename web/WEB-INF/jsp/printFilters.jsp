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

<div id="filters">
	<div id="filterCategories">

		<div id="lisCategories">
			<br>
			<p>
				<b><fmt:message key="fil.by.category" bundle="${lang}" />:</b>
			</p>
			<form id="formFilterCategory"
				action="<c:url value="/printProducts"/>">
				<c:forEach items="${categories}" var="category">
					<c:if test="${category.root eq 0}">
						<input type="checkbox" class="categoryEnable" name="categoryId"
							value="${category.id}">
						<c:out value="${category.name}"></c:out>
						<br>
					</c:if>
					<c:if test="${category.root eq 1}">
						<p class="categoryEnable">
							<b><c:out value="${category.name}"></c:out>:</b>
						</p>
					</c:if>

				</c:forEach>
				<br> <input type="submit" id="submitFilterCategory"
					value=<fmt:message key="apply" bundle="${lang}" /> />
			</form>
		</div>
	</div>
</div>