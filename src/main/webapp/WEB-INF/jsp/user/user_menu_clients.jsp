<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<html>
<head>
    <title>My Server Page</title>
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
<c:import url="/WEB-INF/jsp/header_logout.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<h3 align="center">${rb['user_title']}</h3>
<div>
    <c:import url="/WEB-INF/jsp/user/menu_user1.jsp"/>
</div>

<table id="f1">
    <tr>
        <td>
            ${rb['info_about_clients']}
        </td>
    </tr>
</table>
<table id="f1">
    <tr>
        <td align="center">
            ${rb['user_menu_clients_show']}
            <form action="${pageContext.request.contextPath}/controller" method="post">
            <br>
                <input type="hidden" name="command" value="usr_user_menu_show_clients_link_to_login">
                <input type="submit" value="${rb['submit']}">
            </form>
        </td>
    </tr>
</table>

<table id="f1">
    <tr>
        <td align="center">
            ${rb['user_menu_clients_create']}
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="usr_user_menu_clients_create"/>
                <br>
                <table id="t1">
                    <tr>
                        <td align="right">
                            ${rb['name']}:
                        </td>
                        <td align="left">
                            <input required type="text" name="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['sname']}:
                        </td>
                        <td align="left">
                            <input required type="text" name="sname"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['email']}:
                        </td>
                        <td align="left">
                            <input type="text" name="email"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['phone']}:
                        </td>
                        <td align="left">
                            <input type="text" name="phone"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['bday']}:
                        </td>
                        <td align="left">
                            <input type="date" name="bday" value="1900-01-01"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['sex']}:
                        </td>
                        <td align="left">
                            <select size="1" name="sex">
                                <option selected value=""></option>
                                <option value="male">${rb['male']}</option>
                                <option value="female">${rb['female']}</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['country']}:
                        </td>
                        <td align="left">
                            <select size="1" name="country">
                                <c:forEach var="country" items="${country_list}">
                                    <option value="${country.name}">${country.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${rb['adress']}:
                        </td>
                        <td align="left">
                            <input required type="text" name="address"/>
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