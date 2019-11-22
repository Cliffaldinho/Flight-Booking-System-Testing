// Controller to receive the search parameters at the very start of the search process such as to/from locations, dates,
// and the type of search (Return, Single, or Multi-city), and to take the user to a page to enter their preferences
// and importance weightings

package seng3150.group1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
@RequestMapping("/homepage-form")
public class BeginSearchController {


    @PostMapping
    public ModelAndView viewFlights(HttpSession session, @RequestParam String departure, @RequestParam String arrival, @RequestParam String departureDate, @RequestParam String additionalDestination, @RequestParam String additionalDeparture, @RequestParam String category, @RequestParam String additionalDepartureDate) throws ParseException {
        // ----------- Set initial search parameters in session ------------------------------
        session.setAttribute("departure", departure);
        session.setAttribute("arrival", arrival);
        session.setAttribute("departureDate", departureDate);
        session.setAttribute("searchState", 0);

        //For debugging
        session.setAttribute("category", category);

        if (category.equals("Return") || category.equals("Multi City")) {
            session.setAttribute("additionalDestination", additionalDestination);
            session.setAttribute("additionalDeparture", additionalDeparture);
            session.setAttribute("additionalDepartureDate", additionalDepartureDate);

            // Create a session attribute to represent flight search state
            session.setAttribute("searchState", 1);
        }

        //DON'T LIKE THIS: Just have placeholder instead of not being able to change at all unless
        //the additionalDestinationCheck is clicked... We can submit both regardless.
        /*
        if ((additionalDestinationCheck)||(category.equals("Multi City"))) {
            System.out.println("additionalDeparture: "+ additionalDeparture);
            System.out.println("additionalDestination: " +  additionalDestination);
        }
        */

        //if (category.equals("Return")) {}
        // -----------------------------------------------------------------------------------



        return new ModelAndView("loadingPreferencesQuestionaire");
    }
}