// Receive an AJAX request for adding or removing a flight to or from the shortlist and send the updated shortlist
// as the response in a JSON format

package seng3150.group1.controllers;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.KShortestSimplePaths;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class ShortlistController {
    @RequestMapping(value = "/shortlist", method = RequestMethod.POST)
    public @ResponseBody String processAJAXRequest(HttpSession session,
            @RequestParam LinkedList<Integer> flightInformationId,
            @RequestParam LinkedList<Integer> legNo,
            @RequestParam String action) {

        FlightOptionContainer desiredFlight = SearchHelpers.getFlightOptionFromDb(flightInformationId, legNo);
        if (desiredFlight == null)
            return "";

        if (action.equals("add")) {
            System.out.println("Adding " + desiredFlight);
            return SearchHelpers.addToShortlist(session, desiredFlight);
        }
        else if (action.equals("remove")) {
            System.out.println("Removing " + desiredFlight);
            return SearchHelpers.removeFromShortlist(session, desiredFlight);
        }

        System.out.println(session.getAttribute("shortlist").toString());

        return "";
    }
}