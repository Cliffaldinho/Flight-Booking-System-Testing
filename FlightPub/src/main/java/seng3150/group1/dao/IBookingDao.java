package seng3150.group1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seng3150.group1.entities.Booking;
import seng3150.group1.entities.UserBooking;

import java.util.List;

/**
 * Repository interface for all database access/manipulation methods for Booking entities.
 * These methods are all automatically implemented by Spring when an instance is autowired into another class,
 * thanks to extending the JpaRepository interface
 */
@Repository
public interface IBookingDao extends JpaRepository<Booking, Integer> {
    /**
     * Returns all Booking objects which are part of the input Userbooking
     * (i.e. which have the same userBookingId as the supplied UserBooking)
     * @param userBooking - a UserBooking whose id is the parameter for the search
     * @return a list of Booking entities from the database with matching userBookingIds to the input userBooking
     */
    List<Booking> findAllByUserBooking(UserBooking userBooking);

    /**
     * Finds the Booking instance from the row in the database with the given bookingId
     * @param bookingId - the bookingId of the Booking you want to retrieve
     * @return the matching Booking object, or null if there is no match
     */
    Booking findById(int bookingId);
}
