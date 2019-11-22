package seng3150.group1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import seng3150.group1.models.AirlineReviewInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import seng3150.group1.entities.Airlines;

@Controller
@RequestMapping("/reviewResults")
public class ReviewResultsController {

    @PostMapping
    public ModelAndView viewReviewResults(HttpSession session, @RequestParam String airlineCode) { //controller that is called once an airline is clicked

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("flightpub");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Airlines airlineLinkedToFlight = em.find(Airlines.class, airlineCode); //here we work out which airline is associate with the flight clicked
        transaction.commit();


        AirlineReviewInfo selectedAirline = null;
        if (airlineLinkedToFlight != null) {
            selectedAirline = new AirlineReviewInfo();
            selectedAirline.convertAirlineToAirlineReviewInformation(airlineLinkedToFlight);
        }

        return new ModelAndView("airlineReview", "selectedAirline", selectedAirline);
    }

}