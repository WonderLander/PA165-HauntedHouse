<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
<head>
    <title>${house.name}</title>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h3>Add comment (${house.name})</h3>
        <form action="${pageContext.request.contextPath}/comments/create" method="post">
            <div class="form-group">
                <label>Author</label>
                <input type="text" class="form-control" name="author">
            </div>
            <div class="form-group">
                <label>Comment</label>
                <input type="text" class="form-control" name="text">
            </div>
            <input type="hidden" name="id" value="${house.id}">
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</div>
<div class="container">
    <div class="jumbotron">
        <div class="panel panel-default">
        <c:forEach items="${comments}" var="comment">
            <div class="container">
                <div class="jumbotron">
                    <h3>${comment.author}</h3>
                    <h5>${comment.date}</h5>
                    <div class="h-divider"></div>
                    <p>${comment.text}</p>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
