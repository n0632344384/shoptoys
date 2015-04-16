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
	<%-- deny register for users who already logged in --%>
	<c:if test="${empty sessionScope.login}">
		<div id="register">
			<c:choose>
				<%-- information message when registration executed without errors --%>
				<c:when test="${!empty registrationDone}">
					<div id="registrationDone">
						<c:out value="${registrationDone}" />
					</div>
				</c:when>

				<c:otherwise>
					<%-- form for registering users in shop --%>
					<form action="<c:url value="/register"/>" method="post">
						<input type="hidden" name="register" value="register" />

						<%-- error massage when trying to register --%>
						<c:if test="${!empty error}">
							<div id="registerError">
								<c:out value="${error}" />
							</div>
						</c:if>

						<br> <br>
						<div id="registerHeader">
							<b><fmt:message key="registration" bundle="${lang}" /></b>
						</div>

						<div id="registerLeft">
							<fmt:message key="login" bundle="${lang}" />
							*<br> <input type="text" id="login" name="login" /><br>
							<fmt:message key="loginpass" bundle="${lang}" />
							*<br> <input type="password" id="pass1" name="pass1" /><br>
							<fmt:message key="loginpass" bundle="${lang}" />
							*<br> <input type="password" id="pass2" name="pass2" /><br>
							<fmt:message key="fname" bundle="${lang}" />
							*<br> <input type="text" id="fname" name="fname" /><br>
							<fmt:message key="lname" bundle="${lang}" />
							*<br> <input type="text" id="lname" name="lname" /><br>
							<fmt:message key="email" bundle="${lang}" />
							*<br> <input type="text" id="email" name="email" /><br>
							<fmt:message key="account" bundle="${lang}" />
							*<br> <input type="text" id="account" name="account" />
						</div>

						<div id="registerRight">
							<fmt:message key="age" bundle="${lang}" />
							*<br> <select id="age" name="age">
								<option value="18" selected>18</option>
								<c:forEach var="i" begin="19" end="60" step="1">
									<option value="${i}"><c:out value="${i}" /></option>
								</c:forEach>
							</select><br>
							<fmt:message key="gender" bundle="${lang}" />
							*<br> <input type="radio" id="gender" name="gender" tabindex=""
								value="true" checked="checked">
							<fmt:message key="genderM" bundle="${lang}" />
							<input type="radio" id="gender" name="gender" tabindex="" value="false">
							<fmt:message key="genderF" bundle="${lang}" />
							<br>
							<fmt:message key="zip" bundle="${lang}" />
							*<br> <input type="text" id="zip" name="zip" /><br>
							<fmt:message key="state" bundle="${lang}" />
							*<br> <input type="text" id="state" name="state" /><br>
							<fmt:message key="city" bundle="${lang}" />
							*<br> <input type="text" id="city" name="city" /><br>
							<fmt:message key="street" bundle="${lang}" />
							*<br> <input type="text" id="street" name="street" /><br>
							<fmt:message key="phone" bundle="${lang}" />
							*<br> <input type="text" id="phone" name="phone" />
						</div>

						<div id="registerSubmit">
							<input type="submit" id="submitRegister" name="register"
								value=<fmt:message key="register" bundle="${lang}" /> disabled="disabled">
						</div>
					</form>
					<%-- action="<c:url value="/register"/>" method="post" --%>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
	<%-- test="${empty sessionScope.login} --%>
</div>