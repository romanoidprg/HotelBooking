<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>My Server Page</title>
    <script type="text/javascript">
    <!--

    function isRoomsNotRepeat (){
	valid = true;
	let list = new Set();
        for (i = 0; document.getElementsByName('rooms_id').length-1; i++) {
            index = document.getElementsByName('rooms_id')[i].options.selectedIndex;
            if (list.has(document.getElementsByName('rooms_id')[i].options[index].value)) {
                alert ("${rb['rooms_repeat']}");
                valid = false;
                break;
            } else {
                list.add(document.getElementsByName('rooms_id')[i].options[index].value);
            }
        }
        return valid;
    }

    //-->
    </script>
</head>

<body>
<c:import url="/WEB-INF/jsp/header_logout.jsp"/>

<h3 align="center">${rb['admin_title']}</h3>
<div>
    <c:import url="/WEB-INF/jsp/admin/menu_admin1.jsp"/>
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
                            <tr>
                                <td></td>
                                <c:if test="${(order_detail.status == 'AWAITING') && (not empty order_detail.rooms) }">
                                    <td>
                                        ${rb['select_room']}
                                    </td>
                                </c:if>
                            </tr>
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
                                    <c:if test="${order_detail.status == 'AWAITING'}">
                                        <td align="center">
                                            <select form="f_conf_await" name="rooms_id">
                                                <c:forEach var="room" items="${empty_rooms}">
                                                    <c:if test="${room.type==entry.type && room.size==entry.size}">
                                                        <option value="${room.id}">${room.id}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </c:if>
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
            </table>
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
                            <input type="hidden" name="related_client_id" value="${ related_client_id }"/>
                            <input id="sk" type="submit" value="${rb['return']}">
                        </form>
                    </td>
                    <c:if test="${order_detail.status == 'AWAITING'}">
                        <td>
                            <form id="f_conf_await" action="${pageContext.request.contextPath}/controller"
                                  onsubmit="return isRoomsNotRepeat()" method="post">
                                <input type="hidden" name="command" value="adm_confirm_awaiting_order"/>
                                <input type="hidden" name="order_id" value="${order_detail.id}"/>
                                <input style="background-color: Aquamarine;" type="submit"
                                       value="${rb['confirm_order']}">
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="adm_cancel_awaiting_order"/>
                                <input type="hidden" name="order_id" value="${order_detail.id}"/>
                                <input style="background-color: lightpink;" type="submit" value="${rb['cancel_order']}">
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${order_detail.status == 'CONFIRMED'}">
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="adm_activate_confirmed_order"/>
                                <input type="hidden" name="order_id" value="${order_detail.id}"/>
                                <input style="background-color: Aquamarine;" type="submit"
                                       value="${rb['activate_order']}">
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="adm_cancel_awaiting_order"/>
                                <input type="hidden" name="order_id" value="${order_detail.id}"/>
                                <input style="background-color: lightpink;" type="submit" value="${rb['cancel_order']}">
                            </form>
                        </td>

                    </c:if>
                    <c:if test="${order_detail.status == 'ACTIVE'}">
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="adm_done_active_order"/>
                                <input type="hidden" name="order_id" value="${order_detail.id}"/>
                                <input style="background-color: Aquamarine;" type="submit"
                                       value="${rb['done_order']}">
                            </form>
                        </td>
                    </c:if>
                </tr>
            </table>
    </tr>

</table>
</body>
</html>