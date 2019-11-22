// Controller for selecting a flight to purchase
package seng3150.group1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import seng3150.group1.helpers.SearchHelpers;
import seng3150.group1.models.FlightOptionContainer;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @PostMapping
    public ModelAndView flightsSliders(HttpSession session,
                                       @RequestParam LinkedList<Integer> flightInformationId,
                                       @RequestParam LinkedList<Integer> legNo)

    {
        //add selected flights to session list for payment screen
        FlightOptionContainer desiredFlight = SearchHelpers.getFlightOptionFromDb(flightInformationId, legNo);
        LinkedList<FlightOptionContainer> selectedFlightList = new LinkedList<>();
        selectedFlightList.add(desiredFlight);
        session.setAttribute("selectedFlightList", selectedFlightList);

        return new ModelAndView("payment");
    }
}