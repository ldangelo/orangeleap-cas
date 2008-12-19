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
			<div class="header><spring:message code="screen.logout.header" /></div>
			<p><spring:message code="screen.logout.success" /></p>
			<p><spring:message code="screen.logout.security" /></p>

			<%--
			 Implementation of support for the "url" parameter to logout as recommended in CAS spec section 2.3.1.
			 A service sending a user to CAS for logout can specify this parameter to suggest that we offer
			 the user a particular link out from the logout UI once logout is completed.  We do that here.
			--%>
			<c:if test="${not empty param['url']}">
			<p>
				<spring:message code="screen.logout.redirect" arguments="${fn:escapeXml(param.url)}" />
			</p>
			</c:if>
		</div>

    </div>
</div>
</body>
</html>



