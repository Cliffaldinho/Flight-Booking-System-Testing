package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "Availability")
@NamedQuery(
        name = "GetFlightAvailabilities",
        query = "SELECT a FROM Availability a WHERE a.flightNumber = :fn AND a.dateTime = :dt AND a.airline.airlineCode = :ac"
)
public class Availability {
    @EmbeddedId
    private AvailabilityId pk;

    @Basic(optional = false)
    @Column(name = "NumberAvailableSeatsLeg1")
    private int numberAvailableSeatsLeg1;

    @Column(name = "FlightNumber", insertable = false, updatable = false)
    private String flightNumber;

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getClassCode() {return pk.getClassCode();}

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Column(name = "DepartureTime", insertable = false, updatable = false)
    private String dateTime;

    @ManyToOne
    @JoinColumn(name = "AirlineCode", insertable = false, updatable = false)
    private Airlines airline;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Airlines getAirline() {
        return airline;
    }

    public void setAirline(Airlines airline) {
        this.airline = airline;
    }

    @Column(name = "NumberAvailableSeatsLeg2")
    private int numberAvailableSeatsLeg2 ;

    @Column(name = "AvailabilityId")
    private int availabilityId ;

    public int getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(int availabilityId) {
        this.availabilityId = availabilityId;
    }

    public AvailabilityId getPk() {
        return pk;
    }

    public void setPk(AvailabilityId pk) {
        this.pk = pk;
    }

    public int getNumberAvailableSeatsLeg1() {
        return numberAvailableSeatsLeg1;
    }

    public void setNumberAvailableSeatsLeg1(int numberAvailableSeatsLeg1) {
        this.numberAvailableSeatsLeg1 = numberAvailableSeatsLeg1;
    }

    public int getNumberAvailableSeatsLeg2() {
        return numberAvailableSeatsLeg2;
    }

    public void setNumberAvailableSeatsLeg2(int numberAvailableSeatsLeg2) {
        this.numberAvailableSeatsLeg2 = numberAvailableSeatsLeg2;
    }
}
