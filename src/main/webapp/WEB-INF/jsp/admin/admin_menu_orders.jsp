<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${rb['hotel_name']}</title>
</head>

<style>
#f1 {
    border: solid blue;
    width: 1000px;
    margin:auto;
    }
#t1 {
    width: 500px;
    margin:auto;

}



</style>

<body>
<table style="width: 100%;border-bottom: 1px solid;">
    <tr>
        <td align="left">
            <c:import url="/WEB-INF/jsp/header_logout.jsp"/>
        </td>
        <td align="right">
            <c:import url="/WEB-INF/jsp/header.jsp"/>
        </td>
    </tr>
</table>

<h3 align="center">${rb['admin_title']}</h3>
<div>
    <c:import url="/WEB-INF/jsp/admin/menu_admin2.jsp"/>
</div>
<table id="f1">
    <tr>
        <td align="center">
            ${rb['admin_menu_orders_showfilter']}
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="adm_show_orders_by_filter">
                <input type="hidden" name="new_orders_list" value="true"/>

                <br>
                <table id="t1">
                    <tr>
                        <td align="right">
                            ${rb['period']} ${rb['from']}:
                        </td>
                        <td align="left">
                            <input type="date" name="date_from_for_show_orders" value="1900-01-01"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['to']}:
                        </td>
                        <td align="left">
                            <input type="date" name="date_before_for_show_orders" value="2100-01-01"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['status']}:
                        </td>
                        <td align="left">
                            <select size="1" name="order_status">
                                <option selected value=""></option>
                                <option value="AWAITING" style="background-color: lightPink;">AWAITING</option>
                                <option value="CONFIRMED" style="background-color: DarkKhaki;">CONFIRMED</option>
                                <option value="ACTIVE" style="background-color: Aquamarine;">ACTIVE</option>
                                <option value="DONE" style="background-color: Gainsboro;">DONE</option>
                                <option value="CANCELED" style="background-color: Gainsboro;">CANCELED</option>
                                <option value="OUT_OF_DATE" style="background-color: Thistle;">OUT_OF_DATE</option>
                                <option value="NOT_CLOSED" style="background-color: HotPink;">NOT_CLOSED</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['name_login_include']}:
                        </td>
                        <td align="left">
                            <input type="text" name="login_include">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['name_client_include']}:
                        </td>
                        <td align="left">
                            <input type="text" name="name_include">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['sname_client_include']}:
                        </td>
                        <td align="left">
                            <input type="text" name="sname_include">
                        </td>
                    </tr>
                </table>
                <br>
                <input type="submit" value="${rb['submit']}">

            </form>
        </td>
    </tr>
</table>

</body>
</html>