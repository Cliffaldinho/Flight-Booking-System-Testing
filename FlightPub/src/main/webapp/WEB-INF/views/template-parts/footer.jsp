
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

</div>
</div>
</div>
</div>

<div class="account" id="account">
    <div class="placeholder">
    <button onclick="closeLogin();" value="Close" class="close-btn">Close</button>

                <h2>Log in</h2>
                <form method="post" action="/login">

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa-lg" aria-hidden="true"></i></span>
                        <input type="text" name="username" placeholder="Username"/>
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"  style="margin-right: 7px;"></i></span>
                        <input type="password" name="password" placeholder="Password"/>
                    </div><br>
                    ${loginErrorMessage}
                    <input type="submit" />
                </form>
    <h5>Don't have an account yet? <a href = "register">Register here</a></h5>
    </div>
</div>

<div class="shortlist " id="shortlist">
    <button onclick="closeShortlist();" value="Close" class="close-btn">Close</button>

    <h3>Shortlisted Flights</h3>
    <br>
    <div class="shortlistContent">
    <c:choose>
        <c:when test = "${empty shortlist}">
            <h4>You have not shortlisted any flights yet.</h4>
        </c:when>

        <c:otherwise>
            <c:forEach items="${shortlist}" var="flight">
                <div class="listingContainer">
                    <div class="ticketSummary">
                        <div class="ticketFlightSpecifics">


                            <form action="/shortlist" method="post" onclick="submitToShortlist(this)">
                                <c:forEach items="${flight.legs}" var="leg">
                                    <input type="hidden" name="flightInformationId" value = "${leg.flightInformationId}">
                                    <input type="hidden" name="legNo" value = "${leg.legNo}">
                                </c:forEach>
                                <input type="hidden" name="action" value = "remove">
                                <i class="far fa-window-close fa-2x"></i>
                            </form>

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
                            <p class="ticketDetailBold whiteText">$${flight.totalPriceAsString}</p>
                            <form action="view-flight" method="get">
                                <c:forEach items="${flight.legs}" var="leg">
                                    <input type="hidden" name="flightInformationId" value = "${leg.flightInformationId}">
                                    <input type="hidden" name="legNo" value = "${leg.legNo}">
                                </c:forEach>
                                <input type="hidden" name="action" value = "remove">
                                <button name="optionIndex" class="blue" value="View Flight" type="submit">View Flight</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </div>
</div>

<footer>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-md-4">
                <h3>flightpub</h3>
                <ul>
                    <li>
                        <a href="../">
                            Home
                        </a>
                    </li>
                    <li>
                        <a href="../">
                            New Search
                        </a>
                    </li>
                    <li>
                        <a href="/account/home">
                            Account
                        </a>
                    </li>
                    <li>
                        <c:choose>
                            <c:when test = "${empty user}">
                                <a onclick="showLogin();">Log In</a>
                            </c:when>

                            <c:otherwise>
                                <a href = "logout">Log Out</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
            <div class="col-xs-12 col-md-4">
                <h3>values</h3>
                <p>Fly a mile higher with flightpub</p>
            </div>
            <div class="col-xs-12 col-md-4">
                <h3>group one</h3>
                <ul>
                    <li> Christopher Chapman </li>
                    <li> Dylan Cawsey </li>
                    <li> Nathan Rudge</li>
                    <li> Thomas Schwenke </li>
                    <li> Victor Hondermarck</li>
                </ul>
            </div>
        </div>
    </div>
</footer>
</body>

<script type="text/javascript">

        $( document ).ready(function() {
            if(window.location.pathname == '/') {
                $("#not-home").removeClass("container").removeClass("margintop150").addClass("margintop120");

            }
        });

        setInterval(function() {
            $("#additionalDeparture").val($("#arrival").val());
        }, 100);

        $(function(){
            $("#category").on('change', function(){
                if ($("#category option:selected").text() == 'Multi City') {
                    showAdditionalDestinations();
                }
            })

        });



    var display = true;

    function testScroll(ev){
        if((window.pageYOffset>1000) && display)
        {
            //document.getElementById("bottomthird").style.display ="block";
            //display = false;
        }
    }
    window.onscroll=testScroll;

    function closeRecommendations()
    {
        display = false;
        document.getElementById("bottomthird").style.display ="none";
    }

    function showReviews() {
        document.getElementById("ReviewPopup").style.display = "block";
    }

    function closeReviews() {
        document.getElementById("ReviewPopup").style.display = "none";
    }

    $('#show-legs-btn').hover(
        function() {
            //console.log('hover over');
            $(this).children('#show-legs').show();
        },
        function() {
            //console.log('hover out');
            $(this).children('#show-legs').hide();
        });


    function advancedOptions()
    {
        document.getElementById("advanced").style.display = "block";
    }

    function showLogin()
    {
        document.getElementById("shortlist").style.display = "none";
        document.getElementById("account").style.display = "block";
    }

    function closeLogin()
    {
        document.getElementById("account").style.display = "none";
    }

    function showShortlist()
    {
        document.getElementById("shortlist").style.display = "block";
        document.getElementById("account").style.display = "none";
        $('#shortlist').children().css({
            'animation' : 'animSlideIn 0s forwards',
            '-webkit-animation' : 'animSlideIn 0s forwards'
        });
        $('#shortlist').css({
            'animation' : 'animSlideIn 0.5s forwards',
            '-webkit-animation' : 'animSlideIn 0.5s forwards'
        });
    }

    function closeShortlist()
    {
        $('#shortlist').children().css({
            'animation' : 'animFadeOut 0.25s forwards',
            '-webkit-animation' : 'animFadeOut 0.25s forwards'
        });
        $('#shortlist').animate({width: 'toggle'});
        setTimeout(function(){ document.getElementById("shortlist").style.display = "none"; }, 500);

        document.getElementById("account").style.display = "none";
    }

    function showAdditionalDestinations()
        {
            if ((document.getElementById("category").text = "Multi City") || (document.getElementById("additionalDestinationCheck").checked)) {
                document.getElementById("showadditional").style.display = "block";
                document.getElementById("category").value = "Multi City"
                document.getElementById("hide-destination-check").style.display = "none";
            }
        }

    function autofillReturn()
    {
        if ((document.getElementById("category").text = "Return"))
        {
            document.getElementById("additionalDeparture").value = document.getElementById("arrival").value;
            document.getElementById("additionalDestination").value = document.getElementById("departure").value;
        }
    }

</script>
</html>