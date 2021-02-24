<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>My Server Page</title>
</head>

<style>
#f1 {
    color:blue;
    border-style: solid;
    width: 1000px;
    margin:auto;
    }
</style>

<body>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<h3 align="center">${rb['admin_title']}</h3>
<div>
    <c:import url="/WEB-INF/jsp/admin/menu_admin.jsp"/>
</div>
<form id="f1" align="center" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="admin_menu_users_showall">
    <text align="center">${rb['admin_menu_users_showall']}</text>
    <br>
    <br>
    <input type="submit" value="${rb['submit']}">
</form>
<form id="f1" align="center" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="admin_menu_users_showfilter">
    <text align="center">${rb['admin_menu_users_showfilter']}</text>
    <br>
    <br>
    <input type="submit" value="${rb['submit']}">
</form>

</body>
</html>