<%--
  Created by IntelliJ IDEA.
  User: Lukas Sadlek
  Date: 16.12.2018
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bogeyman detail</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<h1>Bogeyman with id: ${bogeyman.id}</h1>
<p><b>Name: </b>${bogeyman.name}</p>
<p><b>Type: </b>${bogeyman.type}</p>
<p><b>House: </b>${bogeyman.house.name}</p>
<p><b>Starts haunting at: </b>${bogeyman.hauntStartTime}</p>
<p><b>Ends haunting at: </b>${bogeyman.hauntEndTime}</p>
<p><b>Description: </b>${bogeyman.description}</p>
<p><b>Reason: </b>${bogeyman.reason}</p>

<h2>Abilities of bogeyman</h2>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Cooldown</th>
    </tr>
    </thead>
    <tbody>
    <tbody>
    <c:forEach items="${abilities}" var="ability">
        <tr>
            <td>${ability.id}</td>
            <td><c:out value="${ability.name}"/></td>
            <td>${ability.description}</td>
            <td>${ability.cooldown} seconds</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
