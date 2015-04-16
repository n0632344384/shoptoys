<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SHOP</title>
<link type="text/css" rel="stylesheet" href="resources/css/index.css" />
<script type='text/javascript' src='resources/js/jquery-2.1.3.min.js'></script>
<script type='text/javascript' src='resources/js/filterCategory.js'></script>
<script type='text/javascript' src='resources/js/basket.js'></script>
</head>
<body>
	<%-- variable to print filter by category on main page --%>
	<c:set var="filterCategory" value="true"></c:set>
	<div id="header">
		<%@ include file="header.jsp"%>
	</div>
	<div id="content">
		<%@ include file="sidebarLeft.jsp"%>
		<%@ include file="center.jsp"%>
		<%@ include file="sidebarRight.jsp"%>
	</div>
</body>
</html>