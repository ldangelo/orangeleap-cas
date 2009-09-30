<%@ page trimDirectiveWhitespaces="true" session="true" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Authentication Service</title>
    <link rel="stylesheet" type="text/css" href="resources/ext/resources/css/ext-all.css"/>
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/login.css"/>
    <script type="text/javascript" src="resources/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="resources/ext/ext-all.js"></script>
    <script type="text/javascript" src="resources/javascripts/generic.js"></script>
</head>
<body>
<div id="header" class="x-hidden">
    <h1>Authentication Service</h1>
</div>

<div id="center" class="x-hidden">


    <div id="centerDiv">
        <h1><spring:message code="screen.logout.header"/></h1>

        <div id="msg" class="success">
            

            <p class="status"><spring:message code="screen.logout.success"/></p>

            <p class="sec-warning"><spring:message code="screen.logout.security"/></p>

            <%--
                Implementation of support for the "url" parameter to logout as recommended in CAS spec section 2.3.1.
                A service sending a user to CAS for logout can specify this parameter to suggest that we offer
                the user a particular link out from the logout UI once logout is completed.  We do that here.
               --%>
            <c:if test="${not empty param['url']}">
                <p>
                    <spring:message code="screen.logout.redirect" arguments="${fn:escapeXml(param.url)}"/>
                </p>
            </c:if>
        </div>
        <a href="login">Login Again</a>
    </div>
</div>


<div id="footer" class="x-hidden">
    Copyright 2009 &copy; Orange Leap
</div>

        <script>
        document.cookie = "JSESSIONID=0; path=/${cas.contextPrefix}jasperserver;";
        document.cookie = "JSESSIONID=0; path=/${cas.contextPrefix}orangeleap;";
        </script>


</body>
</html>

