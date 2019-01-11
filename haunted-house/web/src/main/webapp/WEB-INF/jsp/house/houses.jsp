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

<my:pagetemplate title="Houses">
<jsp:attribute name="body">
    <table class="table table-striped">
        <tr>
            <th>House</th>
            <th>Address</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${houses}" var="house">
            <tr>
                <td data-toggle="tooltip" title="${house.history}">${house.name}</td>
                <td>${house.address}</td>
                <td><a href="${pageContext.request.contextPath}/houses/${house.id}">View</a></td>
                <td><a href="${pageContext.request.contextPath}/houses/edit/${house.id}">Edit</a></td>
            </tr>
        </c:forEach>


    </table>

</jsp:attribute>
</my:pagetemplate>