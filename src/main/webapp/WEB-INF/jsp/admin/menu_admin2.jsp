<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>My Server Page</title>
</head>

<body>
<c:import url="/WEB-INF/jsp/header_logout.jsp"/>

<table style="border-style: solid; width: 1000px; height: 25px; margin:auto;">
    <tr>
        <td align="center" style="width: 33%;">
            <a href="${pageContext.request.contextPath}/controller?command=adm_admin_menu_users_select">${rb['admin_menu_users']}</a>
        </td>
        <td align="center" style="background-color: lightgray;">
            <a href="${pageContext.request.contextPath}/controller?command=adm_admin_menu_orders_select">${rb['admin_menu_orders']}</a>
        </td>
        <td align="center" style="width: 33%;">
            <a href="${pageContext.request.contextPath}/controller?command=all_menu_info">${rb['menu_info']}</a>
        </td>
    </tr>
</table>
<br>

</body>
</html>