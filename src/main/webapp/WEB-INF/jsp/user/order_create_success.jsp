<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>My Server Page</title>
</head>


<body>
<c:import url="/WEB-INF/jsp/header_logout.jsp"/>

<h3 align="center">${rb['user_title']}</h3>


<table id="f1">
    <tr>
        <td align="center">
            ${rb['order_create_success']}
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="usr_goto_prepare_order"/>
                <br>
                <input type="submit" value="${rb['back']}">
            </form>
        </td>
    </tr>
</table>

</body>
</html>