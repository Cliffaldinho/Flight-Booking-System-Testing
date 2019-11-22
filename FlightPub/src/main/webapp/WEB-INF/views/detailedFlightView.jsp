
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page session="true" %>
<jsp:include page="template-parts/header.jsp" />



<div class="container-fluid">

    <c:choose>
        <c:when test="${selectedFlight == null}">
            <h2>Sorry, this flight could not be found</h2>
        </c:when>
        <c:otherwise>
                    <p class="flight-return">${selectedFlight.numOfStopovers} Stop Overs</p>
        <div class="row">






            <div class="col-xs-12 col-md-6 col-md-offset-5">
                <div class="flight-route-price">
                    <br>
                    <p class="route">From: ${selectedFlight.flyingFrom}
                        <img src="http://flightpub.clickk.com.au/imagery/arrow-icon.png" alt="right arrow" class="flight-arrow">
                        To: ${selectedFlight.flyingTo}</p>

                    <p class="flight-price">$${selectedFlight.totalPriceAsString}</p>
                    <p class="flight-return">One Way</p>
                    <p class="flight-return">Total Duration: ${selectedFlight.durationAsString}</p>

                    <!-- Display number of Stopovers, or "direct" if no stopovers-->
                    <c:choose>
                        <c:when test="${selectedFlight.numOfStopovers == 0}">
                            <p class="flight-return">Direct Flight</p>
                        </c:when>
                        <c:when test="${selectedFlight.numOfStopovers == 1}">
                            <p class="flight-return">${selectedFlight.numOfStopovers} Stop Over</p>
                        </c:when>
                        <c:otherwise>
                            <p class="flight-return">${selectedFlight.numOfStopovers} Stop Overs</p>
                        </c:otherwise>
                    </c:choose>

                </div>

            </div>
        </div>


    <div class="row">

        <div class="col-xs-12 col-md-6 col-md-offset-5">
                <c:set var = "legNo" scope = "page" value = "1"/>
                <c:forEach items="${selectedFlight.legs}" var="leg">

                        <div class="airline-recommendation">
                            <img class="airline-logo" src="/img/airline-logos/${leg.airlineCode}.png" alt="${leg.airline} logo"><br>
                            <p>${leg.airlineRating}/10 users recommend</p>
                        </div>


                    <h3>Leg <c:out value = "${legNo}"/> </h3>
                    <table class="flight-detailed-info-table">
                        <tbody>
                        <tr><td class="heading-col">Departing From:</td><td class="data-col">${leg.flyingFrom}</td></tr>
                        <tr><td class="heading-col">Departure Time:</td><td class="data-col">${leg.departureDate}</td></tr>
                        <tr><td class="heading-col">Arriving At:</td><td class="data-col">${leg.flyingTo}</td></tr>
                        <tr><td class="heading-col">Arrival Time:</td><td class="data-col">${leg.arrivalDate}</td></tr>
                        <tr><td class="heading-col">Duration:</td><td class="data-col">${leg.durationAsString}</td></tr>
                        <tr><td class="heading-col">Price:</td><td class="data-col">$${leg.priceAsString}</td></tr>
                        <tr><td class="heading-col">Class:</td><td class="data-col">${leg.className}</td></tr>
                        <tr><td class="heading-col">Airline:</td><td class="data-col">${leg.airline}</td></tr>
                        <tr><td class="heading-col">Aircraft:</td><td class="data-col">${leg.aircraft}</td></tr>



                        </tbody></table>

                    <c:set var = "legNo" scope = "page" value="${legNo +1}"/>
                </c:forEach>
            </div>

        <div class="col-xs-12 col-md-3">
            <form id="shortlistForm" action="/shortlist" method="post">
                <c:forEach items="${selectedFlight.legs}" var="leg">
                    <input type="hidden" name="flightInformationId" value = "${leg.flightInformationId}">
                    <input type="hidden" name="legNo" value = "${leg.legNo}">
                </c:forEach>
                <input type="hidden" name="action" value = "add">
                <input type="button" onclick="submitToShortlist(this.form)" value="Add to Shortlist">

            </form>
            <c:choose>
                <c:when test="${sessionScope.searchState == 0}">


                    <form action = "payment" method = "post">
                        <c:forEach items="${selectedFlight.legs}" var="leg">
                            <input type="hidden" name="flightInformationId" value = "${leg.flightInformationId}">
                            <input type="hidden" name="legNo" value = "${leg.legNo}">
                        </c:forEach>
                        <input type="submit" value="Book Now">
                    </form>
                </c:when>

                <c:when test="${sessionScope.searchState == 1}">
                    <form id="legSelection1" action="/leg-selection" method="post">
                        <c:forEach items="${selectedFlight.legs}" var="leg">
                            <input type="hidden" name="flightInformationId" value = "${leg.flightInformationId}">
                            <input type="hidden" name="legNo" value = "${leg.legNo}">
                        </c:forEach>
                        <input type="hidden" name="currentLeg" value="1">
                        <input type="submit" value="Select First Leg">
                    </form>
                </c:when>

                <c:when test="${sessionScope.searchState == 2}">
                    <form id="legSelection2" action="/leg-selection" method="post">
                        <c:forEach items="${selectedFlight.legs}" var="leg">
                            <input type="hidden" name="flightInformationId" value = "${leg.flightInformationId}">
                            <input type="hidden" name="legNo" value = "${leg.legNo}">
                        </c:forEach>
                        <input type="hidden" name="currentLeg" value="2">
                        <input type="submit" value="Select Second Leg">
                    </form>
                </c:when>
            </c:choose>

        </div>
    </div>
        </c:otherwise>
    </c:choose>

</div>

<jsp:include page="template-parts/footer.jsp" />