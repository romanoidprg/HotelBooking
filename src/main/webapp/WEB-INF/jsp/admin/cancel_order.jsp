<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>My Server Page</title>
</head>


<body>
<c:import url="/WEB-INF/jsp/header_logout.jsp"/>

<h3 align="center">${rb['admin_title']}</h3>


<table id="f1">
    <tr>
        <td align="center">
            <c:if test="${success == true}">
                ${rb['order_cancel_success']}
            </c:if>
            <c:if test="${success != true}">
                ${rb['order_cancel_not_success']}
            </c:if>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="${last_show_orders_command}"/>
                <input type="hidden" name="new_orders_list" value="false"/>
                <br>
                <input type="submit" value="${rb['back']}">
            </form>
        </td>
    </tr>
</table>

</body>
</html>