<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>My Server Page</title>
</head>

<style>
#f1 {
    border: solid blue;
    width: 1000px;
    margin:auto;
    }


</style>

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

<h3 align="center">${rb['user_title']}</h3>


<table id="f1">
    <tr>
        <td align="center">
            ${rb['client_create_success']}
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="usr_user_menu_clients"/>
                <br>
                <input type="submit" value="${rb['back']}">
            </form>
        </td>
    </tr>
</table>

</body>
</html>