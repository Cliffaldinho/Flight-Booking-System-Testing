<jsp:include page="template-parts/header.jsp" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<div class="container">

        <h1> Flight Review</h1>

        <div class="row">
            <div class="col-xs-12 col-md-12" class="paddingTitleReview">
                <h3> Please complete the information below to review the following flight: "Flight Name" </h3>
            </div>
			

			<form action="add-review" method="post" class="reviewForm"> <!-- This is the form that the user completes to enter a review. Each sliders are listed below-->
               <table class="sliders">
                   <tr>
                       <td colspan="3">Service quality</td>
                   </tr>
                   <tr>
                       <td>Poor</td>
                       <td><input type="range" min="1" max="5" name="serviceRating"></td> <!--score from 0-5-->
                       <td>Excellent</td>
                   </tr>

                   <tr>
                       <td colspan="3">Catering</td>
                   </tr>
                   <tr>
                       <td>Poor</td>
                       <td><input type="range" min="1" max="5" name="cateringRating"></td>
                       <td>Excellent</td>
                   </tr>

                   <tr>
                       <td colspan="3">Seat space and comfort</td>
                   </tr>
                   <tr>
                       <td>Poor</td>
                       <td><input type="range" min="1" max="5" name="comfortRating"></td>
                       <td>Excellent</td>
                   </tr>

                   <tr>
                       <td colspan="3">Entertainment</td>
                   </tr>
                   <tr>
                       <td>Poor</td>
                       <td><input type="range" min="1" max="5" name="entertainmentRating"></td>
                       <td>Excellent</td>
                   </tr>

                   <tr>
                       <td colspan="3">Cleanliness</td>
                   </tr>
                   <tr>
                      <td>Poor</td>
                       <td><input type="range" min="1" max="5" name="cleanlinessRating"></td>
                       <td>Excellent</td>
                   </tr>

                   <tr>
                       <td colspan="3">Punctuality</td>
                   </tr>
                   <tr>
                       <td>Poor</td>
                       <td><input type="range" min="1" max="5" name="punctualityRating"></td>
                       <td>Excellent</td>
                   </tr>

                   <tr>
                       <td colspan="3">How likely are you to recommend this flight?</td>
                   </tr>
                   <tr>
                       <td>Very Unlikely</td>
                       <td><input type="range" min="1" max="5" name="recommendationRating"></td>
                       <td>Very Likely</td>
                   </tr>
               </table>

                <!--Submit or cancel the form-->
                <!--<div class="formbuttons">-->
                <!--</div>-->
                <input type="submit" name = "submitButtonValue" value = "Submit">
            </form>
            <form action="home" method="get">
                <input type="submit" value = "Cancel">
            </form>



         </div>
</div>


<jsp:include page="template-parts/footer.jsp" />