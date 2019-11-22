    // File containing functions used to receive JSON data from AJAX requests, convert it into HTML, and append it to
    // sections of the page, displaying the information

//Takes the JSON data of a list of FlightOptionContainers and builds the html of the results listing.
var additionalFlightData; // Holds the JSON array of Flights from the initial search
var originalFlightData; // Holds the array of flights from the recommendation search
// We need these (so we can compare the two arrays and see how many more/less flights the recommendation provides

function generateResultsListing(data) {
    $('.flight-results-container').html('');
    var i;
    var flightOption = "";
    for (i = 0; i < data.FlightOptionContainer.length; i++) {

        var individualLegScore;
        var reviewsNotFound;
        var sameAirline = "StartValue", sameAirlineIndicator;

        var legsInfo = '<div class="detailedViewListing" style="display:none;">';
        var dotListItems = "";
        var j;
        for (j = 0; j < data.FlightOptionContainer[i].FlightLegContainer.length; j++) {
            var leg = data.FlightOptionContainer[i].FlightLegContainer[j];

            if (j > 0) {
                dotListItems += '<li class="listitem"></li>';
            }

            if (leg.airlineRating == 0) {
                individualLegScore = "TBA";
                reviewsNotFound = '<input name="inputButton" class= "viewReviewButton" type="submit" value="'+ individualLegScore +'" disabled />' ;
            }
            else {
                individualLegScore = leg.airlineRating + " / 10";
                reviewsNotFound = '<input name="inputButton" class= "viewReviewButton" type="submit" value="'+ individualLegScore +'" style= "text-decoration: underline;" />' ;
            }

            if(sameAirline == "StartValue") {
                sameAirline = leg.airlineCode;
                sameAirlineIndicator = true;
            }

            else if (sameAirline != leg.airlineCode) {
                sameAirlineIndicator = false;
            }

            legsInfo +=
                '<div class="listingContainter">' +
                '   <div class="listingHeader">' +
                '       <div class="listingHeaderElements">Leg ' +(j+1) +'</div>' +
                '       <div class="listingHeaderElements">' +leg.departureLocation +" - " +leg.arrivalLocation +'</div>' +
                '       <div class="listingHeaderElements">' +leg.duration +'</div>' +
                '   </div>' +
                '   <div class="listingContent">' +
                '       <div class="listingReview">' +'<form class ="viewReviewForm" action ="/reviewResults" method="post">' +'<input type="hidden" name ="airlineCode" value="'+leg.airlineCode+'">' +reviewsNotFound +'</form>' +'</div>' +
                '       <div class="listingSummaryContainer">' +
                '           <p class="listingDetail">' +leg.departureDate +' - ' +leg.arrivalDate +'</p>' +
                '           <p class="listingDetail">' +leg.airline +' | ' +leg.aircraft +'</p>' +
                '           <p class="listingDetail">Flight Number: ' +leg.flightNumber +'</p>' +
                '       </div>' +
                '       <div class="listingClass">' +
                '           <div class="classIcon"></div>' +
                '           <div class="classText">' +leg.class +'</div>' +
                '       </div>' +
                '   </div>' +
                '</div>';
        }

        legsInfo += '</div>';

        var viewLegsButton = '<form action="view-flight" method="get">';
        var k;
        for (k = 0; k < data.FlightOptionContainer[i].FlightLegContainer.length; k++) {
            var leg = data.FlightOptionContainer[i].FlightLegContainer[k];
            viewLegsButton += '<input type="hidden" name="flightInformationId" value = "' + leg.flightInformationId + '">' +
                '<input type="hidden" name="legNo" value = "' + leg.legNumber + '">';
        }
        viewLegsButton += '<button name="optionIndex" class="blue" value="View Flight" type="submit">View Flight</button>\n' +
            ' </form>';

        var reviewScore;

        if (data.FlightOptionContainer[i].airlineAvgScore == 0) {
            reviewScore = '<p class="ticketSummaryReview">TBA</p>';
        }
        else if (j > 1 && sameAirlineIndicator != true){
            reviewScore = data.FlightOptionContainer[i].airlineAvgScore + " / 10";
        }
        else {
            var scoreFromReviewsClickable = data.FlightOptionContainer[i].airlineAvgScore + " / 10";
            reviewScore = '<form action ="/reviewResults" method="post">' +'<input type="hidden" name ="airlineCode" value="'+leg.airlineCode+'">' +'<input name="inputButton" class= "review" type="submit" value="'+ scoreFromReviewsClickable +'"/>' + '</form>';
        }

        var flightResult =
            '<div class="listingContainer">' +
                '<div class="ticketSummary">' +
                    '<div class="ticketFlightSpecifics" onclick="expandFlightOption(this)">' +
                        '<div class="ticketScoreAirlineClass">' +
                            '<p class="score">' +data.FlightOptionContainer[i].score +'</p>' +
                        '</div>' +
                        '<div class="ticketFlightTimesDestinations">' +
                            '<div class="ticketDepartureInfo">' +
                                '<p class="ticketDetailBold">' +data.FlightOptionContainer[i].departureLocation +'</p>' +
                                '<p>' +data.FlightOptionContainer[i].departureDate +'</p>' +
                            '</div>' +
                            '<div class="ticketTimeInfo">' +
                                '<p>' +data.FlightOptionContainer[i].duration +'</p>' +
                                '<ul class="listname">'
                                    + dotListItems +
                                '</ul>';

        // Cater message for stopovers according to particular flight listing
        var stopoverDescription = '';
        var totalStopovers = j-1;

        if (totalStopovers === 0)
            stopoverDescription = '<p class="stopoverDirect">Direct</p>';
        else if (totalStopovers === 1)
            stopoverDescription = '<p class="stopoversExist">1 stop</p>';
        else
            stopoverDescription = '<p class="stopoversExist">' +data.FlightOptionContainer[i].numOfStopovers +' stops</p>';

        flightResult += stopoverDescription +
                            '</div>' +
                            '<div class="ticketArrivalInfo">' +
                                '<p class="ticketDetailBold">' +data.FlightOptionContainer[i].arrivalLocation +'</p>' +
                                '<p>' +data.FlightOptionContainer[i].arrivalDate +'</p>' +
                            '</div>'+
                        '</div>' +
                    '</div>' +
                    '<div class="dot"></div>' +
                    '<div class="ticketCostSpecifics">' +
                        '<p class="ticketSummaryReview">' +reviewScore +'</p>' +
                        '<p class="ticketDetailBold whiteText">$' +data.FlightOptionContainer[i].price +'</p>'
                        + viewLegsButton +
                    '</div>' +
                '</div>'
                + legsInfo +
            '</div>';
        flightOption += flightResult;
    }
    $('.flight-results-container').html(flightOption);
}

