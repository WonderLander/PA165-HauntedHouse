<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: nay
  Date: 16.12.2018
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/bogeyman/create"
           modelAttribute="bogeymanCreate" cssClass="form-horizontal">
        <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
        <div class="col-sm-10">
            <form:input path="name" cssClass="form-control"/>
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
        <form:label path="hauntStartTime" cssClass="col-sm-2 control-label">Haunting starts at</form:label>
        <div class="col-sm-10">
            <form:input path="hauntStartTime" cssClass="form-control"/>
        </div>
        <form:label path="hauntEndTime" cssClass="col-sm-2 control-label">Haunting ends at</form:label>
        <div class="col-sm-10">
            <form:input path="hauntEndTime" cssClass="form-control"/>
        </div>
        <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
        <div class="col-sm-10">
            <form:input path="description" cssClass="form-control"/>
        </div>
        <form:label path="reason" cssClass="col-sm-2 control-label">Reason</form:label>
        <div class="col-sm-10">
            <form:input path="reason" cssClass="form-control"/>
        </div>
        <div class="form-group">
            <form:label path="house" cssClass="col-sm-2 control-label">Haunts in house</form:label>
            <div class="col-sm-10">
                <form:select path="house" cssClass="form-control">
                    <c:forEach items="${houses}" var="h">
                        <form:option value="${h}">${h}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="house" cssClass="error"/>
            </div>
        </div>
    <button class="btn btn-primary" type="submit">Create bogeyman</button>
</form:form>
</body>
</html>
