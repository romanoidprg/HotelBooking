<%@ page contentType="text/html;charset=UTF-8"  language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>
<c:if test="${not empty role}">
    <div align="left">
        ${login}
        <c:if test="${role == 'ADMIN'}">
            (${rb['admin']})
        </c:if>
        <c:if test="${role == 'USER'}">
            (${rb['user']})
        </c:if>
        <a href="${pageContext.request.contextPath}/controller?command=ALL_LOGOUT">${rb['logout']}</a>
    </div>
</c:if>

</body>
</html>