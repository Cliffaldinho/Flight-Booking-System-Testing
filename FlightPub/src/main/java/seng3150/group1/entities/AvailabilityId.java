package seng3150.group1.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AvailabilityId implements Serializable{
    @ManyToOne
    @JoinColumn(name = "AirlineCode")
    private Airlines airline;

    @Column(name = "FlightNumber")
    private String flightNumber;

    @Column(name = "DepartureTime")
    private String dateTime;

    @ManyToOne
    @JoinColumn(name = "ClassCode")
    private TicketClass ticketClass;

    @ManyToOne
    @JoinColumn(name = "TicketCode")
    private TicketType ticketType;

    public Airlines getAirline() {
        return airline;
    }

    public void setAirline(Airlines airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getClassCode() {return ticketClass.getClassCode();}

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public TicketClass getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilityId that = (AvailabilityId) o;
        return Objects.equals(airline, that.airline) &&
                Objects.equals(flightNumber, that.flightNumber) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(ticketClass, that.ticketClass) &&
                Objects.equals(ticketType, that.ticketType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(airline, flightNumber, dateTime, ticketClass, ticketType);
    }
}
