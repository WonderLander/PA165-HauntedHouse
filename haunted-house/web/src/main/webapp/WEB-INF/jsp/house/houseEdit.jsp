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
<my:pagetemplate title="Edit house">
<jsp:attribute name="body">


    <h3>${savedHouse.name}</h3>


<form action="${pageContext.request.contextPath}/houses/edit" method="post">
    <div class="form-group">
        <label>Address</label>
        <input type="text" class="form-control" name="address" value="${savedHouse.address}" required>
    </div>
    <div class="form-group">
        <label>Date</label>
        <input type="date" class="form-control" name="StringDate" placeholder="YYYY-MM-DD" value="${savedHouse.date}" required>
    </div>
    <div class="form-group">
        <label>History</label>
        <input type="text" class="form-control" name="history" value="${savedHouse.history}" required>
    </div>
    <input type="hidden" name="id" value="${savedHouse.id}">
    <input type="hidden" name="name" value="${savedHouse.name}">

    <button type="submit" class="btn btn-default">Submit</button>
</form>



</jsp:attribute>
</my:pagetemplate>