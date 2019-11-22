// File to provide several helper functions used by other classes and controllers. Contains methods for adding and
// removing items from the shortlist, converting a list of flights into JSON, as well as retrieving a set of
// flight legs from the database (given the flightInformationId and legNo for each leg)

package seng3150.group1.helpers;

import seng3150.group1.entities.FlightInformation;
import seng3150.group1.models.FlightLegContainer;
import seng3150.group1.models.FlightOptionContainer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

public class SearchHelpers {

    //Converts a list of FlightOptionContainers into JSON data
    public static String flightsToJson(LinkedList<FlightOptionContainer> flightOptions) {
        StringBuilder responseJSON = new StringBuilder();
        int i = 0;
        responseJSON.append("{");
        if (flightOptions != null) {
            responseJSON.append("\"FlightOptionContainer\":[");
            for (FlightOptionContainer fo : flightOptions) {
                responseJSON.append("{");
                responseJSON.append("\"score\":\"" + fo.getScore() + "\",");
                responseJSON.append("\"price\":\"" + fo.getTotalPriceAsString() + "\",");
                responseJSON.append("\"departureLocation\":\"" + fo.getFlyingFrom() + "\",");
                responseJSON.append("\"arrivalLocation\":\"" + fo.getFlyingTo() + "\",");
                responseJSON.append("\"airlineAvgScore\":\"" + fo.getAverageAirlineRating() + "\",");
                responseJSON.append("\"duration\":\"" + fo.getDurationAsString() + "\",");
                responseJSON.append("\"numOfStopovers\":\"" + fo.getNumOfStopovers() + "\",");
                responseJSON.append("\"departureDate\":\"" + fo.getDepartureDate() + "\",");
                responseJSON.append("\"arrivalDate\":\"" + fo.getArrivalDate() + "\",");
                int j = 0;
                responseJSON.append("\"FlightLegContainer\":[");
                for (FlightLegContainer leg : fo.getLegs()) {
                    responseJSON.append("{");
                    responseJSON.append("\"departureLocation\":\"" + leg.getFlyingFrom() + "\",");
                    responseJSON.append("\"arrivalLocation\":\"" + leg.getFlyingTo() + "\",");
                    responseJSON.append("\"departureDate\":\"" + leg.getDepartureDate() + "\",");
                    responseJSON.append("\"arrivalDate\":\"" + leg.getArrivalDate() + "\",");
                    responseJSON.append("\"duration\":\"" + leg.getDurationAsString() + "\",");
                    responseJSON.append("\"price\":\"" + leg.getPriceAsString() + "\",");
                    responseJSON.append("\"airline\":\"" + leg.getAirline() + "\",");
                    responseJSON.append("\"airlineCode\":\"" + leg.getAirlineCode() + "\",");
                    responseJSON.append("\"airlineRating\":\"" + leg.getAirlineRating() + "\",");
                    responseJSON.append("\"class\":\"" + leg.getClassName() + "\",");
                    responseJSON.append("\"aircraft\":\"" + leg.getAircraft() + "\",");
                    responseJSON.append("\"flightNumber\":\"" + leg.getFlightNo() + "\",");
                    responseJSON.append("\"flightInformationId\":" + leg.getFlightInformationId() + ",");
                    responseJSON.append("\"legNumber\":" + leg.getLegNo() + "");

                    responseJSON.append("}");
                    if (j < fo.getLegs().size() - 1)
                        responseJSON.append(",");
                    j++;
                }
                responseJSON.append("]");
                responseJSON.append("}");
                if (i < flightOptions.size() - 1)
                    responseJSON.append(",");
                i++;
            }
            responseJSON.append("]");
        }
        responseJSON.append("}");

        return responseJSON.toString();

    }

