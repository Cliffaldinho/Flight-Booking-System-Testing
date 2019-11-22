
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="template-parts/header.jsp" />


<div class="content">
    <div class = "container-fluid">
        <div class="row">
            <div class = "col">
                <div class = "searchingInfo">
                    <br>
                    <h1>We've found many flights matching your search</h1>
                    <br><br>

                </div>
            </div>
        </div>


        <div class="row">
            <div class="col">
                <div class = "preferences-questionnaire">
                    <h2>You can tell us how important each of the following things are for your flight, so we can find the best flight for you:</h2>
                    <form action="flights-sliders" method="post">
                       <table class="sliders">
                           <tr>
                               <td colspan="3">Lowest Price</td>
                           </tr>
                           <tr>
                               <td>Not Important</td>
                               <td><input type="range" min="1" max="5" name="priceWeight" value=${searchCriteria.costPlaceholder}></td>
                               <td>Very Important</td>
                           </tr>

                           <tr>
                               <td colspan="3">Shortest Travel Time</td>
                           </tr>
                           <tr>
                               <td>Not Important</td>
                               <td><input type="range" min="1" max="5" name="durationWeight" value=${searchCriteria.durationPlaceholder}></td>
                               <td>Very Important</td>
                           </tr>

                           <tr>
                               <td colspan="3">Comfort</td>
                           </tr>
                           <tr>
                               <td>Not Important</td>
                               <td><input type="range" min="1" max="5" name="ratingWeight" value=${searchCriteria.comfortPlaceholder}></td>
                               <td>Very Important</td>
                           </tr>

                           <tr>
                               <td colspan="3">No Stopovers</td>
                           </tr>
                           <tr>
                               <td>Not Important</td>
                               <td><input type="range" min="1" max="5" name="noStopOversWeight" value=${searchCriteria.noStopOversPlaceholder}></td>
                               <td>Very Important</td>
                           </tr>

                           <tr>
                           <!--    <td colspan="3">Flexible Dates</td>
                           </tr>
                           <tr>
                               <td>Not Flexible</td>
                               <td><input type="range" min="1" max="5" name="flexibleDatesWeight"></td>
                               <td>Very Flexible</td>
                           </tr>-->
                       </table>


                        <input type="submit" name = "submit" value = "Submit your preferences">
                        <input type="submit" name = "submit" value = "Skip"><br><br>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="template-parts/footer.jsp" />