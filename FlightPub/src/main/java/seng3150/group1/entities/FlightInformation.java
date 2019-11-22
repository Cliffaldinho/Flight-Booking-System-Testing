package seng3150.group1.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FlightInformation")
public class FlightInformation {
    //flightInformationId
    @Id
    @Column(name = "flightInformationId")
    private int flightInformationId;

    @ManyToOne
    @JoinColumn(name = "AirlineCode", insertable=false, updatable=false)
    private Airlines airline;

    //FlightNumber
    @Column(name = "FlightNumber")
    private String flightNumber;

    //DepartureTime
    @Column(name = "DepartureTime")
    private String departureTime;

    //ArrivalTimeStopOver
    @Column(name = "ArrivalTimeStopOver")
    private String arrivalTimeStopOver;

    //DepartureTimeStopOver
    @Column(name = "DepartureTimeStopOver")
    private String departureTimeStopOver;

    //ArrivalTime
    @Column(name = "ArrivalTime")
    private String arrivalTime;

    //PlaneCode
    @Column(name = "PlaneCode")
    private String planeCode;

    //Duration
    @Column(name = "Duration")
    private int duration;

    //DurationSecondLeg
    @Column(name = "DurationSecondLeg")
    private Integer durationSecondLeg;

    //Price
    @Column(name = "Price")
    private double price;

    //PriceLeg1
    @Column(name = "PriceLeg1")
    private double priceLeg1;

    //PriceLeg2
    @Column(name = "PriceLeg2")
    private double priceLeg2;

    //ClassCode
    @Column(name = "ClassCode")
    private String classCode;

    @ManyToOne
    @JoinColumn(name = "Departure", insertable=false, updatable=false)
    private Destinations departure;

    @ManyToOne
    @JoinColumn(name = "Stopover", insertable=false, updatable=false)
    private Destinations stopover;

    @ManyToOne
    @JoinColumn(name = "Arrival", insertable=false, updatable=false)
    private Destinations arrival;

    /** A specific flight can be booked many times */
    @OneToMany(mappedBy = "flightInformation")
    private List<Booking> bookings;

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getFlightInformationId() {
        return flightInformationId;
    }

    public void setFlightInformationId(int flightInformationId) {
        this.flightInformationId = flightInformationId;
    }

    public Airlines getAirline() {
        return airline;
    }

    public void setAirline(Airlines airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTimeStopOver() {
        return arrivalTimeStopOver;
    }

    public void setArrivalTimeStopOver(String arrivalTimeStopOver) {
        this.arrivalTimeStopOver = arrivalTimeStopOver;
    }

    public String getDepartureTimeStopOver() {
        return departureTimeStopOver;
    }

    public void setDepartureTimeStopOver(String departureTimeStopOver) {
        this.departureTimeStopOver = departureTimeStopOver;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getPlaneCode() {
        return planeCode;
    }

    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Integer getDurationSecondLeg() {
        return durationSecondLeg;
    }

    public void setDurationSecondLeg(Integer durationSecondLeg) {
        this.durationSecondLeg = durationSecondLeg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceLeg1() {
        return priceLeg1;
    }

    public void setPriceLeg1(double priceLeg1) {
        this.priceLeg1 = priceLeg1;
    }

    public double getPriceLeg2() {
        return priceLeg2;
    }

    public void setPriceLeg2(double priceLeg2) {
        this.priceLeg2 = priceLeg2;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Destinations getDeparture() {
        return departure;
    }

    public void setDeparture(Destinations departure) {
        this.departure = departure;
    }

    public Destinations getStopover() {
        return stopover;
    }

    public void setStopover(Destinations stopover) {
        this.stopover = stopover;
    }

    public Destinations getArrival() {
        return arrival;
    }

    public void setArrival(Destinations arrival) {
        this.arrival = arrival;
    }


}
