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
<my:pagetemplate title="Create house">
<jsp:attribute name="body">

<form action="${pageContext.request.contextPath}/houses/create" method="post">
    <div class="form-group">
        <label>House name</label>
        <input type="text" class="form-control" name="name" required>
    </div>
    <div class="form-group">
        <label>Address</label>
        <input type="text" class="form-control" name="address">
    </div>
    <div class="form-group">
        <label>Date</label>
        <input type="date" class="form-control" name="StringDate" placeholder="DD.MM.YYYY">
    </div>
    <div class="form-group">
        <label>History</label>
        <input type="text" class="form-control" name="history">
    </div>

    <button type="submit" class="btn btn-default">Submit</button>
</form>



</jsp:attribute>
</my:pagetemplate>
