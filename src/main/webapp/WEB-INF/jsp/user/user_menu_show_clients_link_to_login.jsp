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

<div>
    <c:import url="/WEB-INF/jsp/user/menu_user1.jsp"/>
</div>

<h4 align="center">${rb['information_about_clients']}</h4>


<table id="f1" align="center">
    <tr style="font-weight: bold">
        <td> ${rb['id']}</td>
        <td>| ${rb['name']}</td>
        <td>| ${rb['sname']}</td>
        <td>| ${rb['email']}</td>
        <td>| ${rb['phone']}</td>
        <td>| ${rb['bday']}</td>
        <td>| ${rb['sex']}</td>
        <td>| ${rb['country']}</td>
        <td>| ${rb['address']}</td>
        <td>|</td>
        <td>${rb['delete']}</td>
    </tr>
    <tr>
    </tr>

    <c:forEach var="client" items="${client_list}">
        <tr>
            <td>
                <c:out value="${ client.id }"/>
            </td>
            <td>|
                <c:out value="${ client.name }"/>
            </td>
            <td>|
                <c:out value="${ client.sName }"/>
            </td>
            <td>|
                <c:out value="${ client.eMail }"/>
            </td>
            <td>|
                <c:out value="${ client.phone }"/>
            </td>
            <td>|
                <c:out value="${ client.birthDay }"/>
            </td>
            <td>|
                <c:out value="${ client.sex }"/>
            </td>
            <td>|
                <c:out value="${ client.country }"/>
            </td>
            <td>|
                <c:out value="${ client.address }"/>
            </td>
            <td>|</td>
            <td align="center">
                <form id="f22" action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="usr_delete_client"/>
                    <input type="hidden" name="id_client_to_delete" value="${ client.id }"/>
                    <input id="i1" type="submit" value="V"/>
                </form>
            </td>
            <td align="center">
            </td>
            <td align="center">
            </td>
        </tr>
        <c:set var="chb_count"/>
    </c:forEach>
</table>
<table id="f1">
    <tr>
        <td align="center">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="usr_user_menu_clients"/>
                <br>
                <input type="submit" value="${rb['return']}"/>
            </form>
        </td>
    </tr>
</table>

</body>
</html>