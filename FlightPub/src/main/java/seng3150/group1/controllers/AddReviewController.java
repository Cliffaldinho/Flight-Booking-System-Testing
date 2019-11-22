// Receive a user's review inputs and create the review in the database

package seng3150.group1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.*;
import javax.servlet.http.HttpSession;

import seng3150.group1.dao.IBookingDao;
import seng3150.group1.dao.IUserBookingDao;
import seng3150.group1.entities.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequestMapping("/account/add-review")
public class AddReviewController {

    @Autowired
    IUserBookingDao userBookingDao;

    @Autowired
    IBookingDao bookingDao;

    @PostMapping
    public ModelAndView flightsReview(
            HttpSession session,
            //@ModelAttribute("airline") Airlines airline,
            @RequestParam double serviceRating, //get all this data from the form that calls this action
            @RequestParam double cateringRating,
            @RequestParam double comfortRating,
            @RequestParam double entertainmentRating,
            @RequestParam double cleanlinessRating,
            @RequestParam double punctualityRating,
            @RequestParam double recommendationRating,
            @RequestParam String submitButtonValue)
    {

        //we can create a new AirlineReview entity instance and add it to database using the code below
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("flightpub");
        EntityManager em = emf.createEntityManager();

        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Airlines airline = (Airlines) session.getAttribute("airline"); //get the airline associated with the flight to be reviewed

            AirlineReviews newReview = new AirlineReviews(); //create new airlineReview

            //set new airline review information from the form input data
            newReview.setAirline(airline);
            newReview.setAirlineServiceRating(serviceRating*2);
            newReview.setAirlineFoodAndBeverageRating(cateringRating*2);
            newReview.setAirlineSeatAndComfortRating(comfortRating*2);
            newReview.setAirlineEntertainmentRating(entertainmentRating*2);
            newReview.setAirlineCleanlinessRating(cleanlinessRating*2);
            newReview.setAirlinePunctualityRating(punctualityRating*2);
            newReview.setAirlineRecommendedRating(recommendationRating*2);
            newReview.setAirlineOverallRating((serviceRating*2+cateringRating*2+comfortRating*2+entertainmentRating*2+cleanlinessRating*2+punctualityRating*2+recommendationRating*2)/7); //set overallRating by summing all rating values. Times by 2 as we get a score out and we want a score out of 10.

            em.persist(newReview);
            em.flush();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("UpdateAirlineReviewScore");
            storedProcedure.registerStoredProcedureParameter("AirlineCode", String.class, ParameterMode.IN);
            storedProcedure.setParameter("AirlineCode", airline.getAirlineCode());
            storedProcedure.execute();

            /*now mark this leg as booked in the database*/
            //retrieve info from session to find booking that was reviewed
            int bookingId = (Integer) session.getAttribute("reviewingFlightBookingId");

            //set booking as reviewed
            Booking booking = bookingDao.findById(bookingId);
            booking.setReviewed(true);
            bookingDao.saveAndFlush(booking);

            try {
                transaction.commit(); //add review to database table (AirlineReview)
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            em.close();
            return new ModelAndView("reviewCompletionSuccess");
        }
    }
}
