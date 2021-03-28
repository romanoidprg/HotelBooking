<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>HotelBooking</title>
</head>
<body>
<c:import url="/WEB-INF/jsp/header_logout.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<h1 align="center"> ${rb['hotel_name']} </h1>
<br>
<h4 align="center">${rb['registration_not_success']}</h4>

<form align="center" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="all_redirect_to_registration">
    <input type="submit" value="${rb['return_to_registration']}">
</form>
<form align="center" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="ALL_ENTER_IF_SESSION">
    <input type="submit" value="${rb['return_to_start']}">
</form>


</body>
</html>