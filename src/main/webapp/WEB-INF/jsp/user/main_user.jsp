<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>My Server Page</title>
</head>
<body>
<table style="width: 100%;border-bottom: 1px solid;">
    <tr>
        <td align="left">
            <c:import url="/WEB-INF/jsp/header_logout.jsp"/>
        </td>
        <td align="right">
            <c:import url="/WEB-INF/jsp/header.jsp"/>
        </td>
    </tr>
</table>

<h3 align="center">${rb['user_title']}</h3>
 <c:import url="/WEB-INF/jsp/user/menu_user.jsp"/>
</body>
</html>