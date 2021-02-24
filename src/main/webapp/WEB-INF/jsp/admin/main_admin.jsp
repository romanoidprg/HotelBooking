<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>My Server Page</title>
</head>
<body>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<h3 align="center">${rb['admin_title']}</h3>
 <div>
 <c:import url="/WEB-INF/jsp/admin/menu_admin.jsp"/>
 </div>
</body>
</html>