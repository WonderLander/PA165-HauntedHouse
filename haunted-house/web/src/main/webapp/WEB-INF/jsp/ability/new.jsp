<%--
  Created by IntelliJ IDEA.
  User: xwenzl
  Date: 17.12.18
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Abilities">
<jsp:attribute name="body">
<form:form method="post" action="${pageContext.request.contextPath}/ability/create"
           modelAttribute="abilityCreate" cssClass="form-horizontal">
    <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
    <div class="col-sm-10">
        <form:input path="name" cssClass="form-control"/>
    </div>
    <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
    <div class="col-sm-10">
        <form:input path="description" cssClass="form-control"/>
    </div>
    <form:label path="cooldown" cssClass="col-sm-2 control-label">Cooldown</form:label>
    <div class="col-sm-10">
        <form:input path="cooldown" cssClass="form-control"/>
    </div>
    <button class="btn btn-primary" type="submit">Create ability</button>
</form:form>
</jsp:attribute>
</my:pagetemplate>
