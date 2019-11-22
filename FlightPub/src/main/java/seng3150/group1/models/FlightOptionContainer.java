// Class used to hold a series of flight legs from a start destination to an arrival destination. Contains methods for
// accessing information about the series of flights as a whole, as well as a reference to the list of flight legs.

package seng3150.group1.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class FlightOptionContainer implements Comparable<FlightOptionContainer>, Serializable {

    private List<FlightLegContainer> legs;
    private double score = 0;
    private int index;//used to identify the index of this flightOption in the session list for when the user clicks "View Flight"

    public FlightOptionContainer(){}
    public FlightOptionContainer(List<FlightLegContainer> legs) {
        this.legs = legs;
    }

    @Override
    public boolean equals(Object o) {
        //parse parameter to matching type if possible
        if(getClass() != o.getClass()){
            return false;
        }
        FlightOptionContainer fo2 = (FlightOptionContainer) o;

        //don't bother comparing elements if lengths are different
        if(legs.size() != fo2.getLegs().size()){
            return false;
        }

        //compare each leg against the corresponding leg in the other FlightOptionContainer
        boolean equivalent = true;
        for (int i = 0; i < legs.size(); i++){
            if(!legs.get(i).equals(fo2.getLegs().get(i))){
                return false;
            }
        }

        //if all legs were the same then we can return true
        return true;
    }

    /**
     * This method takes in a list of flights from a search, the user's search criteria from the sliders, and then
     * gives each a score relative to all flights in the list. The score is made up of
     * - a relative rating out of 10 for cheapness
     * - a relative rating out of 10 for quickness
     * - a rating out of 5 from how many users recommend this airline.
     * Each of these is weighted based on the search criteria, then added together to form the score.
     * --NOT IMPLEMENTED YET--
     * It's then affected by multipliers which reduce the score of flights with more stopovers/further from the
     * original date searched for (the severity of which is decided by the search criteria)
     * ------------------------
     * Finally the score is converted to a 0 - 100 score (100 is the best score) to be nicely displayed,
     * and the list of search results is reordered by score.
     * @param flights - a list of FlightOptionContainers, typically populated from a database search query
     * @param criteria - all the search criteria specified for the current search.
     * @return the same list of flights, reordered by how well they satisfy the search criteria
     */
    public static LinkedList<FlightOptionContainer> rankAndSortFlights (LinkedList<FlightOptionContainer> flights, SearchCriteria criteria){

        //Find cheapest price and shortest duration amongst the list to do relative ranking.
        double cheapest = Double.MAX_VALUE;
        long shortest = Integer.MAX_VALUE;
        double comfiest = 1;
        for (FlightOptionContainer f: flights){
            System.out.println("Flight Option cost "+f.getTotalPrice());
            if (f.getTotalPrice() < cheapest){
                cheapest = f.getTotalPrice();
            }
            System.out.println("Flight Option takes "+f.getTotalDuration());
            if(f.getTotalDuration() < shortest){
                shortest = f.getTotalDuration();
            }
            System.out.println("Flight Option rating is "+f.getAverageAirlineRating());
            if(f.getAverageAirlineRating() > comfiest){
                comfiest = f.getAverageAirlineRating();
            }
        }

        //Now compare flights to eachother and give them each a relative "score"
        for (FlightOptionContainer flight: flights){
            //overall score for the flight
            double score = 0;

            //First, calculate all variables for the score formula

            //generate relative score from 0 - 10 on cheapness of flight compared to others
            //cheapest flight will get 10 score, but the score will decrease as the price gets further from the cheapest
            //to make the score get lower more drastically, increase the *2 at the end
            double priceScore = 10 * cheapest/(cheapest+(flight.getTotalPrice()-cheapest)*2);//Double.max(10*((flight.getPrice()-cheapest)/cheapest), 0);

            //generate relative score from 0 - 10 on cheapness of flight compared to others
            //shortest flight will get 10 score, but the score will decrease as the duration gets further from the shortest
            //to make the score get lower more drastically, increase the *2 at the end
            double durationScore = 10 * shortest/(shortest+(flight.getTotalDuration()-shortest)*2);//double durationScore = Double.max(10*((flight.getDuration()-shortest)/shortest), 0);

            //generate score from 0 - 5 on comfort of flight, based on % of users which recommend the airline
            //(100% = 5, 60% = 3 etc.)
            double comfortScore = 10 * comfiest/(comfiest + (comfiest - flight.getAverageAirlineRating()));
            //double comfortScore = 10 * flight.getAverageAirlineRating()/10;//convert percentage to decimal, * by 5 to get score out of 5

            //0 - 1 multiplier to devalue flights with stopovers.
            // 1 if direct flight, minus an amount for each stopover (based on sliders)
            //or if "direct flights only" was specified/ "No stopovers" was given 5/5 on sliders,
                //set to 0 straight away
            double stopOversMultiplier = 1;

            if(criteria.getNoStopOversWeight() == 1 && flight.getNumOfStopovers() != 0){
                    stopOversMultiplier = 0;
            }
            else{
                double reductionPerStopOver = 0 + 2*(criteria.getNoStopOversWeight());
                stopOversMultiplier = 1 - reductionPerStopOver * flight.getNumOfStopovers();
            }

            /*if(criteria.isDirectFlightsOnly() && flight.getNumOfStopovers() != 0){
                stopOversMultiplier = 0;
            }
            else {
                stopOversMultiplier = 1 - 0.1 * flight.getNumOfStopovers();
            }*/



            //############################ NOT IMPLEMENTED YET - WILL ALWAYS BE 1 ####################################
            //0 - 1 multiplier for how close the dates are to those the user specified
            double closeDatesMultiplier = 1;

            //refundable multiplier to make flight score 0 if it is non-refundable when the user requires it
            // 0 if criteria required refundable flights only and the flight doesn't have it, otherwise 1.
            /*double refundableMultiplier = 1;
            if(criteria.isRefundable() && flight.isRefundable()){
                refundableMultiplier = 0;
            }*/

            //We've now calculated all required variables for the score formula!
            //generate initial score based on the cost, duration and airline rating of the flight, weighted by user sliders
            priceScore *= criteria.getCostWeight();
            durationScore *= criteria.getDurationWeight();
            comfortScore *= criteria.getComfortWeight();
            score = priceScore + durationScore + comfortScore;

            //apply multipliers
            //score *= stopOversMultiplier;

            System.out.println("~~~~~~~~~~~~~~~~~\nCostWeight: "+criteria.getCostWeight());
            System.out.println("Duration score: "+durationScore*criteria.getDurationWeight());
            System.out.println("Comfort score: "+comfortScore*criteria.getComfortWeight());
            System.out.println("Total score: "+score);

            //apply additional multipliers to score based on other search criteria
            //ALL OF THESE ARE 1 AT THE MOMENT
            //score = score * closeDatesMultiplier * stopOversMultiplier * wifiMultiplier * refundableMultiplier;

            //make sure the score isn't 0 to avoid maths errors, then store the resulting score with this flight
            if(score == 0){
                flight.setScore(0.000001);
            }
            else {
                flight.setScore(score);
            }
        }

        //now that we've given each flight a score based on flight attributes and SearchCriteria,
        //reorder them based on that score.
        Collections.sort(flights);

        /*Finally, convert all scores to an integer score out of 100
         to make them more meaningful when displayed to the user*/
        //First find the highest score
        double topScore = 0;
        for (FlightOptionContainer f: flights){
            if (f.getScore() > topScore){
                topScore = f.getScore();
            }
        }

        int i = 0;
        //Change every score to a % of the top score
        for (FlightOptionContainer f: flights){
            f.setScore(round(((f.getScore()/topScore)*100), 0));
            f.setIndex(i);
            i++;
        }



        return flights;
    }

    /**Finds the difference between the departure date of the first leg, and the arrival date of the last leg
     * @return the total duration of all legs in the flight in minutes.
     */
    public long getTotalDuration(){
        //finds total time spent flying
        long totalMinutes = 0;
        for (FlightLegContainer leg:legs) {
            totalMinutes += leg.getDuration();
        }

        return totalMinutes;
    }

    /**
     * Gives a nice human-readable version of totalDuration
     * @return - a String in the format 25hr 34min displaying the time between the departure of the first leg and
     * the arrival of the last leg
     */
    public String getDurationAsString() {
        return Long.toString(getTotalDuration()/60) + "hr " + Long.toString(getTotalDuration() % 60) + "min";
    }

    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    /**
     *
     * @param dateTime date String in the format "2017-09-23 13:50:00"
     * @return a Date Object generated from the given string.
     */
    private Date StringToDate(String dateTime){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        int year = Integer.parseInt(dateTime.substring(0,4));
        int month = Integer.parseInt(dateTime.substring(5,7));
        int day = Integer.parseInt(dateTime.substring(8,10));
        int hour = Integer.parseInt(dateTime.substring(11,13));
        int minute = Integer.parseInt(dateTime.substring(14,16));
        cal.set(year, month, day, hour, minute);
        return cal.getTime();
    }

    /**
     * Rounds our score double to a given number of decimal places
     * @param value - double to round
     * @param places - no of decimal places
     * @return - rounded double
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal((value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public int compareTo(FlightOptionContainer f2) {
        if(f2.getScore() - this.getScore() < 0){
            return -1;
        }
        else if(f2.getScore() - this.getScore() == 0){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return "FlightOptionContainer{" +
                "legs=" + legs.toString() +
                '}';
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for (FlightLegContainer leg : legs){
            totalPrice += leg.getPrice();
        }
        return totalPrice;
    }

    public String getTotalPriceAsString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(getTotalPrice());
    }

    public String getDepartureDate(){
        return legs.get(0).getDepartureDate();
    }

    public String getArrivalDate(){
        return legs.get(legs.size()-1).getArrivalDate();
    }

    public String getFlyingFrom(){
        return legs.get(0).getFlyingFrom();
    }

    public String getFlyingTo(){
        return legs.get(legs.size() -1).getFlyingTo();
    }

    public List<FlightLegContainer> getLegs(){
        return legs;
    }

    public void setLegs(List<FlightLegContainer> legs) {
        this.legs = legs;
    }

    public double getAverageAirlineRating(){
        double totalRating = 0;
        int legsWithNoReviews = 0;
        for (FlightLegContainer leg : legs){
            if (leg.getAirlineRating() ==0) {
                legsWithNoReviews ++; //keep a track of how many legs don't have reviews
            }
            totalRating += leg.getAirlineRating(); //sum each leg's review score
        }
        if (totalRating == 0) {  //if no reviews have been entered yet for all legs.
            return 0; //to avoid divide by zero exceptions
        }
        else {
            return round(totalRating/(legs.size()-legsWithNoReviews),1); //average the sum of all reviews (do not include the legs that do not have reviews.
        }
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getNumOfStopovers(){
        return legs.size()-1;
    }

}
