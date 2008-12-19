<%@ page session="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
	    <title>MPower Open - Authentication Service</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <c:url var="resources" value="/resources"/>
        <link rel="stylesheet" type="text/css" media="screen, projection" href="${resources}/stylesheets/screen.css"/>
        <link rel="stylesheet" type="text/css" media="print" href="${resources}/stylesheets/print.css">
        <!--[if IE]>
        <link href="${resources}/stylesheets/master_IE.css" rel="stylesheet" type="text/css" />
        <![endif]-->
        <!--[if lte IE 6]>
            <link href="${resources}/stylesheets/master_IE6.css" rel="stylesheet" type="text/css" />
        <![endif]-->
        <link rel="shortcut icon" type="image/ico" href="${resources}/images/favicon.ico" />

        <script type="text/javascript" src="${resources}/javascripts/jquery.js"></script>
        <script type="text/javascript" src="${resources}/javascripts/jquery.tablesorter.js"></script>
        <script type="text/javascript" src="${resources}/javascripts/jquery.bgiframe.js"></script>
        <script type="text/javascript" src="${resources}/javascripts/jquery.hoverIntent.js"></script>
        <script type="text/javascript" src="${resources}/javascripts/jquery.cluetip.js"></script>
        <script type="text/javascript" src="${resources}/javascripts/jquery.autocomplete.js"></script>
        <script type="text/javascript" src="${resources}/javascripts/jqModal.js"></script>
        <script type="text/javascript" src="${resources}/javascripts/jqDnR.js"></script>
        <script type="text/javascript" src="${resources}/javascripts/date.js"></script>
        <script type="text/javascript" src="${resources}/javascripts/mpower.js"></script
	</head>

	<body>
        <div class="bodyContent">

        <!-- Top banner, containing Logo and login status -->
        <div id="banner"></div>

        <!-- Navigation bar  -->
        <div id="navmain">
            <div class="navLeftCap"></div>
            <div class="container">
                <ul class="primaryNav">
                    <li></li>
                </ul>
                <div class="clearBoth"></div>
            </div>
            <div class="navRightCap"></div>
        </div>

        <div class="clearBoth"></div>


        <div class="contentFull mainForm">




