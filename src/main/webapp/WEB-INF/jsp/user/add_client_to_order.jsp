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
    <c:import url="/WEB-INF/jsp/user/menu_user2.jsp"/>
</div>

<h4 align="center">
    <c:if test="${not empty clients_to_add_to_order}">
        ${rb['clients_not_in_order']}
    </c:if>
    <c:if test="${empty clients_to_add_to_order}">
        ${rb['all_clients_added_to_order']}
    </c:if>
</h4>
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
        <td>${rb['select']}</td>
    </tr>
    <tr>
    </tr>

    <c:forEach var="client" items="${clients_to_add_to_order}">
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
                <input form="form_add" type="checkbox" name="client_ids_to_add_to_order" value="${ client.id }"/>
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
            <form id="form_add" action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="usr_add_clients_to_order"/>
                <br>
                <c:if test="${not empty clients_to_add_to_order}">
                    <input type="submit" value="${rb['add']}"/>
                </c:if>
                <input form="form_back" type="submit" value="${rb['back']}"/>
            </form>
            <form id="form_back" action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="usr_goto_prepare_order"/>
            </form>
        </td>
    </tr>
</table>
</body>
</html>