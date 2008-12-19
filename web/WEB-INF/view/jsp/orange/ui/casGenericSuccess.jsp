<%@ page trimDirectiveWhitespaces="true" session="true" pageEncoding="utf-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>MPower Login</title>
    <c:url value="/resources" var="resources"/>
    <link href="${resources}/stylesheets/login.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" type="image/ico" href="${resources}/images/favicon.ico"/>
    <script type="text/javascript" src="${resources}/javascripts/jquery.js"></script>
</head>
<body>
<div class="loginPane">
    <div class="loginContent">
        <img alt="MPower Logo" src="${resources}/images/mpowerLogo.gif"/>

        <div id="msg" class="success">
			<div class="header"><spring:message code="screen.success.header" /></div>
			<p><spring:message code="screen.success.success" /></p>
			<p><spring:message code="screen.success.security" /></p>
		</div>
        
    </div>
</div>
</body>
</html>



