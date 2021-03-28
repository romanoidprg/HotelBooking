<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>My Server Page</title>
</head>
<body>
<c:import url="/WEB-INF/jsp/header_logout.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<h3 align="center">${rb['user_title']}</h3>
 <c:import url="/WEB-INF/jsp/user/menu_user.jsp"/>
</body>
</html>