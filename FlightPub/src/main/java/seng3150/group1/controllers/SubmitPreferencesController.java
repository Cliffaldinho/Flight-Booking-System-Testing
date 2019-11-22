// Controller for submitting a user's preferences and taking the user to the search results page
// (The search will be performed upon an AJAX request from the next page)

package seng3150.group1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import seng3150.group1.models.FlightOptionContainer;
import seng3150.group1.models.Recommendation;
import seng3150.group1.models.SearchCriteria;

import javax.servlet.http.*;
import java.util.LinkedList;

@Controller
@RequestMapping("/flights-sliders")
public class SubmitPreferencesController {
    @PostMapping
    public ModelAndView flightsSliders(HttpSession session,
                                       @RequestParam String priceWeight,
                                       @RequestParam String durationWeight,
                                       @RequestParam String ratingWeight,
                                       @RequestParam String noStopOversWeight,
                                       @RequestParam String submit
    )
    {
        //store search criteria in session
        SearchCriteria sc;
        //if the user clicked skip, then just weight everything equally
        if(submit.equals("Skip")){
            sc = new SearchCriteria("3","3","3","3");
        }
        //otherwise pass in weights from the sliders
        else{
            sc = new SearchCriteria(priceWeight,durationWeight,ratingWeight, noStopOversWeight);
        }

        session.setAttribute("searchCriteria", sc);

        //generate a recommendation to pop up based on searchCriteria
        session.setAttribute("recommendation", Recommendation.generate(sc));

        //retrieve list of search results from session, then rank and sort them based on the searchCriteria
        //LinkedList<FlightOptionContainer> flights = (LinkedList<FlightOptionContainer>) session.getAttribute("searchResults"); --nato

        //FlightOptionContainer.rankAndSortFlights(flights,sc); --nato

        return new ModelAndView("flights");
    }
}
