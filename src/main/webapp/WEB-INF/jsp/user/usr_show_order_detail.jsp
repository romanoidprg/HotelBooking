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

<table id="f1">
    <tr>
        <td align="center">
            <p id="p1">${rb['order_detail']} ${order_detail.id}</p>
        </td>
    </tr>
    <tr>
        <td>
            <table id="f3">
                <tr>
                    <td id="f2">
                        <p id="p2">${rb['date_in']}:</p>
                        <table>
                            <tr>
                                <td id="t4">
                                    <p id="p1">${order_detail.dateIn}</p>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td id="f2">
                        <p id="p2">${rb['date_out']}:</p>
                        <table>
                            <tr>
                                <td id="t4">
                                    <p id="p1">${order_detail.dateOut}</p>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td id="f2">
                        <p id="p2">${rb['clients_in_order']}:</p>
                        <table>
                            <c:forEach var="entry" items="${order_detail.clients}">
                                <tr>
                                    <td id="t3">
                                        <p id="p1">${entry.key.name} ${entry.key.sName}. (${rb['foodplan']}:
                                            ${entry.value})</p>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td id="f2">
                        <p id="p2">${rb['rooms_in_order']}:</p>
                        <table>
                            <c:forEach var="entry" items="${order_detail.rooms}">
                                <tr>
                                    <td id="t3">
                                        <p id="p1">
                                            <c:choose>
                                                <c:when test="${entry.size == 'ONE_BED'}">
                                                    ${rb['one_bad']}
                                                </c:when>
                                                <c:when test="${entry.size == 'TWO_BED'}">
                                                    ${rb['two_bad']}
                                                </c:when>
                                                <c:when test="${entry.size == 'THREE_BED'}">
                                                    ${rb['three_bad']}
                                                </c:when>
                                                <c:when test="${entry.size == 'FAMILY'}">
                                                    ${rb['family']}
                                                </c:when>
                                            </c:choose>
                                            .
                                            <c:choose>
                                                <c:when test="${entry.type == 'ECONOM'}">
                                                    ${rb['econom']}
                                                </c:when>
                                                <c:when test="${entry.type == 'STANDARD'}">
                                                    ${rb['standard']}
                                                </c:when>
                                                <c:when test="${entry.type == 'LUX'}">
                                                    ${rb['lux']}
                                                </c:when>
                                            </c:choose>
                                            <c:if test="${entry.id>0}">
                                                . № ${entry.id}.
                                            </c:if>
                                        </p>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td id="f2">
                        <p id="p2">${rb['status']}:</p>
                        <table>
                            <tr>
                                <td id="t3">
                                    <p id="p1">${order_detail.status})</p>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <p id="p1">${rb['order_cost']}: ${order_detail.stringCost}</p>
                        <br>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <table>
                            <tr>
                                <td>
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="${last_show_orders_command}"/>
                                        <input type="hidden" name="new_orders_list" value="false"/>
                                        <input id="sk" type="submit" value="${rb['return']}">
                                    </form>
                                </td>
                                <td>
                                    <c:if test="${order_detail.status == 'CONFIRMED'}">
                                        <form action="${pageContext.request.contextPath}/controller"
                                              method="post" target="_blank">
                                            <input type="hidden" name="command" value="usr_show_invoice"/>
                                            <input type="hidden" name="order_id" value="${order_detail.id}"/>
                                            <input id="sk" type="submit" value="${rb['invoice']}">
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

            </table>
        </td>
    </tr>

</table>

</body>
</html>