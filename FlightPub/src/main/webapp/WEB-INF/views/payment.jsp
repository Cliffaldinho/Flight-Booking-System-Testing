<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="true" %>


<jsp:include page="template-parts/header.jsp" />

<div class="content">

    <div class="container-fluid">
        <div class="row">
            <!--Payment Details Form-->
            <div class="col-xs-12 col-md-6">
                <h2>Checkout</h2>
                <form action = "confirm-payment" method="post">
                    <h3>1. Please provide your billing details, or <a href="payment.html">sign in</a></h3>
                    <table>
                        <tr><td class = "heading-col">First Name</td> <td class="data-col"><input type="text" name="firstname" value = "${user.firstname}"></td></tr>
                        <tr><td class = "heading-col">Last name</td> <td class="data-col"><input type="text" name="lastname" value = "${user.lastname}"></td></tr>
                        <tr><td class = "heading-col">Email</td> <td class="data-col"><input type="email" name="email" value = "${user.email}"></td></tr>
                        <tr><td class = "heading-col">Confirm Email</td> <td class="data-col"><input type="email" name="email2" value = "${user.email}"></td></tr>
                        <tr><td class = "heading-col">Phone number</td> <td class="data-col"><input type="text" name="phone" value = "${user.phonenumber}"></td></tr>
                        <tr><td class = "heading-col">Address</td> <td class="data-col"><input type="text" name="address" value = "${user.address}"></td></tr>
                        <tr><td class = "heading-col">Date of Birth</td> <td class="data-col"><input type="date" name="dob"></td></tr>
                    </table>

                    <h3>2. Please provide your Visa Or Mastercard details</h3>

                    <table>
                        <tr><td class = "heading-col">Name on Card</td> <td class="data-col"><input type="text" name="nameOnCard"></td></tr>
                        <tr><td class = "heading-col">Card Type</td> <td class="data-col"><select name="cardType">
                            <option value="VISA">VISA</option>
                            <option value="MasterCard">MasterCard</option>
                        </select>
                        </td></tr>
                        <tr><td class = "heading-col">Card Number</td> <td class="data-col"><input type="text" name="cardNumber"></td></tr>
                        <tr><td class = "heading-col">CVV</td> <td class="data-col"><input type="text" name="cvv"></td></tr>
                    </table>

                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 col-md-6">
                                <input type="submit" value="Confirm Purchase">
                            </div>
                            <div class="col-xs-12 col-md-6">
                                <img src="http://flightpub.clickk.com.au/imagery/eway-logo.jpg" class="eway-logo">
                            </div>
                        </div>
                    </div>

                </form>
            </div>

            <!--Flight Booking Summary-->
            <div class="col-xs-12 col-md-6">
                <div class="payment-flight-summary">
                    <h3>Ticket Details</h3>


                    <c:set var = "total" scope = "page" value = "${0}"/>
                    <c:forEach items="${sessionScope.selectedFlightList}" var="flight">
                        <c:set var = "total" scope = "page" value = "${total + flight.totalPrice}"/>
                        <div class="listingContainer">
                            <div class="ticketSummary">
                                <div class="ticketFlightSpecifics">
                                    <br/><br/>
                                    <div class="ticketFlightTimesDestinations">
                                        <div class="ticketDepartureInfo">
                                            <p class="ticketDetailBold">${flight.flyingFrom}</p>
                                            <p>${flight.departureDate}</p>
                                        </div>
                                        <div class="ticketTimeInfo">
                                            <p>${flight.durationAsString}</p>
                                            <ul class="listname">
                                                <c:forEach begin="1" end="${flight.numOfStopovers}" varStatus="loop">
                                                    <li class="listitem"></li>
                                                </c:forEach>
                                            </ul>
                                            <c:choose>
                                                <c:when test = "${flight.numOfStopovers == 0}">
                                                    <p class="stopoverDirect">Direct</p>
                                                </c:when>
                                                <c:when test = "${flight.numOfStopovers == 1}">
                                                    <p class="stopoversExist">1 stop</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p class="stopoversExist">${flight.numOfStopovers} stops</p>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="ticketArrivalInfo">
                                            <p class="ticketDetailBold">${flight.flyingTo}</p>
                                            <p>${flight.arrivalDate}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="dot"></div>
                                <div class="ticketCostSpecifics">
                                    <p class="ticketSummaryReview">
                                        <c:choose>
                                            <c:when test="${flight.averageAirlineRating == 0}">
                                                <form action="/reviewResults" method="post">
                                                    <input name="inputButton" class="review-payment" type="button" value="TBA">
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <form action="/reviewResults" method="post">
                                                    <input name="inputButton" class="review-payment" type="button" value="${flight.averageAirlineRating} / 10">
                                                </form>
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                    <p class="ticketDetailBold whiteText">$ ${flight.totalPriceAsString}</p>

                                </div>
                            </div>
                        </div>
                    </c:forEach>


                    <strong>Order Total: <fmt:formatNumber value = "${total}" type = "currency" currencySymbol="$"/></strong>

                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="template-parts/footer.jsp" />

