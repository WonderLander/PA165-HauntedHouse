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

<!--
<form action="${pageContext.request.contextPath}/houses/edit" method="post">
    <div class="form-group">
        <label>Address</label>
        <input type="text" class="form-control" name="address" value="${savedHouse.address}">
    </div>
    <div class="form-group">
        <label>Date</label>
        <input type="date" class="form-control" name="StringDate" placeholder="DD.MM.YYYY" value="${savedHouse.date}">
    </div>
    <div class="form-group">
        <label>History</label>
        <input type="text" class="form-control" name="history" value="${savedHouse.history}">
    </div>
    <input type="hidden" name="id" value="${savedHouse.id}">
    <input type="hidden" name="name" value="${savedHouse.name}">

    <button type="submit" class="btn btn-default">Submit</button>
</form>
    -->
    <form:form method="POST" action="${pageContext.request.contextPath}/houses/edit" modelAttribute="house">
        <div class="form-group ${address_error?'has-error':''}">
            <form:label path="address" cssClass="col-sm-2 control-label">Address</form:label>
            <div class="col-sm-10">
                <form:input path="address" cssClass="form-control" required="true" value="${savedHouse.address}"/>
                <form:errors path="address" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${date_error?'has-error':''}">
            <form:label path="date" cssClass="col-sm-2 control-label">Date</form:label>
            <div class="col-sm-10">
                <form:input path="date" cssClass="form-control" required="true" type="date" value="${savedHouse.date}"/>
                <form:errors path="date" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${history_error?'has-error':''}">
            <form:label path="history" cssClass="col-sm-2 control-label">History</form:label>
            <div class="col-sm-10">
                <form:input path="history" cssClass="form-control" required="true" value="${savedHouse.history}"/>
                <form:errors path="history" cssClass="help-block"/>
            </div>
        </div>

        <input type="hidden" name="id" value="${savedHouse.id}">
        <input type="hidden" name="name" value="${savedHouse.name}">
    <td><button class="btn btn-default" type="submit">Submit</button></td>

</form:form>


</jsp:attribute>
</my:pagetemplate>