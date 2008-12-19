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
    <script type="text/javascript" src="${resources}/javascripts/effects.core.js"></script>
    <script type="text/javascript" src="${resources}/javascripts/effects.pulsate.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            // a little bling for the error message
            $("#status").hide().effect("pulsate",{times: 3},700).show();
        });

    </script>

</head>
<body>
<div class="loginPane">
    <div class="loginContent">
        <img alt="MPower Logo" src="${resources}/images/mpowerLogo.gif"/>

        <h1 class="loginHeader">Please sign in.</h1>

        <form:form method="post" id="fm1" cssClass="fm-v clearfix" commandName="${commandName}" htmlEscape="true">
            <form:errors path="*" cssClass="errors" id="status" element="div"/>
            <table class="loginInfo">
                <tr>
                    <td style="text-align:right"><label for="username"><spring:message
                            code="screen.welcome.label.netid"/></label></td>
                    <td><form:input cssClass="required" cssErrorClass="error" id="username" size="25" tabindex="1"
                                    accesskey="${userNameAccessKey}" path="username" autocomplete="false"
                                    htmlEscape="true"/></td>
                </tr>
                <tr>
                    <td style="text-align:right"><label for="password"><spring:message
                            code="screen.welcome.label.password"/></label></td>
                    <td><spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey"/>
                        <form:password cssClass="required" cssErrorClass="error" id="password" size="25" tabindex="2"
                                       path="password" accesskey="${passwordAccessKey}" htmlEscape="true"
                                       autocomplete="off"/></td>
                </tr>

                <tr>
                    <td colspan="2" align="center">
                        <input type="hidden" name="lt" value="${flowExecutionKey}"/>
                        <input type="hidden" name="_eventId" value="submit"/>

                        <input class="btn-submit" name="submit" accesskey="l"
                               value="<spring:message code="screen.welcome.button.login" />" tabindex="4"
                               type="submit"/>
                        <input id="formReset" class="btn-reset" name="reset" accesskey="c"
                               value="<spring:message code="screen.welcome.button.clear" />" tabindex="5" type="reset"/>
                    </td>
                </tr>
                <tr class="buttonBar">
                    <td colspan="2"><a href="#">Forgot your password?</a></td>
                </tr>
            </table>

            <p class="welcomeMessage"><spring:message code="screen.welcome.security"/></p>

        </form:form>

    </div>
</div>
</body>
</html>
