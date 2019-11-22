package seng3150.group1.entities;

import java.util.List;

//data class FlightListingBean(val flight: Flights, availabilities: Iterable<Availability>)

public class FlightListingBean {
    private Flights flight;
    private List<AvailabilityPriceBean> availabilities;


    public FlightListingBean(Flights flight, List<AvailabilityPriceBean> availabilities) {
        this.flight = flight;
        this.availabilities = availabilities;
    }

    public Flights getFlight() {
        return flight;
    }

    public void setFlight(Flights flight) {
        this.flight = flight;
    }

    public List<AvailabilityPriceBean> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<AvailabilityPriceBean> availabilities) {
        this.availabilities = availabilities;
    }
}
