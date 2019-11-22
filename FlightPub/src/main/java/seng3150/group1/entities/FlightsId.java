package seng3150.group1.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FlightsId implements Serializable{
    @ManyToOne
    @JoinColumn(name = "AirlineCode")
    private Airlines airline;

    @Basic(optional = false)
    @Column(name = "FlightNumber")
    private String flightNumber;

    @Basic(optional = false)
    @Column(name = "DepartureTime")
    private String departureTime;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightsId flightsId = (FlightsId) o;
        return Objects.equals(airline, flightsId.airline) &&
                Objects.equals(flightNumber, flightsId.flightNumber) &&
                Objects.equals(departureTime, flightsId.departureTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(airline, flightNumber, departureTime);
    }
}