// The function to call for each flight listing's onclick event in the results page to display the leg information div
function expandFlightOption(flightOption) {
    $(flightOption).parent().next('.detailedViewListing').slideToggle();
}

// Takes the JSON data of a list of FlightOptionContainers and builds the html of the shortlist.
function generateShortList(data) {

    var shortlistContent = '<button onclick="closeShortlist();" value="Close" class="close-btn">Close</button>\n' +
        '\n' +
        '        <h3>Shortlisted Flights</h3>\n' +
        '    <br>' +
        '<div class="shortlistContent">';

    if (data.FlightOptionContainer.length == 0) {
        shortlistContent += '<h4>You have not shortlisted any flights yet.</h4>';
        $('#shortlist-header-text').html('Shortlist');
    } else {

        var i;
        var flightOption = "";
        for (i = 0; i < data.FlightOptionContainer.length; i++) {

            var individualLegScore;
            var reviewsNotFound;
            var sameAirline = "StartValue", sameAirlineIndicator;

            var legsInfo = '<div class="detailedViewListing" style="display:none;">';
            var dotListItems = "";
            var j;
            for (j = 0; j < data.FlightOptionContainer[i].FlightLegContainer.length; j++) {
                var leg = data.FlightOptionContainer[i].FlightLegContainer[j];

                if (j > 0) {
                    dotListItems += '<li class="listitem"></li>';
                }

                if (leg.airlineRating == 0) {
                    individualLegScore = "TBA";
                    //reviewsNotFound = '<input name="inputButton" class= "viewReviewButton" type="submit" value="'+ individualLegScore +'" disabled />' ;
                }
                else {
                    individualLegScore = leg.airlineRating + " / 10";
                    //reviewsNotFound = '<input name="inputButton" class= "viewReviewButton" type="submit" value="'+ individualLegScore +'" style= "text-decoration: underline;" />' ;
                }
                reviewsNotFound = '<form action="/reviewResults" method="post">' +
                    '<input name="inputButton" class="review-payment" type="button" value="' + individualLegScore + '">' +
                    '</form>';

                if(sameAirline == "StartValue") {
                    sameAirline = leg.airlineCode;
                    sameAirlineIndicator = true;
                }

                else if (sameAirline != leg.airlineCode) {
                    sameAirlineIndicator = false;
                }

            }

            var removeButton = '<form action="/shortlist" method="post" onclick="submitToShortlist(this)">';


            var viewLegsButton = '<form action="view-flight" method="get">';
            var k;
            for (k = 0; k < data.FlightOptionContainer[i].FlightLegContainer.length; k++) {
                var leg = data.FlightOptionContainer[i].FlightLegContainer[k];
                viewLegsButton += '<input type="hidden" name="flightInformationId" value = "' + leg.flightInformationId + '">' +
                    '<input type="hidden" name="legNo" value = "' + leg.legNumber + '">';
                removeButton += '<input type="hidden" name="flightInformationId" value = "' + leg.flightInformationId + '">' +
                    '<input type="hidden" name="legNo" value = "' + leg.legNumber + '">';
            }
            viewLegsButton += '<button name="optionIndex" class="blue" value="View Flight" type="submit">View Flight</button>\n' +
                ' </form>';

            removeButton += '<input type="hidden" name="action" value = "remove">\n' +
                '                <i class="far fa-window-close fa-2x"></i>\n' +
                '                </form>';

            var reviewScore;

            if (data.FlightOptionContainer[i].airlineAvgScore == 0) {
                reviewScore = "TBA";
            }
            else if (j > 1 && sameAirlineIndicator != true){
                reviewScore = data.FlightOptionContainer[i].airlineAvgScore + " / 10";
            }
            else {
                var scoreFromReviewsClickable = data.FlightOptionContainer[i].airlineAvgScore + " / 10";
                reviewScore = '<form class ="viewReviewFormFlightOption" action ="/reviewResults" method="post">' +'<input type="hidden" name ="airlineCode" value="'+leg.airlineCode+'">' +'<input name="inputButton" class= "viewReviewButtonFlightOption" type="submit" value="'+ scoreFromReviewsClickable +'"/>' + '</form>';
            }

            var flightResult =
                '<div class="listingContainer">' +
                '<div class="ticketSummary" onclick="expandFlightOption(this)">' +
                '<div class="ticketFlightSpecifics">' +
                '<div class="ticketScoreAirlineClass">' +
                removeButton +
                '</div>' +
                '<div class="ticketFlightTimesDestinations">' +
                '<div class="ticketDepartureInfo">' +
                '<p class="ticketDetailBold">' +data.FlightOptionContainer[i].departureLocation +'</p>' +
                '<p>' +data.FlightOptionContainer[i].departureDate +'</p>' +
                '</div>' +
                '<div class="ticketTimeInfo">' +
                '<p>' +data.FlightOptionContainer[i].duration +'</p>' +
                '<ul class="listname">'
                + dotListItems +
                '</ul>';

            // Cater message for stopovers according to particular flight listing
            var stopoverDescription = '';
            var totalStopovers = j-1;

            if (totalStopovers === 0)
                stopoverDescription = '<p class="stopoverDirect">Direct</p>';
            else if (totalStopovers === 1)
                stopoverDescription = '<p class="stopoversExist">1 stop</p>';
            else
                stopoverDescription = '<p class="stopoversExist">' +data.FlightOptionContainer[i].numOfStopovers +' stops</p>';

            flightResult += stopoverDescription +
                '</div>' +
                '<div class="ticketArrivalInfo">' +
                '<p class="ticketDetailBold">' +data.FlightOptionContainer[i].arrivalLocation +'</p>' +
                '<p>' +data.FlightOptionContainer[i].arrivalDate +'</p>' +
                '</div>'+
                '</div>' +
                '</div>' +
                '<div class="dot"></div>' +
                '<div class="ticketCostSpecifics">'
                + reviewsNotFound +
                '<p class="ticketDetailBold whiteText">$' +data.FlightOptionContainer[i].price +'</p>'
                + viewLegsButton +
                '</div>' +
                '</div>'
                + legsInfo +
                '</div> </div>';
            flightOption += flightResult;
        }
        shortlistContent += flightOption + "</div>";


    }

    $('#shortlist-header-text').html('Shortlist (' + data.FlightOptionContainer.length + ')');
    $("#shortlist").html(shortlistContent);
    showShortlist();
}

