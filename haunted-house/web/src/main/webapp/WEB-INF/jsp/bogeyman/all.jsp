<%--
  Created by IntelliJ IDEA.
  User: Lukas Sadlek
  Date: 16.12.2018
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/bogeyman/new">Create new bogeyman</a>
<table class="table">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bogeymen}" var="bogeyman">
        <tr>
            <td>${bogeyman.id}</td>
            <td><c:out value="${bogeyman.name}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
