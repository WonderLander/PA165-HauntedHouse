<%--
  Created by IntelliJ IDEA.
  User: Lukas Sadlek
  Date: 16.12.2018
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="${house.name}">
    <jsp:attribute name="body">
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
<p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/bogeyman/new"
      role="button">Create new bogeyman</a></p>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Type</th>
        <th>House</th>
        <th>Starts haunting at</th>
        <th>Ends haunting at</th>
        <th>Description</th>
        <th>Reason</th>
        <!--<th></th>
        <th></th>-->
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bogeymen}" var="bogeyman">
        <tr>
            <td>${bogeyman.id}</td>
            <td><c:out value="${bogeyman.name}"/></td>
            <td>${bogeyman.type}</td>
            <td>${bogeyman.house.name}</td>
            <td>${bogeyman.hauntStartTime}</td>
            <td>${bogeyman.hauntEndTime}</td>
            <td>${bogeyman.description}</td>
            <td>${bogeyman.reason}</td>
            <td><p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/bogeyman/detail/${bogeyman.id}"
                      role="button">Detail</a></p></td>
            <td><p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/bogeyman/edit/${bogeyman.id}"
                      role="button">Edit</a></p></td>
            <td><p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/bogeyman/delete/${bogeyman.id}"
                      role="button">Delete</a></p></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</jsp:attribute>
</my:pagetemplate>