//Method to convert the data in a form into a JSON object
//From stackoverflow user "some"
//https://stackoverflow.com/questions/41431322/how-to-convert-formdatahtml5-object-to-json
function formToJSON( elem ) {
    var current, entries, item, key, output, value;
    output = {};
    entries = new FormData( elem ).entries();
    // Iterate over values, and assign to item.
    while ( item = entries.next().value )
    {
        // assign to variables to make the code more readable.
        key = item[0];
        value = item[1];
        // Check if key already exist
        if (Object.prototype.hasOwnProperty.call( output, key)) {
            current = output[ key ];
            if ( !Array.isArray( current ) ) {
                // If it's not an array, convert it to an array.
                current = output[ key ] = [ current ];
            }
            current.push( value ); // Add the new value to the array.
        } else {
            output[ key ] = value;
        }
    }
    return output;
}


// Function to be called by a button in a form which will either remove or add a flight to the shortlist
// The information for the flight as well as whether to add or remove is to be inside the form passed in as a parameter.
// The form should have a flightInformationId, and legNo for each leg, as well as "remove" or "add" as the action
function submitToShortlist(formArray) {

    //Convert the form containing the flight option container's info into a JSON object
    var formAsJson = formToJSON(formArray);
    //Convert that JSON object into a string containing the information as parameters
    var formAsString = "";
    var i;

    //Check if there are more than 1 leg and add the information for each leg in a loop
    if (formAsJson.flightInformationId.constructor === Array) {
        for (i = 0; i < formAsJson.flightInformationId.length; i++) {
            formAsString += "flightInformationId=" + formAsJson.flightInformationId[i];
            formAsString += "&legNo=" + formAsJson.legNo[i] + "&";
        }
    } else { //otherwise there is only a single flight leg
        formAsString += "flightInformationId=" + formAsJson.flightInformationId;
        formAsString += "&legNo=" + formAsJson.legNo + "&";
    }
    formAsString += "action=" + formAsJson.action;

    //Send ajax request with these parameters
    $.ajax({
        url : "/shortlist",
        data: formAsString,
        type: "POST",
        dataType: 'json',
        success: function (data) {
            // Populate the shortlist with the updated shortlist received as JSON
            generateShortList(data);
        }
    });
    // When we are removing from the shortlist, fade out the old contents so there isn't a jarring instant change
    if (formAsJson.action === "remove")
        $('.shortlistContent').css({
            'animation' : 'animFadeOut 0.25s forwards',
            '-webkit-animation' : 'animFadeOut 0.25s forwards'
        });

}
