// Displays the information for a series of flights given the id numbers for each leg

package seng3150.group1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import seng3150.group1.entities.FlightInformation;
import seng3150.group1.helpers.SearchHelpers;
import seng3150.group1.models.*;

import javax.persistence.*;
import javax.servlet.http.*;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/view-flight")
public class ViewSingleFlightController {
    @GetMapping
    public ModelAndView flightsSliders(HttpSession session,
                                       @RequestParam LinkedList<Integer> flightInformationId,
                                       @RequestParam LinkedList<Integer> legNo
    )
    {

        FlightOptionContainer theChosenOne = SearchHelpers.getFlightOptionFromDb(flightInformationId, legNo);

        return new ModelAndView("detailedFlightView", "selectedFlight", theChosenOne);
    }
}
