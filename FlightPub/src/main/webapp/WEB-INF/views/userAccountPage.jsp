<jsp:include page="template-parts/header.jsp" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="container">

        <h1>  Welcome to your account ${user.firstname}</h1>

        <div class = "row col-xs-12 col-md-12 col-lg-12 col-xl-12">
            <div class="col-xs-12 col-md-9 col-lg-9 col-xl-9 recent-flights userAccountMain">
            <h3> Your Recent Flights </h3>
            <c:choose>
            <c:when test="${not empty flightHistory}">
                <c:forEach items="${flightHistory}" var="foc">
                        <h4 class="route">${foc.departureDate}: ${foc.flyingFrom}
                            <img src="http://flightpub.clickk.com.au/imagery/arrow-icon.png" alt="right arrow" class="flight-arrow">
                            ${foc.flyingTo}
                            (<c:choose>
                                <c:when test="${foc.numOfStopovers == 0}">
                                    Direct Flight
                                </c:when>
                                <c:when test="${foc.numOfStopovers == 1}">
                                    ${foc.numOfStopovers} Stop Over
                                </c:when>
                                <c:otherwise>
                                    ${foc.numOfStopovers} Stop Overs
                                </c:otherwise>
                            </c:choose>)
                            </h4>

                        <c:forEach items="${foc.legs}" var="leg"> <!-- here each flight that the user has been on is listed -->

                            <div class="listingContainer">
                                <div class="ticketSummary">
                                    <div class="ticketFlightSpecifics">
                                        <br/><br/>
                                        <div class="ticketFlightTimesDestinations">
                                            <div class="ticketDepartureInfo">
                                                <p class="ticketDetailBold">${leg.flyingFrom}</p>
                                                <p>${leg.departureDate}</p>
                                            </div>
                                            <div class="ticketTimeInfo">
                                                <p>${leg.durationAsString}</p>
                                                <p>${leg.airline}</p>
                                            </div>
                                            <div class="ticketArrivalInfo">
                                                <p class="ticketDetailBold">${leg.flyingTo}</p>
                                                <p>${leg.arrivalDate}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="ticketCostSpecifics">
                                        <p class="ticketSummaryReview">
                                            <form action="/reviewResults" method="post">
                                                <input type="hidden" name="airlineCode" value="${leg.airlineCode}">
                                                <input name="inputButton" class="review" type="submit" value="${leg.airlineRating} / 10">
                                            </form>
                                        </p>
                                        <p class="ticketDetailBold whiteText">$${leg.priceAsString}</p>
                                        <c:choose>
                                            <c:when test = "${leg.reviewed}">
                                                Already Reviewed
                                            </c:when>
                                            <c:otherwise>
                                                <form action="reviewFlight" method="post">
                                                    <input type="hidden" name="airlineCode" value="${leg.airlineCode}" />
                                                    <input type="hidden" name="bookingId" value="${leg.bookingId}" />
                                                    <button type="submit">Review Flight</button>
                                                </form>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>

                            <br/>








                        </c:forEach>
                    <p></p>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <br><br>
                <h4>You've not booked any flights yet. Time to get searching!</h4>
            </c:otherwise>
            </c:choose>
            </div>

            <div class="col-xs-12 col-md-3 col-lg-3 col-xl-3">
                <div>

                    <h3> Your Details </h3>
                    <p><strong> Name: </strong>${user.firstname} ${user.middlename} ${user.lastname} </p>
                    <p><strong> Email: </strong>${user.email}</p>
                    <p><strong> Phone Number: </strong>${user.phonenumber}</p>
                    <p><strong> Address: </strong>${user.address} </p>

                    <form action="/account/edit-profile" method="get">
                        <input type="submit" value="Edit">
                    </form>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="details col-xs-12 col-md-6">
                <!--Old location of profile details -->
                <p></p><p></p>
            </div>
        </div>

</div>

        <jsp:include page="template-parts/footer.jsp" />



