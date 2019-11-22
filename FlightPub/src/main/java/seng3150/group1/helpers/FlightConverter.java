/*
    Class with static method used to convert a list of FlightInformation objects
     (entities mapped by JPA from a query) into FlightLegContainer objects.
 */

package seng3150.group1.helpers;

import seng3150.group1.entities.FlightInformation;
import seng3150.group1.models.*;

import java.util.LinkedList;

public class FlightConverter {

    /**
     * Takes a FlightInformation entity and extracts the information for both legs into FlightLegContainers
     * @param flightInfo - FlightInformation entity to be converted FlightLegContainers
     * @return - the list of FlightLegContainers from the given FlightInformation Entity
     */
    public static LinkedList<FlightLegContainer> informationToLegContainer(FlightInformation flightInfo) {
        LinkedList<FlightLegContainer> toReturn = new LinkedList<>();
        if (flightInfo == null)
            return null;
        // When the contains a stopover, split the information into two separate leg objects.
        if ((flightInfo.getDurationSecondLeg() != null) && ((flightInfo.getArrivalTimeStopOver() != null)) && (!flightInfo.getArrivalTimeStopOver().contains("0000-00-00 00:00:00")))
        {
            FlightLegContainer legOne = new FlightLegContainer();
            FlightLegContainer legTwo = new FlightLegContainer();

            legOne.setAircraft(flightInfo.getPlaneCode());
            legTwo.setAircraft(flightInfo.getPlaneCode());
            legOne.setAirline(flightInfo.getAirline().getAirlineName());
            legTwo.setAirline(flightInfo.getAirline().getAirlineName());

            legOne.setAirlineCode(flightInfo.getAirline().getAirlineCode());
            legTwo.setAirlineCode(flightInfo.getAirline().getAirlineCode());

            legOne.setAirlineRating(flightInfo.getAirline().getAirlineRating());
            legTwo.setAirlineRating(flightInfo.getAirline().getAirlineRating());

            legOne.setDepartureDate(flightInfo.getDepartureTime());
            legOne.setArrivalDate(flightInfo.getArrivalTimeStopOver());
            legTwo.setDepartureDate(flightInfo.getDepartureTimeStopOver());
            legTwo.setArrivalDate(flightInfo.getArrivalTime());

            legOne.setFlyingFrom(flightInfo.getDeparture().getAirport());
            legOne.setFlyingTo(flightInfo.getArrival().getAirport());
            legTwo.setFlyingFrom(flightInfo.getStopover().getAirport());
            legTwo.setFlyingTo(flightInfo.getArrival().getAirport());

            legOne.setFlightNo(flightInfo.getFlightNumber());
            legTwo.setFlightNo(flightInfo.getFlightNumber());

            legOne.setFlightInformationId(flightInfo.getFlightInformationId());
            legTwo.setFlightInformationId(flightInfo.getFlightInformationId());

            legOne.setDuration(flightInfo.getDuration());
            legTwo.setDuration(flightInfo.getDurationSecondLeg());

            legOne.setPrice(flightInfo.getPriceLeg1());
            legTwo.setPrice(flightInfo.getPriceLeg2());

            legOne.setClassCode(flightInfo.getClassCode());
            legTwo.setClassCode(flightInfo.getClassCode());

            legOne.setLegNo(1);
            legTwo.setLegNo(2);

            toReturn.add(legOne);
            toReturn.add(legTwo);
        }
        else //Case when direct flight
        {
            FlightLegContainer flight = new FlightLegContainer();

            flight.setAirline(flightInfo.getAirline().getAirlineName());
            flight.setAircraft(flightInfo.getPlaneCode());

            flight.setAirlineCode(flightInfo.getAirline().getAirlineCode());

            flight.setAirlineRating(flightInfo.getAirline().getAirlineRating());

            flight.setArrivalDate(flightInfo.getArrivalTime());
            flight.setDepartureDate(flightInfo.getDepartureTime());

            flight.setFlyingFrom(flightInfo.getDeparture().getAirport());
            flight.setFlyingTo(flightInfo.getArrival().getAirport());

            flight.setFlightNo(flightInfo.getFlightNumber());
            flight.setFlightInformationId(flightInfo.getFlightInformationId());

            flight.setClassCode(flightInfo.getClassCode());

            flight.setDuration(flightInfo.getDuration());
            flight.setPrice(flightInfo.getPrice());
            flight.setLegNo(0);

            toReturn.add(flight);
        }
        return toReturn;
    }

}
