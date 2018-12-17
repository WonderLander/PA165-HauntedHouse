<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:pagetemplate>
<jsp:attribute name="body">
    <div class="container">
        <div class="jumbotron">
            <h1>PA165-HauntedHouse</h1>
            <div class="h-divider"></div>
            <p>There are many houses which are haunted by scary bogeymen. This application serves as tool for finding such houses and bogeymen and all about them.</p>
            <button onclick="location.href='${pageContext.request.contextPath}/houses/'" type="button" class="btn btn-primary btn-lg">ENTER</button>
        </div>
    </div>

</jsp:attribute>
</my:pagetemplate>
