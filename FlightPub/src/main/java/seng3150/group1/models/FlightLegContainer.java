// Class used to represent a single flight between two airports.

package seng3150.group1.models;
import org.jgrapht.graph.DefaultEdge;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FlightLegContainer extends DefaultEdge implements  Serializable  {

    //Data needed for ranking flights
    private double price;
    private int duration; //duration of flight in minutes
    /** A % rating of how many users would recommend this flight's airline */
    private double airlineRating;
    private String className;

    private boolean refundable = true;

    //Data that's only there to be displayed on the results page
    private String flightNo = "";
    private String flyingFrom ="";
    private String flyingTo ="";
    private String airline = "";
    private String aircraft = "";
    private String departureDate;
    private String arrivalDate;
    private String airlineCode;
    private String classCode;//can be ECO, PME, BUS, FIR
    private int flightInformationId;
    private int legNo; //1 for first leg, 2 for second leg, 0 for an entire flight

    //only used when this leg is being displayed as part of user flight history/being reviewed
    private boolean reviewed;
    private int bookingId;

    @Override
    public boolean equals(Object o) {
        FlightLegContainer other = (FlightLegContainer) o;
        return flightInformationId == other.getFlightInformationId() &&
                legNo == other.getLegNo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, duration, airlineRating, refundable, flightNo, flyingFrom, flyingTo, airline, aircraft, departureDate, arrivalDate, airlineCode, classCode);
    }

    public FlightLegContainer(){}

    public FlightLegContainer(double price, int duration, double airlineRating, boolean refundable, String flightNo, String flyingFrom, String flyingTo, String airline, String aircraft, String departureDate, String arrivalDate, String classCode, String airlineCode) {
        this.price = price;
        this.duration = duration;
        this.airlineRating = airlineRating;
        this.refundable = refundable;
        this.flightNo = flightNo;
        this.flyingFrom = flyingFrom;
        this.flyingTo = flyingTo;
        this.airline = airline;
        this.aircraft = aircraft;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.classCode = classCode;
        this.airlineCode = airlineCode;

        this.reviewed = false;
    }

    public String getDurationAsString() {
        return Long.toString(getDuration()/60) + "hr " + Long.toString(getDuration() % 60) + "min";
    }

    @Override
    public String toString() {
        return "( Depart: " +  flyingFrom + " at "+arrivalDate + ", Destination: " + flyingTo + " at "+departureDate +")";
    }

    /**Finds the difference between the departure date of the first leg, and the arrival date of the last leg
     * @return the total duration of all legs in the flight in minutes.
     */
    public long getDuration(){
        return duration;
        /*
        Date departureDate = StringToDate(getDepartureDate());
        Date arrivalDate = StringToDate(getArrivalDate());
        return getDateDiff(departureDate, arrivalDate, TimeUnit.MINUTES);*/
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

    public double getPrice() {
        return price;
    }

    public String getPriceAsString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getAirlineRating() {
        return airlineRating;
    }

    public void setAirlineRating(double airlineRating) {
        this.airlineRating = airlineRating;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getFlyingFrom() {
        return flyingFrom;
    }

    public void setFlyingFrom(String flyingFrom) {
        this.flyingFrom = flyingFrom;
    }

    public String getFlyingTo() {
        return flyingTo;
    }

    public void setFlyingTo(String flyingTo) {
        this.flyingTo = flyingTo;
    }

    public String getClassCode() {return classCode;}

    public void setClassCode(String classCode) {
        this.classCode = classCode;

        switch (classCode) {
            case "ECO":
                className = "Economy";
                break;
            case "PME":
                className = "Premium Economy";
                break;
            case "BUS":
                className = "Business";
                break;
            case "FIR":
                className = "First Class";
                break;
        }

    }

    public String getAirlineCode() {return airlineCode;}

    public void setAirlineCode(String airlineCode) {this.airlineCode = airlineCode;}

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    public int getLegNo() {
        return legNo;
    }

    public void setLegNo(int legNo) {
        this.legNo = legNo;
    }

    public int getFlightInformationId() {
        return flightInformationId;
    }

    public void setFlightInformationId(int flightInformationId) {
        this.flightInformationId = flightInformationId;
    }

    public String getClassName() {
        return className;
    }


    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}