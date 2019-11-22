package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "Price")
@NamedQuery(
        name = "GetPricesAvailability",
        query = "SELECT p FROM Price p WHERE (:depTime BETWEEN p.priceId.startDate AND p.endDate) AND (p.priceId.flightNumber = :fn) " +
                "AND (p.priceId.ticketType.ticketCode = :tc) AND (p.priceId.ticketClass.classCode = :cc)"
)
public class Price {
    @EmbeddedId
    private PriceId priceId;

    @Basic(optional = false)
    @Column(name = "EndDate")
    private String endDate;

    @Basic(optional = false)
    @Column(name = "Price")
    private double price;

    @Column(name = "PriceLeg1")
    private double priceLeg1;

    @Column(name = "PriceLeg2")
    private double priceLeg2;

    public PriceId getPriceId() {
        return priceId;
    }

    public void setPriceId(PriceId priceId) {
        this.priceId = priceId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
}
