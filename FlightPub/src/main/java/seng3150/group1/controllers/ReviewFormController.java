// Display the review form for a specific flight booking for the user to review their flight
package seng3150.group1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import seng3150.group1.entities.Airlines;

@Controller
@RequestMapping("/account/reviewFlight")
public class ReviewFormController {

    @PostMapping
    public ModelAndView viewFlights(HttpSession session,
                                    @RequestParam String airlineCode,
                                    @RequestParam String bookingId) { //controller that is called once the review flight button is clicked

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("flightpub");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Airlines airlineLinkedToFlight = em.find(Airlines.class, airlineCode); //here we work out which airline is associate with the flight clicked

        session.setAttribute("airline", airlineLinkedToFlight); //pass the airline entity instance to the session

        //pass the bookingId through so we can know which flight is being reviewed
        session.setAttribute("reviewingFlightBookingId", Integer.parseInt(bookingId));

        return new ModelAndView("flightReviewPage");
    }

}