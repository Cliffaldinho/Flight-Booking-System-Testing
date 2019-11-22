package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "Flights")
public class Flights {
    @EmbeddedId
    private FlightsId flightsId;

    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "AirlineCode", insertable=false, updatable=false)
    private Airlines airline;

    @ManyToOne
    @JoinColumn(name = "DepartureCode")
    private Destinations departureDestination;

    @ManyToOne
    @JoinColumn(name = "DestinationCode")
    private Destinations destination;

    @ManyToOne
    @JoinColumn(name = "StopOverCode")
    private Destinations StopOverDestination;

    @Basic(optional = false)
    @Column(name = "DepartureTime", insertable=false, updatable=false)
    private String departureTime;


    @Column(name = "ArrivalTimeStopOver")
    private String arrivalTimeStopOver;


    @Column(name = "DepartureTimeStopOver")
    private String departureTimeStopOver;

    @Basic(optional = false)
    @Column(name = "ArrivalTime")
    private String arrivalTime;

    @ManyToOne
    @JoinColumn(name = "PlaneCode")
    private PlaneType planeType;

    @Basic(optional = false)
    @Column(name = "Duration")
    private Integer duration;

    @Column(name = "DurationSecondLeg")
    private Integer durationSecondLeg;

    public FlightsId getFlightsId() {
        return flightsId;
    }

    public void setFlightsId(FlightsId flightsId) {
        this.flightsId = flightsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airlines getAirline() {
        return airline;
    }

    public void setAirline(Airlines airline) {
        this.airline = airline;
    }

    public Destinations getDepartureDestination() {
        return departureDestination;
    }

    public void setDepartureDestination(Destinations departureDestination) {
        this.departureDestination = departureDestination;
    }

    public Destinations getDestination() {
        return destination;
    }

    public void setStopOverDestination(Destinations departureDestination) {
        this.StopOverDestination = StopOverDestination;
    }

    public Destinations getStopOverDestination() {
        return StopOverDestination;
    }

    public void setDestination(Destinations destination) {
        this.destination = destination;
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

    public PlaneType getPlaneType() {
        return planeType;
    }

    public void setPlaneType(PlaneType planeType) {
        this.planeType = planeType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Integer getDurationSecondLeg() {
        return durationSecondLeg;
    }

    public void setDurationSecondLeg(int durationSecondLeg) {
        this.durationSecondLeg = durationSecondLeg;
    }
}
