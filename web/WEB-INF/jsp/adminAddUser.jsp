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

<div id="addUser">
	<b><fmt:message key="addNewUser" bundle="${lang}" /></b><br> <br>

	<%-- error massage when trying to add user to shop --%>
	<c:if test="${!empty param.error}">
		<div id="addUserError">
			<c:out value="${param.error}" />
			<br> <br>
		</div>
	</c:if>

	<%-- information message when adding user to shop executed without errors --%>
	<c:if test="${!empty param.addUserDone}">
		<div id="addUserDone">
			<c:out value="${param.addUserDone}" />
			<br> <br>
		</div>
	</c:if>

	<div id="formAddUser">
		<%-- form for adding users to shop --%>
		<form action="<c:url value="/admin/addUser"/>" method="post">

			<div id="formAddUserLeft">
				<input type="hidden" name="addUser" value="addUser" /> <input
					type="checkbox" name="userEnabled" value="true" checked="checked">

				<fmt:message key="userEnabled" bundle="${lang}" />
				<br>

				<fmt:message key="login" bundle="${lang}" />
				*<br> <input type="text" name="login" value="" /> <br>

				<fmt:message key="loginpass" bundle="${lang}" />
				*<br> <input type="password" name="loginpass" value="" /> <br>

				<fmt:message key="loginpass" bundle="${lang}" />
				*<br> <input type="password" name="loginpass2" value="" /> <br>

				<fmt:message key="fname" bundle="${lang}" />
				*<br> <input type="text" name="fname"
					value="${!empty pageScope.oldFName ? pageScope.oldFName : ''}" />
				<br>

				<c:out value="${pageScope.oldFName}" />

				<fmt:message key="lname" bundle="${lang}" />
				*<br> <input type="text" name="lname" value="" /> <br>

				<fmt:message key="email" bundle="${lang}" />
				*<br> <input type="text" name="email" value="" /><br>

				<fmt:message key="account" bundle="${lang}" />
				*<br> <input type="text" name="account" value="" /><br>

				<fmt:message key="age" bundle="${lang}" />
				*<br> <select name="age">
					<option value="18" selected>18</option>
					<c:forEach var="i" begin="19" end="60" step="1">
						<option value="${i}"><c:out value="${i}" /></option>
					</c:forEach>
				</select><br>
			</div>
			<div id="formAddUserRight">
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
						<option value="${role.id}"><c:out value="${role.name}" /></option>
					</c:forEach>
				</select> <br>

				<fmt:message key="zip" bundle="${lang}" />
				*<br> <input type="text" name="zip" value="" /> <br>

				<fmt:message key="state" bundle="${lang}" />
				*<br> <input type="text" name="state" value="" /> <br>

				<fmt:message key="city" bundle="${lang}" />
				*<br> <input type="text" name="city" value="" /> <br>

				<fmt:message key="street" bundle="${lang}" />
				*<br> <input type="text" name="street" value="" /> <br>

				<fmt:message key="phone" bundle="${lang}" />
				*<br> <input type="text" name="phone" value="" /> <br> <br>
				<br> <input type="submit" name="addUser"
					value=<fmt:message key="add" bundle="${lang}" />>
			</div>
		</form>
		<%-- action="<c:url value="/admin/addUser"/>" method="post" --%>
	</div>
</div>