package seng3150.group1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seng3150.group1.entities.FlightInformation;

/**
 * Repository interface for all database access/manipulation methods for FlightInformation entities.
 * These methods are all automatically implemented by Spring when an instance is autowired into another class,
 * thanks to extending the JpaRepository interface
 */
@Repository
public interface IFlightInformationDao extends JpaRepository<FlightInformation, Integer> {

    /**
     * Finds the FlightInformation instance from the row in the database with the given bookingId
     * @param flightInformationId - the flightInformationId of the FlightInformation entry you want to retrieve
     * @return the matching FlightInformation object, or null if there is no match
     */
    FlightInformation findByFlightInformationId(int flightInformationId);
}
