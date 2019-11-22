// Controller to

package seng3150.group1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import seng3150.group1.dao.IBookingDao;
import seng3150.group1.dao.IUserBookingDao;
import seng3150.group1.entities.Booking;
import seng3150.group1.entities.User;
import seng3150.group1.entities.UserBooking;
import seng3150.group1.helpers.SearchHelpers;
import seng3150.group1.models.FlightLegContainer;
import seng3150.group1.models.FlightOptionContainer;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/account/home")
public class AccountController {

    @Autowired
    IUserBookingDao userBookingDao;

    @Autowired
    IBookingDao bookingDao;

    @GetMapping
    public ModelAndView userAccountPage(HttpSession session) {
        //retrieve logged in user from session
        User user = (User) session.getAttribute("user");
        LinkedList<FlightOptionContainer> flightHistory = new LinkedList<>();

        //get all userBookings they've made and store as flightOptionContainers
        List<UserBooking> userBookings = userBookingDao.findAllByUser(user);

        for(UserBooking userbooking: userBookings){
            //get each booking within each userBooking,
            //and extract the flightInformationId, legNo and whether it's been reviewed
            List<Booking> bookings = bookingDao.findAllByUserBooking(userbooking);

            LinkedList<Integer> flightInformationIds = new LinkedList<>();
            LinkedList<Integer> legNos = new LinkedList<>();
            LinkedList<Boolean> isReviewedList = new LinkedList<>();
            LinkedList<Integer> bookingIds = new LinkedList<>();
            for(Booking booking: bookings){
                //add nums to appropriate lists
                flightInformationIds.add(booking.getFlightInformation().getFlightInformationId());
                legNos.add(booking.getLegNo());
                isReviewedList.add(booking.isReviewed());
                bookingIds.add(booking.getId());
            }

            //use this data to recompile a FlightOptionContainer matching what the user booked
            FlightOptionContainer foc = SearchHelpers.getFlightOptionFromDb(flightInformationIds,legNos);

            //add in the "reviewed" booleans for each leg
            for (int i = 0; i < foc.getLegs().size(); i++){
                foc.getLegs().get(i).setReviewed(isReviewedList.get(i));
                foc.getLegs().get(i).setBookingId(bookingIds.get(i));
            }

            //store completed container with all flights in user's history
            flightHistory.add(foc);

        }

        //reverse order of list, which will make them appear in the order they were added to the DB (booking order)
        Collections.reverse(flightHistory);

        return new ModelAndView("userAccountPage", "flightHistory", flightHistory);
    }
}