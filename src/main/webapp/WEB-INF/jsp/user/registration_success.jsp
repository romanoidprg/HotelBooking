<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>HotelBooking</title>
</head>
<body>
<table style="width: 100%;border-bottom: 1px solid;">
    <tr>
        <td align="left">
            <c:import url="/WEB-INF/jsp/header_logout.jsp"/>
        </td>
        <td align="right">
        </td>
    </tr>
</table>

<h1 align="center"> ${rb['hotel_name']} </h1>
<br>
<h1 align="center">${rb['registration_success']}</h1>

<form align="center" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="usr_login">
    <input type="submit" value="${rb['return_to_login']}">
</form>

</body>
</html>