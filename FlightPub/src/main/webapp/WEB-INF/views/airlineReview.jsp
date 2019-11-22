<%@ page import="seng3150.group1.models.AirlineReviewInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-parts/header.jsp" />
<div class="content autoWidth">

    <c:choose>
        <c:when test="${selectedAirline == null}">
            <h2>Sorry, this airline could not be found</h2>
        </c:when>
        <c:otherwise>

            <div class="container">

                <div class="row">
                    <h1> ${selectedAirline.airlineName} </h1>
                    <img src="img/airline-logos/${selectedAirline.airlineCode}.png" alt="${selectedAirline.airlineName} logo">
                </div>

                <div class="row mediumTopPadding">
                    <h4> Overall Score:  ${selectedAirline.overallRating} / 10    </h4>
                </div>


                <div class="row">
                    <div class="col-xs-12 col-md-6">

                        <div class="mediumTopPadding">
                            <h5 class="inlineDisplay"> Service Rating</h5>
                                <div class="starRatingDynamicResponse">
                                    <div class="starRatingDynamicResponse layerTop" style="width: ${selectedAirline.serviceRating*10+2}%"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                                    <div class="starRatingDynamicResponse layerBottom"> <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                                </div>
                        </div>

                        <div class="smallTopPadding">
                            <h5 class="inlineDisplay"> Food and beverage</h5>
                            <div class="starRatingDynamicResponse">
                                <div class="starRatingDynamicResponse layerTop" style="width: ${selectedAirline.foodAndBeverageRating*10+2}%"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                                <div class="starRatingDynamicResponse layerBottom"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                            </div>
                        </div>

                        <div class="smallTopPadding">
                            <h5 class="inlineDisplay"> Comfort rating</h5>
                            <div class="starRatingDynamicResponse">
                                <div class="starRatingDynamicResponse layerTop" style="width: ${selectedAirline.seatAndComfortRating*10+2}%"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                                <div class="starRatingDynamicResponse layerBottom"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                            </div>
                        </div>

                        <div class="smallTopPadding">
                            <h5 class="inlineDisplay"> Entertainement rating</h5>
                            <div class="starRatingDynamicResponse">
                                <div class="starRatingDynamicResponse layerTop" style="width: ${selectedAirline.entertainementRating*10+2}%"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                                <div class="starRatingDynamicResponse layerBottom"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                            </div>
                        </div>

                    </div>

                    <div class="col-xs-12 col-md-6">

                        <div class="mediumTopPadding">
                            <h5 class="inlineDisplay"> Cleanliness Rating</h5>
                            <div class="starRatingDynamicResponse">
                                <div class="starRatingDynamicResponse layerTop" style="width: ${selectedAirline.cleanlinessRating*10+2}%"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                                <div class="starRatingDynamicResponse layerBottom"> <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                            </div>
                        </div>

                        <div class="smallTopPadding">
                            <h5 class="inlineDisplay"> Punctuality rating</h5>
                            <div class="starRatingDynamicResponse">
                                <div class="starRatingDynamicResponse layerTop" style="width: ${selectedAirline.punctualityRating*10+2}%"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                                <div class="starRatingDynamicResponse layerBottom"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                            </div>
                        </div>

                        <div class="smallTopPadding">
                            <h5 class="inlineDisplay"> Recommendation rating</h5>
                            <div class="starRatingDynamicResponse">
                                <div class="starRatingDynamicResponse layerTop" style="width: ${selectedAirline.recommendedRating*10+2}%"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                                <div class="starRatingDynamicResponse layerBottom"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="template-parts/footer.jsp" />