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
<div class="col-xs-6">
    <img src="<c:url value="/${img}"/>" class="img-thumbnail" style="size: auto">

</div>
<div class="col-xs-6">
    <h2>${house.name}</h2>
    <h4>${house.address}</h4>
    <p>${house.history}</p>
    <table>
        <tr>
            <th>Bogeyman</th>
            <th>Type</th>
            <th>Haunts</th>
            <th></th>
        </tr>
        <c:forEach items="${house.bogeymen}" var="bogey">
            <tr>
                <td>${bogey.name}</td>
                <td>${bogey.type}</td>
                <td>${bogey.hauntStartTime} - ${bogey.hauntEndTime}</td>
                <td><a href="houses/${house.id}">View</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<a href="/pa165/comments/house/${house.id}">Your comments</a>

</body>
</html>
