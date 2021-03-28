<%@ page contentType="text/html;charset=UTF-8"   language="java" isELIgnored="false" %>
<html>
<head>
    <title>My Server Page</title>
</head>
<body>

<h1 align="center"> HOTEL CALIFORNIA</h1>

<nav align="center">
    <a  href="${pageContext.request.contextPath}/controller?command=ALL_LANG_SELECT&locale=ru_RU">РУССКИЙ</a> |
    <a  href="${pageContext.request.contextPath}/controller?command=ALL_LANG_SELECT&locale=en_US">ENGLISH</a> |
    <a  href="${pageContext.request.contextPath}/controller?command=ALL_LANG_SELECT&locale=es_ES">ESPANIOL</a>
</nav>

</body>
</html>