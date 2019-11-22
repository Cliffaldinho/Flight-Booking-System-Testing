<%--
   Created by IntelliJ IDEA.
   User: Dylan
   Date: 15/05/2018
   Time: 9:23 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome To FlightPub</title>
</head>
<body>
<jsp:include page="template-parts/header.jsp" />

<div class="hero">
    <form class="" method="post" action="/homepage-form">
        <h1> Welcome to flightpub ${user.firstname}</h1>
        <div class="container">
            <div class="row">
                <div class="third">
                    <label>Flying From</label>
                    <input type="text" class="form-control autocomplete" id="departure" name="departure" value="Sydney, Australia">
                </div>
                <div class="third">
                    <label>Flying To</label>
                    <input type="text" class="form-control autocomplete" id="arrival"  name="arrival" value="Melbourne, Australia">
                </div>
                <div class="third">
                    <label>Departure Date</label><br>
                    <input id="departuredate" type="date" name="departureDate" value="2018-09-27" >
                </div>
            </div>
            <div id="showadditional" class="row">
                <div class="third">
                    <label>Additional Departure</label>
                    <input type="text" class="form-control autocomplete" id="additionalDeparture" name="additionalDeparture" value="Melbourne, Australia">
                </div>
                <div class="third">
                    <label>Additional Destination</label>
                    <input type="text" class="form-control autocomplete" id="additionalDestination" name="additionalDestination" value="Perth, Australia">
                </div>
                <div class="third">
                    <label>Departure Date</label>
                    <input id="additionalDepartureDate" type="date" name="additionalDepartureDate" value="2018-09-30" >
                </div>
            </div>
        </div>
            <div class="container">
                    <label>Category</label>
                    <select class="form-control" id="category" name="category" onchange="autofillReturn();">
                        <option>One Way</option>
                        <option>Return</option>
                        <option>Multi City</option>
                    </select>
            </div>
            <div class="container submit-margin">
                <input type="submit">
            </div>

    </form>
</div>
<script type="text/javascript">
    var countries = ['Adelaide, Australia',
        'Amsterdam, Netherlands',
        'Atlanta, United States',
        'Bangkok, Thailand',
        'Brisbane, Australia',

        'Canberra, Australia'
        ,'Paris - Charles De Gaulle, France',
        'Cairns, Australia',

        'Doha, Qatar'
        ,'Darwin, Australia',
        'Dubai, Unit Arab Emirates',
        'Rome-Fiumicino, Italy'
        ,'Rio De Janeiro, Brazil',
        'Hobart, Australia',
        'Helsinki, Finland',
        'Hong Kong, China'
        ,'Honolulu, United States'
        ,
        'New York - JFK, United States',
        'Johannesburg, South Africa'
        ,'Kuala Lumpur, Malaysia',
        'Los Angeles, United States',
        'New York - Laguardia, United States',
        'London-Gatwick, United Kingdom'
        ,
        'London-Heathrow, United Kingdom',
        'Madrid, Spain',
        'Melbourne, Australia',
        'Miami, United States',
        'Munich, Germany'
        ,'Tokyo - Narita, Japan',
        'Gold Coast, Australia',
        'Chicago - OHare Intl., United States',
        'Paris - Orly, France',
        'Perth, Australia'
        ,'San Francisco, United States',
        'Singapore, Singapore',
        'Sydney, Australia'
        ,'Vienna, Austria'
        ,'Toronto, Canada'
    ];

    function autocomplete(inp, arr) {
        /*the autocomplete function takes two arguments,
        the text field element and an array of possible autocompleted values:*/
        var currentFocus;
        /*execute a function when someone writes in the text field:*/
        inp.addEventListener("input", function(e) {
            var a, b, i, val = this.value;
            /*close any already open lists of autocompleted values*/
            closeAllLists();
            if (!val) { return false;}
            currentFocus = -1;
            /*create a DIV element that will contain the items (values):*/
            a = document.createElement("DIV");
            a.setAttribute("id", this.id + "autocomplete-list");
            a.setAttribute("class", "autocomplete-items");
            /*append the DIV element as a child of the autocomplete container:*/
            this.parentNode.appendChild(a);
            /*for each item in the array...*/
            for (i = 0; i < arr.length; i++) {
                /*check if the item starts with the same letters as the text field value:*/
                if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                    /*create a DIV element for each matching element:*/
                    b = document.createElement("DIV");
                    /*make the matching letters bold:*/
                    b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                    b.innerHTML += arr[i].substr(val.length);
                    /*insert a input field that will hold the current array item's value:*/
                    b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                    /*execute a function when someone clicks on the item value (DIV element):*/
                    b.addEventListener("click", function(e) {
                        /*insert the value for the autocomplete text field:*/
                        inp.value = this.getElementsByTagName("input")[0].value;
                        /*close the list of autocompleted values,
                        (or any other open lists of autocompleted values:*/
                        closeAllLists();
                    });
                    a.appendChild(b);
                }
            }
        });
        /*execute a function presses a key on the keyboard:*/
        inp.addEventListener("keydown", function(e) {
            var x = document.getElementById(this.id + "autocomplete-list");
            if (x) x = x.getElementsByTagName("div");
            if (e.keyCode == 40) {
                /*If the arrow DOWN key is pressed,
                increase the currentFocus variable:*/
                currentFocus++;
                /*and and make the current item more visible:*/
                addActive(x);
            } else if (e.keyCode == 38) { //up
                /*If the arrow UP key is pressed,
                decrease the currentFocus variable:*/
                currentFocus--;
                /*and and make the current item more visible:*/
                addActive(x);
            } else if (e.keyCode == 13) {
                /*If the ENTER key is pressed, prevent the form from being submitted,*/
                e.preventDefault();
                if (currentFocus > -1) {
                    /*and simulate a click on the "active" item:*/
                    if (x) x[currentFocus].click();
                }
            }
        });
        function addActive(x) {
            /*a function to classify an item as "active":*/
            if (!x) return false;
            /*start by removing the "active" class on all items:*/
            removeActive(x);
            if (currentFocus >= x.length) currentFocus = 0;
            if (currentFocus < 0) currentFocus = (x.length - 1);
            /*add class "autocomplete-active":*/
            x[currentFocus].classList.add("autocomplete-active");
        }
        function removeActive(x) {
            /*a function to remove the "active" class from all autocomplete items:*/
            for (var i = 0; i < x.length; i++) {
                x[i].classList.remove("autocomplete-active");
            }
        }
        function closeAllLists(elmnt) {
            /*close all autocomplete lists in the document,
            except the one passed as an argument:*/
            var x = document.getElementsByClassName("autocomplete-items");
            for (var i = 0; i < x.length; i++) {
                if (elmnt != x[i] && elmnt != inp) {
                    x[i].parentNode.removeChild(x[i]);
                }
            }
        }
        /*execute a function when someone clicks in the document:*/
        document.addEventListener("click", function (e) {
            closeAllLists(e.target);
        });
    }
    autocomplete(document.getElementById("departure"), countries);
    autocomplete(document.getElementById("additionalDestination"), countries);
    autocomplete(document.getElementById("additionalDeparture"), countries);
    autocomplete(document.getElementById("arrival"), countries);
</script>
<jsp:include page="template-parts/footer.jsp" />