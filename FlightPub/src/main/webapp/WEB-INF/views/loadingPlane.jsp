<%@ page import="seng3150.group1.models.FlightOptionContainer" %>
<%@ page import="java.util.LinkedList" %><%--
Created by IntelliJ IDEA.
User: Dylan
Date: 28/05/2018
Time: 12:01 AM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Flight Results</title>
</head>
<body>
<jsp:include page="template-parts/header.jsp" />


<div class="plane-loading">
<h1 >We're finding the best possible flights for you</h1>

<div class="circle"></div>
<jsp:include page="template-parts/globe.jsp" />

<h3 >Soar above the rest with FlightPub</h3>
</div>
<script src="/js/planeLoading.js"></script>

<jsp:include page="template-parts/footer.jsp" />