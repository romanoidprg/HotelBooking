<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>My Server Page</title>
    <script type="text/javascript">
    <!--

    function isDatesCorrect (){
	valid = true;

        if (document.f_show_orders.date_from_for_show_orders.value
            > document.f_show_orders.date_before_for_show_orders.value)
        {
                alert ("${rb['date_range_incorrect']}");
                valid = false;
        }

        return valid;
    }

    //-->
    </script>
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

<h3 align="center">${rb['user_title']}</h3>
<div>
    <c:import url="/WEB-INF/jsp/user/menu_user2.jsp"/>
</div>

<table id="f1">
    <tr>
        <td align="center">
            ${rb['info_about_orders']}
        </td>
    </tr>
</table>
<table id="f1">
    <tr>
        <td align="center">
            ${rb['user_menu_orders_show']}
            <form name="f_show_orders" onsubmit="return isDatesCorrect()"
                  action="${pageContext.request.contextPath}/controller" method="post">
            <br>
                <input type="hidden" name="command" value="all_show_orders_related_to_login">
                <input type="hidden" name="new_orders_list" value="true"/>

                <input type="hidden" name="login_for_show_orders" value="${login}">
                ${rb['from']}:
                <input type="date" name="date_from_for_show_orders" value="1900-01-01"/>
                ${rb['to']}:
                <input type="date" name="date_before_for_show_orders" value="2100-01-01"/>
                <input type="submit" value="${rb['submit']}">
            </form>
        </td>
    </tr>
</table>

<table id="f1">
    <tr>
        <td align="center">
            ${rb['goto_prepare_order']}
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <br>
                <input type="hidden" name="command" value="usr_goto_prepare_order"/>
                <input type="submit" value="${rb['submit']}">
            </form>
        </td>
    </tr>
</table>

</body>
</html>