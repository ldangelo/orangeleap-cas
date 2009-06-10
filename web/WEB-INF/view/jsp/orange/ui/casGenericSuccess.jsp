<%@ page trimDirectiveWhitespaces="true" session="true" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Orange Leap Authentication Service</title>
    <link rel="stylesheet" type="text/css" href="resources/ext/resources/css/ext-all.css"/>
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/login.css"/>
    <script type="text/javascript" src="resources/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="resources/ext/ext-all.js"></script>
    <script type="text/javascript" src="resources/javascripts/generic.js"></script>
</head>
<body>
<div id="header" class="x-hidden">
    <h1>Orange Leap Authentication Service</h1>
</div>

<div id="center" class="x-hidden">


    <div id="centerDiv">
        <h1><spring:message code="screen.success.header" /></h1>

        <div id="msg">

            <p class="status"><spring:message code="screen.success.success" /></p>

            <p class="sec-warning"><spring:message code="screen.success.security" /></p>

        </div>

    </div>
</div>


<div id="footer" class="x-hidden">
    Copyright 2009 &copy; Orange Leap
</div>

</body>
</html>
