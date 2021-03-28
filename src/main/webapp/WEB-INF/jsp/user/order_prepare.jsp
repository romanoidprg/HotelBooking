<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>My Server Page</title>
    <script type="text/javascript">
    <!--

    function isDatesCorrect (){
	valid = true;

        if (${order.dateIn > order.dateOut})
        {
                alert ("${rb['date_range_incorrect']}");
                valid = false;
        }

        return valid;
    }

    //-->
    </script>
</head>

<body>
<c:import url="/WEB-INF/jsp/header_logout.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<h3 align="center">${rb['user_title']}</h3>
<div>
    <c:import url="/WEB-INF/jsp/user/menu_user2.jsp"/>
</div>

<table id="f1">
    <tr>
        <td align="center">
            <p id="p1">${rb['order_prepare']}</p>
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
                                    <p id="p1">${order.dateIn}</p>
                                </td>
                            </tr>
                        </table>
                        <form id="f_date_in" action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="usr_set_order_date_in"/>
                            ${rb['set_date_in']}: <input required type="date" name="date_in"/>
                            <input id="sk" type="submit" value="${rb['submit']}">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td id="f2">
                        <p id="p2">${rb['date_out']}:</p>
                        <table>
                            <tr>
                                <td id="t4">
                                    <p id="p1">${order.dateOut}</p>
                                </td>
                            </tr>
                        </table>
                        <form id="f_date_out" action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="usr_set_order_date_out"/>
                            ${rb['set_date_out']}: <input required type="date" name="date_out"/>
                            <input id="sk" type="submit" value="${rb['submit']}">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td id="f2">
                        <p id="p2">${rb['clients_in_order']}:</p>
                        <table>
                            <tr>
                                <td>
                                </td>
                                <td align="center">
                                    <c:if test="${not empty order.clients}">
                                        ${rb['change_foodplan']}
                                    </c:if>
                                </td>
                                <td align="center">
                                    <c:if test="${not empty order.clients}">
                                        ${rb['delete_from_order']}
                                    </c:if>
                                </td>
                            </tr>
                            <c:forEach var="entry" items="${order.clients}">
                                <tr>
                                    <td id="t3">
                                        <p id="p1">${entry.key.name} ${entry.key.sName}. (${rb['foodplan']}:
                                            ${entry.value})</p>
                                    </td>
                                    <td id="t5" align="center">
                                        <br>
                                        <form action="${pageContext.request.contextPath}/controller">
                                            <input type="hidden" name="command" value="usr_set_client_foodplan"/>
                                            <input type="hidden" name="client_id" value="${entry.key.id}"/>
                                            <select size="1" name="foodplan">
                                                <option selected value="NONE">NONE</option>
                                                <option value="AI">AI</option>
                                                <option value="FB">FB</option>
                                                <option value="HB">HB</option>
                                                <option value="HBd">HBd</option>
                                                <option value="BB">BB</option>
                                                <option value="BBd">BBd</option>
                                            </select>
                                            <input id="sk" type="submit" value="V">
                                        </form>
                                    </td>
                                    <td id="t5" align="center">
                                        <br>
                                        <form action="${pageContext.request.contextPath}/controller">
                                            <input type="hidden" name="command" value="usr_delete_client_from_order"/>
                                            <input type="hidden" name="client_id" value="${entry.key.id}"/>
                                            <input id="sk" type="submit" value="V">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br>
                        <br>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="usr_goto_add_client_to_order"/>
                            <input id="sk" type="submit" value="${rb['add_client_to_order']}">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td id="f2">
                        <p id="p2">${rb['rooms_in_order']}:</p>
                        <table>
                            <tr>
                                <td>
                                </td>
                                <td align="center">
                                    <c:if test="${not empty order.rooms}">
                                        ${rb['delete_from_order']}
                                    </c:if>
                                </td>
                            </tr>
                            <c:forEach var="entry" items="${order.rooms}">
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
                                        </p>
                                    </td>
                                    <td id="t5" align="center">
                                        <br>
                                        <form action="${pageContext.request.contextPath}/controller" method="post">
                                            <input type="hidden" name="command" value="usr_delete_room_from_order"/>
                                            <input type="hidden" name="room_id" value="${entry.id}"/>
                                            <input type="hidden" name="room_size" value="${entry.size}"/>
                                            <input type="hidden" name="room_type" value="${entry.type}"/>
                                            <input id="sk" type="submit" value="V">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br>
                        <br>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="usr_add_room_to_order"/>
                            ${rb['add_room']}: ${rb['room_size']}
                            <select size="1" name="room_size">
                                <option selected value="ONE_BED">${rb['one_bad']}</option>
                                <option value="TWO_BED">${rb['two_bad']}</option>
                                <option value="THREE_BED">${rb['three_bad']}</option>
                                <option value="FAMILY">${rb['family']}</option>
                            </select>
                            ${rb['room_type']}
                            <select size="1" name="room_type">
                                <option selected value="ECONOM">${rb['econom']}</option>
                                <option value="STANDARD">${rb['standard']}</option>
                                <option value="LUX">${rb['lux']}</option>
                            </select>
                            <input id="sk" type="submit" value="${rb['add_room_to_order']}">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <p id="p1">${rb['order_cost']}: ${order.stringCost}</p>
                        <br>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td align="center">
            <c:if test="${ (not empty order.rooms ) && (not empty order.clients)}">
                <form id="f_submit" action="${pageContext.request.contextPath}/controller"
                      onsubmit="return isDatesCorrect()" method="post">
                    <input type="hidden" name="command" value="usr_send_order_for_submit"/>
                    <input id="sk" type="submit" value="${rb['send_order_for_submit']}">
                </form>
            </c:if>
        </td>
    </tr>

</table>
</body>
</html>