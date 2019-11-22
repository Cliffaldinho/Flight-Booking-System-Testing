// Controller and methods to receive AJAX requests to perform a search, building a graph to find flight paths,
// and return any routes found.

package seng3150.group1.controllers;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.KShortestSimplePaths;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seng3150.group1.entities.FlightInformation;
import seng3150.group1.helpers.FlightConverter;
import seng3150.group1.helpers.SearchHelpers;
import seng3150.group1.models.*;

import java.sql.Date;
import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class
AjaxSearchController {
    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public @ResponseBody String processAJAXRequest(
            HttpSession session,
            @RequestParam("recommendation") String recommendation,
            @RequestParam("departure") String departure,
            @RequestParam("arrival") String arrival,
            @RequestParam("departureDate") String departureDate) {

        System.out.println("Performing Search...");
        System.out.println("Departure: " + departure);
        System.out.println("Arrival: " + arrival);
        System.out.println("Departure Date: " + departureDate);

        //The list of all flight legs returned from the search
        flc = new LinkedList<>();

        departure = departure.substring(0, departure.indexOf(","));
        arrival = arrival.substring(0, arrival.indexOf(","));

        Date departDate = Date.valueOf(departureDate);
        Date departWindowEnd;

        //RECOMMENDATIONS - Give a backpacker a larger window of flights to search
        if (recommendation.equals("Backpacker"))
            departWindowEnd = new Date(departDate.getTime() + 72*60*60*1000);
        else
            departWindowEnd = new Date(departDate.getTime() + 48*60*60*1000);

        //RECOMMENDATIONS - Give different amounts of stopovers to different user categories
        if (recommendation.equals("Holiday Maker"))
            maxFlightLegs = 5;
        else if (recommendation.equals("Business Traveller"))
            maxFlightLegs = 0;
        else
            maxFlightLegs = 2;


        // ------------ JPA CONNECTION - FlightInformation (Entity) ------------------
        EntityManager em = Persistence.createEntityManagerFactory("flightpub").createEntityManager();

        TypedQuery<FlightInformation> query = em.createQuery("SELECT fi FROM FlightInformation fi WHERE fi.departureTime BETWEEN :sTime AND :fTime ", FlightInformation.class);


        query.setParameter("sTime", departDate.toString() + " 00:00:00")
                .setParameter("fTime", departWindowEnd.toString()  + " 23:59:59");

        try {
            // Get the results from the query
            List<FlightInformation> jpaResults = query.getResultList();

            //Split the FlightInformation into legs
            splitFlightInformationToFlightLegs(jpaResults);

            //Find the possible flight paths and rank and sort them based on the preferences from the session
            LinkedList<FlightOptionContainer> flightOptions = findPossibleFlightPaths(departure, arrival);

            //If the search should eliminate all flight options with an average score of < 5
            if (recommendation.equals("Frequent Weekender"))
                filterBadAirlines(flightOptions);

            System.out.println("---FlightOptions"+flightOptions.size());
            SearchCriteria sc = (SearchCriteria)session.getAttribute("searchCriteria");

            FlightOptionContainer.rankAndSortFlights(flightOptions,sc);



            //Convert the flight options to JSON and send is as the response
            String response = SearchHelpers.flightsToJson(flightOptions);
            System.out.println("JSON: " + response);
            return response;

        } catch (NoResultException nre) {
            return null;
        }
    }

    private LinkedList<FlightLegContainer> flc;
    private int maxFlightLegs;
    private HashMap<String, LinkedList<FlightLegContainer>> dictionary;
    private DirectedWeightedMultigraph<DestinationNode, FlightLegContainer> g = new DirectedWeightedMultigraph<>(FlightLegContainer.class);

    private void filterBadAirlines(LinkedList<FlightOptionContainer> flights) {
        LinkedList<Integer> toRemoveIndices = new LinkedList<>();

        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getAverageAirlineRating() < 5)
                toRemoveIndices.add(i);
        }

        for (int i = toRemoveIndices.size()-1; i >= 0; i--) {
            if (i == toRemoveIndices.get(i))
                flights.remove(i);
        }
    }

    //Method to convert a list of FlightInformation entities to a list of FlightLegContainers
    private void splitFlightInformationToFlightLegs(List<FlightInformation> input)
    {
        for (FlightInformation f : input)
        {
            LinkedList<FlightLegContainer> legs = FlightConverter.informationToLegContainer(f);
            flc.addAll(legs);
        }
        System.out.println("---FLC" + flc.size());
    }


    //Method to create the graph of possible flights from A to B and returns a list of valid paths (FlightOptions).
    private LinkedList<FlightOptionContainer> findPossibleFlightPaths(String airportDeparture, String airportArrival)
    {

        dictionary = new HashMap<>();
        g = new DirectedWeightedMultigraph<>(FlightLegContainer.class);


        System.out.println();
        System.out.println("----GENERATING GRAPH----");
        System.out.println("Flying from: " + airportDeparture);
        System.out.println("Flying to: " + airportArrival);
        System.out.println("------------------------");

        DestinationNode departure = new DestinationNode(airportDeparture, "0000-00-00 00:00:00");
        DestinationNode destination = new DestinationNode(airportArrival, "9999-00-00 00:00:00");
        g.addVertex(departure);
        g.addVertex(destination);
        //Create the dictionary mapping a location with a list of flights that all leave from that location
        for (FlightLegContainer temp: this.flc)
        {
            //Get the list of flights that leave from this location
            LinkedList<FlightLegContainer> list = dictionary.get(temp.getFlyingFrom());

            //If no list exists for this location yet, then make one
            if (list == null) {
                list = new LinkedList<>();
                dictionary.put(temp.getFlyingFrom(), list);
            }

            //Add this flight to the list
            list.add(temp);
        }

        String[] history = new String[5];
        history[0] = departure.getLocation();
        // Begin recursion
        exploreNode(departure, destination, 0, history);

        System.out.println("Graph Explored");

        System.out.println(g.toString());

        //Set<DestinationNode> nodes = g.vertexSet();
        //System.out.println(nodes);

        //Error might be here
        // Begin k-shortest paths
        KShortestSimplePaths<DestinationNode, FlightLegContainer> kShortestFlightPath = new KShortestSimplePaths<>(g, 10);

        List<GraphPath<DestinationNode, FlightLegContainer>> paths = kShortestFlightPath.getPaths(departure, destination, 50);
        //List<GraphPath<DestinationNode, FlightLegContainer>> paths = new KShortestSimplePaths<>(g, 10).getPaths(departure, destination, 50);
        /**LinkedList<FlightOptionContainer> listOfFlightOC2 = new LinkedList<>();

        System.out.println("Found paths: " + paths.toString());

        for (int k=0; k < paths.size(); k++)
        {
            FlightOptionContainer foc2 = new FlightOptionContainer(paths.get(k).getEdgeList());
            listOfFlightOC2.add(foc2);
        }

         mock it up
        return listOfFlightOC2;*/
        System.out.println("One");
        LinkedList<FlightOptionContainer> listOfFlightOC2 = new LinkedList<>();

         System.out.println("Found paths: " + paths.toString());

         for (int k=0; k < paths.size(); k++)
         {
         FlightOptionContainer foc2 = new FlightOptionContainer(paths.get(k).getEdgeList());
         listOfFlightOC2.add(foc2);
         }

         //LinkedList<FlightOptionContainer> a = new LinkedList();
         //return a;
         return listOfFlightOC2;
    }


    // Depth first method to explore/create the graph of possible flight paths
    private void exploreNode(DestinationNode current, DestinationNode destination, int depth, String[] history) {
        //Get all flights that depart from this location
        List<FlightLegContainer> flights = dictionary.get(current.getLocation());
        if (flights == null)
            return;


        //For each of the flights that leave from here, draw an edge to the flight's destination
        for (FlightLegContainer flight : flights) {
            if (depth > maxFlightLegs)
                return;

            // DON'T FORGET TO ADD CUSHION TO DEPARTURE
            if (!arrayContains(history, flight.getFlyingTo()) && flight.getDepartureDate().compareTo(current.getTime()) > 0) {

                if (flight.getFlyingTo().equals(destination.getLocation())) {
                    g.addEdge(current, destination, flight);
                    g.setEdgeWeight(flight, flight.getDuration());
                } else {
                    DestinationNode node = new DestinationNode(flight.getFlyingTo(), flight.getArrivalDate());
                    g.addVertex(node);
                    // For edge weight: add stopover time to travel time (on the end)
                    g.addEdge(current, node, flight);
                    g.setEdgeWeight(flight, flight.getDuration());
                    exploreNode(node, destination, depth+1, arrayCopyAndAdd(history, current.getLocation()));
                }
            }
        }
    }

    //Helper method to see if a string already exists inside an array
    public static boolean arrayContains(String[] array, String element) {
        if (array == null || element == null)
            return false;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null)
                return false;
            else if (array[i].equals(element))
                return true;
        }
        return false;
    }


    // Helper method to make a copy of an array but with an additional element in the next empty index
    public static String[] arrayCopyAndAdd(String[] original, String newElement) {
        if (original == null || newElement == null)
            return null;

        String[] newArray = new String[original.length+1];

        for (int i = 0; i < original.length+1; i++) {
            if (i >= original.length || original[i] == null)
                newArray[i] = newElement;
            else {
                newArray[i] = original[i];
            }

        }
        return newArray;
    }
}