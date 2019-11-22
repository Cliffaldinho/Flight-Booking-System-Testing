<%@ page import="seng3150.group1.models.FlightOptionContainer" %>
<%@ page import="seng3150.group1.models.SearchCriteria" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: Dylan
  Date: 28/05/2018
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<html>
<head>
    <title>Flight Results</title>
</head>
<body>
<jsp:include page="template-parts/header.jsp" />
<script src="/js/AjaxResponseHandler.js"></script>

<script type="text/javascript">
    // As soon as the page is loaded, perform the AJAX request to perform the search and receive the results as JSON
    $(function(){
        $.ajax({
            url: '/ajax',
            // Retrieve the dates and locations from the session
            data: {'recommendation':'none',
                // Use the correct dates/locations for either a direct search or multicity/return search
                <c:choose>
                    <c:when test="${(sessionScope.searchState == 0) or (sessionScope.searchState == 1)}">
                        'departureDate':'${sessionScope.departureDate}',
                        'departure':'${sessionScope.departure}',
                        'arrival':'${sessionScope.arrival}'},
                    </c:when>
                    <c:when test="${sessionScope.searchState == 2}">
                'departureDate':'${sessionScope.additionalDepartureDate}',
                'departure':'${sessionScope.additionalDeparture}',
                'arrival':'${sessionScope.additionalDestination}'},
                    </c:when>
                </c:choose>

            type: "POST",
            dataType: 'json',
            success: function(data){
                // Save the data received from the initial search
                originalFlightData = data;
                generateResultsListing(data);

                $('.preloader').css({
                    'animation' : 'animFadeOut 1.0s forwards',
                    '-webkit-animation' : 'animFadeOut 1.0s forwards'
                });
                setTimeout(function(){ $('.preloader').hide(); }, 1000);

                // In 5 seconds, do a search (based on the user category) to find more flight options
                // The delay is needed so the user is only given the recommendation if they do not find anything quickly
                setTimeout(function(){ recommendedSearch(); }, 5000);//set to 5000
            }
        });
    });
</script>



<div class="preloader">
    <div class="plane-loading">
        <h1 >We're finding the best possible flights for you</h1>
        <div class="circle"></div>
        <jsp:include page="template-parts/globe.jsp" />

        <h3 >Soar above the rest with FlightPub</h3>
    </div>
    <script src="/js/planeLoading.js"></script>
</div>

    <% if (session.getAttribute("category").equals("Multi City")
    || (session.getAttribute("category").equals("Return"))) { %>
<h2>Your flight results - Leg <%= (int)session.getAttribute("searchState") %></h2>
    <% } else { %>
<h2>Your flight results</h2>
    <% } %>



<div class="flight-results-container">

</div>

<form action = "flight-sliders" method = "post">
    <input type="submit" value="Refine Search">
</form>

<div class="col-xs-12 flightlist">
    <div id="bottomthird" class="fade-in">
        <script type="text/javascript">
            // Perform the search to find additional flights
            function recommendedSearch() {
                $.ajax({
                    url: '/ajax',
                    // Retrieve the dates and locations from the session
                    data: {'recommendation':'${searchCriteria.userCategory}',
                    // Use the correct dates/locations for either a direct search or multicity/return search
                        <c:choose>
                            <c:when test="${(sessionScope.searchState == 0) or (sessionScope.searchState == 1)}">
                            'departureDate':'${sessionScope.departureDate}',
                            'departure':'${sessionScope.departure}',
                            'arrival':'${sessionScope.arrival}'},
                        </c:when>
                        <c:when test="${sessionScope.searchState == 2}">
                            'departureDate':'${sessionScope.additionalDepartureDate}',
                            'departure':'${sessionScope.additionalDeparture}',
                            'arrival':'${sessionScope.additionalDestination}'},
                        </c:when>
                        </c:choose>
                    type: "POST",
                    dataType: 'json',
                    success: function(data) {
                        // Save the data received from the recommended search
                        additionalFlightData = data;

                        // Get the difference in the number of flights found by the two searches
                        var difference = additionalFlightData.FlightOptionContainer.length - originalFlightData.FlightOptionContainer.length;

                        if (difference > 0)
                            $('#recommendationPreText').html("We found " + difference + " more options for you");
                        else if ((difference < 0) && (-difference < originalFlightData.FlightOptionContainer.length))
                            $('#recommendationPreText').html("We can remove " + -difference + " irrelevant options for you");
                        else
                            return; // If the additional search found nothing extra, don't show the pop-up

                        document.getElementById("bottomthird").style.display ="block";
                        $('#bottomthird').css({
                            'animation' : 'animFadeIn 0.5s forwards',
                            '-webkit-animation' : 'animFadeIn 0.5s forwards'
                        });
                    }
                });

            }

            function showRecommendation() {
                generateResultsListing(additionalFlightData);

                $('.flight-results-container').css({
                    'animation' : 'animFadeIn 0.5s forwards',
                    '-webkit-animation' : 'animFadeIn 0.5s forwards'
                });

                closeRecommendations();
            }

        </script>

        <button onclick="closeRecommendations();" value="Close">Close</button>

        <div class="container">
            <div class="row">
                <div class="test">
                    <h2>You look like a ${searchCriteria.userCategory} to us!</h2>
                    <br/>
                    <h3><span id="recommendationPreText"></span>${recommendation}</h3>
                    <form action="" method="post">
                            &nbsp;<input type="button" onclick="showRecommendation();" value="Update Search">
                    </form>
                    <button onclick="closeRecommendations();" value="Close">Close</button>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="template-parts/footer.jsp" />


