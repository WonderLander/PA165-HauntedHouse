<%--
  Created by IntelliJ IDEA.
  User: Lukas Sadlek
  Date: 16.12.2018
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="Create bogeyman">
<jsp:attribute name="body">
<form:form method="post" action="${pageContext.request.contextPath}/bogeyman/create"
           modelAttribute="bogeymanCreate" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
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
                <form:input path="hauntStartTime" cssClass="form-control" placeholder="HH:MM:SS"/>
                <form:errors path="hauntStartTime" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${hauntEndTime_error?'has-error':''}">
            <form:label path="hauntEndTime" cssClass="col-sm-2 control-label">Haunting ends at</form:label>
            <div class="col-sm-10">
                <form:input path="hauntEndTime" cssClass="form-control" placeholder="HH:MM:SS"/>
                <form:errors path="hauntEndTime" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${description_error?'has-error':''}">
            <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
            <div class="col-sm-10">
                <form:input path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${reason_error?'has-error':''}">
            <form:label path="reason" cssClass="col-sm-2 control-label">Reason</form:label>
            <div class="col-sm-10">
                <form:input path="reason" cssClass="form-control"/>
                <form:errors path="reason" cssClass="help-block"/>
            </div>
        </div>
            <form:label path="formHouse" cssClass="col-sm-2 control-label">Haunts in house</form:label>
            <div class="col-sm-10">
                <form:select path="formHouse" cssClass="form-control">
                    <c:forEach items="${houses}" var="h">
                        <form:option value="${h}">${h}</form:option>
                    </c:forEach>
                </form:select>
        </div>
    <button class="btn btn-primary" type="submit">Create bogeyman</button>
</form:form>
</jsp:attribute>
</my:pagetemplate>