    //Function to retrieve a set of flight legs from the database and put them into a FlightOptionContainer
    // null is returned if there is no valid flight option
    public static FlightOptionContainer getFlightOptionFromDb(LinkedList<Integer>flightInformationIds,
                                                              LinkedList<Integer> legNos) {

        if (flightInformationIds == null || legNos == null || flightInformationIds.size() != legNos.size() ||
                flightInformationIds.size() == 0)
            return null;

        EntityManager em = Persistence.createEntityManagerFactory("flightpub").createEntityManager();
        //List to store the legs for this flight
        LinkedList<FlightLegContainer> legs = new LinkedList<>();

        //For each flightInformationId given, perform the query to get that flight leg from the database
        for (int i = 0; i < flightInformationIds.size(); i++) {

            // ------------ JPA CONNECTION ATTEMPT - FlightInformation (Entity) ------------------
            TypedQuery<FlightInformation> query = em.createQuery(
                    "SELECT fi FROM FlightInformation fi WHERE fi.flightInformationId = :flightInformationId",
                    seng3150.group1.entities.FlightInformation.class);


            query.setParameter("flightInformationId", flightInformationIds.get(i));
            List<FlightInformation> flightInformationList;

            try {
                flightInformationList = query.getResultList();

            } catch (NoResultException nre) {
                System.out.println("Flight " + flightInformationIds.get(i) + " not found.");
                return null;
            }

            if (flightInformationList.size() == 0) {
                return null;
            }

            for (FlightInformation f : flightInformationList) {
                //Convert the flight information to FlightLegContainers
                LinkedList<FlightLegContainer> legContainers = FlightConverter.informationToLegContainer(f);
                //For this flight, only add the leg we were after
                for (FlightLegContainer leg : legContainers) {
                    if (leg.getLegNo() == legNos.get(i))
                        legs.add(leg);
                }
            }

            String previousArrival = null;
            for (FlightLegContainer leg : legs) {
                if (previousArrival != null && previousArrival.compareTo(leg.getDepartureDate()) > 0) {
                    return null;
                }
                previousArrival = leg.getArrivalDate();
            }


        }

        return new FlightOptionContainer(legs);

    }

    // Returns the updated shortlist in a JSON string after adding a FlightOptionContainer if it isn't already in the shortlist
    public static String addToShortlist(HttpSession session, FlightOptionContainer flightOption) {
        if (flightOption == null)
            return null;

        LinkedList<FlightOptionContainer> shortlist = (LinkedList<FlightOptionContainer>)session.getAttribute("shortlist");

        // If the shortlist doesn't exist, create it
        if (shortlist == null) {
            shortlist = new LinkedList<>();
            session.setAttribute("shortlist", shortlist);
        }

        // Add the flight option if it is not already in the shortlist
        FlightOptionContainer flightInShortlist = existsInShortlist(shortlist, flightOption);
        if (flightInShortlist == null)
            shortlist.add(flightOption);

        return flightsToJson(shortlist);
    }

    // Returns the updated shortlist in a JSON string after adding a FlightOptionContainer if it isn't already in the shortlist
    public static String removeFromShortlist(HttpSession session, FlightOptionContainer flightOption) {
        if (flightOption == null)
            return null;

        LinkedList<FlightOptionContainer> shortlist = (LinkedList<FlightOptionContainer>)session.getAttribute("shortlist");

        // If the shortlist doesn't exist, create it
        if (shortlist == null) {
            shortlist = new LinkedList<>();
            session.setAttribute("shortlist", shortlist);
        }

        // Add the flight option if it is not already in the shortlist
        int indexInShortlist = indexInShortlist(shortlist, flightOption);
        if (indexInShortlist != -1)
            shortlist.remove(indexInShortlist);

        return flightsToJson(shortlist);
    }

    private static FlightOptionContainer existsInShortlist(LinkedList<FlightOptionContainer> shortlist,
                                                           FlightOptionContainer flightOption) {
        if (flightOption == null)
            return null;

        for (FlightOptionContainer shortlistedFlight : shortlist) {
            if (shortlistedFlight.equals(flightOption))
                return shortlistedFlight;
        }

        // return null if the flight did not already exist
        return null;
    }

    public static int indexInShortlist(LinkedList<FlightOptionContainer> shortlist,
                                                           FlightOptionContainer flightOption) {
        if (flightOption == null)
            return -1;

        int i = 0;
        for (FlightOptionContainer shortlistedFlight : shortlist) {
            if (shortlistedFlight.equals(flightOption))
                return i;
            i++;
        }

        // return -1 if the flight did not exist
        return -1;
    }

}
