<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>My Server Page</title>
</head>
<body>

<jsp:forward page="/controller?command=${redirect_command}&page_to_redirect=${page_to_redirect}"/>

</body>
</html>