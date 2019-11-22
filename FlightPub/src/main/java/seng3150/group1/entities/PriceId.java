package seng3150.group1.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PriceId implements Serializable{
    @ManyToOne
    @JoinColumn(name = "AirlineCode")
    private Airlines airline;

    @Basic(optional = false)
    @Column(name = "FlightNumber")
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "ClassCode")
    private TicketClass ticketClass;

    @ManyToOne
    @JoinColumn(name = "TicketCode")
    private TicketType ticketType;

    @Basic(optional = false)
    @Column(name = "StartDate")
    private String startDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceId priceId = (PriceId) o;
        return Objects.equals(airline, priceId.airline) &&
                Objects.equals(flightNumber, priceId.flightNumber) &&
                Objects.equals(ticketClass, priceId.ticketClass) &&
                Objects.equals(ticketType, priceId.ticketType) &&
                Objects.equals(startDate, priceId.startDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(airline, flightNumber, ticketClass, ticketType, startDate);
    }
}
