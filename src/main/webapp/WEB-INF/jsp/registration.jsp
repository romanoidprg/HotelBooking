<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>HotelBooking</title>
</head>
<body>
<c:import url="header.jsp"/>

<h1 align="center"> ${rb['hotel_name']} </h1>
<br>
<h1 align="center">${rb['registration']}</h1>

<form align="center" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="all_registration">

    <text align="center">${rb['login']}</text>
    <br>
    <input autofocus type="text" name="login">
    <br>
    <text align="center">${rb['password']}</text>
    <br>
    <input type="password" name="password">
    <br>
    <input type="submit" value="${rb['register']}">
</form>

</body>
</html>