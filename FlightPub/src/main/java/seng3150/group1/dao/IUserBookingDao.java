package seng3150.group1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import seng3150.group1.entities.User;
import seng3150.group1.entities.UserBooking;

import java.util.List;

/**
 * Repository interface for all database access/manipulation methods for UserBooking entities.
 * These methods are all automatically implemented by Spring when an instance is autowired into another class,
 * thanks to extending the JpaRepository interface.
 */
@Repository
public interface IUserBookingDao extends JpaRepository<UserBooking, Integer> {

    /**
     * Returns a lll UserBooking entities made by the input user (i.e. whose userIds match the input's userId)
     * @param user - a user whose userId is the parameter for the search
     * @return a list of UserBooking entities from the database with matching userIds to the input User
     */
    List<UserBooking> findAllByUser(User user);
}
