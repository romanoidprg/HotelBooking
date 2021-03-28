<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>My Server Page</title>
</head>
<body>
<table style="width:800px; margin:auto; border:solid;">
    <tr>
        <td align="center">
            ${rb['hotel_name']}
        </td>
    </tr>
    <tr>
        <td>
            ${rb['invoice']}
        </td>
    </tr>
</table>
<br>
<table style="width:800px; margin:auto; border:dotted;">
    <tr>
        <td>
            ${rb['payment_data']}
        </td>
        <td>
            BELARUSBANK IBAN 0000 1111 2222 3333 4444
        </td>
    </tr>
    <tr>
        <td>
            ${rb['order_number']}
        </td>
        <td>
            ${order_detail.id}
        </td>
    </tr>
    <tr>
        <td>
            ${rb['date_in']}
        </td>
        <td>
            ${order_detail.dateIn}
        </td>
    </tr>
    <tr>
        <td>
            ${rb['date_out']}
        </td>
        <td>
            ${order_detail.dateOut}
        </td>
    </tr>
    <tr>
        <td style="border-bottom: dotted 1px;">
            <br>
            ${rb['clients']}:
        </td>
        <td></td>
        <td style="border-bottom: dotted 1px;">${rb['cost_per_day']}:</td>
    </tr>
    <c:forEach var="client" items="${order_detail.clients}">
        <tr>
            <td>
                ${client.key.name} ${client.key.sName}. (${rb['foodplan']}: ${client.value})
            </td>
            <td></td>
            <td>${client.value.stringCostPerDay}</td>
        </tr>
    </c:forEach>
    <tr>
        <td style="border-bottom: dotted 1px;">
            <br>
            ${rb['rooms']}:
        </td>
        <td></td>
        <td style="border-bottom: dotted 1px;"></td>
    </tr>
    <c:forEach var="room" items="${order_detail.rooms}">
        <tr>
            <td>
                №.${room.id}. ${room.size}. ${room.type}
            </td>
            <td></td>
            <td>${room.stringCost}</td>
        </tr>
    </c:forEach>
    <tr>
        <td style="border-bottom: dotted 1px;border-top: dotted 1px;"> <br> ${rb['order_cost_per_day']}</td>
        <td></td>
        <td style="border-bottom: dotted 1px;border-top: dotted 1px;"> <br> ${order_detail.stringCostPerDay}</td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td> <br> X</td>
    </tr>
    <tr>
        <td>
            ${rb['days']}
        </td>
        <td></td>
        <td>
            <br>
            ${order_detail.days}
        </td>
    </tr>
    <tr>
        <td>
            <br>
            <br>
            ${rb['total_cost']}</td>
        <td style="font-weight: bold;">
            <br>
            <br>
            ${order_detail.stringCost}
        </td>
        <td></td>
    </tr>
</table>
<br>
<table style="width:800px; margin:auto; border:dotted;">
    <tr>
        <td>
            ${rb['invoice_info']}
        </td>
    </tr>
</table>

</body>
</html>