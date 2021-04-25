<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>My Server Page</title>
</head>

<body>
<table id="f1">
    <tr>
        <td align="center" style="font-weight: bold;">
            ${rb['info_about']}
        </td>
    </tr>
</table>
<br>
<table id="f1">
    <tr>
        <td align="center" style="border: solid 1px gray;font-weight: bold;width: 200px;">
            ${rb['room_size']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;">
            ${rb['place_count']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;width: 200px;">
            ${rb['price_per_day']}
        </td>
    </tr>
    <c:forEach var="size" items="${room_sizes}">
        <ctg:enumFields var="enumObj" enumName="${size}" enumClass="RoomSize">
            <tr>
                <td style="border: solid 1px gray;">
                    ${enumObj}
                </td>
                <td align="center" style="border: solid 1px gray;">
                    ${enumObj.placeCount}
                </td>
                <td align="center" style="border: solid 1px gray;">
                    ${enumObj.stringCostPerDay}
                </td>
            </tr>
        </ctg:enumFields>

    </c:forEach>
</table>

<br>

<table id="f1">
    <tr>
        <td align="center" style="border: solid 1px gray;font-weight: bold; width: 200px;">
            ${rb['room_type']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;">
            ${rb['tv']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;">
            ${rb['phone']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;">
            ${rb['fridge']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;">
            ${rb['safe']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold; width: 200px;">
            ${rb['price_factor']}
        </td>
    </tr>
    <c:forEach var="size" items="${room_types}">
        <ctg:enumFields var="enumObj" enumName="${size}" enumClass="RoomType">
            <tr>
                <td style="border: solid 1px gray;">
                    ${enumObj}
                </td>
                <td align="center" style="border: solid 1px gray;">
                    <c:if test="${enumObj.tv}">
                        V
                    </c:if>
                    <c:if test="${not enumObj.tv}">
                        -
                    </c:if>
                </td>
                <td align="center" style="border: solid 1px gray;">
                    <c:if test="${enumObj.phone}">
                        V
                    </c:if>
                    <c:if test="${not enumObj.phone}">
                        -
                    </c:if>
                </td>
                <td align="center" style="border: solid 1px gray;">
                    <c:if test="${enumObj.fridge}">
                        V
                    </c:if>
                    <c:if test="${not enumObj.fridge}">
                        -
                    </c:if>
                </td>
                <td align="center" style="border: solid 1px gray;">
                    <c:if test="${enumObj.safe}">
                        V
                    </c:if>
                    <c:if test="${not enumObj.safe}">
                        -
                    </c:if>
                </td>
                <td align="center" style="border: solid 1px gray;">
                    ${enumObj.costFactor}
                </td>

            </tr>
        </ctg:enumFields>

    </c:forEach>
</table>

<br>

<table id="f1">
    <tr>
        <td align="center" style="border: solid 1px gray;font-weight: bold; width: 200px;">
            ${rb['food_plan']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;">
            ${rb['breakfast']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;">
            ${rb['dinner']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;">
            ${rb['supper']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;">
            ${rb['drinks']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold; width: 200px;">
            ${rb['price_per_day']}
        </td>
    </tr>
    <c:forEach var="plan" items="${food_plans}">
        <ctg:enumFields var="enumObj" enumName="${plan}" enumClass="FoodPlan">
            <tr>
                <td style="border: solid 1px gray;">
                    ${enumObj}
                </td>
                <td align="center" style="border: solid 1px gray;">
                    <c:if test="${enumObj.breakfast}">
                        V
                    </c:if>
                    <c:if test="${not enumObj.breakfast}">
                        -
                    </c:if>
                </td>
                <td align="center" style="border: solid 1px gray;">
                    <c:if test="${enumObj.dinner}">
                        V
                    </c:if>
                    <c:if test="${not enumObj.dinner}">
                        -
                    </c:if>
                </td>
                <td align="center" style="border: solid 1px gray;">
                    <c:if test="${enumObj.supper}">
                        V
                    </c:if>
                    <c:if test="${not enumObj.supper}">
                        -
                    </c:if>
                </td>
                <td align="center" style="border: solid 1px gray;">
                    <c:if test="${enumObj.drinks}">
                        V
                    </c:if>
                    <c:if test="${not enumObj.drinks}">
                        -
                    </c:if>
                </td>
                <td align="center" style="border: solid 1px gray;">
                    ${enumObj.stringCostPerDay}
                </td>

            </tr>
        </ctg:enumFields>

    </c:forEach>
</table>

<br>

<table id="f1">
    <tr>
        <td align="center" style="border: solid 1px gray;font-weight: bold; width: 200px;">
            ${rb['order_status']}
        </td>
        <td align="center" style="border: solid 1px gray;font-weight: bold;">
            ${rb['order_status_info']}
        </td>
    </tr>
    <c:forEach var="status" items="${statuses}">
        <tr>
            <c:if test="${status == 'AWAITING'}">
                <td align="center" style="border: solid 1px gray; background-color: lightPink;">
                    ${status}
                </td>
                <td style="border: solid 1px gray;">
                    ${rb['awaiting_st']}
                </td>
            </c:if>
            <c:if test="${status == 'CONFIRMED'}">
                <td align="center" style="border: solid 1px gray; background-color: DarkKhaki;">
                    ${status}
                </td>
                <td style="border: solid 1px gray;">
                    ${rb['confirmed_st']}
                </td>
            </c:if>
            <c:if test="${status == 'ACTIVE'}">
                <td align="center" style="border: solid 1px gray; background-color: Aquamarine;">
                    ${status}
                </td>
                <td style="border: solid 1px gray;">
                    ${rb['active_st']}
                </td>
            </c:if>
            <c:if test="${status == 'DONE'}">
                <td align="center" style="border: solid 1px gray; background-color: Gainsboro;">
                    ${status}
                </td>
                <td style="border: solid 1px gray;">
                    ${rb['done_st']}
                </td>
            </c:if>
            <c:if test="${status == 'CANCELED'}">
                <td align="center" style="border: solid 1px gray; background-color: Gainsboro;">
                    ${status}
                </td>
                <td style="border: solid 1px gray;">
                    ${rb['canceled_st']}
                </td>
            </c:if>
            <c:if test="${status == 'OUT_OF_DATE'}">
                <td align="center" style="border: solid 1px gray; background-color: Thistle;">
                    ${status}
                </td>
                <td style="border: solid 1px gray;">
                    ${rb['out_of_date_st']}
                </td>
            </c:if>
            <c:if test="${status == 'NOT_CLOSED'}">
                <td align="center" style="border: solid 1px gray; background-color: HotPink;">
                    ${status}
                </td>
                <td style="border: solid 1px gray;">
                    ${rb['not_closed_st']}
                </td>
            </c:if>


        </tr>
    </c:forEach>
</table>

</body>
</html>