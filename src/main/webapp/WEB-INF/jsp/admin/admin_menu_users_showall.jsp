<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>My Server Page</title>
    <script type="text/javascript">
    <!--

    function isDatesCorrect (form){
	valid = true;

        if (document.getElementsByName(form)[0].date_from_for_show_orders.value
            > document.getElementsByName(form)[0].date_before_for_show_orders.value)
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
<table style="width: 100%;border-bottom: 1px solid;">
    <tr>
        <td align="left">
            <c:import url="/WEB-INF/jsp/header_logout.jsp"/>
        </td>
        <td align="right">
        </td>
    </tr>
</table>

<h3 align="center">${rb['admin_title']}</h3>

<div>
    <c:import url="/WEB-INF/jsp/admin/menu_admin1.jsp"/>
</div>

<h4 align="center">${rb['information_about_logins']}</h4>


<table id="f1" align="center">
    <tr id="s1">
        <td> ${rb['id']}</td>
        <td>| ${rb['login']}</td>
        <td>| ${rb['isadmin']}</td>
        <td>|</td>
        <td>${rb['change_role']} |</td>
        <td> ${rb['show_orders']}</td>
        <td align="center">| ${rb['show_users']}</td>
    </tr>
    <tr>
    </tr>

    <c:forEach var="login" items="${login_list}" varStatus="status">
        <tr>
            <td>
               ${ login.id }
            </td>
            <td>|
                ${ login.login }
            </td>

            <td>|
                ${ login.admin }
            </td>
            <td>|</td>
            <td align="center">
                <form id="f22" action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="adm_set_login_role"/>
                    <input type="hidden" name="name_include" value="${name_include}"/>
                    <input type="hidden" name="login_id_for_set_role" value="${login.id}"/>
                    <input id="i1" type="submit" value="V"/>
                </form>
            </td>
            <td align="center">
                <form id="f22" name="${ login.id }"
                      action="${pageContext.request.contextPath}/controller"
                      onsubmit="return isDatesCorrect(${login.id})" method="post">
                    <input type="hidden" name="command" value="all_show_orders_related_to_login"/>
                    <input type="hidden" name="new_orders_list" value="true"/>
                    <input type="hidden" name="login_for_show_orders" value="${login.login}"/>
                    <input type="date" name="date_from_for_show_orders" value="1900-01-01"/>
                    <input type="date" name="date_before_for_show_orders" value="2100-01-01"/>
                    <input id="i1" type="submit" value="V"/>
                </form>
            </td>
            <td align="center">
                <form id="f22" action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="adm_show_clients_related_to_login"/>
                    <input type="hidden" name="login_for_show_clients" value="${login.login}"/>
                    <input id="i1" type="submit" value="V"/>
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
                <input type="hidden" name="command" value="adm_admin_menu_users_select"/>
                <br>
                <input type="submit" value="${rb['return']}"/>
            </form>
        </td>
    </tr>
</table>

</body>
</html>