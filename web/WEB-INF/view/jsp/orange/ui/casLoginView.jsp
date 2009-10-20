<%--
<%@ page trimDirectiveWhitespaces="true" session="true" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Authentication Service</title>
    <link rel="stylesheet" type="text/css" href="resources/ext/resources/css/ext-all.css"/>
    <link rel="stylesheet" type="text/css" href="resources/ext/resources/css/xtheme-gray.css"/>
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/login.css"/>
    <script type="text/javascript" src="resources/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="resources/ext/ext-all.js"></script>
    <script type="text/javascript" src="resources/javascripts/login.js"></script>
</head>
<body class="gray">
		
		<div id="header" class="x-hidden">
  	      <!--            <img src="images/orangeleap-logo-tag.png" /> -->
        </div>
        
        <div id="center" class="x-hidden">

                <div id="centerDiv">
                    <h1>Login</h1>

                    <div id="loginForm">
                    <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
			        <form:errors path="*" cssClass="errors" id="errorMessage" element="div" />

                            <ol>
                                <li>
                                    <label for="username"><spring:message code="screen.welcome.label.netid" /></label>
                                    <form:input cssClass="required" cssErrorClass="error" id="username" accesskey="u" size="25" tabindex="1" path="username" autocomplete="false" htmlEscape="true" />
                                </li>
                            </ol>
                            <ol>
                                <li>
                                    <label for="password"><spring:message code="screen.welcome.label.password" /></label>
				                    <form:password cssClass="required" cssErrorClass="error" id="password" accesskey="p" size="25" tabindex="2" path="password" htmlEscape="true" autocomplete="off" />
                                    <input type="hidden" name="lt" value="${flowExecutionKey}" />
					                <input type="hidden" name="_eventId" value="submit" />
                                </li>
                            </ol>
                            <ol>
                                <li class="buttons">
                                    <input class="btn-submit" name="submit" accesskey="l" value="<spring:message code="screen.welcome.button.login" />" tabindex="4" type="submit" />
                                    <input class="btn-reset" name="reset" accesskey="c" value="<spring:message code="screen.welcome.button.clear" />" tabindex="5" type="reset" />
                                </li>
                            </ol>

                    </form:form>
                        <a href="#" id="forgotPasswordLink">Forgot Password?</a>
                    </div>
                </div>
        </div>

        <div id="footer" class="x-hidden">
                Copyright 2009 &copy; Orange Leap
        </div>
        
	    <%@ include file="/WEB-INF/view/jsp/orange/ui/includes/clearcookies.jsp"%>
	    
</body>
</html>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title><spring:message code="pleaseSignIn"/></title>
        <link rel="stylesheet" type="text/css" href="resources/ext/resources/css/ext-all.css"/>
        <link rel="stylesheet" type="text/css" href="resources/ext/resources/css/xtheme-gray.css"/>
        <link rel="stylesheet" type="text/css" href="resources/stylesheets/login.css"/>
        <link rel="shortcut icon" type="image/ico" href="images/favicon.ico" />
        <script type="text/javascript" src="resources/ext/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="resources/ext/ext-all.js"></script>
        <!--<script type="text/javascript" src="resources/javascripts/login.js"></script>-->
    </head>
    <body>
        <div class="loginPane">
            <div class="loginContent">
                <img src="images/orangeleap-logo-tag.png" />
                <h1 class="loginHeader"><spring:message code="pleaseSignIn"/></h1>

                <form:form method="post" commandName="${commandName}" htmlEscape="true">
                    <form:errors path="*" cssClass="errors" id="errorMessage" element="div" />
                    <table class="loginInfo">
                        <tr>
                            <th><label for="username"><spring:message code="userNameSite"/>:</label></th>
                            <td>
                                <form:input id="username" cssClass="loginField" size="30" tabindex="1" path="username" htmlEscape="true" />
                            </td>
                        </tr>
                        <tr>
                            <th><label for="password"><spring:message code="password"/></label></th>
                            <td>
                                <form:password id="password" cssClass="loginField" accesskey="p" size="30" tabindex="2" path="password" htmlEscape="true" autocomplete="off" />
                            </td>
                        </tr>
                        <tr>
                            <td class="loginButton" colspan="2">
                                <input type="hidden" name="lt" value="${flowExecutionKey}" />
                                <input type="hidden" name="_eventId" value="submit" />
                                <input class="loginButton" name="submit" id="submit" type="submit" value="<spring:message code='signIn'/>" />
                            </td>
                        </tr>
                    </table>
                </form:form>
                <a href="#" id="forgotPasswordLink"><spring:message code='forgotPassword'/></a>
            </div>
        </div>
        <%@ include file="/WEB-INF/view/jsp/orange/ui/includes/clearcookies.jsp"%>
    </body>
</html>