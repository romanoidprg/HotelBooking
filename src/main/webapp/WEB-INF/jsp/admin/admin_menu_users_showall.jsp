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
#s1 {
font-weight: bold;
    }
#f2 { display: inline;
}

</style>

<body>

<h3 align="center">${rb['admin_title']}</h3>

<div>
    <c:import url="/WEB-INF/jsp/admin/menu_admin.jsp"/>
</div>

<form id="f1" align="center" action="${pageContext.request.contextPath}/controller" method="post">
    <table>
        <tr id="s1">
            <td> ${rb['id']}</td>
            <td>| ${rb['login']}</td>
            <td>| ${rb['password']}</td>
            <td>| ${rb['isadmin']}</td>
            <td>|</td>
            <td>${rb['change_role']}</td>
        </tr>
        <tr>
        </tr>

        <c:forEach var="login" items="${login_list}" varStatus="status">
            <tr>
                <td>
                    <c:out value="${ login.id }"/>
                </td>
                <td>|
                    <c:out value="${ login.login }"/>
                </td>
                <td>|
                    <c:out value="${ login.password }"/>
                </td>
                <td>|
                    <c:out value="${ login.admin }"/>
                </td>
                <td>|</td>
                <td align="center">
                    <form id="f2" action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="set_login_role"/>
                        <input type="hidden" name="login_id_for_set_role" value="${login.id}"/>
                        <input type="submit" value="V"/>
                    </form>
                </td>
            </tr>
            <c:set var="chb_count"/>
        </c:forEach>
    </table>
</form>


</body>
</html>