<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>${rb['hotel_name']}</title>
    <script type="text/javascript">
    <!--
    function goToOrder(f) {
           document.forms[f].submit();

    }
    function colorOver(rw) {
        document.getElementsByName(rw)[0].style.background = 'aqua';
        document.getElementsByName(rw)[0].style.cursor = 'hand';
    }
    function colorOut(rw) {
        document.getElementsByName(rw)[0].style.background = 'WHITE';
    }
    function backColor(td, status) {
        if (status == 'AWAITING'){
            document.getElementsByName(td)[0].style.background = 'lightPink';
        } else
        if (status == 'ACTIVE'){
            document.getElementsByName(td)[0].style.background = 'Aquamarine';
        } else
        if (status == 'CONFIRMED'){
            document.getElementsByName(td)[0].style.background = 'DarkKhaki';
        } else
        if (status == 'NOT_CLOSED'){
            document.getElementsByName(td)[0].style.background = 'HotPink';
        } else
        if (status == 'OUT_OF_DATE'){
            document.getElementsByName(td)[0].style.background = 'Thistle';
        } else
        {
            document.getElementsByName(td)[0].style.background = 'Gainsboro';
        }
    }

    //-->
    </script>
</head>

<body>
<c:import url="/WEB-INF/jsp/header_logout.jsp"/>

<h3 align="center">${rb['user_title']}</h3>

<div>
    <c:import url="/WEB-INF/jsp/user/menu_user2.jsp"/>
</div>

<h4 align="center">${rb['information_about_orders']}</h4>


<table id="f1" align="center">
    <tr style="font-weight: bold" align="center">
        <td> ${rb['id']}</td>
        <td> ${rb['date_in']}</td>
        <td> ${rb['date_out']}</td>
        <td> ${rb['number_of_clients']}</td>
        <td> ${rb['number_of_rooms']}</td>
        <td> ${rb['order_total_cost']}</td>
        <td> ${rb['status']}</td>
        <td></td>
    </tr>
    <tr>
    </tr>

    <c:forEach var="order" items="${order_list}">
        <tr name="${ order.id }" onclick="goToOrder('f'+'${ order.id }')" onmouseover="colorOver('${ order.id }')" onmouseout="colorOut('${ order.id }')">
            <td>
                <c:out value="${ order.id }"/>
            </td>
            <td align="center" style="border-left: solid black 1px;">
                <c:out value="${ order.dateIn }"/>
            </td>
            <td align="center" style="border-left: solid black 1px;">
                <c:out value="${ order.dateOut }"/>
            </td>
            <td align="center" style="border-left: solid black 1px;">
                <c:out value="${ order.clientsAmount }"/>
            </td>
            <td align="center" style="border-left: solid black 1px;">
                <c:out value="${ order.roomsAmount }"/>
            </td>
            <td align="right" style="border-left: solid black 1px; padding-right: 30px">
                <c:out value="${ order.stringCost }"/>
            </td>
            <td name="td${order.id}" align="center" style="border-left: solid black 1px;">
                <script type="text/javascript">
                    backColor('td'+'${order.id}', '${ order.status }');
                </script>
                ${ order.status }
            </td>
            <td align="center">
                <form id="f22" name="f${ order.id }" action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="all_show_order_detail"/>
                    <input type="hidden" name="last_command" value="all_show_orders_related_to_login"/>
                    <input type="hidden" name="date_from_for_show_orders" value="${date_from_for_show_orders}"/>
                    <input type="hidden" name="date_before_for_show_orders" value="${date_before_for_show_orders}"/>
                    <input type="hidden" name="related_login" value="${ related_login }"/>
                    <input type="hidden" name="order_id_to_show_detail" value="${ order.id }"/>
                </form>
            </td>
        </tr>
        <c:set var="chb_count"/>
    </c:forEach>
</table>
<table id="f1">
    <tr>
        <td align="center">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="usr_user_menu_orders"/>
                <br>
                <input type="submit" value="${rb['return']}"/>
            </form>
        </td>
    </tr>
</table>
</body>
</html>