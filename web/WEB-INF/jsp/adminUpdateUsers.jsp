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

<div id="tableProd">
	<%-- button to add new user --%>
	<b><fmt:message key="addNewUser" bundle="${lang}" /></b>
	<form action="<c:url value="/admin?task=addUser"/>" method="post">
		<input type="submit" name="add"
			value=<fmt:message key="add" bundle="${lang}" />>
	</form>
	<br>

	<%-- error massage when trying to update user to existed user --%>
	<c:if test="${!empty param.error}">
		<div id="updateUserError">
			<c:out value="${param.error}" />
			<br> <br>
		</div>
	</c:if>

	<%-- information message when product removed without errors --%>
	<c:if test="${!empty param.delUserDone}">
		<div id="delUserDone">
			<c:out value="${param.delUserDone}" />
			<br>
		</div>
	</c:if>

	<%-- information message when product updated without errors --%>
	<c:if test="${!empty param.updateUserDone}">
		<div id="updateUserDone">
			<c:out value="${param.updateUserDone}" />
			<br>
		</div>
	</c:if>

	<%-- table of users --%>
	<b><fmt:message key="updateUsers" bundle="${lang}" /></b><br>

	<div id="tableUsers">
		<c:forEach items="${users}" var="user">
			<div id="tableUsersRow">
				<%-- form for updating and removing users --%>
				<form action="<c:url value="/admin/updateUser"/>" method="post">
					<div id="tableUsersLeft">
					<%--value="${user.enabled eq '1' ? 'true' : 'false'}"--%>
						<input type="hidden" name="paginationCurPage"
							value="${param.page}"> <input type="checkbox"
							name="userEnabled"
							value="true"
							${user.enabled eq '1' ? 'checked="checked"' : ''}>
						<c:choose>
							<c:when test="${user.enabled eq '1'}">
								<fmt:message key="userEnabled" bundle="${lang}" />
							</c:when>
							<c:otherwise>
								<fmt:message key="userDisabled" bundle="${lang}" />
							</c:otherwise>
						</c:choose>

						<br>

						<fmt:message key="userId" bundle="${lang}" />
						<br> <input type="text" name="userId" value="${user.id}"
							readonly /> <br> <input type="hidden" name="loginOrig"
							value="${user.login}" />

						<fmt:message key="login" bundle="${lang}" />
						*<br> <input type="text" name="login" value="${user.login}" />
						<br>

						<fmt:message key="loginpass" bundle="${lang}" />
						*<br> <input type="text" name="loginpass"
							value="${user.password}" /> <br>

						<fmt:message key="fname" bundle="${lang}" />
						*<br> <input type="text" name="fname"
							value="${user.firstName}" /> <br>

						<fmt:message key="lname" bundle="${lang}" />
						*<br> <input type="text" name="lname"
							value="${user.lastName}" /> <br>

						<fmt:message key="email" bundle="${lang}" />
						*<br> <input type="text" name="email" value="${user.email}" /><br>

						<fmt:message key="account" bundle="${lang}" />
						*<br> <input type="text" name="account"
							value="${user.account}" /><br>

						<fmt:message key="age" bundle="${lang}" />
						*<br> <select name="age">
							<c:forEach var="i" begin="18" end="60" step="1">
								<option value="${i}" ${user.age eq i ? 'selected' : ''}><c:out
										value="${i}" /></option>
							</c:forEach>
						</select><br>
					</div>
					<div id="tableUsersRight">
						<fmt:message key="gender" bundle="${lang}" />
						*<br>
						<c:choose>
							<c:when test="${user.gender}">
								<input type="radio" name="gender" tabindex="" value="true"
									checked="checked">
								<fmt:message key="genderM" bundle="${lang}" />
								<input type="radio" name="gender" tabindex="" value="false">
								<fmt:message key="genderF" bundle="${lang}" />
							</c:when>
							<c:otherwise>
								<input type="radio" name="gender" tabindex="" value="true">
								<fmt:message key="genderM" bundle="${lang}" />
								<input type="radio" name="gender" tabindex="" value="false"
									checked="checked">
								<fmt:message key="genderF" bundle="${lang}" />
							</c:otherwise>
						</c:choose>
						<br>

						<fmt:message key="role" bundle="${lang}" />
						*<br> <select name="userRole">
							<c:forEach items="${roles}" var="role">
								<option value="${role.id}"
									${user.role.id eq role.id ? 'selected' : ''}>
									<c:out value="${role.name}" /></option>
							</c:forEach>
						</select> <br>

						<fmt:message key="addressId" bundle="${lang}" />
						<br> <input type="text" name="addressId"
							value="${user.address.id}" readonly /> <br>

						<fmt:message key="zip" bundle="${lang}" />
						*<br> <input type="text" name="zip"
							value="${user.address.zip}" /> <br>

						<fmt:message key="state" bundle="${lang}" />
						*<br> <input type="text" name="state"
							value="${user.address.state}" /> <br>

						<fmt:message key="city" bundle="${lang}" />
						*<br> <input type="text" name="city"
							value="${user.address.city}" /> <br>

						<fmt:message key="street" bundle="${lang}" />
						*<br> <input type="text" name="street"
							value="${user.address.street}" /> <br>

						<fmt:message key="phone" bundle="${lang}" />
						*<br> <input type="text" name="phone"
							value="${user.address.phone}" /> <br> <br>
					</div>

					<input id="tableUsersRowUpdate" type="submit" name="update"
						value=<fmt:message key="update" bundle="${lang}" />> <br>
				</form>
				<%-- action="<c:url value="/admin/updateUser"/>" method="post" --%>
				<form id="tableUsersRowDelete"
					action="<c:url value="/admin/delUser"/>" method="post">
					<input type="hidden" name="userId" value="${user.id}" /> <input
						type="submit" name="delete"
						value=<fmt:message key="delete" bundle="${lang}" />>
				</form>

			</div>
		</c:forEach>
		<%-- items="${users}" var="user" --%>


		<%-- pagination for printed users --%>
		<div id="usersPagination">
			<%-- displaying page numbers. The when condition does not display a link for the current page --%>
			<table id="pagination">
				<tr>
					<%-- displaying previous link except first page --%>
					<c:if test="${currentPage != 1}">
						<td><a href="?task=users&page=${currentPage - 1}">&lt;</a></td>
					</c:if>

					<c:forEach begin="1" end="${numOfPages}" var="i">
						<c:choose>
							<c:when test="${currentPage eq i}">
								<td>${i}</td>
							</c:when>
							<c:otherwise>
								<td><a href="?task=users&page=${i}">${i}</a></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<%-- displaying next link except last page --%>
					<c:if test="${currentPage lt numOfPages}">
						<td><a href="?task=users&page=${currentPage + 1}">&gt;</a></td>
					</c:if>
				</tr>
			</table>
		</div>

	</div>
</div>