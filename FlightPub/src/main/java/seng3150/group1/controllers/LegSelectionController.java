// Controller that receives the selection of legs for either a multi-city/return or a single flight and adds the flight
// option into the session as a selected flight. These selected flights are the ones that will be purchased by a user

package seng3150.group1.controllers;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.KShortestSimplePaths;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import seng3150.group1.entities.FlightInformation;
import seng3150.group1.helpers.SearchHelpers;
import seng3150.group1.models.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/leg-selection")
public class LegSelectionController {
   @PostMapping
   public ModelAndView processFlightLegSelection(HttpSession session,
                                                 @RequestParam LinkedList<Integer> flightInformationId,
                                                 @RequestParam LinkedList<Integer> legNo,
                                                 @RequestParam int currentLeg)
   {

        FlightOptionContainer desiredFlight = SearchHelpers.getFlightOptionFromDb(flightInformationId, legNo);

        //First Leg
        if (currentLeg == 1)
        {
            session.setAttribute("searchState", 2);
            session.setAttribute("legSelection1", desiredFlight);
            return new ModelAndView("flights");
        }
        //Second Leg
        else if (currentLeg == 2)
        {
            session.setAttribute("searchState", 3);
            session.setAttribute("legSelection2", desiredFlight);

            LinkedList<FlightOptionContainer> selectedFlights = new LinkedList<>();
            selectedFlights.add((FlightOptionContainer) session.getAttribute("legSelection1"));
            selectedFlights.add(desiredFlight);

            session.setAttribute("selectedFlightList", selectedFlights);

            return new ModelAndView("payment");
        }

        return new ModelAndView();
    }
}