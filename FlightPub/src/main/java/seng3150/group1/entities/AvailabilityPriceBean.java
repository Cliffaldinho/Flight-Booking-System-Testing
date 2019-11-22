package seng3150.group1.entities;

import java.util.List;

public class AvailabilityPriceBean {
    private Availability availability;
    private List<Price> prices;

    public AvailabilityPriceBean(Availability availability, List<Price> prices) {
        this.availability = availability;
        this.prices = prices;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
