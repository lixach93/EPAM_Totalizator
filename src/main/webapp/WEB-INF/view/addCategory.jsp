<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 13.02.2019
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"  language="java" %>
<html>
<head>
    <title></title>

    <link href="../../resources/styles/st.css" type="text/css" rel="stylesheet" />
</head>
<body>
<h1>FSFDSADA</h1>
    <form method="post" action="${pageContext.request.contextPath}/test">
        <input type="hidden"  name="command" value="addCategory"/>
        <input type="text"  name="category" />
        <input type="submit" value="submit" />
    </form>
</body>
</html>
