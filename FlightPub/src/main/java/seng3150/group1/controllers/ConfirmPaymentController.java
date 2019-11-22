// Receive a user's payment and account information, book the currently selected flights (in the session) and
// send an email receipt

package seng3150.group1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import seng3150.group1.dao.IBookingDao;
import seng3150.group1.dao.IFlightInformationDao;
import seng3150.group1.dao.IUserBookingDao;
import seng3150.group1.entities.Booking;
import seng3150.group1.entities.FlightInformation;
import seng3150.group1.entities.User;
import seng3150.group1.entities.UserBooking;
import seng3150.group1.helpers.SendEmail;
import seng3150.group1.models.FlightLegContainer;
import seng3150.group1.models.FlightOptionContainer;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;

@Controller
@RequestMapping("/confirm-payment")
public class ConfirmPaymentController {

    @Autowired
    private IUserBookingDao userBookingDao;

    @Autowired
    private IBookingDao bookingDao;

    @Autowired
    private IFlightInformationDao flightInformationDao;

    @PostMapping
    public ModelAndView confirmPayment(HttpSession session)

    {
        /*
            TODO - server side validation of user payment details
         */

        //get flights being purchased
        LinkedList<FlightOptionContainer> selectedFlightsList = (LinkedList<FlightOptionContainer>) session.getAttribute("selectedFlightList");

        //record this booking with the user's flight history if there's a logged in user
        User user = (User) session.getAttribute("user");

        if(user != null){
            //essentially stores 1 or more flightOptionContainers and their booking user together in the database
            UserBooking userBooking = new UserBooking(user);
            userBookingDao.saveAndFlush(userBooking);

            //store all legs of all flightOptions as one userbooking (multiple FOC may be present for multicity/return booking
            for (FlightOptionContainer foc:selectedFlightsList){
                for(FlightLegContainer flc:foc.getLegs()){
                    FlightInformation fi = flightInformationDao.findByFlightInformationId(flc.getFlightInformationId());
                    int legNumber = flc.getLegNo();
                    Booking booking = bookingDao.saveAndFlush(new Booking(userBooking,fi,legNumber));
                }
            }

            SendEmail.emailReceipt(user, selectedFlightsList);

            /*OLD Implementation using flightInfoIds passed through as model attributes
            //Build and add each row of the Booking table (stores each leg of the userBooking)
            int i = 0;
            while (i < flightInformationId.size()){
                //record the associated userbooking, flightInformation and legNo of each leg in the userbooking
                FlightInformation fi = flightInformationDao.findByFlightInformationId(flightInformationId.get(i));
                int legNumber = legNo.get(i);
                Booking booking = bookingDao.saveAndFlush(new Booking(userBooking,fi,legNumber));
                i++;
            }*/
        }
        return new ModelAndView("paymentSuccess");
    }
}