<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" isELIgnored="false"%>

<html>

<head>
    <title>HotelBooking</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<h1 align="center"> ${rb['hotel_name']} </h1>
<br>
<h1 align="center"> ${rb['signin']}</h1>
<br>
<h4 align="center" color="red"> ${rb['incorrect_login']}</h4>
<form align="center" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="all_login">

    <text align="center">${rb['login']}</text>
    <br>
    <input autofocus type="text" name="login">
    <br>
    <text align="center">${rb['password']}</text>
    <br>
    <input type="password" name="password">
    <br>
    <input type="submit" value="${rb['submit']}">
</form>

<form align="center" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="all_redirect_to_registration">
    <text align="center">${rb['registration_welcome']}</text>
    <br>
    <input type="submit" value="${rb['register']}">
</form>

</body>
</html>