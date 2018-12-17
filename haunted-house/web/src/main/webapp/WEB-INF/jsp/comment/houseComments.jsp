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

<my:pagetemplate title="${house.name} comments">
<jsp:attribute name="body">
<div class="container">
    <div class="jumbotron">
        <h3>Add comment (${house.name})</h3>
        <form action="${pageContext.request.contextPath}/comments/create" method="post">
            <div class="form-group">
                <label>Author</label>
                <input type="text" class="form-control" name="author">
            </div>
            <div class="form-group">
                <label>Comment</label>
                <input type="text" class="form-control" name="text">
            </div>
            <input type="hidden" name="id" value="${house.id}">
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</div>
<div class="container">
    <div class="jumbotron">
        <div class="panel panel-default">
        <c:forEach items="${comments}" var="comment">
            <div class="container">
                <div class="jumbotron">
                    <h3>${comment.author}</h3>
                    <h5>${comment.date}</h5>
                    <div class="h-divider"></div>
                    <p>${comment.text}</p>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</div>
</jsp:attribute>
</my:pagetemplate>
