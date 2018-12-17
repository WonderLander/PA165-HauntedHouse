<%--
  Created by IntelliJ IDEA.
  User: xwenzl
  Date: 17.12.18
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Abilities</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Cooldown</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${abilities}" var="ability">
        <tr>
            <td>${ability.name}</td>
            <td>${ability.description}</td>
            <td>${ability.cooldown}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/ability/new"
      role="button">Create ability</a></p>


</body>
</html>
