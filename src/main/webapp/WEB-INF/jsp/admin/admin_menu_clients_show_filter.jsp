<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${rb['hotel_name']}</title>
    <script type="text/javascript">
    <!--
    function ClientsInfo(client, phone, email) {
           alert('${rb['phone']}: ' + phone + '    ' + '${rb['email']}: ' + email);
    }
    function stopAlert() {
           event.stopPropagation();
    }
    function colorOver(tr) {
        document.getElementsByName(tr)[0].style.background = 'aqua';
        document.getElementsByName(tr)[0].style.cursor = 'hand';
    }
    function cursorText(td) {
        document.getElementsByName(td)[0].style.cursor = 'text';
    }
    function colorOut(tr) {
        document.getElementsByName(tr)[0].style.background = 'WHITE';
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

#s1 {
font-weight: bold;

    }
#i1 {
background-color: SeaShell;
font-weight: bold;
    }
#f2 { display: inline;
}
#s2 { width: 100%;
}





</style>

<body>
<c:import url="/WEB-INF/jsp/header_logout.jsp"/>

<h3 align="center">${rb['admin_title']}</h3>

<div>
    <c:import url="/WEB-INF/jsp/admin/menu_admin1.jsp"/>
</div>

<h4 align="center">${rb['information_clients']}</h4>


<table id="f1" align="center">
    <tr id="s1">
        <td align="center">${rb['id']}</td>
        <td align="center">${rb['name']}</td>
        <td align="center">${rb['sname']}</td>
        <td align="center">${rb['sex']}</td>
        <td align="center">${rb['country']}</td>
        <td align="center">${rb['address']}</td>
        <td align="center" style="width: 15%;">${rb['related_login']}</td>
        <td align="center" style="width: 10%;">${rb['show_orders_with_client']}</td>
    </tr>
    <tr>
    </tr>

    <c:forEach var="client" items="${client_list}">
        <tr name="${ client.key.id }" onclick="ClientsInfo('${client.key.id}', '${client.key.phone}', '${client.key.eMail}')"
            onmouseover="colorOver('${ client.key.id }')" onmouseout="colorOut('${ client.key.id }')">
            <td >
                <c:out value="${ client.key.id }"/>
            </td>
            <td style="border-left: solid 1px">
                <c:out value="${client.key.name}"/>
            </td>
            <td style="border-left: solid 1px">
                <c:out value="${client.key.sName}"/>
            </td>
            <td style="border-left: solid 1px">
                <c:out value="${client.key.sex}"/>
            </td>
            <td style="border-left: solid 1px">
                <c:out value="${client.key.country}"/>
            </td>
            <td style="border-left: solid 1px">
                <c:out value="${client.key.address}"/>
            </td>
            <td style="border-left: solid 1px; padding-left: 10px">
                <c:out value="${client.value}"/>
            </td>
            <td name="td${client.key.id}" align="center" style="border-left: solid 1px" onclick="stopAlert()"
                onmouseover="cursorText('td${client.key.id}')" >
                <form id="f2" name=""
                      action="${pageContext.request.contextPath}/controller"
                      onsubmit="return isDatesCorrect()" method="post">
                    <input type="hidden" name="command" value="adm_show_orders_related_to_client"/>
                    <input type="hidden" name="last_command_before_orders_show" value="adm_admin_menu_clients_showfilter"/>
                    <input type="hidden" name="new_orders_list" value="true"/>
                    <input type="hidden" name="related_client_id" value="${client.key.id}"/>
                    <input type="hidden" name="related_login" value="${client.value}"/>
                    <input id="i1" type="submit" value="V"/>
                </form>
            </td>
        </tr>
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