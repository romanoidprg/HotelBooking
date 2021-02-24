<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>My Server Page</title>
</head>
<style>
    #admin_menu {
    border-style: solid;
    width: 1000px; height: 25px;
    margin:auto;
}
</style>

<body>
<nav id="admin_menu" align="center">
    <a  href="${pageContext.request.contextPath}/controller?command=admin_menu_users_select">${rb['admin_menu_users']}</a>|
    <a  href="${pageContext.request.contextPath}/controller?command=admin_menu_orders_select">${rb['admin_menu_orders']}</a>|
    <a  href="${pageContext.request.contextPath}/controller?command=admin_menu_rooms_select">${rb['admin_menu_rooms']}</a>|
    <a  href="${pageContext.request.contextPath}/controller?command=admin_menu_prices_select">${rb['admin_menu_prices']}</a>
</nav>
<br>

</body>
</html>