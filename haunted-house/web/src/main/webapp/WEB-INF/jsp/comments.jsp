<%--
  Created by IntelliJ IDEA.
  User: ondra
  Date: 15/12/2018
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>comments</p>
    <c:forEach items="${comments}" var="comment">
        <p>${comment.author}</p>
    </c:forEach>

<div>
    <p>create comment</p>

    <form action="${pageContext.request.contextPath}/comments/create" method="post">
        <input type="text" name="author"/>
        <input type="text" name="text"/>
        <input type="date" name="date"/>
        <input type="submit"/>
    </form>
</div>
</body>



</html>
