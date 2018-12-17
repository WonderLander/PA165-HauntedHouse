<%--
  Created by IntelliJ IDEA.
  User: Ondrej Stursa
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="${house.name}">
<jsp:attribute name="body">

<div class="col-xs-6">
    <h2>${house.name}</h2>
    <h4>${house.address}</h4>
    <p>${house.history}</p>
    <table class="table table-condensed">
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
                <td><a href="/pa165/bogeyman/detail/${bogey.id}">View</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/pa165/comments/house/${house.id}">Your comments</a>
</div>
<div class="col-xs-6">
    <img src="<c:url value="http://coloringhome.com/coloring/xcg/nnM/xcgnnMx7i.jpg"/>" class="img-thumbnail" style="size: auto">

</div>


</jsp:attribute>
</my:pagetemplate>
