<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>My Server Page</title>
</head>

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
    <c:import url="/WEB-INF/jsp/admin/menu_admin1.jsp"/>
</div>
<table id="f1">
    <tr>
        <td align="center">
            ${rb['admin_menu_users_showall']}
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="adm_admin_menu_users_showall">
                <br>
                <table id="t1">
                    <tr>
                        <td align="right">
                            ${rb['name_include']}:
                        </td>
                        <td align="left">
                            <input type="text" name="name_include"/>
                        </td>
                    </tr>
                </table>
                <br>
                <input type="submit" value="${rb['submit']}">
            </form>
        </td>
    </tr>
</table>
<br>
<table id="f1">
    <tr>
        <td align="center">
            ${rb['admin_menu_users_showfilter']}
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="adm_admin_menu_clients_showfilter"/>
                <input type="hidden" name="new_search_client" value="true"/>
                <br>
                <table id="t1">
                    <tr>
                        <td align="right">
                            ${rb['name_include']}:
                        </td>
                        <td align="left">
                            <input type="text" name="name_include"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['sname_include']}:
                        </td>
                        <td align="left">
                            <input type="text" name="sname_include"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['email_include']}:
                        </td>
                        <td align="left">
                            <input type="text" name="email_include"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['phone_include']}:
                        </td>
                        <td align="left">
                            <input type="text" name="phone_include"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['bday']}:
                        </td>
                        <td align="left">
                            ${rb['from']} : <input type="date" name="bday_from" value="1900-01-01"/>
                            <br>
                            ${rb['to']}: <input type="date" name="bday_to" value="2100-01-01"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['sex']}:
                        </td>
                        <td align="left">
                            <select size="1" name="sex">
                                <option selected value=""></option>
                                <option value="MALE">${rb['male']}</option>
                                <option value="FEMALE">${rb['female']}</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['country']}:
                        </td>
                        <td align="left">
                            <select size="1" name="country" >
                                <option value="" selected></option>
                                <c:forEach var="country" items="${country_list}">
                                    <option value="${country.name}">${country.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['adress_include']}:
                        </td>
                        <td align="left">
                            <input type="text" name="adress_include"/>
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