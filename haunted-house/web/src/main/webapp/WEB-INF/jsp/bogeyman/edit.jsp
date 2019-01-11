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
<my:pagetemplate title="Edit bogeyman">
<jsp:attribute name="body">


    <h3>${savedBogey.name}</h3>


<form:form method="post" action="${pageContext.request.contextPath}/bogeyman/edit"
           modelAttribute="bogey" cssClass="form-horizontal">

        <div class="form-group">
            <form:label path="type" cssClass="col-sm-2 control-label">Type</form:label>
            <div class="col-sm-10">
                <form:select path="type" cssClass="form-control">
                    <c:forEach items="${types}" var="t">
                        <form:option value="${t}">${t}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="type" cssClass="error"/>
            </div>
        </div>
        <div class="form-group ${hauntStartTime_error?'has-error':''}">
            <form:label path="hauntStartTime" cssClass="col-sm-2 control-label">Haunting starts at</form:label>
            <div class="col-sm-10">
                <form:input path="hauntStartTime" cssClass="form-control" placeholder="HH:MM:SS" value="${savedBogey.hauntStartTime}"/>
                <form:errors path="hauntStartTime" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${hauntEndTime_error?'has-error':''}">
            <form:label path="hauntEndTime" cssClass="col-sm-2 control-label">Haunting ends at</form:label>
            <div class="col-sm-10">
                <form:input path="hauntEndTime" cssClass="form-control" placeholder="HH:MM:SS" value="${savedBogey.hauntEndTime}"/>
                <form:errors path="hauntEndTime" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${description_error?'has-error':''}">
            <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
            <div class="col-sm-10">
                <form:input path="description" cssClass="form-control" value="${savedBogey.description}"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${reason_error?'has-error':''}">
            <form:label path="reason" cssClass="col-sm-2 control-label">Reason</form:label>
            <div class="col-sm-10">
                <form:input path="reason" cssClass="form-control" value="${savedBogey.reason}"/>
                <form:errors path="reason" cssClass="help-block"/>
            </div>
        </div>

        <input type="hidden" name="id" value="${savedBogey.id}">
    <input type="hidden" name="name" value="${savedBogey.name}">

    <button class="btn btn-primary" type="submit">Edit bogeyman</button>
</form:form>



</jsp:attribute>
</my:pagetemplate>