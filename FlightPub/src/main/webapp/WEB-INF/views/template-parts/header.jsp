
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Fredoka+One|PT+Sans" rel="stylesheet">
    <script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
    <link rel="icon" href="http://flightpub.clickk.com.au/logo.png">
    <script src="/js/AjaxResponseHandler.js"></script>
    <title>Welcome to FlightPub</title>

</head>
<body>

<header>


    <nav class="navbar navbar-expand-md navbar-inverse sticky-top">
        <img src="http://flightpub.clickk.com.au/logo.jpg" alt="FlightPub Logo" class="logo">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"><i class="fas fa-bars"></i></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/"><i class="fa fa-home" aria-hidden="true"></i>Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/"><i class="fa fa-search" aria-hidden="true"></i>New Search </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/account/home"><i class="fa fa-user" aria-hidden="true"></i>Account </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" onclick="showShortlist();"><i class="fa fa-list" aria-hidden="true"></i>
                        <span id="shortlist-header-text">
                        <c:choose>
                            <c:when test = "${empty shortlist}">
                                Shortlist
                            </c:when>

                            <c:otherwise>
                                Shortlist (${fn:length(shortlist)})
                            </c:otherwise>
                        </c:choose>
                        </span>
                    </a>
                </li>

                <li class="nav-item">
                    <c:choose>
                        <c:when test = "${empty user}">
                            <a class="nav-link" onclick="showLogin();"><i class="fas fa-sign-in-alt"></i>Log In</a>
                        </c:when>

                        <c:otherwise>
                            <a class="nav-link" href="/logout";><i class="fas fa-sign-out-alt"></i>Log Out</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </nav>
</header>
<body>




<div id="not-home" class="container margintop150">
    <div  class="row">


