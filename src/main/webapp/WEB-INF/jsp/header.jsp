<%@ page contentType="text/html;charset=UTF-8"  language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>
<c:if test="${not empty role}">
<div align="left">
    ${login} ${role} <a  href="${pageContext.request.contextPath}/controller?command=LOGOUT">${rb['logout']}</a>
</div>
</c:if>
<nav align="right">
    <a  href="${pageContext.request.contextPath}/controller?command=LANG_SELECT&locale=ru_RU">РУССКИЙ</a> |
    <a  href="${pageContext.request.contextPath}/controller?command=LANG_SELECT&locale=en_US">ENGLISH</a> |
    <a  href="${pageContext.request.contextPath}/controller?command=LANG_SELECT&locale=es_ES">ESPANIOL</a>
</nav>

</body>
</html>